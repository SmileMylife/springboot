package com.example.springboot;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class PersonTest {
    @NonNull
    private String age;

    @Nullable
    private String name;

    public PersonTest(@NonNull String age, @Nullable String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        PersonTest personTest = new PersonTest(null, "zhangpei");
    }
}
