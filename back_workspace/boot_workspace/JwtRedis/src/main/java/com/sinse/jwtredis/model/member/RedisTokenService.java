package com.sinse.jwtredis.model.member;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService {
    private final StringRedisTemplate redis;

    public RedisTokenService(StringRedisTemplate stringRedisTemplate) {
        this.redis = stringRedisTemplate;
    }

    //사용자 버전 조회(버전이 없으면 0을 반환)
    /* 사용자별로 관리하는 버전 번호를 조회하는 용도의 메서드 정의
    *  참고) [ 버전을 사용하는 이유? ]
    *    -> JWT+Redis 환경에서 로그아웃 처리 시 블랙리스트 만으로는 충분하지 않을 때가 있음
    *       예를 들어, 회원이 로그아웃 하거나 비밀번호를 바꿨을 때, 기존에 발급된 Access/Refresh 토큰은 모두 무효화
    *       이때, 토큰 페이로드에 Ver:1과 같은 사용자 버전을 넣어두고 Redis에서 "uv:userId 2"로 저장해놓으면,
    *       토큰의 페이로드에 갖고 있는 버전1은 Redis보다 낮으므로 낮은 버전을 가진 토큰은 전부 무효화 처리 가능
    *
    *       [ 블랙리스트와 차이점 ]
    *       블랙리스트 방식 - 토큰 단위로 차단하는 방식, 만료될 때까지 redis에 개별 키 보관
    *       버전 방식 - 사용자 단위로 이 사용자가 사용중인 모든 디바이스까지 한꺼번에 토큰을 무효화
    * */

    public int currentUserVersion(String userId){
        String v = redis.opsForValue().get("uv:"+userId);
        //위 메서드에 의해 redis 내부에서는 GET uv:<userId> 가 수행됨
        //해당 키가 존재할 경우 저장된 문자열을 그대로 반환
        //StringRedisTemplate 객체는 redis가 반환한 nil을 java의 null로 반환
        return (v==null)?0:Integer.parseInt(v);
    }
}
