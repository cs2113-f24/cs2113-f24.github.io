---
layout: toc
permalink: /j/2
---

*View all the videos from this unit a [single playlist on youtube](https://youtube.com/playlist?list=PLnVRBITSZMSMKR0ZOuz1AHmFPoD6MrMcy)*

# Polymorphism and Abstract Classes

Recall that there are four basic mechanisms / ideas underlying object oriented programming:

1.   encapsulation
2.   information hiding
3.   inheritance
4.   polymorphism

**Encapsulation** (bundling together data and the functions that operate on that data) and **information hiding** (language mechanisms to enforce the separation of interface from implementation) "solved" the first of our three problems with Procedural Programming not giving us full separation of interface from implementation: the "what to do with structs" problem. 

We've covered both of these over the last two weeks. We've also covered **inheritance** as a mechanism to modify or extend functionality. It solves the second of the three problems with procedural programming not giving us full separation of interface from implementation: modifying or extending functionality without having to access implementation. 

In this lesson, we will cover **polymorphism**, which is the last of the four basic mechanisms/ideas behind object oriented programming. It solves our last "problem": how to allow multiple implementations for the same interface. 


## Inheritance: following "is-a" to its logical conclusion

Let's return the example from [J1 notes](/j/1) of `Points` and `LabPoints`. Recall the two interfaces and their primary functionality:

<div class="side-by-side">
<div class="side-by-side-a">
```java
public class Point {
  private double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return x + " " + y;
  }
}
```
</div>
<div class="side-by-side-b">
```java
public class LabPoint extends Point {
  private String lab;

  public LabPoint(double x, double y, String lab) {
    super(x, y);
    this.lab = lab;
  }

  public String toString() {
    return super.toString() + " " + lab;
  }
}
```
</div>
</div>

For starters, let's consider a simple program involving only `Point` objects. 

```java
import java.util.*;

public class Ex1 {
  public static void main(String[] args) {
    Random rand = new Random(System.currentTimeMillis());
    Point v  = new Point(3, 4);
    Point w  = new Point(5, 2);
    Point u;

    if(rand.nextBoolean())
      u = v;
    else
      u = w;

    System.out.println(u.toString());
  }
}
```
Hopefully you see that what this program does is instantiate two points [(3,4) and (5,2)], and then randomly choose one of the two and assign reference `u` to point to it. You run this program and you might see `3 4` printed out. Run it again, and you might see `5 2` instead. It's random. 

Now, recall that inheritance is supposed to give us an "is-a" relationship, as in "an object of type `LabPoint` is-a `Point`. If that's true, then the variable `u` should be able to point to a `LabPoint` just as much as it can point to a `Point`. So let's try it. The following program, `Ex2.java`, is just like the earlier program, except that the second `Point` `w` we instantiate is actually the `LabPoint` (5,2) with label "A", rather than just the plain-ol' `Point` (5,2). The code compiles, which certainly proves that the compiler is at peace with the prospect of the variable `u` referencing a `LabPoint` rather than a plain-ol' `Point`. 

```java
import java.util.*;

public class Ex2 {
  public static void main(String[] args) {
    Random   rand = new Random(System.currentTimeMillis());
    Point    v    = new Point(3, 4);
    LabPoint w    = new LabPoint(5, 2, "A");
    Point    u;

    if( rand.nextBoolean() )
      u = v;
    else
      u = w;

    System.out.println(u.toString());
  }
```

Now, here's the $1,000,000 question: when you run this program and the random choice is to set `u = w`, what gets printed out? Do I get `5 2` — since `u` is declared as a reference to a `Point` — or do I get `5 2 A` — since the object `u` refers to is actually a `LabPoint`? What do you think? The answer is ... drum-roll please ... `5 2 A`! 

```
~/$ java Ex2
5.0 2.0 A
```

This is actually pretty amazing. "Why?", you ask? Because the the *compiler* didn't know which function was going to be called! It couldn't know, because ultimately which actual method gets executed depends (down to the millisecond) on when the user chooses to execute the program. The random number generator is seeded on the current time. Moreover, from one run to the next, without re-compiling, a different method gets executed. This is truly new. You have never before seen code where you couldn't tell exactly which function/method would execute at a given call site. In this example, it's only at *run-time* that we can determine which function to execute. BTW: The only sane way for this to work (and the way it does work) is for the virtual machine, when it comes to the site of the call `u.toString()`, to check what type of object `u` points to at runtime and allow that to determine which version of `toString()` gets executed.

A call site like this is called polymorphic. The word means "many shapes" and it's trying to get at the idea that many different functions may result from a single call site. Another common term to refer to a call like this is as a *dynamic function call*. A function call for which the actual function to be executed can be determined at compile time is referred to as static, because it is "unchanging" from run-to-run of the program, whereas a call for which the question of which actual function to execute can only be determined as the program executes is called dynamic, because it changes from run-to-run or even from call-to-call within a single program execution. 

Note that polymorphism is a concept applied both at compile time, and at runtime. When compiling, the compiler allows you to set a reference of a parent class equal to a child class object, due to the inheritance relationship the compiler is aware of. At runtime, the runtime-type of the object is looked up in memory to achive *dynamic binding* of the correct method (potentially of the child class). **You'll see some examples in the worksheet where we're asking of how which method is called when polymorphic objects are *passed into* methods (as opposed to methods being called on a polymorphic object reference) -- remember here that this is a compile-time decision.**


## Polymorphism

Suppose we have a class `Class1` and classes `Class2`, `Class3`, ... `ClassK`, that are derived from `Class1`, either directly or through a chain of `extends`. Suppose further that the base class `Class1` defines a method `method(Type1,Type2,...,TypeN)`, and that many of the derived classes override this method. If a variable `var` is declared as a reference to the base class `Class1`, then when the call structure below

```java
Class1 var;

var = new Class3(...);

var.method(arg1,...,argn) // calls Class3's method, not Class1 
```

the actual method that gets executed is based on the type of the object `var` currently references **not** the type in which `var` was declared. So, if `var` currently points to an object of class `ClassI`, then the `ClassI` version of the method is the one that actually gets called. 

The typing of `var` as a `Class1` is still correct. But the inheritance says that all sub-types of `Class1` are *really* a `Class1`, just with "more stuff." So there is nothing wrong with using `var` to reference a `Class2` or a `ClassK`. 

But, the thing that `var` references may not be exactly a `Class1`, so when you use the `.` operator to access a member or method, you're really access the more specific version, namely a `Class2` or `ClassK`. 

This polymorphism of `var` is extremely powerful! It allows you to write generic interfaces, say for example the `toString()` interface for converting an object to a `String` in `println()`, and then each implementation can be used slightly differently without the caller/designer of the interface having having to know the specific type. 

## We are all instances of class `Object`


We can see a slightly more interesting example of a polymorphic function call if I let you in on a little secret: all classes are derived from a class called `Object`. Specifically, when you define a class without the `extends` keyword, that class implicitly "extends" `Object`. So the class `Point` above is an `Object`. Now you can look `Object` up in the [Java API documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html), and you'll see that it has some methods. Most notably for us, it has the method "`toString()`". That means you can call `.toString()` for any object. So let's expand our example: 

```
    Object
      ^
      |
    Point
      ^
      |
   LabPoint

```

And the method `println()` takes advantage of this polymorphism by calling the `toString()` method of all objects passed to it; the implementation of `toString()` depends on the actual type of that instance of the `Object`. The fact that `Object` has a `toString()` method (inherited by all other objects) means this will *always* succeed in printing something.


Using this knowledge, the polymorphism in the following program really comes to the front:

```java
import java.util.*;
public class Ex3 {
  public static void main(String[] args) {
    Random   rand = new Random(System.currentTimeMillis());
    Point    v    = new Point(3, 4);
    LabPoint w    = new LabPoint(5, 2, "A");
    String   x    = "I'm a string";
    Scanner  y    = new Scanner(System.in);

    Object u;
    int i = rand.nextInt(4);

    if(i == 0)
      u = v;
    else if(i == 1)
      u = w;
    else if(i == 2)
      u = x;
    else
      u = y;
    System.out.println(u.toString()); //<--
  }
}
```


<font color=red><h3>Let's take 20 minutes to go through questions 1-5 on the J3 worksheet.</h3></font>

# Casting, Polymorphism and `instanceof`

Polymorphism implies that you might have use a more general type (say an `Point`) to reference a more expressive type (say an `LabPoint`), like in the example above. For example, we know that in `LabPoint` that if we did the following
 
```java

Point p1 = new Point(2, 3);
Point p2 = new Point(1, 5);
LabPoint p3 = new LabPoint(3, 4, "yellow");
Point[] points = {p1, p2, p3};

Point a = points[2]; //a is really a LabPoint
```

We know that `a` is a `LabPoint` because we wrote the code, and we could, technically cast that to a `LabPoint` if we wanted to.

```java 
LabPoint a = (LabPoint) points[2];

LabPoint a = (LabPoint) points[0]; // <---- this will compile, but crash at runtime!
```

But this is quite dangerous, and should feel a bit ugly (because it is). For starters, we just happen to know that this is a `LabPoint` but from `points`' perspective, it sees everything as a `Point`. So this cast is **dangerous** and can cause runtime errors and will sometimes cause compile time warnings, for instance, if you are loopoing through the `points` array without knowing what type of object is actually inside of it at each index. It's also not neccesary because the interface for `Point` is consistent with `LabPoint` and so we can take advantage of polymorphism. 

However, there are times where you will need to know what is the real class-type of an instance object. Java has a method for doing that using `instanceof`

```java
for(int i=0; i < points.length; i++){
  if( points[i] instanceof LabPoint )
    //... do something specific to this class
}
```

While there are some programs where this is unavoidable (we often see this when writing our own `equals()` methods), you should typical try to design your code such that it's fully polymorphic, as in it doesn't matter which of the specific sub-types the instance is. 

# Abstract Classes

Consider that we're developing a program to represent individuals at GW, simplified for now to consider just faculty and students (we know that there are more than just professors and students at GW). We also need to be able to be able to quickly read in a `Person` (e.g., using a `Scanner`), sort all the persons we read in in alphabetic order, and retrieve their email addresses.



Before learning about OOP, you may be tempted to program two classes separately a `GWStudent` class and a `GWFaculty` class, but now we know that we should leverage inheritance to find commonality between professors and students in a super class, perhaps a `GWPerson` class. Like so

```
         GWPerson
         ^     ^
        .'     '.
       |        | 
 GWStudent    GWFaculty
```

This is great! This means we can create array's of `GWPerson`s like so and take advantage of both inheritance and polymorphism in the code we design

```java
GWPerson persons[] = new GWPerson[10];
persons[0] = new GWStudent(/*...*/);
persons[1] = new GWFaculty(/*...*/);
//...
//sort persons using before method.
```

But let's think about this for a second; would we ever instantiate a `GWPerson` object? No. The whole universe at GW are students and professors. Yes, they are both `GWPerson`s but each person must be either a student or professor. 

Moreover, there is going to be some functionality that doesn't make sense to implement in the `GWPerson` class because it will have to be overwritten in the sub-class. For example, consider a method `email()` which returns the person's email. This is wholy dependent on if the person is a student or a professor. 

> Note that a student's email address is `@gwmail.gwu.edu` and a faculty email address is `@email.gwu.edu`. 

There is no reason to then implement `email()` at all at the `GWPerson` level, but instead it must be implemented in the subtype, specific to students or professors. We don't know the domain of the email until we know which sub-class of `GWPerson` (either student of faculty). 

While it may be impossible to implement an `email()` method, there are other possible  methods of `GWPerson` that should be common to a `GWStudent` and `GWFaculty`. For example, a `fullname()` method that prints the full name (first and last together) is the same for everyone. 

As such, we have a super class, `GWPerson`, that is both obvious in that it should be implemented and included in the class hierarchy, but has features that cannot actually be implemented. Additionally, a `GWPerson` object should never need to be instantiated ... so what really is `GWPerson` in this class hierarchy?

## Abstract GWPerson Class

In Java, we have a notion of **abstract classes** to solve this very problem. It allows the program to describe a class that fits into a class hierarchy for inheritance and code-reuse, e.g., `GWPerson`, but should never actually be instantiated because some features/methods cannot exist until properly defined in a sub class.

To indicate that a class or method is "abstract" we use the `abstract` keyword to note it when declaring the class, and also in which methods are `abstract`. Like below.

Also, recall the `protected` modifier (as opposed to `public` or `private`); a `protected` field or method means that an child class of the class "sees" that item, but other classes that are not subclasses cannot. This includes any code *outside* of the subclass trying to access the item through the subclass object: this is forbidden. `protected` also means the item will be visible, in addition to the subclass, to another other class in the parent class' package.

```java
public abstract class GWPerson { //note GWPerson is abstract !
    protected String fname;
    protected String lname;
    protected String GWID;
    protected String uname;

    public GWPerson(String fname, String lname, String GWID, String uname);
    
    //these methods can be same for all persons but perhaps overwritten
    //to include more specifics in subclasses
    public boolean before(Person p){...} //returns True if this "before" p in
                                     //alphabetic ordering
    public String fullname() {...}
    public String toString() {...}


    //this method cannot be defined here and must be implemnted in the subclass
    public abstract String email(); //abstract method

}
```

To see the full implementation, click below.
* <a href="javascript:void(0)" onclick="$('.gwperson-java').toggle('slow')">GWPerson.java </a> 
<div class="gwperson-java" style="display:none">
```java
import java.util.*;

public abstract class GWPerson {
    protected String fname;
    protected String lname;
    protected String GWID;
    protected String uname;

    public GWPerson(String fname, String lname, String GWID, String uname) {
        this.fname=fname;
        this.lname=lname;
        this.GWID=GWID;
        this.uname=uname;
    }

    public boolean before(GWPerson p) {
        //last name not the same
        if(! this.lname.equals(p.lname)){ 
            return this.lname.compareTo(p.lname) < 0;
        }
        //first name not the same
        if(! this.fname.equals(p.fname)){
            return this.fname.compareTo(p.fname) < 0;
        }
        //first and last name the same, compare GWID's
        return this.GWID.compareTo(p.GWID) < 0;
    }

    public String fullname() {
        return this.fname + " " + this.lname;
    }

    public String toString() {
        return this.lname + ", " + this.fname + "(" + this.GWID + "," + this.uname + ")";
    }

    public abstract String email();
}
```
</div>

Declaring a class `abstract` means that this class cannot be directly instantiated. For example, the following code **will not compile**:

```java
GWPerson p = new GWPerson(/*...*/);
```

That's because `GWPerson` is simply an abstract representation of a person and not fully implemented. Only (non abstract) subclasses of `GWPerson` instantiate a person (via polymorphism). For example,


```java
GWPerson p = new GWStudent(/*...*/);
```

In this case, `GWStudent` is-a `GWPerson` as it will `extends` the `GWPerson` class. The same will be the case for `GWFaculty` class. 

## Extending an Abstract GWPerson Class

When you extend an abstract class, the Java compiler requires you to finish the implementation of that class. In this case, we must overwrite the method `email()`. If we were to implement a `GWStudent`, we must implement the abstract methods, including the `email()` method and the `read()` method. If you do not, the compiler provides the following warning:

```
GWStudent.java:1: error: GWStudent is not abstract and does not override abstract method email() in GWPerson
public class GWStudent extends GWPerson{
       ^
1 error
```

Once we do implement `email()`, implementing `GWStudent` and `GWFaculty` are relatively straight forward

```java
import java.util.*;

public class GWStudent extends GWPerson {
    protected int classYear; //e.g., class of 2024

    public GWStudent(String fname, String lname, String GWID, String uname, int classYear) {
        super(fname, lname, GWID, uname);
        this.classYear = classYear;
    }

    //implmenting abstract method
    public String email() {
        return uname + "@gwmail.gwu.edu";
    }


    //overwriting super method
    public String toString() {
        return super.toString() + " -- Class of "+this.classYear;
    }

    //adding new static method
    public static GWStudent read(Scanner sc) {
        String fname = sc.next();
        String lname = sc.next();
        String GWID = sc.next();
        String uname = sc.next();
        int year = sc.nextInt();
        return new GWStudent(fname, lname, GWID, uname, year);
    }


}

```

```java
import java.util.*;

public class GWFaculty extends GWPerson {
    protected String dept; //department

    public GWFaculty(String fname, String lname, String GWID, String uname, String dept){
        super(fname, lname, GWID, uname);
        this.dept = dept;
    }

    public String email() {
        return uname + "@email.gwu.edu";
    }

    public String toString() {
        return super.toString() + " -- " + this.dept;
    }

    //adding new static method
    public static GWFaculty read(Scanner sc) {
        String fname = sc.next();
        String lname = sc.next();
        String GWID = sc.next();
        String uname = sc.next();
        String dept = sc.nextLine();
        return new GWFaculty(fname, lname, GWID, uname, dept);
    }

}
```


<font color=red><h3>Let's take 10 minutes to go through questions 6-7 on the J3 worksheet.</h3></font>

## Leveraging Abstract Classes

Abstract classes provide a way to guarantee a subclass of the given class implements all the necessary functionality (i.e., the abstract methods). That means, you can program using the abstract class in a polymorphic way with the expectation that you'll have those methods available to you. 

For example, consider this program that reads in information about students and faculty, sorts them, and prints it out in a nice format. 

```java
import java.util.*;
public class ReadPersons {

    public static void main(String args[]) {

        GWPerson persons[] = new GWPerson[100];

        Scanner sc = new Scanner(System.in);

        //read in students and faculty
        int n = 0;
        while(n < 100){
            System.out.println("Select:\n"+
                               "(s) Student\n"+
                               "(f) Faculty\n"+
                               "(d) Done");
            String opt = sc.next();
            if(opt.equals("s")) 
                persons[n++] = GWStudent.read(sc);
            else if(opt.equals("f"))
                persons[n++] = GWFaculty.read(sc);
            else if(opt.equals("d"))
                break;
            else{
                System.out.println("Unknown option");
                continue;
            }
            System.out.println("");
        }

        //before a bubble sort
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(!persons[i].before(persons[j])) {
                    GWPerson tmp = persons[i];
                    persons[i] = persons[j];
                    persons[j] = tmp;
                }
            }
        }

        System.out.println("");
        System.out.println("-----");
        System.out.println("");

        //print out the info
        for(int i = 0; i < n; i++){
            System.out.println(persons[i] + " -- " + persons[i].email()); 
        }
        
    }
}
```

Importantly, note that we are able to refer to `persons` array of type `GWPerson[]` despite `GWPerson` being an abstract class. The only instances of `GWPerson` are either `GWFaculty` or `GWStudent`, and the functionality differences rely on *both* polymorphism and inheritance.

For example, with the following inputs:
```
s John Smith G12245 jsmith 2021
s Yang Li G22213 yli 2023
s Taylor Swift G3123565 tswift 2024
s Priyanka Chopra G123052 pchopra 2025
f Adam Aviv G8182309 aaviv Computer Science
f Pablo Frank-Bolton G0731345 pfrank Computer Science
s Harry Styles G120345 styles 2026
d
```

We would get the following output from this program

```
Aviv, Adam (G8182309,aaviv) --  Computer Science -- aaviv@email.gwu.edu
Chopra, Priyanka (G123052,pchopra) -- Class of 2025 -- pchopra@gwmail.gwu.edu
Frank-Bolton, Pablo (G0731345,pfrank) --  Computer Science -- pfrank@email.gwu.edu
Li, Yang (G22213,yli) -- Class of 2023 -- yli@gwmail.gwu.edu
Reynolds, Ryan (G123052,rrey) -- Class of 2025 -- rrey@gwmail.gwu.edu
Smith, John (G12245,jsmith) -- Class of 2021 -- jsmith@gwmail.gwu.edu
Styles, Harry (G120345,styles) -- Class of 2026 -- styles@gwmail.gwu.edu

```

<font color=red><h3>Let's take 25 minutes to go through the rest of the questions on the J3 worksheet.</h3></font>


---
*Material from this unit was adopted and/or derived from USNA ic211 (spring 2019)*


