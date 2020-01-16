package club.banyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AboutController {
    @GetMapping("/about")
    String getabout() {
        return "about";
    }
   /* @GetMapping("/about/{username}")
    String getAbout(@PathVariable String username){
        return String.format("user %s",username);

    }*/
}
