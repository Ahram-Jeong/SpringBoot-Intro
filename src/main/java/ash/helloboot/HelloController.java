package ash.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class HelloController {
   private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
//    @RequestMapping(value = "/hello", method = GET)
    public String hello(String name)  {
        return helloService.sayHello(Objects.requireNonNull(name)); // null 방지
    }
}
