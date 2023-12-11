---
layout: toc
permalink: project/1
---

*View the video for this project on [Youtube](https://youtu.be/s1Q4ioDXMu4)*

# Project 1: It's a cat and mouse game...

This is a two part project. The first part of the project will require you to submit a UML diagram and descriptions of your OOP design to complete the project. The second part of the project is your full submission.

## Setup

Download the [City.java](./City.java) file.

Download the [Creature.java](./Creature.java) file.

Download the [GridPoint.java](./GridPoint.java) file.

Download the [PlotterPoint.java](./PlotterPoint.java) file.

Download the [Simulator.java](./Simulator.java) file.

Download the [Plotter.jar](./Plotter.jar) file.

Download the [Project1_Tester.java](./Project1_Tester.java) file.

### Github setup

Use git, as discussed in Lab 0, to create a repo called `gitusername-project1`, add these six files to it, and commit and push the changes to github. The timestamp of your invitation of the grader as a collaborator must be from this lecture session.

---

## Mice and Cat Simulation


In this project, you will complete a point simulation of a city of mice and cats! Cats chase mice, and when they catch a mouse, they eat it. If a cat doesn't eat enough, it passes away (as do mice after a certain amount of time). These creatures live inside a city, where they roam around a two dimensional grid.

Your job in this lab is use good OOP to design this simulation. 

## A tour of the starter code

You are provided with the following Java files that you should review. They are heavily commented, and will provide the guide posts for completing this project. Not all details are described here, so I strongly encourage you to read through the entirety of the code provided for you. 

### `Creature.java`

A class that represents a basic creature in the simulation. You'll need to extend this class for your mice and cats.

### `City.java` 

This class represents the city/space in which the creatures live. All the creates are stored in the `creatures` list:

```java
    private List<Creature> creatures; //list of all creatures
```

The `creatures` is a full list of all the active creatures in the simulation. 

Importantly, the `City` class has a method `simulate` that describes the primary routine of each iteration of the simulation. You cannot edit this method, but you should review it carefully because it will dictate how you design your other classes. This routine is provided below:

```java
   //You need to realize in your code such that simulate works for
    //**ALL** levels of simulkation, which means you'll need to take
    //advantage of inheritance and polymorphism.
    public void simulate() {
        //DO NOT EDIT!
        
        //You get this one for free, but you need to review this to
        //understand how to implement your various creatures

        //First, for all creatures ...
        for(Creature c : creatures) {
            c.step(); 
        } //move everyone forward one step in simulation
        
        //Second, for all cratures ...
        for(Creature c : creatures) {
            c.takeAction(); 
        }//take some action based on the new positions

        //Third, for all creatures ...
        LinkedList<Creature> deadCreatures = new LinkedList<Creature>();
        for(Creature c: creatures) {
            if(c.isDead()) 
                deadCreatures.add(c);
        }//find those that are dead after the action is taken

        //Four, for all creatures ...
        for(Creature c: deadCreatures) {
            creatures.remove(c);
        }//remove any creatures that are dead
        
        //Five, add in any new creatures that have been added before ...
        addNewCreatures();

        //Five, for all creatures
        for(Creature c : creatures) {
            System.out.println(c);
        }//print out all creatures

```

Reading through the `simulate` method, you see that first, all the creatures are moved forward by taking a `step` in the simulation. These steps involve any directional changes as well as passing away. 

In the `takeAction` method, each creature assess its surroundings, determines if it should eat something, chase something, or do something.

After all the actions are taken, any creatures that are dead are removed from the `creatures` list. And then any new creatures are added. Note that there is a method `addNewCreatures` that is provided for you, and duplicated below.

```java
 public void addNewCreatures() {
        while(!creaturesToAdd.isEmpty()) {
            creatures.add(creaturesToAdd.remove());
        }
    }
```

This method clears the `creaturesToAdd` queue. So if you want to add a new creature, you construct it and add it to this queue. There is an example of this in the constructor of the `City`.

### `Simulator.java` 

This class has the main method for the simulation. You may need to do minor modifications to this while editing and to meet the requirements of the assignment.

### `GridPoint.java`

This class implements a row/column pair that can be used as a key in a map. You should read this class, but you will not need to edit it. 


## Running, Visualization and debugging your simulation 

Like in Lab 2, we will separate the simulation from visualization. 

To compile all your files at once, use  
```javac -classpath ".;./Plotter.jar;./junit-platform-console-standalone-1.7.0-M1.jar" *.java``` for Windows 

```javac -classpath ".:./Plotter.jar:./junit-platform-console-standalone-1.7.0-M1.jar" *.java``` for Mac 

### Simulating

You can run the simulator with the following arguments:

```                  
                      .-- Number of rounds to simulate
                      |    .- The seed of the random number generator
                      v    v
java Simulator 8 2 0 1000 42 
               ^ ^ ^
               | | '-number of inital zombie cats (this was from an old semester, ignore it for now)
               | '- number of initial cats
               '- number of initial mice
```

The output of the simulator is a series of `x` and `y` coordinates describing the locations of each of the creatures. It may look like below:

```
56 34 k
53 33 y
57 34 k
43 4 y
58 35 k
58 16 b
71 34 y
done 1000
```

Like in Lab 2, the first two numbers are a coordinate, and the letter is a color. Once complete, the `done` command is given. Different from Lab 2, `done` also outputs a round number. 


Optionally, you can include `--DEBUG` as the final argument, like below, if you want to iterative step through the simulation

```
$ java Simulator 8 2 0 1000 42 --DEBUG
42 10 b
50 3 b
38 66 b
63 13 b
53 76 b
55 32 b
48 23 b
27 23 b
43 43 y
27 20 c
done 1
Enter anything to continue: 
41 10 b
50 2 b
38 67 b
64 13 b
54 76 b
54 32 b
47 23 b
28 23 b
45 43 y
27 22 c
done 2
Enter anything to continue: 
```

> A quick note about the simulation space. It's a *torus*. That means when you go off one edge, you wrap around to the other edge of the board. You can see this in the animation above. Your code should account for that. 

### Visualizing

Like in Lab 2, we've provided a plotting tool `Plotter.jar` which you can pipe your output to, like so

```
java Simulator 8 2 0 1000 42 | java -jar Plotter.jar
```

This will open a visual, like the animation above. There are two arguments you can provide to the plotter.

* `java -jar Plotter.jar 42` : the 42 indicates how many milliseconds between iterations. Note that the default rate is 500 millis, or half a second.

* `java -jar Plotter.jar --DEBUG` : if the debug flag is set, then the plotter renders additional information about the location of points and the round number. Like below.

<img src="/images/Zombie-Simulator-Debug-Plotter.png" alt="Simulation GIF" width="60%" 
style="display: block;
margin-left: auto;
margin-right: auto;"/>

Note that you can combine these two arguments, but you must set the millis first, and then the debug flag. 

```
java -jar Plotter.jar 42 --DEBUG
```

> **DEBUGGING TIP**: If you want to connect your simulator to the plotter, but also print out debug information from your simualor code, use `System.err.println()` methods. Only items printed to `stdout` are passed to the plotter. If you have any changes to the expected output (like extra print statements) the Plotter won't run and will just show a gray screen.

> **DEBUGGING TIP**: If your simulator is printing output, but nothing shows in the window when you are running the plotter, check the format of the output of the simulator, and make sure it is only two numbers followed by a letter (separated by spaces).

## Junit testing

To run your unit tests -- remember, you need to have the junit jar file in the same directory -- compile and run your code with the following commands on the terminal (depending on your OS):

### Windows
`javac -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar;./Plotter.jar" *.java`
`java -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Project1_Tester`

### Mac/Linux
`javac -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar:./Plotter.jar" *.java`
`java -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Project1_Tester`

## Requirements

There are two requirements for this project. The first requirement (Part A) involves presenting a UML diagram for your project that describes the `Creature` object relationships as well as how you utilized OOP design. The second requirement (Part B) is to actually implement the code. 

### Part A : UML Diagram and Planning Meeting (17 Points)

<div class=requirement>

**This part of the project must be signed off by a TA or LA by the due date specified**

For Part A, you should complete a UML diagram and discuss ut in a 5-minute meeting with a TA or LA during lab or office hours. Use this [signup sheet](https://edstem.org/us/courses/39551/discussion/3519133) to select a time to meet with one of the TAs during lab.

* `UML.png` : Create UML diagram for your simulation code and creatures. This should include primarily your creatures but also the interaction with other code. 

In your meeting, be prepared for feedback that you should incorporate into your project.

</div>
<br>

### Part B: Implementation (83 Points)

<div class=requirement>

Mice and cats should extend the `Creature` class. 

Additionally, in your simulator, have it such that:
* Every 100 rounds, a mouse is added to a random location in the city
* Every 25 rounds, a cat is added to a random location in the city.
**To pass the unit tests, random turning should be implemented by assigning the `nextInt(4)` from the creature's `Random` object (that is set in the constructor). Recall the entire simulation shares the same `Random` object across all creatures in the city**.

Then, implement the following:


####  The `Mouse` class 

* After 20 rounds of simulation time, a mouse produces a new baby mouse at the same location 
* A mouse dies after 22 rounds simulation time
* A mouse randomly changes directions 20% of the time. **To pass the unit tests, have your code look for 0 or 1 as the next random integer out of 0-9 to take the turn. Turning should always happen before stepping**.
* A mouse is displayed as a blue dot
* The `Mouse` class must extend the `Creature` class using good OOP practices 

#### The `Cat` class

* A cat eats a mouse if they end up on the same location. That is, the mouse should die and be removed from the simulation.
* If a cat doesn't eat a mouse within 50 moves, the cat dies.
* A cat searches up to 20 grid points (as measured by the `GridPoint.distance()` method) for a mouse to chase. **To pass the unit tests, it first checks if the cat needs to move NORTH/SOUTH (if the vertical distance is strictly greater than the horizontal distance); otherwise, it will move EAST/WEST. If the mouse is on the other side of the grid but is flagged as closest due to the grid wrapping, you should let your code go in the naive direction towards the mouse even if that makes it go further away from the target -- see test9 for an example here.**
* If the cat finds a mouse, it moves towards the mouse and is displayed using the color cyan. (This is to make it easier for you to debug, and for us to grade). If the cat cannot find a mouse, it moves normaly and is displayed in yellow.
* Cats *jump* two spaces at a time. They do not traverse the grid point they jump over. That is, if they are on space (1,2) they would move to (1,4). 
* Cats randomly turn, change direction, 5% of the time. **To pass the unit tests, have your code look for 0-4 as the next random integer out of 0-99 to take the turn. Turning should always happen before stepping**.
* Cats are displayed as a yellow dot.


</div>

# Grading rubric and submission

Use git, as discussed in lab zero, to submit your work in a repo called `gitusername-project1`. You will be graded on the following:

|Item | Points |
|the name of the repo for this lab matches the pattern  `gitusername-project1` | 3 |
|the grader has been added as a collaborator to the repo| 4 |
|the repo has been made private | 3 |
|you met with a TA to go over your UML diagram | 7 |
|UML diagram submitted that matches your code, using good OOP design | 10 |
|[**unit tests 1-8**] `Mouse` functionality correctly implemented | 48 (6 pts per test)|
|[**unit test 9**] `Cat` functionality correctly implemented | 20 |
|[**unit test 10**] creatures are correctly added to the city |5 |
|TOTAL | 100 |

