# Package Challenge
Mobiquity Assignment

## Introduction

### Problem description
You want to send your friend a package with different things.
Each thing you put inside the package has such parameters as index number, weight and cost. The package has a weight limit. 
Your goal is to determine which things to put into the package so that the total weight is less than or equal to the package 
limit, and the total cost is as large as possible.
You would prefer to send a package which weighs less in case there is more than one package with the same price.

### Implementation 
This project is implemented with Maven, Java 11, Lombok and uses JUnit5 and Mockito for the Unit tests.
A jar called **package-challenge-1.0.0.jar** will be produce using the _maven-shade-plugin_ that will be stored in
the _target_ folder once it is compiled.


## Design Structure
The solution is divided in 5 packages:
1. entities
2. exception
3. helper
4. packer
5. utils

_**Note:**_ Packages 3,4, and 5 have they corresponding test packages for unit testing.

### entities 
In this package we can find Item and Pack entities which holds the processed input file as an object.
These entities have lombok to reduce boilerplate code, are both immutable objects and also use a validation library to 
take care of the constraints of size for each of the elements.

A **Pack** will hold the total weight capacity of the package, and a list of the possible items to choose from.
An **Item** will hold the index, weight and cost of each item.

### exception
Package included in the skeleton of the project that holds the _APIException_, this exception is part of 
the initial constraints and will be thrown when pack method is called with incorrect parameters, for 
example an invalid or an empty file path.

### helper
In this package you can find the _PackageSorter_ class which is the class responsible for running the algorithm for 
each Pack that we send it. The logic of the algorithm is explained in detail in the next section of this README.

### packer
This is the entry point of our project, this static class was also included in the skeleton, and it's the responsible 
for calling the class that will process the input and output file, and also that has the loop to go to each of the 
Packs in the file and call the PackageSorter to get the solutions to each Pack.

### utils
This package will contain the _FileProcessor_ class that is the one in charge of processing the input ad outputs. 
So for the input, it will read the file extract the values, clean them up and map them into our objects (Pack and Item) 
For the output it will get the list of results and transform them into a String.

## Algorithm
This is a problem of combinatorial optimization, where we need to maximize the value or profit of the items that we can 
include in our package.

The solution to this problem is implemented using Dynamic Programming with the bottom-up approach. What this means is that 
we will go in an iterative way first saving all the possible solutions in a matrix. At the end of the iteration we will make 
sure that the maximum profit will be in the last position. 

This approach aims at resolving the sub problems first and with that avoid repetitive processing.
The algorithm runs in _O(n*C)_ where **n** = amount of items and **C** = weight capacity of the package.
We also have a sorting that will add up to the time complexity with O(nlogn)

After we have the matrix of solutions already constructed, we can go back to it iterating over it to find which items were 
the ones that ended up in the solution, since the matrix will only give us which is the optimized value.

## Prerequisites

So please follow these instructions so you can setup the environment properly.

1. Get Git in your local -> In case you don’t have it already, go ahead and follow this guide: 
   [Install-Git](https://www.atlassian.com/git/tutorials/install-git)
2. Get your IDE -> Since this is a Maven project you can use the IDE of your preference, (I used IntelliJ) just make 
   sure to import the project as a “Project from Existing Sources” and choose Maven.
3. Point your IDE to the right JRE version -> This project is build over Java 11 so please make sure that your Java 
   Build Path is pointing to the correct JDK version.
4. Get Lombok -> Lombok is an external jar or plugin that needs to be added into your IDE. For more information on how 
   to add Lombok to your IDE (Eclipse or IntelliJ) follow this guide: [Lombok-IDE](https://www.baeldung.com/lombok-ide)
5. This project will generate a jar version after building, that can be found in the _target_ folder. So in order to 
   generate it please run a mvn clean install or build the project using your IDE.
   
## Improvements
The special case -> _56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)_ 
still doesn't have the correct solution according to the document, it should pickup the one with less weight, and the 
algorithm right now can't solve this special case, so the unit test are changed in order to have one of the solutions we get from it.


## Author

* **Katherine Alfaro Ramirez** - *Initial work* - [kalfie88](https://github.com/kalfie88)