/**
 * @author masai
 * @date 2021/5/19
 *
 * "::" 使用
 */

interface IConvert {
    void convert();
}

class Person{
    Person(){
        System.out.println("new a Person");
    }

    public static void eat(){
        System.out.println("person must eat");
    }
    public void love(){
        System.out.println("person have rights to love");
    }
}
public class Test {
    public static void main(String[] args) {
        IConvert convert = Person::eat;
        convert.convert();
        convert = Person::new;
        convert.convert();
        Person person = new Person();
        convert = person::love;
        convert.convert();
    }
}