import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Simulator {
	
    public static void main(String[] args) {
        boolean DEBUG = false;
        
        //parse arguments
        if(args.length < 4){
            System.out.println("ERROR: missing arguments");
            System.out.println("java Simulator numMice numCats rounds [randSeed]");
            System.exit(1);
        }
        int numMice = Integer.parseInt(args[0]);
        int numCats = Integer.parseInt(args[1]);
        int rounds = Integer.parseInt(args[2]);

        Random rand;
        if(args.length > 3)
            rand = new Random(Integer.parseInt(args[3]));
        else
            rand = new Random(100);

        if(args.length > 4 && args[4].equals("--DEBUG")){
            DEBUG = true;
        }

        // Populate city 
        City city = new City(rand, numMice, numCats);
        int count = 0;

        while (count < rounds) {
            count++;

            //TODO...
            //
            // Every N rounds, add a mouse
            
            //Every M rounds, add a Cat

            //END TODO
            city.simulate();
            System.out.println("done " + count);
            System.out.flush();

            if(DEBUG){
                System.err.print("Enter anything to continue: ");
                try{
                    (new BufferedReader(new InputStreamReader(System.in))).readLine();
                }catch(Exception e){
                    System.exit(1);
                }
            }
        }
    }
}
