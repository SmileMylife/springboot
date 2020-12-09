package com.example.springboot;

import java.util.function.Function;

class I {
    @Override
    public String toString() { return "I"; }
}

class O {
    @Override
    public String toString() { return "O"; }
}

class K {
    @Override
    public String toString() {
        return "K";
    }
}

public class TransformFunction {
    static Function<I,O> transform(Function<I,O> in) {
        System.out.println("入参in" + in);
        return in.andThen(o -> {
            System.out.println(o);
            return o;
        });
    }
    public static void main(String[] args) {
        Function<I,O> f2 = transform(i -> {
            System.out.println(i);
            return new O();
        });
        O o = f2.apply(new I());
    }
}
