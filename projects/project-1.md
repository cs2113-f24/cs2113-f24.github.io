---
layout: toc
permalink: project/1
---

*View the video for this project on [Youtube](https://youtu.be/s1Q4ioDXMu4)*

# Project 1: The DC Metro Trip Planner

part 1: draw your own diagram of the metro map and submit it

## Setup

Download the [MetroSimulator.java](./Project1/MetroSimulator.java) file.

Download the [junit-platform-console-standalone-1.7.0-M1.jar](./Project1/junit-platform-console-standalone-1.7.0-M1.jar) file.

Download the [Project1_Tester.java](./Project1/Project1_Tester.java) file.

### Github setup

Use git, as discussed in Lab 0, to create a repo called `gitusername-project1`, add these three files to it, and commit and push the changes to github. You will need to submit your git log to the submitserver. You will also want to create three additional class files, `Station.java`, `EndStation.java`, and `TransferStation.java` and store them in your repo – you will be submitting these five files to the submitserver.

You will need to use this repo effectively to receive full credit on this assignment, even though you will be submitting it on the submitserver. See the grading rubric below.

---

## DC Metro Trip Planner

![DC Metro](https://rail-it.com/wp-content/uploads/2018/01/metromap-full-1024x899.png "DC Metro map")

In this project, you will complete a point simulation of the [WMATA Trip Planner](https://wmata.com/), which can be used to plan how to arrive at a metro station by a certain time. We will only be implementing a much smaller part of this functionality: counting the number of stops between stations. Our metro system will have regular stations, end stations, and transfer stations, but we will not model the trains themselves, as we only have a week or two for this assignment.

Your job in this project is use good OOP to design this simulation. 

## A tour of the starter code

You are provided with the following Java files that you should review. 
### `MetroSimulator.java`

A class that represents our metro simulation by defining and connecting stations. You'll see that all the required stations are created for you as objects; you will simply need to complete the `makeOrangeLine()`, `makeRedLine()`, and `makePurpleLine()` methods to properly connect them.

### `Project1_Tester.java` 

In this project you should utilize Test Driven Development. All the required functionality in this project is captured in the unit tests; you are welcome to add additional functionality, but you don't have to. Make sure you understand the unit tests thoroughly before starting to write your code.

## Required functionality via unit tests

### test1: the `Station` class

The first unit test, below, checks to see that your `Station` class is able to connect to other stations:

```java
  @Test 
  public void test1(){
    System.out.println("test Station ctor and setup");

    Station s1 = new Station("pink", "Museum");
    String expected = "STATION Museum: pink line, in service: true, previous station: none, next station: none";
    assertEquals(expected, s1.toString());

    Station s2 = new Station("green", "Square");
    Station s3 = new Station("blue", "Plaza");
    s1.addNext(s2);
    expected = "STATION Museum: pink line, in service: true, previous station: none, next station: Square";
    assertEquals(expected, s1.toString());
    expected = "STATION Square: green line, in service: true, previous station: Museum, next station: none";
    assertEquals(expected, s2.toString());

    s1.addPrev(s3);
    expected = "STATION Museum: pink line, in service: true, previous station: Plaza, next station: Square";
    assertEquals(expected, s1.toString());
    expected = "STATION Plaza: blue line, in service: true, previous station: none, next station: Museum";
    assertEquals(expected, s3.toString());

    assertEquals(true, s1.isAvailable());
    s1.switchAvailable();
    expected = "STATION Museum: pink line, in service: false, previous station: Plaza, next station: Square";
    assertEquals(expected, s1.toString());
    s1.switchAvailable();
    expected = "STATION Museum: pink line, in service: true, previous station: Plaza, next station: Square";
    assertEquals(expected, s1.toString());
  }
```

Each station should have a name, a line color, a boolean flag for being in service or not, and connections to previous and next `Station`s. This class only allows a connection to two stations; we'll cover transfer stations below.

The `Station` class is the parent class of `EndStation` and `TransferStation`.

### test2: `Station` equality

```java
  @Test 
  public void test2(){
    System.out.println("test Station equals");

    Station s1 = new Station("pink", "Museum");
    Station s2 = new Station("pink", "Museum");
    Station s3 = new Station("blue", "Museum");
    Station s4 = new Station("pink", "Square");
    assertEquals(true, s1.equals(s1));
    assertEquals(true, s1.equals(s2));
    assertEquals(false, s1.equals(s3));
    assertEquals(false, s1.equals(s4));

    EndStation s5 = new EndStation("pink", "Museum");
    TransferStation s6 = new TransferStation("pink", "Museum");
    assertEquals(true, s1.equals(s5));
    assertEquals(true, s1.equals(s6));
  }
```

Two stations are equal when their line number and name are the same.

### test3 and test4: `EndStation`s

An `EndStation` is a child class of `Station` where the previous and next stations are the same. The `makeEnd()` method is responsible for this functionality:

```java
  @Test 
  public void test3(){
    System.out.println("test EndStation setup");

    EndStation s1 = new EndStation("pink", "Museum");
    String expected = "ENDSTATION Museum: pink line, in service: true, previous station: none, next station: none";
    assertEquals(expected, s1.toString());

    assertEquals(true, s1 instanceof Station);
  }

  @Test 
  public void test4(){
    System.out.println("test EndStation makeEnd");

    EndStation s1 = new EndStation("pink", "Museum");
    Station s2 = new Station("pink", "Square");
    s1.addNext(s2);
    String expected = "ENDSTATION Museum: pink line, in service: true, previous station: none, next station: Square";
    assertEquals(expected, s1.toString());

    s1.makeEnd();
    expected = "ENDSTATION Museum: pink line, in service: true, previous station: Square, next station: Square";
    assertEquals(expected, s1.toString());

    s1 = new EndStation("pink", "Museum");
    s2 = new Station("pink", "Square");
    s2.addNext(s1);
    expected = "ENDSTATION Museum: pink line, in service: true, previous station: Square, next station: none";
    assertEquals(expected, s1.toString());

    s1.makeEnd();
    expected = "ENDSTATION Museum: pink line, in service: true, previous station: Square, next station: Square";
    assertEquals(expected, s1.toString());
  }
```

### test5 and test6: `TransferStation`s

A `TransferStation` is a child class of `Station` where, in addition to inheriting the previous and next stations of the parent class, it stores an `ArrayList` of stations for additional connections. This implies that each stop along a color line has a primary previous and next station (as in the `Station` class), and then a list of alternative previous and next transfer stations.

```java
  @Test 
  public void test5(){
    System.out.println("test TransferStation setup");

    TransferStation s1 = new TransferStation("pink", "Museum");
    String expected = "TRANSFERSTATION Museum: pink line, in service: true, previous station: none, next station: none\n\tTransfers: \n";
    assertEquals(expected, s1.toString());

    boolean result = false;
    if (s1 instanceof Station)
      result = true;
    assertEquals(true, result);
  }

  @Test 
  public void test6(){
    System.out.println("test add TransferStation");

    TransferStation s1 = new TransferStation("pink", "Museum");

    Station s2 = new Station("blue", "Square");
    s1.addTransferStationPrev(s2);
    String expected = "TRANSFERSTATION Museum: pink line, in service: true, previous station: none, next station: none\n\tTransfers: \n" + 
                          "\tSTATION Square: blue line, in service: true, previous station: none, next station: Museum\n";
    assertEquals(expected, s1.toString());

    EndStation s3 = new EndStation("green", "Plaza");
    s1.addTransferStationNext(s3);
    expected = "TRANSFERSTATION Museum: pink line, in service: true, previous station: none, next station: none\n\tTransfers: \n" + 
                          "\tSTATION Square: blue line, in service: true, previous station: none, next station: Museum\n" + 
                          "\tENDSTATION Plaza: green line, in service: true, previous station: Museum, next station: none\n";
    assertEquals(expected, s1.toString());

    TransferStation s4 = new TransferStation("yellow", "Hill");
    s1.addTransferStationPrev(s4);
    expected = "TRANSFERSTATION Museum: pink line, in service: true, previous station: none, next station: none\n\tTransfers: \n" + 
                          "\tSTATION Square: blue line, in service: true, previous station: none, next station: Museum\n" + 
                          "\tENDSTATION Plaza: green line, in service: true, previous station: Museum, next station: none\n" + 
                          "\tTRANSFERSTATION Hill: yellow line, in service: true, previous station: none, next station: Museum\n\tTransfers: \n\n";
    assertEquals(expected, s1.toString());
  }

```

### test7: Connecting stations

Two stations can be connected as primary previous/next stations using the `connect` method:

```java
  @Test 
  public void test7(){
    System.out.println("test Station connect");

    Station s1 = new Station("pink", "Museum");
    String expected = "STATION Museum: pink line, in service: true, previous station: none, next station: none";
    assertEquals(expected, s1.toString());

    Station s2 = new Station("green", "Square");
    s1.connect(s2);
    expected = "STATION Museum: pink line, in service: true, previous station: none, next station: Square";
    assertEquals(expected, s1.toString());
    expected = "STATION Square: green line, in service: true, previous station: Museum, next station: none";
    assertEquals(expected, s2.toString());

    Station s3 = new Station("blue", "Plaza");
    s3.connect(s1);
    expected = "STATION Museum: pink line, in service: true, previous station: Plaza, next station: Square";
    assertEquals(expected, s1.toString());
    expected = "STATION Plaza: blue line, in service: true, previous station: none, next station: Museum";
    assertEquals(expected, s3.toString());
  }

```

### tests 8-10: Making the DC metro 

Refer to these test cases for how you will complete the `makeOrangeLine()`, `makeRedLine()`, and `makePurpleLine()` methods. **You will be required to submit a drawing of the metro system defined by these three methods collectively that has the `previous` and `next` primary connections correctly labelled for each station, as well as the list of transfer connections, inserted in correct order, for full credit.** Please use the format for this diagram that we will show in lecture, otherwise we cannot assign credit for this part. All students should submit their own unique hand- (or computer-) drawn diagrams; this is NOT a group assignment.

Note that you will only be implementing a very small part of the orange and red lines, and then adding a fake purple line; you don't need to create the entire actual metro itself, just what is in these test cases.

### tests 11-13: Counting the number of stops between stations

The final set of unit tests check that you have correctly written a recursive method, `int tripLength(Station dest)`, that returns the number of stops between the station it is called upon and the destination station. 

You may implement this method however you see best, but **you must write this method yourself**; you may NOT copy code from the Internet or another person. Here are some helpful hints for this method:
* Identify one or more base cases for the method, where you will stop the recursion.
* The metro map is a directed graph with loops; to avoid infinite/expensive recursion, keep track of stations you've already visited and do not allow yourself to visit them again
* Write a helper method to help you with the recursion; you can pass more arguments to it than just a destination
* Keep in mind that for a `TransferStation`, you'll not only need to check the primary connections, but also the transfer connections
* To keep this project simple enough to complete in 1-2 weeks, **our metro trips will only be moving "forward"**, that is, we guarantee that the path from the start node to the destination node will only make use of the `next` pointers/fields (and any `next` pointers/fields in the transfers). This way you don't have to deal with handling being able to go in both directions. TLDR if you pass the test cases, you're done (as long as you didn't hard-code them).

**If you would like help debugging this method, you must have print statements inside of it** so that we can trace the path your method takes through your metro map. Students without such print statements who come to office hours/Ed will be asked to return when they have inserted meaningful print statements in every branch of this recursive method.

## Junit testing

To run your unit tests -- remember, you need to have the junit jar file in the same directory -- compile and run your code with the following commands on the terminal (depending on your OS):

### Windows
`javac -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" *.java`
`java -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Project1_Tester`

### Mac/Linux
`javac -classpath ".:./junit-platform-console-standalone-1.7.0-M1.jar" *.java`
`java -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Project1_Tester`

## Requirements

There are two requirements for this project. The first requirement (Part A) involves drawing the metro map specified in tests 8-10 and submitting this to BB for grading. The second requirement (Part B) is to actually implement the code, pass the unit tests, and submit your code to the submitserver.


# Grading rubric and submission

Use git, as discussed in lab zero, to submit your work in a repo called `gitusername-project1`. You will be graded on the following:

In your github repo, type the following command to pipe your log to a text file:
`git log > log_file.txt`

Next, type the following command to compress your files into the required submission format:
`tar -cvf project1.tar  Station.java EndStation.java TransferStation.java MetroSimulator.java log_file.txt`

Submit your code, as `project1.tar` on the submitserver. 

You will be graded on the following:


|Item | Points |
|a correct drawing/diagram of the metro system described above has been submitted to BB |  4 |
|your git logs show comments that helpfully and meaningfully describe what updates you were committing |  2 |
|your git logs show evidence that you committed code at least once during the Project1 lab   |  1 |
|your git logs show at least five commits that are all at least ten minutes apart  |  2 |
|the test cases pass (7 points each) – answers that are hard-coded will not receive credit | 91 |
|TOTAL | 100 |

