# Poker-Hand-Sorter
This command line program was created to determine the winner between 2 players in a round of poker. \
The program expects standard input from the terminal line by line in the below format:
```text
9C 9D 8D 7C 3C 2S KD TH 9H 8H
6C 5H AS 4H 7S 2S KD 7H 2C AC
```
Where each new line is a new round of poker. \
In each round, the first 5 strings represent the hand of the first player and the next 5 strings represent the hand of the second player.\
After all lines have been read by the program, the program will output the number of winning hands per player, such as below:
```text
Player 1: 263
Player 2: 237
```
The program was created using Java version 24.0.1.

### Running the Program
A pre-built .jar file called `my-poker-solution.jar` has been included in this repository.
Given an input file named `poker-hands.txt`, the Poker-Hand-Sorter can be run with the following command:
```shell
cat poker-hands.txt | java -jar my-poker-solution.jar
```

### Building the Program
A `manifest.txt` file is included in this repository to build a new .jar file if required.
To build a new executable .jar file, run the below commands: 
```shell
javac *.java
jar cfm my-poker-solution.jar manifest.txt *.class
```
This will first create class files for each java file, and then a new `my-poker-solution.jar` file that can be run using the command mentioned previously. 