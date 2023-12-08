package ash.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector { // 실제 import 할 클래스의 이름을 리턴해주는 역할
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attrs = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties.class.getName());
        Class propertyClass = (Class) attrs.getFirst("value");
        return new String[] { propertyClass.getName() };
    }
}
