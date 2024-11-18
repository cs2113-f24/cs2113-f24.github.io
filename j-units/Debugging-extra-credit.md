---
layout: toc
permalink: j/extra_credit
---

# Debugging Extra Credit

In this assignment you will have 60 minutes to find and fix a realistic bug in a program that was meant to generate dropdown menus for a web tool that allows students to plan their courses for the CS curriculum.

## Using dictionaries and print statements in python 

Recall, in python there is a function called `print()` that can print out anything. For example, you could do:

```python
    possible_courses = {} # sets up an empty dictionary
    possible_courses["linear_alg"] = ["MATH 2184", "CSCI 4342", "EMSE 2705"]
    possible_courses["stats"] = ["APSC 3115", "CSCI 3362", "CSCI 6362", "CSCI 4341", "STAT 4157"]
    # ... pretend more possible courses are added here ...

    print(possible_courses) # prints out the dictionary possible_courses
```

In the code above, we are storing possible courses that can be used towards an older version of the CS BS curriculum in a python dictionary called `possible_courses`; on the last line, we print out this dictionary to the terminal.

In this way, if we added more possible courses for different degree requirements to our dictionary, for example:

```python
  possible_courses["CS_tech_track"] = mystery_function_that_returns_a_list()
```

We could then check to see whether or not all those CS tech track courses were correct, either by printing out the entire dictionary:
```python
    print("possible courses: " + str(possible_courses)) # prints out the dictionary possible_courses
```

Or by printing out the values (a list) associated with the key above:
```python
    print("possible courses, tech track: " + str(possible_courses["CS_tech_track"])) # prints out the dictionary entry for CS_tech_track in possible_courses
```

Now you know how to use print statements to debug in python :-)

## What's the bug?

When you run `python BS_2019_courses.py` this file generates a JSON called `possible_courses_BS_2019.JSON` which can be used by an [advising tool frontend](https://faculty.cs.gwu.edu/goldfrank/advisingtool/) we're working on in the CS department (**it's not ready yet, so beware!**) to populate valid list of courses that meet each of the degree requirements. 

While we were writing this code, we had a bug: in the **NTT (non-tech track) requirement for the 2019-2021 Bachelor of Science (ancient, I know!) the attached code incorrectly generates duplicates into that list. Specifically, each of the three NTT options shows the course, `CSCI 2501` (which we also no longer offer) as listed twice. This is a bug!** There should be no duplicate courses in this or any of the lists for entries in the `possible_courses` dictionary.

Your mission, in the next hour, is to fix the codebase below to get rid of this bug. Use print statements to help you!

**Each student has been emailed a customized codebase to find the bug and modify.** 

This is a (slightly modified) example of a real bug we encountered while writing this code. Note that the codebase is imperfect and realistic for a library you might find online or inherit: comments are sparse because the developer was in a rush and/or writing the code for themselves, functions are expected to be understood by looking at their contents, and the JSON files are enormous and virtually illegible due to their size. However, when this bug was live, we were able to isolate it and fix it in less than five minutes (though, to be fair, we were familiar with the codebase having written it ourselves). You have a full hour to figure out the issue, with the same tools that we used: debugging print statements!

## The codebase and dataset

Three python files, one of which contains the code that you need to fix. **These files will be emailed to you at the start of the timed assessment.**
* course_list_imports_buggy.py
* BS_2019_courses_buggy.py
* utils_buggy.py

You can run the second file with `python BS_2019_courses_buggy.py` to generate the JSON file `possible_courses_BS_2019.json`; in this file, the possible courses for `elective_1`, `elective_2` `elective_3`, and `elective_4` all contain the bug above where `CSCI 2501` appears more than once in the lists associated with those keys. **Running the python file will show you the duplicates at the bottom** -- this is effectively our test case to see if you fixed the bug or not.

The three python files also make use of, or generate, the following files:
* [all_courses.json](./all_courses.json) 
* [all_GPAC_courses.json](./all_GPAC_courses.json) 
* [BS_2019-2020.json](./BS_2019-2020.json) 
* [cs_offerings.json](./cs_offerings.json) 
* [exceptions.json](./exceptions.json) 
* [possible_courses_BS_2019.json](./possible_courses_BS_2019.json) 


# Requirements, submission, and grading

## Requirements

You should be using print statements that are properly labelled, as above, to work backwards from the incorrectly-created JSON file and localize the fault somewhere in the three python files.

There is only one place you need to fix the code in one of these files to get rid of the bug. However, the fix itself may be multiples of code (in one "chunk").

In order to get credit for the bug, **you need to have meaningful print statements that tell a story** of how you found the bug. **Each print statement must be numbered**, starting at 1, in the order that you put them in the file. If you don't want to see the output of a print statement, please leave it in the file but comment it out. For example, your first few print statements should look like:
```python
print("DEBUG 1: x is " + str(x)) # printing out some variable called x
print("DEBUG 2: len(x) is " + str(len(x))) # printing out the length of a list called x
print("DEBUG 3: y is " + str(y)) # printing out some variable called y
```

Once you have found and fixed the bug, include the following comment in the file next to the line(s) that you commented out (or deleted):

```python
print("### I found the BUG HERE ###")
```

If you delete any of the exisitng lines in any of the files, you will not earn points for this assignment -- just comment things out that you don't need. You should also not be commenting out any of the code other than what is needed to fix the bug, when you find it.

## Grading

It is important to be systematic and efficient when writing and debugging code. Therefore, we will award points according to the following tiers, based on submission time to the submitserver:
* Tier 1: found bug in 5 minutes or less
* Tier 2: found bug in 15 minutes or less
* Tier 3: found bug in 30 minutes or less
* Tier 4: found bug in 45 minutes or less
* Tier 5: found bug in 60 minutes or less

Each of the tiers above will be a multiplier on the number of points you will earn:
* Tier 1: x 5
* Tier 2: x 4
* Tier 3: x 3
* Tier 4: x 2
* Tier 5: x 1

If you have a **documented DSS accomodation for extra time**, you can apply that multiplier to the times listed above (for example, if you get double time on quizzes and exams, and find the bug in 30 minutes, we will treat it as finding the bug in 15 minutes, etc). Please email the professor right after the assessment a reminder to include your extended time accomodations, when applicable.

### Academic Integrity

In an effort to enforce this extra credit as individual work without the benefit of a testing center, we have a **pool of pre-tier points** from which students can earn credit. The more students find the bug, the fewer points each individual student will receive. Therefore, it is in your best interest to NOT cheat and find a way to get outside help on this assignment from your classmates. You must be physically in the classroom in order to earn points, and you must complete this assignment from a single computer and IP address. 

Your debugging must consist of your own, individual work. You may use the internet to look up python syntax questions, but you may NOT use an LLM or any outside party to otherwise help you locate and fix the fault.

We will be tracking this information; any evidence of violating these rules will be considered an honor code violation and will be reported, with the **penalty of a full letter grade drop for your final overall grade in the class**.

### Points possible

Needing to make up both Exam1 and the live coding exam (for caps of 80) means such students stand to earn a 92.5% as the highest possible grade in this course. Our goal is to have students demonstrate, via this extra credit, that they have done the extra work this semester to become ready for A-level work in future courses. Therefore, this extra credit will be worth, at most, one letter increment grade bump in the course (i.e. an A- to an A, or a B+ to an A-, C to a C+, etc.)

There are 63 students in the class this semester, so our pool will start with 63 points. These 63 points will first be divided evenly (with rounding) amongst everyone that is able to find the bug within an hour. Then, the multipliers above will apply. After these calculations, the `N` points an individual student has earned will be added in to their final percentage in the course overall, with the cap above. For example:
* if the student has a 76.3% at the end of the semester, and N=2.52, then their new overall course grade would be a 76.3% + 2.52% = 78.82%, thus lifting them from a C to a C+
* if the student has a 71.1% at the end of the semester and N=22, then their new overall course grade would be a 71.1% + 22% = 93.2%, but we would only lift them from a C- to a C due to the cap above (it does not make sense for one tiny assignment to bring someone up two letter grades)

## Submission

Tar up everything in the zip folder you downloaded with `tar -cvf extra_credit.tar *.*` and submit this to BB before the deadline in class. You must be physically present in class in order to receive credit for this assignment. Do not delete any files from your zip folder before submission, otherwise we cannot award you points.