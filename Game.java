import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    Player computer;
    Player user;
    HashMap<Integer, String> combinations = new HashMap<>();
    int rounds;
    Game(){
        combinations.put(1, "Rock");
        combinations.put(2, "Paper");
        combinations.put(3, "Scissor");
        combinations.put(4, "Reset");
        combinations.put(5, "Exit");
        computer = new Player();
        user = new Player();
        rounds = 0;
    }

    static void printIntro(){
        System.out.println("Welcome to the Rock-Paper-Scissors-Game!");
        System.out.println("You can make your choice by typing in a number into the command line.");
        System.out.println("The game rules are the standard rules of Rock-Paper-Scissors.");
        System.out.println("Have fun!");
        System.out.println("#################################################");
    }

    //prints the options of the player
    static void printChoices(){
        System.out.println("You have these options:");
        System.out.println("1: Rock");
        System.out.println("2: Paper");
        System.out.println("3: Scissor");
        System.out.println("4: Reset");
        System.out.println("5: Exit");
    }

    //gets the user input from the console
    int getUserChoice(){
        Scanner scan = new Scanner(System.in);
        int input = 0;
        while (!(input >= 1 && input <= 5)){
            printChoices();
            try {
                input = scan.nextInt();
                if (input < 1 || input > 5){
                    System.out.println("Something was wrong with your input. Try again!");
                }
            } catch (InputMismatchException e){
                System.out.println("Something was wrong with your input. Try again!");
                scan.nextLine();
            }
        }
        String choice = this.combinations.get(Integer.valueOf(input));
        System.out.println("You chose " + choice);
        return input;
    }


    int getComputerChoice(){
        int i = 1 + (int)(Math.random()*3);
        String choice = this.combinations.get(Integer.valueOf(i));
        System.out.println("The computer chose " + choice);
        return i;
    }

    void userWins(){
        System.out.println("Congratulations, you won this round!");
        this.user.score++;
    }

    void computerWins(){
        System.out.println("Sorry, the computer won this round... Maybe next time!");
        this.computer.score++;
    }

    void tiedGame(){
        System.out.println("That was a tied game, since you chose the same as the computer this round.");
    }

    void makeRound(int userChoice, int computerChoice){
        this.rounds++;
        String userChoiceString = this.combinations.get(Integer.valueOf(userChoice));
        String computerChoiceString = this.combinations.get(Integer.valueOf(computerChoice));
        switch (userChoiceString){
            case "Rock":
                if (computerChoiceString == "Rock"){
                    this.tiedGame();
                } else if (computerChoiceString == "Paper"){
                    this.computerWins();
                } else if (computerChoiceString == "Scissor"){
                    this.userWins();
                }
                break;

            case "Paper":
                if (computerChoiceString == "Rock"){
                    this.userWins();
                } else if (computerChoiceString == "Paper"){
                    this.tiedGame();
                } else if (computerChoiceString == "Scissor"){
                    this.computerWins();
                }
                break;

            case "Scissor":
                if (computerChoiceString == "Rock"){
                    this.computerWins();
                } else if (computerChoiceString == "Paper"){
                    this.userWins();
                } else if (computerChoiceString == "Scissor"){
                    this.tiedGame();
                }
                break;
        }
    }

    void printStats(){
        System.out.println("These are the actual stats:");
        System.out.println("Round: " + this.rounds);
        System.out.println("Your score: " + this.user.score);
        System.out.println("Computer's score: " + this.computer.score);
        System.out.println("#################################################");
    }

    void refreshGame(){
        this.rounds = 0;
        this.user.score = 0;
        this.computer.score = 0;
        printIntro();
    }

    void play(){
        printIntro();
        while(user.choice != 5){
            user.choice = this.getUserChoice();

            if(user.choice == 4){
                this.refreshGame();
            } else if (user.choice < 4) {
                computer.choice = getComputerChoice();
                this.makeRound(user.choice, computer.choice);
                this.printStats();
            } else if (user.choice == 5){
                break;
            }
        }
    }
}
