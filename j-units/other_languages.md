---
layout: toc
permalink: /j/languages
---


# Programming in Other Languages -- DRAFT, currently updating these notes

Although this course has focused on the Java programming language to illustrate object oriented and software engineering concepts this semester, recently it has not been the [most popular programming language people use](https://spectrum.ieee.org/top-programming-languages-2024) (or that employers are looking for). Python is currently dominating.

Now that you know a decent amount of Java (and some C/C++ from your systems programming course), we are going to take a moment to wrap up the semester by discussing 1) when and why you would choose one programming language over another and 2) take a deep dive into OOP in python.


## A little bit about python

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

## What programming language should I use?

There is no "best" programming language; the best programming language for you to use depends on many things! Many of these are tradeoffs. We'll review some of those considerations below.

### Ease of use 

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
Even something as simple as adding an element to the end of an array is different between these two languages, in terms of syntax:

```python
intList = [1, 2, 3]
intList.append(4)
```

Above, we can just call the `append()` method to add 4 to the intList in python. However, if we tried to mimic the code above in Java, it would look like:

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
array.append(1);
array.append(2);
array.append(3);
array.append(4);
```

### Efficieny and utility of code

So far, it might always seem preferable to use python rather than Java. In part, that's because we haven't talked about OOP yet between these two languages. But the previous example regarding adding an element to the end of a list of integers didn't touch on another important topic in choosing a programming language: runtime efficiency.

Although adding to the end of an `ArrayList` in Java is just as simple as doing the same in python (`.add()` versus `.append()`), what's really going on under the hood? 

In the `ArrayList` example above, it turns out that an `ArrayList` actually uses a primitive array to store the elements: this is not obvious to the user, and with good reason. We shouldn't need to worry about the implementation level details of these classes...except when runtime efficiency might be called into question. For example, the `ArrayList` constructor could always create a primitve Java array with a size of ten, and then add to that as many times as the user wants, until it is full. When that happens and the user wants to add a new element, it might double the current size of the array, copy over the elements, and then add in the new one. That step is majorly inefficient! And the alternative: assigning huge empty arrays of thousands of elements is also wasteful. 

If one really cared, at least using a primitve array in Java makes these costs explicit. However, Java, compared to a language like C/C++, is not known for its runtime efficiency. 

#### Runtime efficiency and predictability

Java is a high level programming language not only because it tends to read like English, but also because it simply doesn't allow the user to directly access memory. While you can call a constructor, you don't get to decide when an object that is no longer in use (because no variable points to it anymore) is freed: the garbage collector does this for you. For those of you that have programmed in C/C++, you probably appreciate Java's garbage collection to no end: it frees developers of having to manually manage their own memory, and therefore, also obviates an entire class of errors: memory leaks and dereferecing invalid pointers. What's not to love?!

It turns out, sometimes one needs to have fine-grained manual control over memory allocation and deallocation: Java is not the language for this. For instance, if you were writing code for an ultradependable system (i.e. something that could go into the control system of a nuclear power reactor), you need to be able to *predict* when certain code will run; garabage collection doesn't allow you to do this. In fact, it's not uncommon (at least in the past) that Java's garbage collection would run at the worst time: when free memory was scarce because a program was using a lot of it to do a lot of computations, this is the exact time Java might pause the busy program to free up space. If things didn't work out, this might look like the program was "hanging" to the user. 

We don't want the code in a nuclear power plant to appear to hang...so we can use a language like C to allow us to allocate and deallocate memory in a fine-grained, predictable fashion...but we also now have to be careful about memory leaks and dangling pointers. Always a tradeoff :-) 

By allowing us to more directly manipulate memory, a language like C/C++, unlike Java and python, is useful for coding up things like operating systems. Being lower level with respect to this control around memory manipulation, a language like C/C++ is also useful for things that need to run quickly, like machine learning libraries that train large models on a GPU.

#### Compiled vs interpreted languages

Regardless of 

### Ease of debugging

Runtime efficieny is often not the most important concern in a lot of modern programming. Compared to the 1980s, you have a lot of memory on your computers. Consider that the custom of having file extensions being three or four characters long came from needing to save memory back in the day; this number is otherwise arbitrary! In fact, runtime efficiency (i.e. C) often comes at the cost of the ease of writing and debugging code.

If being extremely memory efficient is not one of your concerns, one can argue that it's much more important to write understandable and maintainable code, versus code that runs very fast on the CPU. Consider the following example:

FIXME

It's hard to read; it might be hard for you to read a year later if you look at your code, and it might be hard for a more novice programmer to understand who you have to work with in the future. You're saving a few milliseconds of time when you run this code, but at what cost? It's often better to write code that might take a tiny bit amount of more time to run (especially when no one will notice this...) than to write the most "efficient" code. The more legible and maintainable code is easier to debug.

However, there are certainly instances where runtime efficiency and memory efficiency are important, and you need to worry about them. One example are embedded systems, such as the code running on very limited battery power and memory space in something like a remote sensor. Training a memory- and power-hungry LLM on such a tiny remote sensor is all but hopeless. In these cases, it's wise to potentially sacrifice some legibility to meet the efficiency requirements.



static typing vs dynamic typing


### Ease of understanding

Java vs python



### Popularity and support

what are the common applicatons of different languages

OOP support -- why use Java over Python?

## Pitfalls of specific languages
- python not compatible with previous versions

### Lab exercises:

1. Localize faults in python language that show an issue with dynamic typing
