import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.*;

/*

You can compile and run your code with the following commands (make sure the unit testing jar file from Lab1 is in your current directory)

Windows:
javac -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" *.java
java -classpath ".;junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Exam1_Tester

Mac/Linux:
javac -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" *.java
java -classpath ".:junit-platform-console-standalone-1.7.0-M1.jar" org.junit.runner.JUnitCore Exam1_Tester

*/

public class Exam1_Tester {

  @Test
  public void test1() {
    Tiger tiger = new Tiger(33, "white");
    assertEquals("33 white", tiger.toString());
  }
  
  @Test
  public void test2() {
    Fish fish = new Fish(11, 6);
    assertEquals("11 6", fish.toString());
  }

  @Test
  public void test3() {
    Tiger tiger1 = new Tiger(33, "white");
    Tiger tiger2 = new Tiger(33, "white");
    Tiger tiger3 = new Tiger(33, "white");
    Tiger tiger4 = new Tiger(33, "white");
    Tiger tiger5 = new Tiger(33, "white");
    Fish fish1 = new Fish(11, 6);
    Fish fish2 = new Fish(11, 6);
    Fish fish3 = new Fish(11, 6);
    Fish fish4 = new Fish(11, 6);
    Fish fish5 = new Fish(11, 6);
    assertEquals(12, Animal.numAnimals);
  }

  @Test
  public void test4() {
    Tiger tiger = new Tiger(30, "white");
    tiger.feed(2);
    assertEquals(36, tiger.getWeight());
  }

  @Test
  public void test5() {
    Fish fish = new Fish(10, 6);
    fish.feed(2);
    assertEquals(22, fish.getWeight());
  }

  @Test
  public void test6() {
    Tiger tiger = new Tiger(30, "white");
    tiger.move(5);
    assertEquals(29, tiger.getWeight());
  }

  @Test
  public void test7() {
    Fish fish = new Fish(10, 6);
    fish.move(50);
    assertEquals(5, fish.getWeight());
  }

  @Test
  public void test8() {
    Tiger tiger = new Tiger(33, "white");
    Fish fish = new Fish(11, 6);
    assertEquals(1, tiger.compareTo(fish));
    assertEquals(-1, fish.compareTo(tiger));
    assertEquals(0, tiger.compareTo(tiger));
    assertEquals(-2, tiger.compareTo("happy birthday!"));
    assertEquals(-2, tiger.compareTo(null));
  }

  @Test
  public void test9() {
    ArrayList<Animal> zoo = new ArrayList<Animal>();
    zoo.add(new Fish(10, 6));
    zoo.add(new Tiger(10, "orange"));
    String printout = Arrays.toString(zoo.toArray());
    assertEquals("[10 6, 10 orange]", printout);
  }

}
