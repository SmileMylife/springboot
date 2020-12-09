package com.example.springboot.java_learn;

public class TestGc {
    public static void main(String[] args) {
        Book book1 = new Book(false);
        Book book2 = new Book(true);
    }
}

class Book {
    private boolean checkFlag = false;

    Book(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    @Override
    protected void finalize() throws Throwable {
        if (checkFlag) {
            System.out.println("gc回收之前需要做的事情");
        }
    }
}
