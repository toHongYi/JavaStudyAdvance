package com.hongyi.day01;

import com.hongyi.day01.entity.Employee;
import org.junit.Test;

import java.util.*;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/15 22:32
 * @description:
 */
public class TestLambda {

    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<String> com = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        TreeSet<String> ts = new TreeSet<>(com);

        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }

        });
    }

    //现在的 Lambda 表达式
    @Test
    public void test2(){
        Comparator<String> com = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> ts = new TreeSet<>(com);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //需求：获取公司中年龄小于 35 的员工信息
    public List<Employee> filterEmployeeAge(List<Employee> emps){
        List<Employee> list = new ArrayList<>();

        for (Employee emp : emps) {
            if(emp.getAge() <= 35){
                list.add(emp);
            }
        }

        return list;
    }




    public static List<Employee> filterEmployees(List<Employee> list) {
        ArrayList<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }

        return emps;
    }


}
