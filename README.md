# MULTITHREADING AND CONCURRENCY
A brief demo of multithreading and concurrency in java and javaee

## Thread Fundamentals

###  Join 
[Java Thread Join Example](https://www.digitalocean.com/community/tutorials/java-thread-join-example)
This java thread join method puts the current thread on wait until the thread on which it’s called is dead. 
If the thread is interrupted, it throws InterruptedException.

###

###

### yield
[readme](./yield/README.md)

## Thread safety
### Synchronization

### Immutable 
### Atomic
[operand](atomic-operations-and-multithreading)

## Locks

## Java Collections

## Inter Thread Communication
For this take a look at java multi-treading course

## Patterns related to multithreading  
Observer
Publish 

## Monitoring 
### SIMULATE THREAD DEADLOCK
Start the project and let it simulate thread deadlock, each thread will stop for the other thread, to determine what the thread lock is 
```` 
jps -lv
jstack [pid]
````
