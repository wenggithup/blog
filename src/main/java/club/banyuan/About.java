package club.banyuan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class About {
    @GetMapping("/about")
    String getabout(){

        return "about";
    }
}
