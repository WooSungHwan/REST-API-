package co.worker.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = {"/", "/hello"})
    public String hello(){
        return "hello";
    }

    @GetMapping("/my")
    public String my(){
        return "my";
    }

}
