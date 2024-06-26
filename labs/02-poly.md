---
layout: default
permalink: /lab/2
---

# Lab 2: I'm a dot thing living in a grid world

*View the video for this lab on [youtube](https://youtu.be/oVsOfsy1-mc).*

## Setup

Download the [DotChaser.java](./DotChaser.java) file.

Download the [Plotter.jar](./Plotter.jar) file.

Download the [Lab2_Tester.java](./Lab2_Tester.java) file.


### Github setup

Use git, as discussed in Lab 0, to create a repo called `gitusername-lab2`, add these two files to it, and commit and push the changes to github. The timestamp of your invitation of the grader as a collaborator must be from this lab session.

### Running your program

Run your lab on the command line by executing the following pipeline

```
java DotChaser | java -jar Plotter.jar
```

The program `Plotter.jar` is provided for you. 


# Part 1: Rewriting the code using OOP 

Take a look at the `DotChaser` program. You can compile and run it as is:

```
javac DotChaser.java
java DotChaser
```

You'll see some interesting (but boring) output.

```
55 50 b
45 50 r
done
56 50 b
45 51 r
done
57 50 b
46 51 r
done
58 50 b
.
.
.
```

Each line represents the location and color of a "Thing" dot in each round. The end of a round is indicated by "done"

```
55 50 b <- round i, blue dot at row 55, column 50
45 50 r <- round i, red dot at row 45, column 50
done    <- end round
56 50 b <- round i+1, blue dot at row 56, column 50
45 51 r <- round i+1, red dot at row 45, column 51
done    <- end round
```

As noted, a list of coordinates is pretty boring, so we also provided you a Plotter program that reads coordinates from `stdin` and plots them for a nice little visual of moving dots. Try it out yourself: 

```
java DotChaser | java -jar Plotter.jar 
```

<div class="requirement">
Troubleshooting

If the GUI window does not load onn Windows, try to either run java through the command line "as administrator", and/or install WSL and run it from a WSL shell. Post to Ed right away if you get stuck -- you may not be able to figure this part out on your own.
</div>

The output of `DotChaser` is piped into Plotter, which plots each of the dots at the row,col coordinates and color given. The "done" at the end of a group of lines tells us that we are done with the "round", so now the updated display should be shown, and what follows will be values for the next "round". What you get is a really pretty animation like below. 

<center>
<img src="/images/DotChaser.png" alt="DotChaser" width="50%" height="50%" />
</center>


So what do you have to do? Well, the program `DotChaser` is written entirely as a *Procedural Program*. You will re-write it as an *Object-Oriented Program*, and once done, you will extend its functionality a bit as well. 

Note that the plotter can plot more than just red and blue. Here are your color choices as you make your things: r (red), b (blue), g (green), y (yellow), o (orange), p (pink), m (magenta), k (black).

# Part 1: Create a UML diagram for your `Thing`s 

Use Violet UML (or another tool, or paper) to generate a UML diagram for your many `Thing` types after reading the explanations below.

> Create a UML diagram. Include it in your repo, named 'UML.png' and link it into your README. 

# Part 2: Rewrite `DotChaser` functionality into several classes using good OOP 

Rewrite `DotChaser` as an object-oriented program. You will (presumably) be creating other `.java` files as well. The **output of `DotChaser` should not change**, but the design must follow all the good object-oriented design principles we have discussed. In particular, you must use encapsulation, information hiding, inheritance, and polymorphism. Maximize code reuse; keep implementation and interface as separate as possible. Your inheritance should follow the picture on the right.

You must do these things:

* **Refactor the code** only: do not change variable names or method names; reuse the existing fields and methods.
* **Remove static methods**: only `main()` should be static in all of your classes.
* **Polymorphism**: You should have `TypeA` and `TypeB` classes; observe how they differ. Your parent class `Thing` most likely will not need a single if statement. If you find you need one/more, make sure there isn't a way to utilize polymorphism instead. If you still find the need for if statements, ask your instructor if they are appropriate. Related to this, your child classes should have at least one method which demonstrates polymorphism. This should naturally come from your design, but we make note of it here as a requirement to give you that extra nudge.
* **Use a linked list**: Remove `Node` from `main()` and write a `List` or `Queue` class called `ThingList`. You'll have to write this. Your `main()` should not have `Node` variables anymore, but instead a `ThingList` variable with nice calls such as `list.add(thing)`. You need to write this code yourself; do not use ChatGPT (for example) to generate this linked list.
* **README.md**: In the  README file and write a paragraph (or two) that explains how your redesign makes use of encapsulation, information hiding, inheritance and polymorphism.

Be sure to add all your new Java files to the repo for submission.

# Part 3: Add a new `Thing` 

The original `DotChaser` had two types of Things: `typeA`, which randomly choses left, right or straight at every round; and `typeB`, which randomly chooses left, right or straight every 10th round. Now that you have a nice object-oriented version, create a third type of `Thing` called `TypeC`. What exactly it does is up to you, but it needs to use some diagonal motion (i.e. left-right-left-right-... sequences, or draws a circle, triangle, or other shape). The principal thing to keep in mind is how OOP makes this easier and cleaner. 


<div class="requirement">
Create a third `ThingC` type that must have.

1. Ensure that the orignal `DotChaser` still works the same as before, even after you've added your new type of `ThingC`. That is, the red and blue dots act normally.
2. The new type of `ThingC` must move in a *new* way different than `typeA` or `typeB`. 
3. In your `README.md` indicate the name of the new type and the Java file used to program it. **Be sure to add that file to your repo for submission :)**
4. Add a paragraph to the `README.md` file that explains how your new OOP design makes adding new types of `ThingC` easy.
5. Add a final paragraph to the `README.md` that explains exactly where in your program there is a polymorphic function call, and how that plays an essential role in the program functioning properly.

*Be sure to add all your new Java files to the repo for submission.*


Here's an example of a solution with an extra yellow thing doing spirals:

<center>
<img src="/images/DotChaser-spiral.png" alt="DotChaser Spiral Yellow" width="50%" height="50%" />
</center>
</div>


# Grading rubric and submission

## Junit testing

To run your unit tests -- remember, you need to have the junit jar file in the same directory -- compile and run your code with the following commands on the terminal (depending on your OS):

### Windows
`javac -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" *.java`
`java -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Lab2_Tester`

### Mac/Linux
`javac -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" *.java`
`java -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Lab2_Tester`

Use git, as discussed in lab zero, to submit your work in a repo called `gitusername-lab2`. You will be graded on the following:

|Item | Points |
|the name of the repo for this lab matches the pattern  `gitusername-lab2` | 3 |
|the grader has been added as a collaborator to the repo| 4 |
|the repo has been made private | 3 |
|[**unit test**] `TypeA` and `TypeB` are child classes of `Thing` | 5 |
|[**unit test**] `Thing` does not use if-statements nor the `instanceof` operator | 5 |
|[**unit test**] no static methods besides `main` in any of your classes | 5 |
|[**unit test**] the `Thing` class is responsible for movement, utilizing good OOP | 5 |
|[**unit test**] the `Thing` class contains the appropriate fields, utilizing good OOP | 5 |
|[**unit test**] the `Thing` class uses abstraction appropriately in all places, utilizing good OOP | 5 |
|[**unit test**] the `TypeA` and `TypeB` contain the minimum number of fields necessary (declare each on a new line), utilizing good OOP | 5 | 
|[**unit test**]`TypeA` and `TypeB` contain the minimum number of methods necessary, utilizing good OOP | 5 | 
|[**unit test**] the `ThingList` class contains the `Node` class as an inner class, utilizing good OOP | 5 |
|[**unit test**] the `ThingList` class has methods to `addAll`, `moveAll`, and `printAll` `Thing`s inside the list/queue | 5 |
|`TypeA`, `TypeB`, and `TypeC` classes turn appropriately upon visual inspection, utilizing good OOP | 20 | 
|a README file as specified above is included | 10 |
|a UML diagram matching the code implementation as been included, that lists all classes and their methods and fields | 10 |
|TOTAL | 100 |
