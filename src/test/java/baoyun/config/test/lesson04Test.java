package baoyun.config.test;

import baoyun.lessons.lesson04.MainConfigOfAutowired;
import baoyun.lessons.lesson04.beans.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class lesson04Test {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        BookService bookService = applicationContext.getBean(BookService.class);

        System.out.println(bookService);

        System.out.println(applicationContext);

    }
}
