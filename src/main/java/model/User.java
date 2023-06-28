package model;

public class User {
    private String username;
    private int age;

    private User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public static User createUser(String username, int age) {
        return new User(username, age);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
