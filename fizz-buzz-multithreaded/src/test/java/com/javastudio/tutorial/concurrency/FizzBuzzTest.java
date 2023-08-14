package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {


    @Test
    void should_print_in_correct_format() throws InterruptedException {

        FizzBuzz fizzBuzz = new FizzBuzz(15);

        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException ignored) {
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException ignored) {
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException ignored) {
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(System.out::println);
            } catch (InterruptedException ignored) {
            }
        }).start();

        TimeUnit.of(ChronoUnit.MILLIS).sleep(1000);
    }

}