package ash.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@HellobootTest
public class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name VARCHAR(50) PRIMARY KEY, count INT)");
    }

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("WOODZ")).isNull();
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
