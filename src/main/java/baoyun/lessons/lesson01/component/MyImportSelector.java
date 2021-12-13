package baoyun.lessons.lesson01.component;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    // 返回值就是要导入到容器中的组件的全类名
    // AnnotationMetadata ： 当前标注@import的类的所有注解的信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"baoyun.beanClass.color.Red"};
    }
}
