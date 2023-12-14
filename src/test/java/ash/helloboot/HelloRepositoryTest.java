package ash.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("조승연")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("WOODZ")).isEqualTo(0);

        helloRepository.increaseCount("WOODZ");
        assertThat(helloRepository.countOf("WOODZ")).isEqualTo(1);

        helloRepository.increaseCount("WOODZ");
        assertThat(helloRepository.countOf("WOODZ")).isEqualTo(2);
    }
}
