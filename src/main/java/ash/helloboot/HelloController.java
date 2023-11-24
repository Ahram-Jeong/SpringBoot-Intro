package ash.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;


@RequestMapping("/hello")
public class HelloController {
   private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @ResponseBody
    @GetMapping
//    @RequestMapping(value = "/hello", method = GET)
    public String hello(String name)  {
        return helloService.sayHello(Objects.requireNonNull(name)); // null 방지
    }
}
