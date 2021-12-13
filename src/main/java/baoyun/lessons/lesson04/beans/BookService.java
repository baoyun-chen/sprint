package baoyun.lessons.lesson04.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    @Qualifier("bookDao1")  // 指定注入名字为bookDao1 的bean
    private BookDao bookDao;

    /**
     *  @Autowired
     *  private BookDao bookDao1;
     *
     *  若有两个类型为BookDao 的bean，按照属性名字决定用哪一个，叫bookDao1 就注入名字为bookDao1的bean
     */


    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
