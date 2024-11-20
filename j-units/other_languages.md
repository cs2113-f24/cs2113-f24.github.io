---
layout: toc
permalink: /j/languages
---


# Programming in Other Languages -- DRAFT, currently updating these notes

Although this course has focused on the Java programming language to illustrate object oriented and software engineering concepts this semester, recently it has not been the [most popular programming language people use](https://spectrum.ieee.org/top-programming-languages-2024) (or that employers are looking for). Python is currently dominating.

Now that you know a decent amount of Java (and some C/C++ from your systems programming course), we are going to take a moment to wrap up the semester by discussing 1) when and why you would choose one programming language over another and 2) take a deep dive into OOP in python.


# A little bit about python

Python is originally a scripting language, which means that it's very easy and quick to write code in it. Compare the Hello World idiom in Java:

```java
public class MyHWExample {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}
```

to the same code in python:

```python
print("Hello World!")
```

Which do you want to code in? 

So why are we "bothering" with Java? The answer is that Java has a lot more robust OOP features than python (which we'll cover below), among other benefits. Sometimes, depending on what you want to program, using Java will result in fewer errors, easier to maintain code, etc.

# What programming language should I use?

There is no "best" programming language; the best programming language for you to use depends on many things! Many of these are tradeoffs. We'll review some of those considerations below.

## Ease of use 

We just saw how much quicker it is to write Hello World in python, versus Java. If you don't need any of the benefits of a heavy-duty OOP language, like Java, in the programming project you're working on, you can consider using python. Often, it's quicker to write code in the latter: the designers of python included lots of syntactic sugar (i.e. shortcuts) to be able to write complex functionality in a single line of code:

```python
print('#'.join(map(str, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])))
```

The code above uses a single line to add a `#` between every integer in that list and turn the result into a string. In Java, it might look like this:

```java
int[] array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
String arrayString = "";
for(int i = 0; i < array.length; i++)
    arrayString += array[i] + "#";
System.out.println(arrayString);
```

There's a shorter way to do this in Java too, such as 

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
String joinedList = list.stream().map(String::valueOf).collect(Collectors.joining("#"));
```

But not only is that two lines of code -- it is arguably a lot more confusing than the python version (for a novice), plus it requires knowing several libraries.

Even something as simple as adding an element to the end of an array is different between these two languages, in terms of syntax. For example, in python you might do:

```python
intList = [1, 2, 3]
intList.append(4)
```

Above, we can just call the `append()` method to add 4 to the intList in python. However, if we tried to mimic the code above in Java, it could look like:

```java
int[] array = {1, 2, 3};
int[] newArray = new int [4];
for (int i = 0; i < array.length; i++)
    newArray[i] = array[i];
newArray[array.length] = 4;
```

This is because once you declare an array of a specific size, you can't update that size. That's probably one of the reasons we end up using `ArrayList` instead, but even that code isn't so clean:

```java
ArrayList<Integer> array = new ArrayList<Integer>();
array.add(1);
array.add(2);
array.add(3);
array.add(4);
```

There is, once again, a shorter way to do this in Java, but one that is arguably still longer and less intelligible than in python:

```java
int[] intArr = { 1, 2, 3};  
List<Integer> list = Arrays.stream(intArr).boxed().collect(Collectors.toList()); 
list.add(4);
```

Unlike Java, python also lets you return more than one item in a return statement. For example, you could write a method to return the min and max of a list, and call it as:

```python
smallest, largest = getMinAndMax([3, 1, 4, 6, 33, 2, 76, 3, 8, 19])

```

For more in-house details about the basics of python, you can check out [CS 1012's python tips](https://www2.seas.gwu.edu/~cs4all/1012_f23/pythontips.html).

## Efficieny and utility of code

So far, it might always seem preferable to use python rather than Java. In part, that's because we haven't talked about OOP yet between these two languages. But the previous example regarding adding an element to the end of a list of integers didn't touch on another important topic in choosing a programming language: runtime efficiency.

Although adding to the end of an `ArrayList` in Java is just as simple as doing the same in python (`.add()` versus `.append()`), what's really going on under the hood? 

In the `ArrayList` example above, it turns out that an `ArrayList` actually uses a primitive array to store the elements: this is not obvious to the user, and with good reason. We shouldn't need to worry about the implementation level details of these classes...except when runtime efficiency might be called into question. For example, the `ArrayList` constructor could always create a primitve Java array with a size of ten, and then add to that as many times as the user wants, until it is full. When that happens and the user wants to add a new element, it might double the current size of the array, copy over the elements, and then add in the new one. That step is majorly inefficient! And the alternative: assigning huge empty arrays of thousands of elements is also wasteful. 

If one really cared, at least using a primitve array in Java (as opposed to an `ArrayList`) makes these costs explicit. However, Java, compared to a language like C/C++, is not known for its runtime efficiency. 

### Runtime efficiency and predictability

Java is a high level programming language not only because it tends to read like English, but also because it simply doesn't allow the user to directly access memory. While you can call a constructor, you don't get to decide when an object that is no longer in use (because no variable points to it anymore) is freed: the garbage collector does this for you. For those of you that have programmed in C/C++, you probably appreciate Java's garbage collection to no end: it frees developers of having to manually manage their own memory, and therefore, also obviates an entire class of errors: memory leaks and dereferecing invalid pointers. What's not to love?!

It turns out, sometimes one needs to have fine-grained manual control over memory allocation and deallocation: Java is not the language for this. For instance, if you were writing code for an ultradependable system (i.e. something that could go into the control system of a nuclear power reactor), you need to be able to *predict* when certain code will run; garabage collection doesn't allow you to do this. In fact, it's not uncommon (at least in the past) that Java's garbage collection would run at the worst time: when free memory was scarce because a program was using a lot of it to do a lot of computations, this is the exact time Java might pause the busy program to free up space. If things didn't work out, this might look like the program was "hanging" to the user. 

We don't want the code in a nuclear power plant to appear to hang...so we can use a language like C to allow us to allocate and deallocate memory in a fine-grained, predictable fashion...but we also now have to be careful about memory leaks and dangling pointers. Always a tradeoff :-) 

By allowing us to more directly manipulate memory, a language like C/C++, unlike Java and python, is useful for coding up things like operating systems. Being lower level with respect to this control around memory manipulation, a language like C/C++ is also useful for things that need to run quickly, like machine learning libraries that train large models on a GPU.



## Ease of understanding

Runtime efficieny is often not the most important concern in a lot of modern programming. Compared to the 1980s, you have a lot of memory on your computers. Consider that the custom of having file extensions being three or four characters long came from needing to save memory back in the day; this number is otherwise arbitrary! In fact, runtime efficiency (i.e. C) often comes at the cost of the ease of writing and debugging code.

If being extremely memory efficient is not one of your concerns, one can argue that it's much more important to write understandable and maintainable code, versus code that runs very fast on the CPU. For example, it turns out that function/method calls are relatively expensive, given that you need to add a stack frame to memory for the function call, set up the arguments, delete the frame when done, and then return to the previous stack frame. There's someting called *inlining* that allows you to perform the same functionality without the explicit method call. For a basic example,

```java
public String sum(int x, String y){
    return x + y;
}
int x = 3;
String z = sum(x, "5");
```

is always going to be more inefficient (in terms of memory and CPU cycles) than rewriting it without a function call:

```java
int x = 3;
String z = x + "5";
```

Fortunately, in this case the simple addition is also easier for a human to understand. Ironically, it's easier to write in Java than in python:

```python
x = 3
z = str(x) + "5" 
```

because we don't need to manually convert `x` to be a String; Java does this for us with more syntactic sugar. 

While Java is notoriously verbose, in general, compared to python (remember the number of lines it takes to print out `Hello World`), sometimes we can have too much of a good thing. For example, what do the lines of code below do?

```python
[print(i+j,end=" ") for i,j in enumerate([x+int(x*1.5) for x in range(2,19)])]
print(":)")
```

First, you get a different result if you run this in the python interpreter, versus running it as a file (because a `print` statement in python actually returns `None`). If you run each line above, individually, you get a different answer than running them together. Gross! For the latter, the output is:
`4 9 14 19 24 30 36 43 49 56 63 70 78 86 94 102 110 :)`

Best of luck to you understanding how that happened if you're a python novice. Here's a more digestible way to write the same code:

```python
result = ""
i = 0
for x in range(2, 19):
    j = x + int(x**1.5)
    result += str(i + j) + ' '
    i += 1
print(result + ":-)")
```

Which one is better depends on what you and your team finds more easy to understand; if you're working with a lot of folks new to python, they'll likely understand the latter much more easily. If it's hard to read, it might be hard for you to read a year later if you look at your code, and it might be hard for a more novice programmer to understand who you have to work with in the future. If it's short and dense, it might be easier to miss simple mistakes -- did you catch the bug in the first version? (Hint: it was doing multiplication, not raising to a power).

Sometimes, well-meaning students try to write the most "efficient" code possible, at the expense of readability or comprehensibility. This kind of micro-efficiency, where these folks saving a few milliseconds of time when you run this code, but at what cost? It's often better to write code that might take a tiny bit amount of more time to run (especially when no one will notice this...) than to write the most "efficient" code. The more legible and maintainable code is easier to debug. 

For example, take a look at this entire Wikipedia article discussing the [fast inverse square root](https://en.wikipedia.org/wiki/Fast_inverse_square_root), which was a good approximation for a necessary calculation used in rendering a video game from the late 1990s. Knowing how to take the inverse square root is useful for rendering graphics to compute angles of incidence, and simlute lighting (for example). We're dealing with vectors here (because of 3D modeling), so the inverse square root formula is:

![inverse_square_root](https://wikimedia.org/api/rest_v1/media/math/render/svg/d77e977afe2ff3fd5ac602d96707d59bda83c08a)

The three V-s represent vector coordinates. Here is what this code might look like in Java as a method for the inverse square root of a number:

```java
public float Slow_rsqrt(float number){

    return 1 / Math.sqrt(x); // expensive division operation!
}
```

Usually, the inverse square root calculation, defined mathematically as `1 / sqrt(x)` , was done using division operations of floating point numbers, but floating point division was very computatoinally expensive on the hardware (CPUs) back then. The *fast* inverse square root was a good approximation of this needed calculation and bypassed the expensive division step. Here is the code for the fast inverse square root algorithm (directly from the wiki above):

```c
float Q_rsqrt( float number )
{
    long i;
    float x2, y;
    const float threehalfs = 1.5F;

    x2 = number * 0.5F;
    y  = number;
    i  = * ( long * ) &y;                       // evil floating point bit level hacking
    i  = 0x5f3759df - ( i >> 1 );               // what the fuck?
    y  = * ( float * ) &i;
    y  = y * ( threehalfs - ( x2 * y * y ) );   // 1st iteration
//  y  = y * ( threehalfs - ( x2 * y * y ) );   // 2nd iteration, this can be removed

    return y;
}
```

Wiki explains the code above as:
"The algorithm accepts a 32-bit floating-point number as the input and stores a halved value for later use. Then, **treating the bits representing the floating-point number as a 32-bit integer**, a **logical shift right by one bit** is performed and the result **subtracted from the number 0x5F3759DF**, which is a floating-point representation of an approximation of `sqrt(2^127)`.[3] This results in the **first approximation of the inverse square root** of the input. Treating the bits again as a floating-point number, it **runs one iteration of Newton's method**, yielding a more precise approximation."

Friends, we are doing all kinds of nastiness with, I kid you not, an actual *magic number*. Fortunately, the code above was eventually made obsolete when hardware manufacturers added new functionality to their CPUs. But before that, sometimes you had no choice but to sacrifice comprehensibility for efficiency. Even in modern times, there are certainly instances where runtime efficiency and memory efficiency are important, and you need to worry about them. One example are embedded systems, such as the code running on very limited battery power and memory space in something like a remote sensor. Training a memory- and power-hungry LLM on such a tiny remote sensor is all but hopeless. In these cases, it's wise to potentially sacrifice some comprehensibility to meet the efficiency requirements.

### Compiled versus interpreted code.

Speaking of runtime efficiency, the compiler is often your friend. Compiled code generally executes faster than interpreted code, but why? In both scenarios, we are translating the high-level language (Java, python, etc) into, ultimately, machine-level instructions (i.e. 64-bit sequences of zeroes and ones that are fed directly into the CPU via registers). In interpreted code, the python program, for example, will read python instructions line-by-line (on the fly) and translate these "individually" into machine instructions.

This works, but it's not as efficient as if the computer was allowed to see the entire program as a whole, analyze it, and optimize the machine-level instructions it wants to run on the CPU (or, in the case of Java, the low-level bytecode that is later translated into machine code). In a compiled language like Java, looking at all the code together, rather than just translating it line-by-line, can result in faster running code. For example, a compiler can do things like *branch prediction* and *loop unrolling* that we won't get into here, but make the code that actually runs on the hardware need fewer instructions. We can't really do certain kinds of speed optimizations without a compiler.

But, again, why do we care about speed in the modern computing era? Well, there are instances, similar to the horror of the fast inverse square root algorithm above, where even a small mathematical operation, when done billions of times, can become expensive overall. There's a reason we don't write machine learning libraries that train deep learning models in Java :-)

## Ease of debugging

Compiled languages, in addition to often being more efficient on the CPU than interpreted languages, are also often easier to debug.

### Static typing vs dynamic typing

Putting execution speed aside for a moment, there are other advantages related to compilation that come into play when choosing a programming language: whether your language is statically or dynamically typed. Statically types languages, like Java, force you to declare your types at compile time. While this is more verbose, it does allow the compiler to catch type mismatches before you are ever allowed to run your code.

By contrast, in a dynamically typed language like python, the type of an assignment is determined at runtime. If I do `x = 5` then at that time x is an integer, while later if I do `x = "yellow"` then `x` becomes a string. Less typing for the developer and more flexibility -- what's not to love? It turns out that it is **very easy to make mistakes** in a language with dynamic typing. There is no compiler to keep you safe. For instance, if I'm working with fMRI data, which measures bloodflow in the brain and stores these values in a 3-dimensional array of voxels, it is very easy to forget if some method in a library I wrote months ago returns a 1D slice of this data, or a 2D slice of this data (or both!). Normally, in Java, the compiler would catch if you are assigning mis-matched types like this unintentionally, but python does not. An entire day wasted tracing down such a bug; ask me how I know.

However, writing code is python is still faster than Java, in part, because you don't have to declare your types up front. 

# Popularity and support

In addition to all these tradeoff we just discussed, sometimes we end up using a language for a task simply because it's popular. For instance, you will often see python used for deep learning (i.e. PyTorch, Tensorflow), at least at the everyday practictioner level. Recall the machine learning libraries actually doing the math to learn the weights in these large models are probably running C++/CUDA (Nvidia's language for programming GPUs), which is much faster than python. But, python became popular for machine learning coding, probably, in part, because it's very easy for people, especially non-CS majors, to learn how to code in it. 

Java, another popular language, became a household name in the 1990s, in part, because it can be used to make GUIs with its object-oriented support. C/C++, on the other hand, has been around for a longer time and is often used where performance and the ability to interface with memory at a lower level is important, like operating systems.

What other common languages are there that you might see in your career?
* Javascript: a language used for client-side web programming, and the source of endless nightmares and [internet memes](https://www.freecodecamp.org/news/explaining-the-best-javascript-meme-i-have-ever-seen/)
* SQL: a programming language for querying relational database tables, and a potentially humbling experience for those of used to procedural languages
* C#: a language used to program for the .NET framework (runs on Microsoft Windows)

# Python!

As you can see, python is currently perhaps the most popular programming language out there. We've already discussed some of its tradeoffs against a language like Java, but we haven't talked about OOP in python. We'll do that here as a great illustration of why to potentially use Java if OOP is important to you.

## Object Oriented Programming in python

Python, like Java, does support the definition of classes and subsequent creation of objects. For example, here is what a person class might look like in Java:

```java
public class Person {

    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return name + " " + age;
    }

    public void sayHi(String other){
        System.out.println("Hello " + other + ", my name is " + name);
    }


}
```

In python, this would get translated to:

```python
class Person:
  def __init__(self, name, age):
    self.__name = name
    self.__age = age

    def __str__(self):
        return self.__name + " " + self.__age

    def sayHi(self, other):
        print("Hello " + other + ", my name is " + self.__name)
```

So far, so good; despite some minor differences in syntax, both classes have private visibility (indicated by `__` in python fields) and give us a constructor and `toString()`. But all is not as it seems! It turns out that the `__` is just a *convention* in python, and there is nothing preventing someone from accessing this "private" field anyway:

```python
vinesh = Person("Vinesh", 33)
vinesh.__age = -2 # doom!
```
It gets worse. Python doesn't have proper support for `static` fields; if we wanted to have a static `planet` field in Person, we might do:

```python
class Person:
  planet = "Earth"

  def __init__(self, name, age):
    self.__name = name
    self.__age = age

    def __str__(self):
        return self.__name + " " + self.__age

    def sayHi(self, other):
        print("Hello " + other + ", my name is " + self.__name)
```

but then you'll find this kind of behavior:

```python
vinesh = Person("Vinesh", 33)
lisa = Person("Lisa", 26)

print(vinesh.planet) # prints "Earth"
print(lisa.planet) # prints "Earth"

Person.planet = "Venus"

print(vinesh.planet) # prints "Venus"
print(lisa.planet) # prints "Venus"


vinesh.planet = "Mars"
print(vinesh.planet) # prints "Mars" 
print(lisa.planet) # prints "Venus" # why?!?!?!??!
```

We could continue on with even more OOP shortcomings in python compared to Java, but that's left as an exercise to the reader. TLDR: if you would benefit from a solid OOP foundation where the compiler can perform all kinds of desirable checks for you, python is probably not the language for you.

## But...DataFrames

Although we just said not to rely on OOP featurs if you're using python, many people are using python to injest data for machine learning. For instance, a library called `pandas` has a class called a `DataFrame` which is the workhorse of many a machine learning application. A [`DataFrame`](https://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.html) acts like a really cool spreadsheet (think MSExcel) where you can do all kinds of things with it, including loading a csv into it and then passing its columns along to a machine learning model.


# Debugging with Python

Next, let's work on the optional extra credit debugging exercise together.


