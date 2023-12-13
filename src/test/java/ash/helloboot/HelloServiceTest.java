package ash.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);
        String ret = helloService.sayHello("조승연");

        Assertions.assertThat(ret).isEqualTo("Hello 조승연");
    }

    private static HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("Woodz");

        Assertions.assertThat(ret).isEqualTo("*Woodz*");
    }
}
