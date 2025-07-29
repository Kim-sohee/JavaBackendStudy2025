package mall.model.member;

import com.github.scribejava.core.builder.api.DefaultApi20;

//scribejava api는 Google이 기본적으로 지원되지만, 그 이외의 naver, kakao는 지원하지 않기 때문에 직접 구현
public class KakaoApi20 extends DefaultApi20{
	private static final String AUTHORIZE_URL = "https://kauth.kakao.com/oauth/authorize";
	private static final String ACCESS_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	
	protected KakaoApi20() {}
	private static class InstanceHolder{
		private static final KakaoApi20 INSTANCE = new KakaoApi20();
	}
	
	public static KakaoApi20 instance() {
		return InstanceHolder.INSTANCE;
	}
	
	//네이버의 동의화면 url
	@Override
	protected String getAuthorizationBaseUrl() {
		return AUTHORIZE_URL;
	}
	
	//네이버의 요처알 토큰 요청 주소
	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL;
	}

}
