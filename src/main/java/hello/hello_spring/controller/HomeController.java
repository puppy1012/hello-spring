package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    /*
        요청이 들어오면 스프링 컨트롤러에 요청이 있는지 탐색, 없으면 스태틱 파일을 탐색
     */
}
