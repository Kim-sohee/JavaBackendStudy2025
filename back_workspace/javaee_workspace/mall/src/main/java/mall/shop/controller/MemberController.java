package mall.shop.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.domain.SnsProvider;
import mall.model.member.MemberService;
import mall.model.member.SnsProviderService;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	private OAuth20Service googleAuthService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SnsProviderService snsProviderService;
	
	//로그인 폼 요청 처리
	@GetMapping("/member/loginform")
	public String getForm() {
		return "shop/member/login";
	}
	
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
		try {
			//계정이 없다면 회원가입 및 로그인 처리
			//회원 등록
			member = new Member();
			member.setSnsProvider(snsProviderService.selectByName("google"));
			member.setId(openid);
			member.setEmail(email);
			member.setName(name);
			
			memberService.regist(member);
		} catch (Exception e) {
			//동일한 계정이 이미 존재한다면 로그인만 처리
			member = memberService.checkDuplicate(openid);
		}
		session.setAttribute("member", member);		//세션이 살아있는 한, Member를 사용할 수 있다.
		
		//2)있으면 그냥 로그인
		
		
		return "redirect:/shop/product/list";
	}
}
