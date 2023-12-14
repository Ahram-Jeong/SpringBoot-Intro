package ash.helloboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HellobootApplication {
	private final JdbcTemplate jdbcTemplate;

    public HellobootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@PostConstruct
	void init() {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name VARCHAR(50) PRIMARY KEY, count INT)");
	}

    public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
