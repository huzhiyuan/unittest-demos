package com.hzy.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        test();
    }

    public static void test(){



    }
    public static void testReduce(){
        int count = Stream.of(1,2,3).reduce(0,(acc,element) -> acc+element);
        System.out.println(count);
    }
    public static void testFlatMap() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        System.out.println(together);
    }

    public static void testCollect() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(Arrays.asList("a", "b", "c").equals(collected));//TODO List重写过equals方法!!!
    }

    public static void testApplay() {
        Function<Integer, Integer> add = x -> x + 5;
        Function<Integer, Integer> mix = x -> x - 3;
        Function<Integer, Integer> h = add.andThen(mix);
        long result = h.apply(1000).longValue();

        System.out.println(result);
    }

    public static void testIfContainsUpper() {
        String test = "dsferDFGREwefwsdBRTHRBHD";
        boolean hasUpperCase = test.chars().anyMatch((int ch) -> Character.isUpperCase(ch));
        System.out.println(hasUpperCase);
    }
}
