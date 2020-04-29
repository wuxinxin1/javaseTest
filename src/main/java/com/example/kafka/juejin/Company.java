package com.example.kafka.juejin;

public class Company {

    private String name;

    private String age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Company(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
