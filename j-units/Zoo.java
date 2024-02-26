/*
INSTRUCTIONS: 
1. Create a class Animal with good OOP 
2. Implement the Comparable interface to allow animals to be sorted by weight
3. Extend the Animal class into a Tiger class and a Fish class. Note that only Tigers have a furColor, and only fish have numFins
4. Complete the toString method to print out the type of each object, along with all its fields
5. Implement good OOP practices as discussed in class
6. Keep track of the number of animals in the zoo without relying on a method
7. Compile against and pass all the tests (your solution no longer needs to work with the Zoo code below)

RULES:

This exam is open-book, open note, open internet with the following exceptions (that are honor code violations):
1. All work submitted must be your own
2. You may not share your solutions with others
3. You may not ask any other party to help you with any part of this assignment
4. You should only be googling syntax errors; you should not be googling for help with writing your code/design/solution/algorithm.
5. You should never be copying any code from any website (except the course webpages)

*/

public class Zoo {

	int[] weights; //all animals have a weight
	String[] furColors; //will have null values for fish
	int[] numFins; //these values are only meaningful for fish
	String[] types; //the type of the animals

	public void feed(int food){
		for(int i = 0; i < weights.length; i++){
			if (types[i].equals("Fish"))
				weights[i] += (int)(food * 0.5 + 11);
			else if (types[i].equals("Tiger"))
				weights[i] += (int)(food / 0.3);
		}
	}

	public void move(int distance){
		for(int i = 0; i < weights.length; i++)
			weights[i] -= 0.1 * distance;
	}


	public String roster(){
		String result = "Here are all the animals: ";
		for(int i = 0; i < types.length; i++)
			result += types[i] + "\n";
		result += "There are " + types.length + " animals total.\n";
		return result;
	}

}