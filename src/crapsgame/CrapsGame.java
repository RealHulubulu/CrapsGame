/*
Daniel Karasek
171224
Intro to Java
 */
package crapsgame;

import java.util.Scanner;


public class CrapsGame {

    public static void main(String[] args) {
        boolean menu = true;
        boolean quit = true;
        boolean program = true;
        boolean keepPlaying = true;
        int x = 1;
        int money;
        int newRoll;
        char input;
        Scanner in = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);

        while (program == true) {
            while (menu == true) {
                System.out.println("Welcome to the Craps Game Program \n\n Enter 1, 2, or 3 for the following: \n\n 1 Play \n\n 2 How to Play \n\n 3 Quit\n");
                
                
                input = reader.next().charAt(0);

                System.out.println("You entered: " + input + "\n");

                if (input == '1') {
                    x = 1;
                    menu = false;
                    keepPlaying = true;
                } else if (input == '2') {
                    x = 2;
                    menu = false;
                } else if (input == '3') {
                    x = 3;
                    menu = false;
                } else {
                    System.out.println("Incorrect Input: Enter 1, 2, or 3\n");
                    System.out.println("Press Any Key To Continue...");
                    new java.util.Scanner(System.in).nextLine();
                    menu = true;
                }
            }
            if (x == 1) {
                money = 100;
                while (keepPlaying) {
                    System.out.println("You have $" + money);

                    PairOfDice dice;
                    dice = new PairOfDice();
                    dice.roll();
                    System.out.println("You rolled a " + dice.getTotal());
                    
                    if (dice.getTotal() == 7 || dice.getTotal() == 11) {
                        money += 50;
                        System.out.println("You have $" + money);
                    } else if (dice.getTotal() == 2 || dice.getTotal() == 3 || dice.getTotal() == 12) {
                        money -= 25;
                        System.out.println("You have $" + money);
                    } else {
                        int pointState = dice.getTotal();
                        System.out.println("You rolled point state. Your roll was " + pointState);
                        System.out.println("Press Any Key to Roll Again");
                        new java.util.Scanner(System.in).nextLine();
                        do { // do while loop 
                            PairOfDice dice1;
                            dice1 = new PairOfDice();
                            dice1.roll();
                            //System.out.println("You rolled a " + dice1.getTotal());
                            newRoll = dice1.getTotal();
                            if (money <= 0)
                                newRoll = 7;
                            else if (newRoll == 7) {
                                System.out.println("You rolled a " + dice1.getTotal());
                                money -= 25;
                                System.out.println("You have $" + money);
                                System.out.println("Press Any Key To Continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else if (newRoll == pointState) {
                                System.out.println("You rolled a " + dice1.getTotal());
                                money += 25;
                                System.out.println("You have $" + money);
                                System.out.println("Press Any Key To Continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else { // staying in pointState{
                                System.out.println("You rolled a " + dice1.getTotal());
                                money += 10;
                                System.out.println("You have $" + money);
                                System.out.println("You remain in point state.\nPress Any Key to Roll Again");
                                new java.util.Scanner(System.in).nextLine();
                            }
                        } while (newRoll != 7 && newRoll != pointState);
                    }
                    if (money <= 0) {
                        System.out.println("You ran out of money. You had a good run, but you are now broke. \nYou can play again starting with $100");
                        keepPlaying = false;
                        menu = true;
                        x = 4;
                        System.out.println("Press Any Key To Continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                    else{   
                    System.out.println("Do you wish to keep playing? Type n to go to the menu. Type any other key to continue playing");
                    String answer = scan.next();
                    answer = answer.toLowerCase();
                    if (answer.charAt(0) == 'n') {
                        keepPlaying = false;
                        menu = true;
                        program = true;
                        
                        
                    } else  {
                        System.out.println("Hope your lucks doesn't run out.\nPress Any Key To Continue...");
                        new java.util.Scanner(System.in).nextLine();
                        keepPlaying = true;
                    }
                    }
                }

            } //System.exit(0);
            else if (x == 2) {
                System.out.println("Instructions");
                System.out.println("You start with $100.\nOn the first roll if you get 7 or 11 you win $50.\nOn the first roll if you roll 2, 3, or 12 you lose $25.");
                System.out.println("On the first roll if you get 4,5,6,8,9, or 10 you enter point state.\nIn point state you have to reroll and:");
                System.out.println("-if you get a 7 you lose $25\n-if you get your original point state roll you win $25\n-if you roll anything else you win $10");
                System.out.println("You exit point state on a 7 or a reroll of your point state roll");
                System.out.println("Press Any Key To Go To The Menu...");
                new java.util.Scanner(System.in).nextLine();
                x = 4;
            } else if (x == 3) {

                while (quit == true) {
                    System.out.println("Are you sure you want to quit? Y/N:");
                    char q = reader.next().charAt(0);
                    if (q == 'y' || q == 'Y') {
                        System.exit(0);
                    } else if (q == 'n' || q == 'N') {
                        quit = false;
                        x = 4;

                    } else {
                        System.out.println("Error: Please enter y or n");
                        quit = true;
                    }
                }// quit = false; need to have a jump back to menu

            } else if (x == 4) {
                program = true;
                menu = true;
                quit = true;
                x = 1;
            }
        }// program
    }

}
// got this code from http://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-1-answer.html 
// I have explanations for each part below
class PairOfDice {


    private int die1;
    private int die2;

    public PairOfDice() {
        die1 = (int) (Math.random() * 6) + 1; // it adds one because modulo 6 ranges from 0,1,2,3,4,5
        die2 = (int) (Math.random() * 6) + 1;
    }

    public void roll() {
        this.die1 = die1;
        this.die2 = die2;
        die1 = (int) (Math.random() * 6) + 1; // it adds one because modulo 6 ranges from 0,1,2,3,4,5
        die2 = (int) (Math.random() * 6) + 1;

    }

    public int getDie1() { // this would return die1's value
        return die1;
    }

    public int getDie2() { // this would return die2's value
        return die2;
    }

    public int getTotal() { // this returns die1 and die2 together
        return die1 + die2;
    }
}
