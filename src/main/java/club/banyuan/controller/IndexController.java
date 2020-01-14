package club.banyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/index/{username:[0-9 a-z]}/blog/{blogusername}")
    String getIndex(@PathVariable String usrname, String blogusername ){
        return usrname;


    }
}
