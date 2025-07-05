package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {

    private final MemberService memberService;

    // 생성자 주입 방식으로 의존성 주입 (가장 권장되는 방식)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입 폼을 보여주는 GET 요청 처리
     * - 요청 URL: /members/new
     * - 반환값: 회원가입 HTML 폼 뷰 이름
     */
    @GetMapping("/members/new")
    public String createForm() {
        // templates/members/createMemberForm.html 렌더링
        return "members/createMemberForm";
    }

    /**
     * 회원 가입 폼 제출 처리 (POST 요청)
     * - 요청 URL: /members/new
     * - 요청 데이터: name 값이 들어 있는 MemberForm 객체
     * - 처리 내용:
     *    1. MemberForm → Member로 변환
     *    2. 서비스 계층을 통해 회원 가입 처리
     *    3. 완료 후 홈 화면으로 리다이렉트
     */
    @PostMapping("members/new")
    public String create(MemberForm form) {
        // 사용자 입력 데이터를 담은 Member 객체 생성
        Member member = new Member();
        member.setName(form.getName());

        // 회원 등록 비즈니스 로직 실행
        memberService.join(member);

        // 홈 화면으로 리다이렉트 (PRG 패턴 적용)
        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
    /*
        DI(Dependency Injection)에는 3가지 방법이 존재한다

        field injection

            @Autowired private MemberService memberService;
            이 방법같은경우 스프링이 뜰때 주입이 진행되고 외부에서 주입하기 어려움

        setter injection

            private MemberService memberService;

            @Autowired
            public MemberController(MemberService memberService) {
                this.memberService = memberService;
            }
            멤버 컨트롤을 호출할때 public으로 열려있어야 작동이 가능
            → setter 메서드에 접근할 수 있어야 스프링이 주입함
            바꿀일이 없지만 외부노출이되며, 중간에 값이 바뀌면 문제 발생

         Constructor injection

                private final MemberService memberService;

                @Autowired
                public MemberController(MemberService memberService) {
                    this.memberService = memberService;
                }
                실행중에 동적으로 바뀌는 일은 절대없다(서버런타임중 값 변경)
                → final로 선언하면 바꿀 수 없다는 의미. 불변성을 갖기 위해 final 사용
     */


