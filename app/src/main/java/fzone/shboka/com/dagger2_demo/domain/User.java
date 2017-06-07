package fzone.shboka.com.dagger2_demo.domain;

/**
 * Created by 王天明 on 2015/12/18 0018.
 */
public class User {

    private String name;
    private String email;
    private String age;
    private String phone;

    public User(){}

    public User(String name, String email, String age, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
