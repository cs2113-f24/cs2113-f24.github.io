---
layout: worksheet
permalink: /worksheet/j10
showsolution: false
---

# Worksheet: J10

This worksheet should be completed in lab. Submit it to BB by Thursday of this week.

## Find the bug!

In this lab, you'll get some additional practice debugging non-trivial programs.

We have included an anonymous student's solution to a programming project from a previous semester. It's similar to your DotChaser assignment, and the instructions for students in the previous semester can be found [here](https://cs2113-s24.github.io/project/1), for reference.

You can download the code as a zip file [here](./debugging_1.zip). Unzip it into its own folder.

This codebase originally passed all the included unit tests, but I have seeded it with a fault that causes it to fail one unit test. Your goal is to **use debug print statements to find this fault**, and then fix it, working backwards from what is mismatched in the unit test. Note that this is a moderately difficult problem, and you should spend some time first understanding what went wrong in the unit tests, before trying to fix the code.

## Grading

You will earn credit by demonstrating that you were able to localize the fault using print statements that trace through the given code. Even if you can look at the code and see the fault, you need to demonstrate, using print statements, how you would have found the fault if it wasn't obvious to you.

### Understanding what is wrong in the test cases

Take a look at the mismatched output, and, using your DotChaser knowledge, figure out an explain what went wrong in `test9`. To do this, take a look at the inputs to the simulator in that test, and then the starting configuration of the board. Draw this board on a sheet of paper with all the animals' coordinates. Then, step through each time, and update the coordinates on your sheet of paper in the way the test case dictates.

As a hint, this test case is checking the `Cat` functionality, which differs from a `Mouse` in that it chases mice and eats them, etc. Since we know that the `Mouse`  functionality is correct because all those other test cases passed, pay special attention to the behavior of the cat as you trace through this test cases.

Compare the output the buggy code gave to the expected output, and explain, in a paragraph or less, at what time step what went wrong, as you compare the two outputs.

### Debugging the code with print statements

Now that you have a sense of what could be going wrong in the given code, use debugging print statements in the format below to trace through the relevant methods that could be broken (based on what difference you observed above). Each debugging statement should be numbered, and explain what it is you are trying to look at. These numbers should start at 1, and should tell a story for how you indentified the fault in the code. You will likely want statements that could look like:

```java
System.out.println("DEBUG 1: x is " + x); //prints out the value of variable x at the current line
System.out.println("DEBUG 2: got inside if statement on line 223"); // shows that you got inside a desired if statement
```

When you have found the fault, include the following debug statement:

```java
System.out.println("DEBUG END: FOUND THE FAULT HERE");
```

### Fixing the fault

Once you've found the error using the techniques above, go ahead and fix the fault so that it passes the two failing test cases. **In comments above each line you add (if needed), include the comment `// FIXING fault`**. Similarly, instead of deleting any lines (if needed), just comment them out and add the phrase **`FIXING FAULT` to the end of the line**.

### Grading Rubric

 You will be graded on the following:

|Item | Points |
|Included a diagram showing the progression of cats/mice on the grid until the fault was demonstrated in `test9` | 25 |
|Included a paragraph convincingly explaining what the fault was, based on examining the unit tests only | 25 |
|Included sufficient debug print statements that convincingly explain/tell a story of how the fault was found, based on the explanation above | 25 |
|Correctly fixed the fault and passes all test cases | 25 |

Students must be present for the full lab session, and spend that time completing this assignment, in order to receive credit.

## Submission

Create a tar file from the folder you are working in by running the following command in the terminal:
`tar -cvf J10.tar *.*`

This will put every file in that folder into a tarfile named `J10.tar`

Then, upload this tarfile to BB by Thursday this week.