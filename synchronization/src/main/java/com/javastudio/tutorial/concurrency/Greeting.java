package com.javastudio.tutorial.concurrency;

/**
 * Greeting methods are thread safe
 */
class GreetingA {

    static synchronized void sayHello() {
        try {
            Thread.sleep(3000);
            System.out.println("Hello");
        } catch (InterruptedException ignored) {
        }
    }

    static synchronized void sayGoodbye() {
        System.out.println("Goodbye");
    }
}

/**
 * Greeting methods are thread safe
 */
class GreetingB {
    private static final Object lock = GreetingB.class;

    public static void sayHello() {
        try {
            synchronized (lock) {
                Thread.sleep(3000);
                System.out.println("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public static void sayGoodbye() {
        synchronized (lock) {
            System.out.println("Goodbye");
        }
    }
}

/**
 * Greeting methods are thread safe
 */
class GreetingC {
    private static final Object lock = GreetingC.class;

    public static void sayHello() {
        try {
            synchronized (lock) {
                Thread.sleep(3000);
                System.out.println("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public static synchronized void sayGoodbye() {
        System.out.println("Goodbye");
    }
}

class GreetingD {
    private static final Object lock = GreetingD.class;

    public void sayHello() {
        try {
            synchronized (lock) {
                Thread.sleep(3000);
                System.out.println("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public synchronized void sayGoodbye() {
        System.out.println("Goodbye");
    }
}

class GreetingE {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void sayHello() {
        try {
            synchronized (lock1) {
                Thread.sleep(3000);
                System.out.println("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public static void sayGoodbye() {
        synchronized (lock2) {
            System.out.println("Goodbye");
        }
    }
}