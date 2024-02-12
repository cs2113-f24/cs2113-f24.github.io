import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;

public class Project1_Tester {

  private boolean checkInFile(String filename, String phrase){
    try {
      File file = new File("./" + filename);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String line = scanner.nextLine();
        line = line.replaceAll("\\s+","") ;
        phrase = phrase.replaceAll("\\s+","") ;
        if (line.contains(phrase))
          return true;
      }
    } catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  private String getCommandLineOutput(String command) {
    String outputCollected = "";

    try {
      Process process = Runtime.getRuntime().exec(command);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) 
        outputCollected += line;
      reader.close();

      reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      while ((line = reader.readLine()) != null) 
        outputCollected += line;
      reader.close();
    } catch (IOException exc) {
      exc.printStackTrace();
    }

    return outputCollected;
  }

  @Test
  public void test1() {
    System.out.println("checking that Creature has a step method with good OOP");
    assertEquals(true, checkInFile("Creature.java", "public void step() {"));
  }

    @Test
  public void test2() {
    System.out.println("checking that Mouse and Cat extend Creature");
    assertEquals(true, checkInFile("Mouse.java", "public class Mouse extends Creature"));
    assertEquals(true, checkInFile("Cat.java", "public class Cat extends Creature"));
  }

  @Test
  public void test3() {
    System.out.println("check that Mouse and Cat call creature's step with good OOP");
    assertEquals(true, checkInFile("Mouse.java", "super.step();"));
    assertEquals(true, checkInFile("Cat.java", "super.step();"));
  }

  @Test
  public void test4() {
    System.out.println("check that Creature has an abstract takeAction with good OOP");
    assertEquals(true, checkInFile("Creature.java", "public abstract void takeAction();"));
  }

  @Test
  public void test5() {
    System.out.println("check that Mouse and cat implement Creature's takeAction with good OOP");
    assertEquals(true, checkInFile("Mouse.java", "public void takeAction(){"));
  }

  @Test
  public void test6() {
    System.out.println("checking that Mouse randomly changes direction 20% of the time and is a blue dot");
    String expected = "62 60 bdone 162 61 bdone 262 62 bdone 362 63 bdone 462 64 bdone 562 65 bdone 661 65 bdone 760 65 bdone 859 65 bdone 958 65 bdone 10";
    assertEquals(expected, getCommandLineOutput("java Simulator 1 0 10 40"));

  }

  @Test
  public void test7() {
    System.out.println("checking that Mouse produces a baby after 20 rounds");
      String expected = "5 28 bdone 14 28 bdone 23 28 bdone 32 28 bdone 41 28 bdone 50 28 bdone 60 29 bdone 70 30 bdone 80 31 bdone 91 31 bdone 102 31 bdone 112 32 bdone 122 33 bdone 132 34 bdone 142 35 bdone 152 36 bdone 162 37 bdone 172 38 bdone 182 39 bdone 192 40 b2 40 bdone 202 41 b1 40 bdone 21";
      assertEquals(expected, getCommandLineOutput("java Simulator 1 0 21 30"));
  }

  @Test
  public void test8() {
    System.out.println("checking that Mouse dies after 22 rounds");
    String expected = "5 28 bdone 14 28 bdone 23 28 bdone 32 28 bdone 41 28 bdone 50 28 bdone 60 29 bdone 70 30 bdone 80 31 bdone 91 31 bdone 102 31 bdone 112 32 bdone 122 33 bdone 132 34 bdone 142 35 bdone 152 36 bdone 162 37 bdone 172 38 bdone 182 39 bdone 192 40 b2 40 bdone 202 41 b1 40 bdone 213 41 b0 40 bdone 2279 40 bdone 23";
    assertEquals(expected, getCommandLineOutput("java Simulator 1 0 23 30"));
  }


  @Test
  public void test9() {
    System.out.println("checking that all Cat functionality is properly implemented");
      String expected = "73 43 b10 71 y76 78 y43 27 y24 34 y8 74 ydone 174 43 b8 71 y76 76 y43 29 y22 34 y6 74 ydone 275 43 b6 71 y76 74 y43 31 y20 34 y4 74 ydone 374 43 b4 71 y76 72 y43 33 y18 34 y2 74 ydone 474 42 b2 71 y76 70 y43 35 y16 34 y0 74 ydone 574 41 b0 71 y76 68 y43 37 y14 34 y78 74 ydone 674 40 b78 71 y76 66 y43 39 y12 34 y76 74 ydone 774 39 b76 71 y76 64 y43 41 y12 36 y74 74 ydone 874 38 b74 71 y76 62 y43 43 y12 38 c74 72 ydone 975 38 b72 71 y76 60 y43 45 y10 38 c74 70 ydone 1076 38 b70 71 y76 58 y43 47 y12 38 c74 68 ydone 1175 38 b68 71 y76 56 c43 49 y14 38 c74 66 ydone 1274 38 b66 71 y76 54 c43 51 y16 38 y74 64 ydone 1373 38 b64 71 y76 52 c43 53 y18 38 y74 62 ydone 1472 38 b62 71 y76 50 c43 55 y20 38 y74 60 ydone 1571 38 b60 71 y76 48 c43 57 y22 38 y74 58 ydone 1670 38 b62 71 y76 46 c43 59 y24 38 y74 56 ydone 1769 38 b64 71 y76 44 c43 61 y26 38 y72 56 ydone 1868 38 b66 71 y74 44 c41 61 y28 38 y70 56 ydone 1967 38 b66 73 y74 42 c39 61 y30 38 y68 56 c67 38 bdone 2066 38 b66 75 y72 42 c39 63 y32 38 y68 54 c67 37 bdone 2165 38 b66 77 y70 42 c39 65 y34 38 y68 52 c67 36 bdone 2266 79 y68 42 c39 67 y36 38 y68 50 c67 37 bdone 2366 1 y68 40 c39 69 y38 38 y68 48 c67 38 bdone 2466 3 y68 38 c39 71 y40 38 y68 46 c67 39 b18 5 ydone 2566 5 y66 38 c39 73 y42 38 y68 44 c67 40 b18 7 ydone 2668 5 y66 36 c39 75 y44 38 y68 42 c67 41 b18 9 ydone 2770 5 y66 38 c39 77 y46 38 y66 42 c67 40 b18 11 ydone 2872 5 y66 40 c39 79 y48 38 y66 40 c67 39 b20 11 ydone 2974 5 y68 40 c39 1 y50 38 c68 40 c67 38 b22 11 ydone 30";
      assertEquals(expected, getCommandLineOutput("java Simulator 1 5 30 35"));
  }

  @Test
  public void test10() {
    System.out.println("checking new mice and cats are added correctly");
      String expected = "73 43 b10 71 y76 78 y43 27 y24 34 y8 74 ydone 174 43 b8 71 y76 76 y43 29 y22 34 y6 74 ydone 275 43 b6 71 y76 74 y43 31 y20 34 y4 74 ydone 374 43 b4 71 y76 72 y43 33 y18 34 y2 74 ydone 474 42 b2 71 y76 70 y43 35 y16 34 y0 74 ydone 574 41 b0 71 y76 68 y43 37 y14 34 y78 74 ydone 674 40 b78 71 y76 66 y43 39 y12 34 y76 74 ydone 774 39 b76 71 y76 64 y43 41 y12 36 y74 74 ydone 874 38 b74 71 y76 62 y43 43 y12 38 c74 72 ydone 975 38 b72 71 y76 60 y43 45 y10 38 c74 70 ydone 1076 38 b70 71 y76 58 y43 47 y12 38 c74 68 ydone 1175 38 b68 71 y76 56 c43 49 y14 38 c74 66 ydone 1274 38 b66 71 y76 54 c43 51 y16 38 y74 64 ydone 1373 38 b64 71 y76 52 c43 53 y18 38 y74 62 ydone 1472 38 b62 71 y76 50 c43 55 y20 38 y74 60 ydone 1571 38 b60 71 y76 48 c43 57 y22 38 y74 58 ydone 1670 38 b62 71 y76 46 c43 59 y24 38 y74 56 ydone 1769 38 b64 71 y76 44 c43 61 y26 38 y72 56 ydone 1868 38 b66 71 y74 44 c41 61 y28 38 y70 56 ydone 1967 38 b66 73 y74 42 c39 61 y30 38 y68 56 c67 38 bdone 2066 38 b66 75 y72 42 c39 63 y32 38 y68 54 c67 37 bdone 2165 38 b66 77 y70 42 c39 65 y34 38 y68 52 c67 36 bdone 2266 79 y68 42 c39 67 y36 38 y68 50 c67 37 bdone 2366 1 y68 40 c39 69 y38 38 y68 48 c67 38 bdone 2466 3 y68 38 c39 71 y40 38 y68 46 c67 39 b18 5 ydone 2566 5 y66 38 c39 73 y42 38 y68 44 c67 40 b18 7 ydone 2668 5 y66 36 c39 75 y44 38 y68 42 c67 41 b18 9 ydone 2770 5 y66 38 c39 77 y46 38 y66 42 c67 40 b18 11 ydone 2872 5 y66 40 c39 79 y48 38 y66 40 c67 39 b20 11 ydone 2974 5 y68 40 c39 1 y50 38 c68 40 c67 38 b22 11 ydone 3076 5 y68 38 c39 3 y52 38 c68 38 c67 37 b24 11 ydone 3178 5 y66 38 c39 5 y54 38 c66 38 c66 37 b26 11 ydone 320 5 y66 36 c39 7 y56 38 c66 36 c65 37 b28 11 ydone 332 5 y66 38 c39 9 y58 38 c64 36 c64 37 b30 11 ydone 344 5 y64 38 c39 11 y58 36 c64 38 c63 37 b32 11 ydone 356 5 y62 38 c41 11 y60 36 c62 38 c62 37 b34 11 ydone 368 5 y62 36 c43 11 y62 36 c62 36 c61 37 b36 11 ydone 3710 5 y60 36 c43 9 y60 36 c60 36 c60 37 b36 9 ydone 3812 5 y60 38 c45 9 y60 34 c60 38 c59 37 b36 7 ydone 3914 5 y58 38 c47 9 y60 36 c58 38 c58 37 b34 7 y58 37 bdone 4016 5 y58 36 c49 9 y58 36 c58 36 c57 37 b32 7 y57 37 bdone 4116 7 y56 36 c51 9 y56 36 c60 36 c56 37 b30 7 y56 37 bdone 4216 9 y56 38 c53 9 y56 38 c58 36 c30 5 y55 37 bdone 4316 11 y54 38 c55 9 y54 38 c56 36 c32 5 y54 37 bdone 4416 13 y54 36 c55 7 y54 36 c54 36 c34 5 y53 37 bdone 4516 15 y52 36 c55 5 y52 36 c52 36 c34 3 y53 38 bdone 4618 15 y52 38 c55 3 y52 34 c52 38 c34 1 y53 39 bdone 4720 15 y54 38 c55 1 y52 36 c54 38 c34 3 y53 38 bdone 4822 15 y52 38 c55 79 y52 38 c52 38 c34 1 y53 37 bdone 4924 15 y54 38 c55 77 y54 38 c54 38 c34 79 y53 36 b24 8 ydone 5036 79 y52 36 b26 8 ydone 5136 77 y51 36 b28 8 ydone 5236 75 y50 36 b30 8 ydone 5336 73 y50 35 b32 8 ydone 5436 71 y50 34 b34 8 ydone 5536 69 y50 33 b36 8 ydone 5636 67 y50 32 b38 8 ydone 5736 69 y50 31 b40 8 ydone 5836 71 y50 30 b42 8 ydone 5936 73 y50 29 b44 8 y50 29 bdone 60";
      assertEquals(expected, getCommandLineOutput("java Simulator 1 5 60 35"));
  }

}
