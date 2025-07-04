package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemberRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberRepositoryImpl memberRepository;
    MemberService memberService;

    @BeforeEach
    public void BeforeEach(){
        memberRepository = new MemberRepositoryImpl();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("LSH");
        //when
        Long result= memberService.join(member);
        //then
        Member findMember = memberService.findMemberById(result).get();
//        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("LSH");
        Member member2= new Member();
        member2.setName("LSH");

        memberService.join(member1);
//        Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
//        try{
//            memberService.join(member1);
//            fail();
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).contains("이미 존재하는 회원입니다.");
//        }



        //when

        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findMemberById() {
    }
}