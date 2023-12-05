package ash.config.autoconfig;

import ash.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

@MyAutoConfiguration
@Conditional(JettyWebServerConfig.JettyCondition.class) // @Conditional을 사용하면, Condition 인터페이스를 구현한 클래스 지정 필수
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory") // Bean 이름은 메소드 명이 기본 값이므로, 메소드 명이 같을 경우 충돌 -> 충돌 우려 시, 이름 값 따로 부여
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }

    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.eclipse.jetty.server.Server", context.getClassLoader());
        }
    }
}
