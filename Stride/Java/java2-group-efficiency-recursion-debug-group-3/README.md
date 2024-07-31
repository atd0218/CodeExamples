[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/14VT37e3)
# Java 2 - Debugging exercise: The efficiency of Recursion

From Canvas: [Java Part II - Efficiency Recursive](https://awstechu.instructure.com/courses/517/discussion_topics/3247)

Recursion can be a powerful tool for implementing complex algorithms. On the other hand, recursion can lead to algorithms that perform poorly. 

In this exercise, you will analyze the question of when recursion is beneficial and when it is inefficient.

From the starter file, __RecursiveFib.java__, is certainly simple, and the method will work correctly. But watch the  output closely as you run the test program. The first few calls to the fib method are fast. For larger values (Do not try calling fib(60)), though, the program pauses an amazingly long time between outputs. It may take a very long time to finish executing!

## Task 1: 

To find out the problem, implement trace messages into the method and rename file as __RecursiveLibTracer.java__.  (Definition of trace message: A message that is printed during a program run for debugging purposes.) The output should show the pattern of recursive calls for computing fib(6).  The output should be similar as below:

```java
Enter n: Entering fib: n = 6
Entering fib: n = 5
Entering fib: n = 4
Entering fib: n = 3
Entering fib: n = 2
Exiting fib: n = 2 return value = 1
Entering fib: n = 1
Exiting fib: n = 1 return value = 1
Exiting fib: n = 3 return value = 2
Entering fib: n = 2
Exiting fib: n = 2 return value = 1
Exiting fib: n = 4 return value = 3
Entering fib: n = 3
Entering fib: n = 2
Exiting fib: n = 2 return value = 1
Entering fib: n = 1
Exiting fib: n = 1 return value = 1
Exiting fib: n = 3 return value = 2
Exiting fib: n = 5 return value = 5
Entering fib: n = 4
Entering fib: n = 3
Entering fib: n = 2
Exiting fib: n = 2 return value = 1
Entering fib: n = 1
Exiting fib: n = 1 return value = 1
Exiting fib: n = 3 return value = 2
Entering fib: n = 2
Exiting fib: n = 2 return value = 1
Exiting fib: n = 4 return value = 3
Exiting fib: n = 6 return value = 8
fib(6) = 8
```
## Task 2
Clearly, the computation of _fib(6)_ calls __fib(4)__ twice and __fib(3)__ three times. It is becoming apparent why the method takes so long. It is computing the same values over and over. As we discussed during DS & Algorithm week, you can solve Fibonacci with an old-fashioned iterative approach. However, what other approach can you improve the efficiency of recursion? (_Hint:_ HashMap)



