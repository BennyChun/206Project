package application.model;

//abstract classes can have non abstract methods (methods that has implementations)
//all sub-classes that extends this abstract class must provide their own implementations for ABSTRACT methods.

public abstract class AbstractLevel {

	/*
	 * A model should have everything (feilds, methods, etc) that can used to represent a Level
	 * a Level is either easy or hard
	 * easy levels generates random numbers between 1 and 9   (less than 10)
	 * hard levels generates random numbers between 1 and 99  (less than 100)
	 * either level will randomly generate 10 numbers (questions to answer)
	 * =============================================================================================================
	 * every integer that gets generated should be an Question object ? (make a question model class, this means we don't need the two easy/hard classes)
	 * if we make numbers model class, each number will have it's own state, and stuff
	 * if we do this, we dont need a hash map, and we can use an array list, much easier to loop through
	 * ArrayList<Numbers> numbers = new ArrayList<>();
	 * and then generate 10 random integers (depending on the given level), store those integers as each individual Question objects
	 * ==================================================================================================================
	 * or do we just generate 10 random numbers put into a hashmap ? hash table ? (but a map cannot be looped easily)
	 * 
	 * 
	 * 
	 * maybe use template method ?, or maybe factory pattern ?
	 * 
	 * 
	 */
	
	
	
	//hook method to be implemented by sub-class
	abstract void randomNumber();
	
	
	//template method
	public final void generate() {
		//generate the random number
		randomNumber();
	}
}
