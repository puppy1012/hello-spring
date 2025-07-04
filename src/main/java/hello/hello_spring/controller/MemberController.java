package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    /*
        DI(Dependency Injection)에는 3가지 방법이 존재한다

        field injection

            @Autowired private MemberService memberService;
            이 방법같은경우 스프링이 뜰때 주입이 진행되고 그 이후 접근방법이 전무하다

        setter injection

            private MemberService memberService;

            @Autowired
            public MemberController(MemberService memberService) {
                this.memberService = memberService;
            }
            멤버 컨트롤을 호출할때 public으로 열려있어야 작동이 가능
            바꿀일이 없지만 외부노출이되며, 중간에 값이 바뀌면 문제 발생

         Constructor injection

                private final MemberService memberService;

                @Autowired
                public MemberController(MemberService memberService) {
                    this.memberService = memberService;
                }
                실행중에 동적으로 바뀌는 일은 절대없다(서버런타임중 값 변경)
     */

}
