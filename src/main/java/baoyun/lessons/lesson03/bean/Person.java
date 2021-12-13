package baoyun.lessons.lesson03.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    /**
     * 使用@Value赋值
     *  1.基本数值
     *  2.SpEL: #{}   Spring Expression Language  可以写表达式， 例如 #{20-2}，#{numberArray[0]}, #{person.getAge()}
     *  3. ${} 得到配置中的值
     */
    @Value("Zoe")
    private String name;

    @Value("#{20-1}")
    private Integer age;

    @Value("${nickName}")
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Person(){

    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
