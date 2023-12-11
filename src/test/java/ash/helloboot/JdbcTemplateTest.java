package ash.helloboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@HellobootTest
public class JdbcTemplateTest {
    @Autowired JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name VARCHAR(50) PRIMARY KEY, count INT)");
    }

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", "WOODZ", 143);
        jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", "황민현", 1);

        Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM hello", Long.class);
        assertThat(count).isEqualTo(2);
    }
}
