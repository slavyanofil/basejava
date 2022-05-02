package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    static int[] values = {1, 2, 3, 3, 2, 3};

    public static void main(String[] args) {
        System.out.println(minValue(values));
        System.out.println(oddOrEven(Arrays.stream(values).boxed().collect(Collectors.toList())));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (acc, x) -> acc * 10 + x);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, Integer::sum);
        boolean isOdd = sum % 2 == 0;
        return integers.stream().filter(i -> isOdd && i % 2 == 0 || !isOdd && i % 2 != 0).collect(Collectors.toList());
    }
}