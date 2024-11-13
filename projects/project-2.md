---
layout: toc
permalink: project/2
---



# Project 2: Client Server Simulator

In this project, you will write a simple server that clients can use to connect and request the factorization of a supplied integer. The latter will allow us to simulate proper multi-threading of the server, as factorization can take quite some time for large numbers. This is one of the reasons *prime number factorization* is a useful tool in cryptography because efficient algorithms for prime factorization are not known. Primes (or semi-primes) are often used in cryptography to secure an encryption because it's very expensive to try to brute force these numbers.


## Setup

You should create at least the two files below:
* `Client.java`: the client code used to interact with a server.
* `Server.java`: the server code to receive and process requests from clients.

Download the [Project2_Tester.java](./Project2_Tester.java) file.

### Github setup

Use git, as discussed in Lab 0, to create a repo called `gitusername-project2`, add these two files to it, and commit and push the changes to github. 

### Testing/Grading

There are test cases to check most of your client and server functionality. You will receive the score you see on the submitserver. See the rubric at the bottom of this page.

## Client/Server Protocol

So that everyone's GWack client can interact with each other and the public channels, we must define a standard protocol. 

#### Initial Handshake

When a client first connects, they need to send a key to the server to indicate they're a valid client (recall that anyone can connect to public ports). Here, the key will be the very insecure passcode `12345`; the server should look for each client to send such a passcode as their first message, make sure it matches this number, and only then allow the client to actually remain connected for future factorization requests.

## Client 

You will implement a client in `Client.java` that connects to the server with a handshake, and then sends and receives messages. See the unit tests for details of what methods you are required to have (and their functionality).

## Server 

You will implement a server in `Server.java` that allows multiple clients to simultaneously connect to the server, perform a handshake, and request that the server factorize an integer.

### the `serve` method

To facilitate grading, the server will have a `serve` method that takes as argument the number of clients it is expected to serve per test case, as opposed to an infinite loop like there was in the exercises from class. This `serve` method will have a loop run for as many clients as specified in its argument.

After accepting a client via a successful handshake, this method will process the client request in a separate thread so that the server can continue to accept connections while these expensive factorization calculations are being performed on behalf of various clients. 

### other server methods

The server should record the time each client was connected so that it can properly return these values with the `getConnectedTimes` method, which should return a sorted `ArrayList` of `LocalDateTime` objects representing the connection time of every client.

See the unit tests for details of what additional methods you are required to have (and their functionality).

# Junit testing

To run your unit tests -- remember, you need to have the junit jar file in the same directory -- compile and run your code with the following commands on the terminal (depending on your OS):

### Windows
`javac -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" *.java`
`java -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Project2_Tester`

### Mac/Linux
`javac -classpath ".:./junit-platform-console-standalone-1.7.0-M1.jar" *.java`
`java -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Project2_Tester`

# Grading rubric and submission

Use git, as discussed in lab zero, to submit your work in a repo called `gitusername-project2`. You will be graded on the following:

In your github repo, type the following command to pipe your log to a text file:
`git log > log_file.txt`

Next, type the following command to compress your files into the required submission format:
`tar -cvf project2.tar Client.java Server.java log_file.txt`

Submit your code, as `project2.tar` on the submitserver. 


|Item | Points |
|your git logs show comments that helpfully and meaningfully describe what updates you were committing |  1 |
|your git logs show evidence that you committed code at least once during the Project2 lab   |  1 |
|your git logs show at least five commits that are all at least ten minutes apart  |  2 |
|the test cases pass (12 points each) – answers that are hard-coded or do not follow written instructions above will not receive credit | 96 |
|TOTAL | 100 |


