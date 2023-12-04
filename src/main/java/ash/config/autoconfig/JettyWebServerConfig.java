package ash.config.autoconfig;

import ash.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory") // Bean 이름은 메소드 명이 기본 값이므로, 메소드 명이 같을 경우 충돌 -> 충돌 우려 시, 이름 값 따로 부여
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
