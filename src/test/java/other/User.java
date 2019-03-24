package other;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/3/5/005.
 */
public class User extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age ;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
