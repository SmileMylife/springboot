package com.example.springboot;

public class TestRTTI {
    private String username;
    private String password;

    public void testString(String username) {
        System.out.println(username);
    }

    public static void main(String[] args) {
        Class<TestRTTI> testRTTIClass = TestRTTI.class;
    }
}
