package club.banyuan;

import club.banyuan.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class TestController {

    /*String getBlogById(@RequestParam(name="number",defaultValue = "10",required = false) Integer id){
        return id.toString();
    }*/
    @ResponseBody
    @GetMapping("/person")
    Person getPerson(Person person){
        return person;
    }
}
