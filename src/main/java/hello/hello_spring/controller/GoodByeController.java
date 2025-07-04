package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodByeController {
    @GetMapping("goodbye")
    public String goodBye(Model model) {
        model.addAttribute("data", "Goodbye");
        return "goodbye";
    }
}
