package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

class GreetingTest {
    @Test
    void in_which_order_it_will_print_when_synchronized_at_instance_method_level() throws Exception {
        new Thread(GreetingA::sayHello).start();
        new Thread(GreetingA::sayGoodbye).start();

        TimeUnit.of(ChronoUnit.MILLIS).sleep(4000);
    }

    @Test
    void in_which_order_it_will_print_when_synchronized_code_block_level() throws Exception {
        new Thread(GreetingB::sayHello).start();
        new Thread(GreetingB::sayGoodbye).start();

        TimeUnit.of(ChronoUnit.MILLIS).sleep(4000);
    }

    @Test
    void in_which_order_it_will_print_when_synchronized_code_block_and_static_method_level() throws Exception {
        new Thread(GreetingC::sayHello).start();
        new Thread(GreetingC::sayGoodbye).start();

        TimeUnit.of(ChronoUnit.MILLIS).sleep(4000);
    }

    @Test
    void in_which_order_it_will_print_when_synchronized_code_block_and_instance_method_level() throws Exception {
        var greeting = new GreetingD();

        new Thread(greeting::sayHello).start();
        new Thread(greeting::sayGoodbye).start();

        TimeUnit.of(ChronoUnit.MILLIS).sleep(4000);
    }

    @Test
    void in_which_order_it_will_print_when_synchronized_code_with_different_object() throws Exception {
        new Thread(GreetingE::sayHello).start();
        new Thread(GreetingE::sayGoodbye).start();

        TimeUnit.of(ChronoUnit.MILLIS).sleep(4000);
    }
}