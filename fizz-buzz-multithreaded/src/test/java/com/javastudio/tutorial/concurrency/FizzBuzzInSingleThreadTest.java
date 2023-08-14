package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzzInSingleThreadTest {
    String solution_with_basic_code_style(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0)
                builder.append("\nFizz");
            else if (i % 3 == 0)
                builder.append("\nFizz");
            else if (i % 5 == 0)
                builder.append("\nBuzz");
            else
                builder.append("\n").append(i);
        }
        return builder.length() > 0 ? builder.substring(1) : builder.toString();
    }

    String solution_with_java_stream(int n) {
        return IntStream.rangeClosed(1, n)
                .mapToObj(i -> i % 3 == 0 ? (i % 5 == 0 ? "FizzBuzz" : "Fizz")
                        : (i % 5 == 0 ? "Buzz" : String.valueOf(i)))
                .collect(Collectors.joining("\n"));
    }

    @Test
    void should_return_numbers_from_1_to_n() {
        System.out.println(solution_with_basic_code_style(1));
    }
}