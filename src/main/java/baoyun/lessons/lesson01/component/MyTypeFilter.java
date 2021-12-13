package baoyun.lessons.lesson01.component;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    @Override
    //MetadataReader: 读取当前类的信息
    //metadataReaderFactory： 读取所有类的信息
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取当前类的资源（类的路径之类的）
        metadataReader.getResource();

        ClassMetadata classData = metadataReader.getClassMetadata();

        String className = classData.getClassName();
        System.out.println("--->"+className);
        if(className.equals("baoyun.beanClass.animals.Dog") ){

            return true;
        }
        return false;
    }
}
