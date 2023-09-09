2//Name: Michael Pollock
//Time: 220 minutes
//References: N/A

public class Guess{
	public static void main(String [] args){
		//Play again
		String playAgain = "y";
		while (playAgain.equals("y")){

			//select difficulty
			ConsoleIO.printLine("5) Expert");
			ConsoleIO.printLine("4) Very Hard");
			ConsoleIO.printLine("3) Normal");
			ConsoleIO.printLine("2) Easy");
			ConsoleIO.printLine("1) Very Easy");
			ConsoleIO.print("Please select difficulty: ");
			int gameDif = ConsoleIO.readInt();
			while (gameDif > 1 && gameDif > 5){
				ConsoleIO.print("Please select a valid input (1-5). ");
				gameDif = ConsoleIO.readInt();
			}
			ConsoleIO.printLine("");//aesthetic

			//generate random number to be guessed based on difficulty chosen
			int secretNum = 0;
			int counter = 1;
			int remainGuess = 12;
			if (gameDif == 5){ //Expert
				secretNum = (int)(Math.random()*15001);
				if (Math.random() < .5){
					secretNum = -secretNum;
				}
				ConsoleIO.print("Would you like to unlock a hint at the cost of 2 guesses? (y/n) ");
				String hint = ConsoleIO.readLine();
				while (!hint.equals("y") && !hint.equals("n")){
					ConsoleIO.print("Please enter a valid choice. ");
					hint = ConsoleIO.readLine();
				}
				if (hint.equals("y")){
					ConsoleIO.printLine("");//aesthetic
					ConsoleIO.printLine("You originally started with 12 guesses. The scope is 15,000 in either direction. Good luck.");
					remainGuess = 10;
					ConsoleIO.printLine("Remaining guesses: "+remainGuess);
				}
				ConsoleIO.print("Guess a random number: ");
			} else if (gameDif == 4){ //Very Hard
				secretNum = (int)(Math.random()*10001);
				if (Math.random() < .5){
					secretNum = -secretNum;
				}
				ConsoleIO.printLine("All aids have been replaced with less helpful aids. Good luck. ");
				ConsoleIO.print("Guess a random number: ");
			} else if (gameDif == 3){ //Normal
				secretNum = (int)(Math.random()*101);
				ConsoleIO.print("Guess a number from 0 to 100: ");
			} else if (gameDif == 2){ //Easy
				secretNum = (int)(Math.random()*11);
				ConsoleIO.print("Guess a number from 0 to 10: ");
			} else { //Very Easy
				ConsoleIO.print("You are unknowingly clairvoyant. Please, tell us what number you are thinking of: ");
			}

			//Initial Guess
			int guess = ConsoleIO.readInt();

			//Very Easy Mode Guess
			if (gameDif == 1){
				secretNum = guess;
				ConsoleIO.printLine("");//aesthetic
				ConsoleIO.printLine("Amazing! "+secretNum+" is the number we were thinking of too!");
			}

			//Normal and Easy Mode Guesses
			while (guess != secretNum && gameDif < 4){
				ConsoleIO.printLine("");//aesthetic
				if (guess < secretNum){
					ConsoleIO.print("The number you are searching for is higher than "+guess+". Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else if (guess > secretNum && gameDif < 4){
					ConsoleIO.print("The number you are searching for is lower than "+guess+". Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				}
			}

			//Very Hard Mode Guesses
			int lastGuess = guess;
			while (guess != secretNum && gameDif == 4){
				//Working through logic
				//sn 500, lg 750 g 900. sn - lg = -250. sn - g = -400. abs sn-lg<sn-g lg closer, colder
				//sn 500, lg 750, g 300. sn - lg = -250. sn - g = 200. abs sn-lg>sn-g g closer, warmer
				//sn -500 lg 700, g 600. sn-lg=-1200, sn-g=-1100 abs sn-lg>sn-g g closer, warmer
				//sn -500 lg 700, g 900. sn-lg=-1200, sn-g=-1400 abs sn-lg<sn-g lg closer, colder
				//use abs to detirmine distance from sn
				int difG = Math.abs(secretNum - guess);
				int difLG = Math.abs(secretNum - lastGuess);
				lastGuess = guess;

				ConsoleIO.printLine("");//aesthetic
				if (counter > 1 && difLG < difG){
					ConsoleIO.printLine("Wrong. Colder. ");
				} else if (counter > 1 && difLG > difG){
					ConsoleIO.printLine("Wrong, but warmer. ");
				} else if (counter > 1 && difLG == difG){
					ConsoleIO.printLine("Equally wrong.");
				}
				if (secretNum - guess > 9000 || secretNum - guess < -9000){
					ConsoleIO.print("You are incorrect by over 9000. Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else if (secretNum - guess > 1000 || secretNum - guess < -1000){
					ConsoleIO.print("You are incorrect by over 1000. Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else if (secretNum - guess > 500 || secretNum - guess < -500){
					ConsoleIO.print("You are incorrect by over 500. Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else if (secretNum - guess > 50 || secretNum - guess < -50){
					ConsoleIO.print("You are incorrect by over 50. Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else if (secretNum - guess > 50 || secretNum - guess < -50){
					ConsoleIO.print("You are incorrect by over 5. Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else {
					ConsoleIO.print("Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				}
			}

			//Expert Mode Guesses
			while (gameDif == 5 && remainGuess > 1 && guess != secretNum){
				remainGuess--;
				ConsoleIO.printLine("");//aesthetic
				ConsoleIO.printLine("Guesses remaining: "+remainGuess);
				if (guess < secretNum){
					ConsoleIO.print("The number you are searching for is higher than "+guess+". Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				} else if (guess > secretNum){
					ConsoleIO.print("The number you are searching for is lower than "+guess+". Try again: ");
					guess = ConsoleIO.readInt();
					counter++;
				}
			}

			//end when 5 and no guesses left
 			if (gameDif == 5 && remainGuess == 1) {
				ConsoleIO.printLine("");//aesthetic
				ConsoleIO.printLine("Sorry, you lose! You'll never know the secret number!");
				ConsoleIO.printLine("(it was "+secretNum+").");
			}

			//end when correct
			if (guess == secretNum && gameDif != 1){
				ConsoleIO.printLine("");//aesthetic
				ConsoleIO.printLine("Congrats! You have guessed "+guess+" and our secret number was also "+secretNum+".");
				ConsoleIO.printLine("It took you "+counter+" guesses.");
			}
			ConsoleIO.printLine("");//aesthetic
			ConsoleIO.print("Would you like to play again? (y/n) ");
			playAgain = ConsoleIO.readLine();
			while (!playAgain.equals("y") && !playAgain.equals("n")){
				ConsoleIO.print("Please enter a valid choice. ");
				playAgain = ConsoleIO.readLine();
			}
			ConsoleIO.printLine("");//aesthetic
		}
	}
}