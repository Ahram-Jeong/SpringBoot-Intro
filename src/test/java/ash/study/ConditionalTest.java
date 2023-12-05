package ash.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {
    @Test
    void conditional() {
        // true
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Config1.class);
        ac.refresh();

        MyBean bean = ac.getBean(MyBean.class);

        // false
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
        ac2.register(Config2.class);
        ac2.refresh();

        MyBean bean2 = ac2.getBean(MyBean.class);
    }

    @Configuration
    @Conditional(TrueCondition.class)
    static class Config1 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Configuration
    @Conditional(FalseCondition.class)
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
