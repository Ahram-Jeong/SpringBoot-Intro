package ash.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController() {
        HelloController helloController = new HelloController((HelloService) name -> name); // 수동 DI
        String ret = helloController.hello("Woodz");

        Assertions.assertThat(ret).isEqualTo("Woodz");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController((HelloService) name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
