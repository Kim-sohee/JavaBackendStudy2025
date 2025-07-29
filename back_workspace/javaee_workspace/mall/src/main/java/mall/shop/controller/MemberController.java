package mall.shop.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.exception.MemberException;
import mall.exception.MemberNotFoundException;
import mall.exception.PasswordEncryptException;
import mall.model.member.MemberService;
import mall.model.member.SnsProviderService;

@Slf4j
@Controller
public class MemberController {

    private final LocalSessionFactoryBean sessionFactory;
	@Autowired
	private OAuth20Service googleAuthService;
	
	@Autowired
	private OAuth20Service naverAuthService;
	
	@Autowired
	private OAuth20Service kakaoAuthService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SnsProviderService snsProviderService;

    MemberController(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//로그인 폼 요청 처리
	@GetMapping("/member/loginform")
	public String getForm() {
		return "shop/member/login";
	}
	
	//로그아웃 요청 처리
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		//세션을 제거할 수는 없으며, 단 세션을 무효화 시켜야 한다.
		session.invalidate();	//현재 사용중인 세션 무효화.. 따라서 이 시점 부터 기존 세션을 참조할 수 없다.
		return "redirect:/shop/main";
	}
	
	//회원가입폼 요청 처리
	@GetMapping("/member/registform")
	public String getRegistForm() {
		return "shop/member/join";
	}
	
	//회원가입 요청 처리
	@PostMapping("/member/regist")
	public String regist(Member member) {
		log.debug("ID "+ member.getId());
		log.debug("Password "+member.getPassword());
		log.debug("name "+member.getName());
		log.debug("email "+member.getEmail());
		
		memberService.regist(member);
		return "redirect:/shop/member/loginform";
	}
	
	//홈페이지 로그인 요청처리
	@PostMapping("/member/login")
	public String homeLogin(Member member, HttpSession session) {
		Member obj = memberService.login(member);
		session.setAttribute("member", obj);
		return "redirect:/shop/main";
	}
	
	
	/*-------------------------------------------------------------------
	 * 구글 로그인 처리
	 * ------------------------------------------------------------------*/
	//인증 동의화면 요청 처리
	@GetMapping("/member/google/authurl")
	@ResponseBody
	public String getGoogleAuthUrl() {
		return googleAuthService.getAuthorizationUrl();
	}
	//구글에 등록해 놓은 콜백 주소로 전송되는 콜백 요청 처리
	@GetMapping("/callback/sns/google")
	public String googleCallback(@RequestParam("code") String code, HttpSession session) throws ExecutionException, IOException, InterruptedException{

		//OAutho20Service 빈 등록 시 이미 Client ID, Client Secret와 등록해 놓았으므로, 토큰 요청 시 Authorization Code만 추가하면 된다.
		OAuth2AccessToken acessToken = googleAuthService.getAccessToken(code);
		
		log.debug("구글이 나에게 보내준 권한 코드는 "+ code);
		log.debug("인증 결과인 token은 "+acessToken);
		
		//토큰을 받았다면 언제든 Resource Owner의 개인정보를 접근할 수 있다.
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo");
		googleAuthService.signRequest(acessToken, request);		//요청 시 사용할 토큰 지정
		Response response = googleAuthService.execute(request);		//사용자 정보 요청 시도!
		
		//json 파싱
		JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
		
		//필요한 개인 정보를 key 값으로 조회하여 가져오기
		String email = json.get("email").getAsString();
		log.debug("사용자의 이메일은 "+email);
		
		String name = json.get("name").getAsString();
		log.debug("사용자명은" + name);
		
		String openid = json.get("id").getAsString();	//SNS 프로바이더가 회원을 구분하는 내부 식별 아이디
		log.debug("sns id는 "+ openid);
		
		//회원가입 및 등록: 토큰을 통해 얻은 회원 정보가 쇼핑몰에 등록되어 있는지 체크
		//1)없으면 가입 후 로그인
		Member member = null;
		member = memberService.selectById(openid);
		if(member == null) {
			//계정이 없다면 회원가입 및 로그인 처리
			//회원 등록
			member = new Member();
			member.setSnsProvider(snsProviderService.selectByName("google"));
			member.setId(openid);
			member.setEmail(email);
			member.setName(name);
			
			memberService.regist(member);
		}
		//2)있으면 그냥 로그인
		session.setAttribute("member", member);		//세션이 살아있는 한, Member를 사용할 수 있다.
		
		return "redirect:/shop/product/list";
	}
	
	/*-------------------------------------------------------------------
	 * 네이버 로그인 처리
	 * ------------------------------------------------------------------*/
	@GetMapping("member/naver/authurl")
	@ResponseBody
	public String getNaverAuthUrl() {
		return naverAuthService.getAuthorizationUrl();
	}
	
	//naver에 등록해 놓은 콜백 주소로 전송되는 콜백 요청 처리
	@GetMapping("/callback/sns/naver")
	public String naverCallback(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws IOException, InterruptedException, ExecutionException {
		//IDP가 전송한 code와 client Id, client Secret를 조합하여, 토큰을 요청하자!
		//clientID, client Secret은 빈 등록 시 이미 등록해놓은 걸 사용한다.
		OAuth2AccessToken accessToken = naverAuthService.getAccessToken(code);
		
		log.debug("네이버에서 발급받은 토큰은 "+accessToken);
		
		//발급받은 토큰을 이용하여 회원정보 조회
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://openapi.naver.com/v1/nid/me");
		naverAuthService.signRequest(accessToken, request);
		Response response = naverAuthService.execute(request);
		
		JsonObject responseJson = JsonParser.parseString(response.getBody()).getAsJsonObject();
		
		log.debug("responseJson = "+responseJson);
		JsonObject userJson = responseJson.getAsJsonObject("response");
		
		String id = userJson.get("id").getAsString();
		String email = userJson.get("email").getAsString();
		String name = userJson.get("name").getAsString();
		
		//회원가입 및 등록: 토큰을 통해 얻은 회원 정보가 쇼핑몰에 등록되어 있는지 체크
		//1)없으면 가입 후 로그인
		Member member = null;
		member = memberService.selectById(id);
		if(member == null) {
			//계정이 없다면 회원가입 및 로그인 처리
			//회원 등록
			member = new Member();
			member.setSnsProvider(snsProviderService.selectByName("naver"));
			member.setId(id);
			member.setEmail(email);
			member.setName(name);
			
			memberService.regist(member);
		}
		//2)있으면 그냥 로그인
		session.setAttribute("member", member);		//세션이 살아있는 한, Member를 사용할 수 있다.
		
		return "redirect:/shop/main";
	}
	
	/*-------------------------------------------------------------------
	 * 카카오 로그인 처리
	 * ------------------------------------------------------------------*/
	//동의 화면 주소 요청 처리
	@GetMapping("member/kakao/authurl")
	@ResponseBody
	public String getKakaoAuthUrl() {
		return kakaoAuthService.getAuthorizationUrl();
	}
	
	//콜백 요청 처리
	@GetMapping("/callback/sns/kakao")
	public String kakaoCallback(String code, HttpSession session) throws IOException, InterruptedException, ExecutionException {
		OAuth2AccessToken accessToken = kakaoAuthService.getAccessToken(code);
		
		log.debug("카카오에서 발급받은 토큰은 "+accessToken);
		
		//발급받은 토큰을 이용하여 회원정보 조회
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://kapi.kakao.com/v2/user/me");
		kakaoAuthService.signRequest(accessToken, request);
		Response response = kakaoAuthService.execute(request);
		
		JsonObject responseJson = JsonParser.parseString(response.getBody()).getAsJsonObject();
		
		log.debug("responseJson = "+responseJson);
		JsonObject userJson = responseJson.getAsJsonObject("response");
		
		return null;
	}
	
	//현재 컨트롤러의 메서드들 에서 발생하는 예외에 대한 처리
	@ExceptionHandler(PasswordEncryptException.class)
	public ModelAndView handle(PasswordEncryptException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		
//		mav.addObject("e", e);
		log.error("암호화에 문제가 생겼습니다.");
		e.printStackTrace();
		
		mav.addObject("msg", "회원가입이 실패하였습니다.");
		return mav;
	}
	
	@ExceptionHandler({MemberException.class, MemberNotFoundException.class})
	public ModelAndView handle(Exception e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		
//		mav.addObject("e", e);
		
		mav.addObject("msg", e.getMessage());
		return mav;
	}
	
	
}
