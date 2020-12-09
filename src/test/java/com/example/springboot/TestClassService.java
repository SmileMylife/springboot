package com.example.springboot;

public class TestClassService {
    public String username = "父亲";

    public String test1() {
        System.out.println("父类方法");
        return this.username;
    }

    private void test() {
        System.out.println("父类");
    }

    public static void main(String[] args) {
        TestClassService testClassService2 = new TestClassService2();
        System.out.println(testClassService2.username);

        System.out.println("直接访问" + testClassService2.username);
        System.out.println(testClassService2.test1());

//        testClassService2.test();
    }
}

class TestClassService2 extends TestClassService {
    public String username = "儿子";

    @Override
    public String test1() {
        System.out.println("子类方法");
        return this.username;
    }

    public void test() {
        System.out.println("子类");
    }
}
