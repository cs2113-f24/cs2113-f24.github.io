---
layout: worksheet
permalink: /worksheet/j9
showsolution: false
---

# Worksheet: J9

Worksheets are self-guided activities that reinforce lectures. They are not graded for accuracy, only for completion. 

This worksheet should be completed in class. Submit it to BB.

## Questions


### q
What is the difference between a design pattern and a java library?

#### s

A design pattern is a template, not code. 

### q
Using the Singleton pattern, write code/pseudocode that ensures that only one database connection object is ever instantiated.

#### s

```
public class DBSingleton {

    private static DBSingleton singletonInstance;
    private static DBConnection connection;

    private DBSingleton(String user, String password, String schema) {
        connection = new DBConnection(user, password, schema);
    }

    public static synchronized DBSingleton getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DBSingleton("kinga", "passy", "book_database");
        }

        return singletonInstance;
    }
}
```

### q
Assume there is an `Animal` abstract parent class that has the following constructor: `public Animal(String name, int weight`.

Using the Factory pattern, write code/pseudocode that sets up a factory for two child classes of `Animal`: `Bird` and `Mammal`. Each of these has a constructor with the same arguments as those of the parent class.

#### s
public class AnimalFactory {

  public static Animal getAnimal(String type, String name, int weight){
      switch(type){
          case "bird":
              return new Bird(name, weight);
          case "beast":
              return new Mammal(name, weight);
      }
    return null;
  }
}


### q
Imagine we have some code for an aquarium that works nicely to schedule feeding the fish in all the tanks, and cleaning the tanks:

```
public class Aquarium {
	public void feedAll(ArrayList<Fish> tanks, int numFishInTank) {...}
	public void cleanAll(ArrayList<Fish> tanks)
}

```

This code works well with the following `Fish` class:
```
public class Fish{
	public void feed(String food, int weight) {...}
	public void clean(String cleaningProduct) {...}
}
```

We also, one day, inherit an animal hospital, where each patient is solo in a cage. We want to be able to use the code above to feed and clean the cages of all the animals, but our hospital has the following API for the mammals they serve:

```
public class Mammal{
	public void feed(String food, int weight, String medication) {...}
	public void clean() {...}
}
```

Use the Adapter pattern to allow us to use the `Mammal` class with the `Aquarium` class above. Each animal is housed alone in its cage during its visit. All mammals are given the same medication, namely, `"antibiotics"`.

#### s
public class MammalAdapter extends Fish {
  private Mammal mammal;
  public MammalAdapter(Mammal m){
    mammal = m;
  }

  public void feed(String food, int weight) {
    mammal.feed(food, weight, "antibiotics");
  }

  public void clean(String cleaningProduct) {
    mammal.clean();
  }
}


### q 
What is the benefit of the Bridge design pattern? Why use one?

#### s
Refactoring your code using the Bridge design pattern eliminates the state space explosion of having a separate class for all the possible combinations of the N dimensions of features the system must model. When adding a new dimension with two options, you don't need to double the number of classes you have; you can just add a single field to your main class.

