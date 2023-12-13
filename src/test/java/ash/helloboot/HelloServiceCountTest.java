package ash.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

@HellobootTest
public class HelloServiceCountTest {
    // SimpleHelloService의 테스트
    // HelloService 타입의 Bean을 의존하고 있는 HelloCountroller (client) 입장에서 기능을 테스트 할 때, SimpleHelloService 대신에 다른 서비스 구현체를 만들고 기능을 똑같이 만들었다고 한다면 그 경우에도 원하는 로직에 대한 동일한 검증 가능
    // 그렇기 때문에 HelloService 인터페이스를 통해 받는 것이 좋음
    @Autowired HelloService helloService;
    @Autowired HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount() {
        IntStream.rangeClosed(1, 10).forEach(count -> {
            helloService.sayHello("조승연");
            Assertions.assertThat(helloRepository.countOf("조승연")).isEqualTo(count);
        });
    }
}
