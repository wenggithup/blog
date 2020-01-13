package club.banyuan.Example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class User {
    @GetMapping("/user/{username}")
    @ResponseBody
    String getPath(@PathVariable String username,
                   @RequestParam(defaultValue = "10") Integer page,
                   @RequestParam(defaultValue = "1") Integer size){
        return "username:"+ username+"page:"+page+"size"+size;
    }
}
