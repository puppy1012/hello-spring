package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

class MemberRepositoryImplTest {

    MemberRepositoryImpl memberRepository = new MemberRepositoryImpl();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("springTest");
        memberRepository.save(member);

        Member result= memberRepository.findByName(member.getName()).get();
        Assertions.assertThat(result.getName()).isEqualTo(member.getName());

    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("springTest1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("springTest2");
        memberRepository.save(member2);

//        Optional<Member> result=memberRepository.findByName("springTest1");
        // "springTest1"이라는 이름으로 저장된 회원을 조회
        Member result1 = memberRepository.findByName("springTest1").get();

        // 조회된 회원의 이름이 예상한 값("springTest1")과 일치하는지 검증
        Assertions.assertThat(result1.getName()).isEqualTo("springTest1");

        // member2 객체에 저장된 이름으로 다시 회원을 조회
        Member result2 = memberRepository.findByName(member2.getName()).get();

        Assertions.assertThat(result1.getName()).isEqualTo(member1.getName());

    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("springTest1");
        memberRepository.save(member1);
        Member member2 = new Member();
        member2.setName("springTest2");
        memberRepository.save(member2);

        List<Member> members= memberRepository.findAll();

        Assertions.assertThat(members.size()).isEqualTo(2);
    }
}
