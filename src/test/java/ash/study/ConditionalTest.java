package ash.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.*;

public class ConditionalTest {
    @Test
    void conditional() {
        // true
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class).run(context -> {
            assertThat(context).hasSingleBean(MyBean.class);
            assertThat(context).hasSingleBean(Config1.class);
        });

        // false
        new ApplicationContextRunner().withUserConfiguration(Config2.class).run(context -> {
            assertThat(context).doesNotHaveBean(MyBean.class);
            assertThat(context).doesNotHaveBean(Config2.class);
        });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional {}

    @Configuration
    @TrueConditional
    static class Config1 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional {}

    @Configuration
    @FalseConditional
    static class Config2 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    static class MyBean {}

    private static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    private static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}
