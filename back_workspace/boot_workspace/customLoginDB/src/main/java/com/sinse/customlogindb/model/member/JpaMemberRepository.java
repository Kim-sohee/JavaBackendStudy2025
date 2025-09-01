package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member,Integer> {
    //이미 수 많은 CRUD 메서드가 지원되지만.. 개발자가 원하는 메서드로 커스텀하려면
    //여기에 재정의 하면 된다..
    public Member findById(String id);
    public Member findByPassword(String password);
}
