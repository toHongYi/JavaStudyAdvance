package com.hongyi.day01.entity;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/15 22:42
 * @description:
 */
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private double salary;

    public Employee(Integer id, String name, Integer age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
