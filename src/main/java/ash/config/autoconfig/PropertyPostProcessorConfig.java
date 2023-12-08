package ash.config.autoconfig;

import ash.config.MyAutoConfiguration;
import ash.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.util.Map;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() { // Bean obj의 초기화가 끝난 다음에, 이 프로세스를 실행해달라
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if(annotation == null) return bean;
                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String) attrs.get("prefix");

                return Binder.get(env).bindOrCreate(prefix, bean.getClass()); // MyConfigurationProperties 어노테이션이 붙어있는 경우, 프로퍼티 값을 바인딩 한다.
            }
        };
    }
}
