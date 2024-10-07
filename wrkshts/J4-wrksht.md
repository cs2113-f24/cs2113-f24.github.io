---
layout: worksheet
permalink: /worksheet/j4
showsolution: false
---

# Worksheet: J4

Worksheets are self-guided activities that reinforce lectures. They are due Thursdays the week they are assigned.

Please submit your answers to the questions as comments in a `J4.md` markdown file you'll be writing in this lab. To render your file, create a github repo and upload your file there -- it can be viewed in your web browser.

# Grading rubric and submission

When you are done, submit your `J4.md` file to BB.

 You will be graded on the following:

|Item | Points |
|Answers are completed (for content)  | 100 |


## Questions

### q

Consider the class declaration below:

```java
// Is this possible?
public class Lion extends Mammal, Carnivore {
    // ...
}
```

* Explain why the following class declaration is not possible in Java. 
* What are the limitations of the  `extends` key word?
* How can you accomplish this inheritance structure task in Java?

#### s

Java classes can only extend from one class! So we can have `Lion extends Mammal` or `Lion extends Carnivore`, but not both. To accomplish functionality similar to this, we would have:

```java
public class Lion extends Mammal implements Carnivore {
    // ...
}
```
As it makes sense for an animal to inherit from the specific class it is (Mammalia), and for it to have an interface that represents how it interacts (or interfaces) with food.

### q

What are some of the functional differences between an `abstract class` and an `interface`? Use the example below to answer this question.

```java
public abstract class Employee {
    // ...
}

// vs.

public interface Employee {
    //...
}
```



#### s
Abstract classes and interfaces can:

* Provide _just_ method declarations that a class must realize

Abstract classes do (and interfaces do not):

* Provide methods and constructors a child class can choose to use, or override
* Provide inherited class variables
* Have access modifiers for inherited methods
* Require the child class to inherit only itself, and no other class


### q

Consider the interfaces for a `Stack` and `Queue` of `int`s. 
```java
public interface Stack {
   public void push(int v);
   public int pop();
   public int peek();
}

public interface Queue {
   public void enqueue(int v);
   public int dequeue();
   public int peek();
}
```


Now suppose you had a `LinkedList` implementation to store `int`s with the following methods defined. 

```java
public class LinkedList implements Stack, Queue {
  public LinkedList() {/*...*/}
  public void addToFront(int v) {/*...*/}
  public int rmFromFront() {/*...*/}
  public void addToBack(int v) {/*...*/}
  public void rmFromBack() {/*...*/}
  
  //FINISH HERE
  
}
```

Using those methods in `LinkedList` complete the realization of a `Stack` and `Queue`:

#### s


```java

public void push(int v) { 
    addToFront(v);
}
public int pop() { 
    rmFromFront(v);
}
public void enqueue(int v) {
    addToBack(v);
}
public int dequeue() {
    rmFromFront(v);
}
public int peek() {
    int v = rmFromFront(v); 
    addToFront(v); 
    return v;
}
```


### q

Rewrite the `Stack` and `Queue` interfaces from above to be generic, as well as the `LinkedList`. Explain how this is now generic to manage collections of any class. 

#### s

```java
public interface Stack<T> {
   public void push(T v);
   public T pop();
   public T peek();
}

public interface Queue<T> {
   public void enqueue(T v);
   public T dequeue();
   public T peek();
}

public class LinkedList<T> implements Stack<T>, Queue<T> {

  public LinkedList() {/*...*/}
  public void addToFront(T v) {/*...*/}
  public T rmFromFront() {/*...*/}
  public void addToBack(T v) {/*...*/}
  public void rmFromBack() {/*...*/}

  public void push(T v) { 
    addToFront(v);
  }
  public T pop() { 
    rmFromFront(v);
  }
  public void enqueue(T v) {
    addToBack(v);
  }
  public T dequeue() {
    rmFromFront(v);
  }
  public T peek() {
    T v = rmFromFront(v); 
    addToFront(v); 
    return v;
  }
}
```


### q

The code below does not use Java generics. Update it to do so. Notably, there should not need casting, but no, the solution isn't just removing the `(String)` casting before the `.get` method. 

```java
import java.util.HashMap;

public class TestHashMap {

    public static void main (String[] argv) {
        // Create a new hashmap.
        HashMap fabFour = new HashMap();

        // Insert four key and value pairs.
        fabFour.put("John", "John Lennon");
        fabFour.put("Paul", "Paul McCartney");
        fabFour.put("George", "George Harrison");
        fabFour.put("Ringo", "Ringo Star");

        // Use a key to retrieve a value.
        String fullName = (String) fabFour.get("Ringo");

        // Prints "Ringo Star"
        System.out.println(fullName);
    }
}
```

#### s

```java
public class TestHashMap {

    public static void main(String[] argv) {
        // Create a new hashmap.
        HashMap<String,String> fabFour = new HashMap<String,String>();

        // Insert four key and value pairs.
        fabFour.put("John", "John Lennon");
        fabFour.put("Paul", "Paul McCartney");
        fabFour.put("George", "George Harrison");
        fabFour.put("Ringo", "Ringo Star");

        // Use a key to retrieve a value.
        String fullName = fabFour.get("Ringo");

        // Prints "Ringo Star"
        System.out.println(fullName);
    }
}
```



### q

What is "Erasure" with java generics? 

For the code below, what does the code "erase" to? 

```java
 public static void main(final String args[]) {
        Shelf<String> favorite_words = shelfBuilder();
        favorite_words.addItem("Zoetrope");
        favorite_words.addItem("Succinct");
        //...        
        String s = favorite_words.getItem(1);
        System.out.println(s);
    }
```

#### s
The Java runtime actually doesn't know anything about generics, when you compile
a Java program with generics in them, at compilation time all "generic" types
are replaced with what they should be. Essentially, when we use a generic, at
compilation time the compiler essentially changes the `Shelf` class to only use the `String` type, so wherever we see a `T` it's replaced with `String`. From there, every time we do something that should return as a specific type `T`, we replace with a cast to that specific type. 

We see this program then erases to:

```java
 public static void main(final String args[]) {
        Shelf favorite_words = shelfBuilder();
        favorite_words.addItem((String)"Zoetrope");
        favorite_words.addItem((String)"Succinct");
        //...        
        String s = (String)favorite_words.getItem(1);
        System.out.println(s);
    }
```


### q

Finish the `main` method in the `TestShelf` class above.

Expected output:
```
Shakespeare Characters: Hamlet Othello Cordelia Juliet
Famous Integers: 13 23 42 1729
```

```java
import java.util.ArrayList;
import java.util.List;

public class Shelf<T> {
    private List<T> shelfItems;

    private String shelfName;

    public Shelf(String shelfName) {
        this.shelfName = shelfName;
        shelfItems = new ArrayList<T>();
    }

    public int addItem(T item) {
        shelfItems.add(item);
        return shelfItems.size();
    }

    public void printShelf() {
        System.out.print(shelfName + ": ");
        for(T item: shelfItems) {
            System.out.print(item.toString() + " ");
        }
        System.out.println();
    }
}
```

```java
public class TestShelf {
    public static void main(final String args[]) {

        // TODO: Create a shelf to store Shakespeare character names:
        //       Hamlet, Othello, Cordelia, and Juliet
        // TODO: Then print the shelf.


        // TODO: Create a shelf to store famous integers:
        //       13, 23, 42, 1729,
        // TODO: Then print the shelf.


    }
}
```


#### s
```java
    public static void main(String[] args) {
        Shelf<String> shakespeare = new Shelf<>("Shakespeare Characters");
        shakespeare.addItem("Hamlet");
        shakespeare.addItem("Othello");
        shakespeare.addItem("Cordelia");
        shakespeare.addItem("Juliet");
        Shelf<Integer> integers = new Shelf<>("Famous Integers");
        integers.addItem(13);
        integers.addItem(23);
        integers.addItem(42);
        integers.addItem(1729);
        shakespeare.printShelf();
        integers.printShelf();
    }
```

### q 

Consider the following code snippets for a `LinkedList` you may implement and a main method:

```java
public class LinkedList {
   private class Node {
      int data;
      Node next;
   }
   Node head;

   void add(int data);
   int get(int idx);
   //...   
```

```java
public class TestingLinkedList {
  public class static main(String args[]) {
     LinkedList ll = new LinkedList();
     
     for(int i = 0; i < 100000; i++){
         ll.add(i * 3);
     }
     
     for(int i = 0; i < 100000; i++){
         System.out.println("" + ll.get(i)); //<-- MARK
     }
  }
}

```

Explain why the line with `MARK` is extremely inefficient? Use Big-O to explain.

#### s
A linked list has a search time of `O(n)`, since you must iterate through the
whole list to know if something is in it or not. Therefore, the for loop that
contains the marked line will have a cost of `O(n)` every single call, and it
will be called once for each item. This means the total runtime cost will be
`O(n^2)`, which is not good :(.

### q

Continuing with the example above, explain why expanding `LinkedList` to implement `Iterable` solves the inefficiency problem you described above. 

#### s

`Iterable` will keep track of where we are in the linked list, essentially holding a pointer to the last item we were at. This means everytime we get the next item, it'll just return `next` of the current location, and move the pointer forward to the next item. This results in a loop that is only `O(n)`, as we only see every item at most once.


### q

Explain why the `Comparable` interface is an interface rather than class?

#### s

The only thing we want for something to be comparable is a function that tells us if one of it is greater than the other. That means we just want the class to define one function and thats it. We could do this using an abstract class, but we wouldn't use any of the other functionality an abstract class provides, and then the class couldn't extend any other class either, so we'd be using something with more power than we need, and limiting our options in the future. So instead we just use an interface.

### q

Add the `compareTo` method in the `Car` class above. So that the main method will print out:

```
Name: Lamborghini Top Speed: 225
Name: Porsche Top Speed: 202
Name: Mustang Top Speed: 144
Name: Jeep Top Speed: 110
```


```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car implements Comparable<Car> {
    public static void main(String[] args) {
        List<Car> carsList = new ArrayList<>();
        carsList.add(new Car("Porsche", 202));
        carsList.add(new Car("Jeep", 110));
        carsList.add(new Car("Mustang", 144));
        carsList.add(new Car("Lamborghini", 225));

        Collections.sort(carsList);
        for(Car car : carsList) {
            System.out.println("Name: " + car.getName() + " Top Speed: " + car.getTopSpeed());
        }
    }
    private String name;
    private Integer topSpeed;

    public Car(String name, Integer topSpeed) {
        this.name = name;
        this.topSpeed = topSpeed;
    }

    public String getName() {
        return name;
    }

    public Integer getTopSpeed() {
        return topSpeed;
    }

    // TODO: Complete the Car class by adding the compareTo method
    //       needed to correctly implement Comparable<Car>.

}

```


#### s
```java
	public int compareTo(Car other) {
		return this.topSpeed - other.topSpeed;
	}
```

