package com.sinse.memberservice.model.member;

import com.sinse.memberservice.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProviderRepository extends JpaRepository<Provider, Integer> {
    //Provider의 이름으로 pk 값 가져오기
    public Provider findByProviderName(String providerName);
}
