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

    public void setAge(@NonNull String age) {
        this.age = age;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @NonNull
    public String getAge() {
        return age;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof PersonTest) {
            PersonTest person = (PersonTest) obj;
            if (person.getAge().equals(this.getAge()) && person.getName().equals(this.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "{username: zhangpei}";
    }
}
