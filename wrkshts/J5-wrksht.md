---
layout: worksheet
permalink: /worksheet/j5
showsolution: false
---

# Worksheet: J5

Worksheets are self-guided activities that reinforce lectures. They are not graded for accuracy, only for completion. Submit a file called `worksheet-J5.md` on BB for this assignment.

## Note

Attempt to answer these questions before running the code. This will improve your ability to analyize and reason about code without an IDE or compiler. This skill we be helpful on the exams.

## Questions

### q
How does object-oriented programming pair so closely with GUIs?
#### s
GUIs have a lot of components that have similar interfaces and jobs, but may have slightly different functionality. For example we might have an exit button, and another such as a submit button, which you can only click when certain things are done. Both of these are buttons, are displayed the same, but have different functionality. We see then that if both of these buttons inherit from a parent Button class, then we won't have much extra code to write beyond what they do when they're clicked. So we see that any part of a GUI is going to be an object, and that we will end up using a lot of inheritance, which is why OOP is so closely tied to GUIs.

### q

What is the relationship between `WindowListener` and `WindowAdapter`?

#### s
`WindowListener` is an interface with methods that correspond to any event that might happen to a window, such as opening, closing, or minimizing it. `WindowAdapter` on the other hand is an abstract class that implements `WindowListener, WindowStateListener, WindowFocusListener`. The main difference we see then is that `WindowAdapter` is essentially a combination of all possible ways to interact(interface :) )  with a window, and so we know that if we have something that is a window, we can inherit from this parent class, indicating it's a window, instead of simply implementing all the interfaces separately. High level the reasoning behind this is as follows: we have many ways to interface with a window, but these overlap with other non-window objects(such as menus), so not everything that implements a subset of these interfaces is a window, but if you implement them ALL the object is a window. So we put all these interfaces in an abstract class so we can say something IS a window, by inheriting it, but we don't only put them in an abstract class as some non-window objects may have similar interfaces.

A side philosophy side tangent: a lot of times we can learn more about something based on how it interacts with things, then its actual definition. This is a common thing in most sciences, usually we don't care about what something is, only how it interacts with other things, to the point a lot of definitions are actually based on how things interact with other things, not just what they are. We see here this is a great example, we're defining what a window is by how it can be interacted with, not what it "is".



### q
What does the program below produce for a GUI? (You can sketch and upload an image or describe it -- do this without running the program to make sure you understand what each line below is doing).
```java
 // For reference
    /*        N
    *         |
    *      W--|--E
    *         |
    *         S
    */
    public static void main(final String args[]) {
        JFrame frame = new JFrame();

        JButton bOne = new JButton("1");
        JButton bTwo = new JButton("2");
        JButton bThree = new JButton("3");
        JButton bFour = new JButton("4");
        JButton bFive = new JButton("5");
        JButton bSix = new JButton("6");

        JPanel primes = new JPanel();
        JPanel composites = new JPanel();

        primes.setLayout(new BorderLayout());
        composites.setLayout(new BorderLayout());

        primes.add(bTwo, BorderLayout.EAST);
        primes.add(bThree,BorderLayout.WEST);
        primes.add(bFive,BorderLayout.NORTH);

        composites.add(bFour, BorderLayout.NORTH);
        composites.add(bSix, BorderLayout.CENTER);
 
        frame.add(primes, BorderLayout.WEST);
        frame.add(composites, BorderLayout.EAST);
        frame.add(bOne, BorderLayout.CENTER);
        frame.pack();
        frame.setTitle("Quiz J4-2");
        frame.setSize(400, 400);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
```
#### s
![](/images/J4-9.png)


### q

Modify the `HelloGoodbyeEx2` code to update the number of times the button has been clicked on the button's label itself.

#### s

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloGoodbyeEx2 {

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setTitle("Hello/Goodbye Ex1");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("Hello clicked 0 times.");
        JButton button = new JButton("Click me!");

        //using an anonymous (static) class
        //avoids having to make ButtonClickListenerEx1 class above
        button.addActionListener(new ActionListener() {
                //implement the one method here
                //shares the name space with the whole class
                //has access to the label field above
                public void actionPerformed(ActionEvent e) {
                    int count = Integer.intValue(label.getText().split(" times.")[0].split(" ")[2]) + 1;

                    if (label.getText().startsWith("Hello")) {
                        label.setText("Goodbye clicked " + count + " times.");
                    }else {
                        label.setText("Hello clicked " + count + " times.");
                    }
                }
            });
        
        f.add(button, BorderLayout.SOUTH);
        f.add(label, BorderLayout.NORTH);

        f.pack();
        f.setVisible(true);
        
    }
}
```


### q

Consider the following Java swing GUI

```java
public class RedPillBluePill extends JFrame {
    JLabel label;

    public RedPillBluePill() {
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());        
        JButton red = new JButton("red");
        JButton blue = new JButton("blue");
        panel.add(red, BorderLayout.EAST);
        panel.add(blue, BorderLayout.WEST);
        label = new JLabel("click a button");
        this.add(label, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);

        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                label.setText("RED");        
            }
        });

        blue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                label.setText("BLUE");
            }

        });
    }
}
```

Convert the `ActionListener`s to Lambda Functions.

#### s
```java
red.addActionListener((e) -> {label.setText("RED");});
blue.addActionListener((e) -> {label.setText("BLUE");});
```


### q

Explain why for `ActionListener` you can use a Lambda function but for `WindowListener` you cannot?


#### s
`WindowListener` has multiple methods that must be implemented, `ActionListener` only one, and lambda functions can only represent one.

### q

Write a program that allows you to enter a 6-digit PIN, like you would on your smartphone to unlock it. It should have the following layout:

```

 [ DISPLAY PIN AS TYPED ]

   [ 1 ]  [ 2 ] [ 3 ] 
   
   [ 4 ]  [ 5 ] [ 6 ]    
   
   [ 7 ]  [ 8 ] [ 9 ]       

   [ < ]  [ 0 ] 

````

Where `[ < ]` is a "backspace" button. The display should show the PIN as it is typed, and when the user enters the PIN 202113, the display changes to "YOU MAY ENTER!"

#### s

``` java
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

class Pin extends JFrame {
    public Pin() {
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("");
        JPanel panelA = new JPanel(new BorderLayout());
        JPanel panelB = new JPanel(new BorderLayout());
        JPanel panelC = new JPanel(new BorderLayout());
        JPanel panelD = new JPanel(new BorderLayout());
        JButton one = new JButton("[1]");
        one.addActionListener((e) -> {
            label.setText(label.getText() + "1");
        });
        panelA.add(one, BorderLayout.WEST);
        JButton two = new JButton("[2]");
        two.addActionListener((e) -> {
            label.setText(label.getText() + "2");
        });
        panelA.add(two, BorderLayout.CENTER);
        JButton three = new JButton("[3]");
        three.addActionListener((e) -> {
            label.setText(label.getText() + "3");
        });
        panelA.add(three, BorderLayout.EAST);
        JButton four = new JButton("[4]");
        four.addActionListener((e) -> {
            label.setText(label.getText() + "4");
        });
        panelB.add(four, BorderLayout.WEST);
        JButton five = new JButton("[5]");
        five.addActionListener((e) -> {
            label.setText(label.getText() + "5");
        });
        panelB.add(five, BorderLayout.CENTER);
        JButton six = new JButton("[6]");
        six.addActionListener((e) -> {
            label.setText(label.getText() + "6");
        });
        panelB.add(six, BorderLayout.EAST);
        JButton seven = new JButton("[7]");
        seven.addActionListener((e) -> {
            label.setText(label.getText() + "7");
        });
        panelC.add(seven, BorderLayout.WEST);
        JButton eight = new JButton("[8]");
        eight.addActionListener((e) -> {
            label.setText(label.getText() + "8");
        });
        panelC.add(eight, BorderLayout.CENTER);
        JButton nine = new JButton("[9]");
        nine.addActionListener((e) -> {
            label.setText(label.getText() + "9");
        });
        panelC.add(nine, BorderLayout.EAST);
        JButton zero = new JButton("[0]");
        zero.addActionListener((e) -> {
            label.setText(label.getText() + "0");
        });
        panelD.add(zero, BorderLayout.WEST);
        JButton back = new JButton("[<]");
        back.addActionListener((e) -> {
            label.setText(label.getText().substring(0,
                    label.getText().length() - 1 > 0 ? label.getText().length() - 2 : 0));
        });
        panelD.add(back, BorderLayout.CENTER);
        JPanel middle = new JPanel(new BorderLayout());
        JPanel bottom = new JPanel(new BorderLayout());
        middle.add(label, BorderLayout.NORTH);
        middle.add(panelA, BorderLayout.CENTER);
        middle.add(panelB, BorderLayout.SOUTH);
        bottom.add(panelC, BorderLayout.NORTH);
        bottom.add(panelD, BorderLayout.CENTER);
        this.add(middle, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.CENTER);
        label.addPropertyChangeListener("text", (e) -> {
            if (label.getText().equals("202113")) {
                label.setText("YOU MAY ENTER");
            }
        });
    }

    public static void main(String[] args) {
        Pin p = new Pin();
        p.setVisible(true);
    }
}
```


