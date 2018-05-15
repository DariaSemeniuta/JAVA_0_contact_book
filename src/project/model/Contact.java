package project.model;

public class Contact {
    private String name;
    private String phoneNumber;
    private String birthday;
    private int age;

    public Contact(String name, String phoneNumber, String birthday, int age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.age = age;
    }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Contact{name=" + name + ", phoneNumber=" + phoneNumber + ", birthday=" + birthday + ", age=" + age + "}";
    }
}
