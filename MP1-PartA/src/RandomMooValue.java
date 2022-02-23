/**
 * Creates a 4-digit secret value (0000 through 9999) for a player to guess. Feedback is returned in the form of big and little moos. Each
 * "MOO!" indicates a digit correctly guessed in both value and position. Each "moo." indicates a digit correctly guessed in terms of
 * value, but not position. If no digits are correctly guessed, then all the user hears are cowbells... Please note that the number generated
 * by the program can be any four-digit number: it can have leading zeros, it can have multiple instances of the same digit, and so on.
 * For example, the following values are all possible: 0000, 0123, 3455, 7870. When generating big (MOO!) and little (moo.) moos, each
 * guessed digit can only match at most one digit in the secret value. For example, if the secret value is 0055 and the user's guess is
 * 5550, our favorite cow should be uttering "MOO! moo. moo." as there is an exact match in digit position 3, plus two inexact matches.
 * @author Hailey Wendt
 * @version 1.0 - 15 February 2022
 */

import java.util.Random;

public class RandomMooValue {

	private final static int NUM_DIGITS = 4;
	private int[] guessArray = new int[NUM_DIGITS];
	private int[] secretArray = new int[NUM_DIGITS];
	private boolean[] guessFlagArray = new boolean[NUM_DIGITS];
	private boolean[] secretFlagArray = new boolean[NUM_DIGITS];
	private int secretValue;
	private int numberOfLittleMoos;
	private int numberOfBigMoos;

	/**
	 * Creates a new RandomMooValue object containing a secret value to be guessed.
	 */
	public RandomMooValue() {
	}

	/**
	 * Generates a new secret value to be guessed in a game of LaurieMOO.
	 * @return true in all cases.
	 */
	public boolean setSecretValue() {
		Random rand = new Random();
		secretValue = rand.nextInt(10000)+1;
		System.out.println(secretValue); 
		int secretTemp = secretValue; 
		secretArray[3] = secretTemp%10;
		secretTemp /= 10; 
		secretArray[2] = secretTemp%10; 
		secretTemp /= 10; 
		secretArray[1] = secretTemp%10;
		secretTemp /= 10;
		secretArray[0] = secretTemp%10;
		secretTemp /= 10;
		return true; 
	}

	/**
	 * Sets the "secret" value to be guessed in a game of LaurieMOO to a known
	 * 4-digit quantity. This method is for testing purposes only.
	 * @param n - The number that will be set as the secret value, if it is within
	 *          the inclusive range of 0000-9999.
	 * @return true if the secret value was reset; false if the passed value was
	 *         outside of the allowed range of values.
	 */
	public boolean setSecretValue(int n) {
		String temp = String.valueOf(n); 
		if (n>9999) {
			if (n<0) {
				return false;
			}
		}
		if (temp.length() != 4) {
			if (temp.length() == 1) {
				secretArray[3] = n%10;
				n /= 10; 
				secretArray[2] = 0; 
				secretArray[1] = 0;
				secretArray[0] = 0;
			}
			else if (temp.length() == 2) {
				secretArray[3] = n%10;
				n /= 10; 
				secretArray[2] = n;
				secretArray[1] = 0;
				secretArray[0] = 0;
			}
			else if (temp.length() == 3) {
				secretArray[3] = n%10;
				n /= 10; 
				secretArray[2] = n%10; 
				n /= 10; 
				secretArray[1] = n;
				secretArray[0] = 0;
			}
			return true; 
		}
		else {
			secretArray[3] = n%10;
			n /= 10; 
			secretArray[2] = n%10; 
			n /= 10; 
			secretArray[1] = n%10;
			n /= 10;
			secretArray[0] = n%10;
			n /= 10;
		secretValue = n;
		return true; 
		}
	}

	/**
	 * The number of digits in the user's guess that exactly (i.e., same position)
	 * matches digits in the secret value.
	 * @param guess - The number that the user guessed.
	 * @return a number 0-4 representing how many digits the user guessed correctly
	 *         by position.
	 */
	public int getBigMooCount(int guess) {
		int tempValue = guess; 
		numberOfBigMoos = 0; 
		guessArray[3] = tempValue%10;
		tempValue /= 10; 
		guessArray[2] = tempValue%10;
		tempValue /= 10; 
		guessArray[1] = tempValue%10;
		tempValue /= 10;
		guessArray[0] = tempValue%10;
		tempValue /= 10;
		for (int i = 0; i < NUM_DIGITS; i++) {
			secretFlagArray[i] = false; 
			guessFlagArray[i] = false; 
			if ( guessArray[i] == secretArray[i] ) {
				secretFlagArray[i] = true; 
				guessFlagArray[i] = true;
				numberOfBigMoos++; 
			} 
		}
		numberOfLittleMoos = 0; 
		return numberOfBigMoos;
	}

	/**
	 * The number of digits in the user's guess that match digits in the secret
	 * value, but are not at the same position.
	 * 
	 * @param guess - The number that the user guessed.
	 * @return a number 0-4 representing how many of the guessed digits match, but
	 *         are in different positions. Note that a guessed number cannot have
	 *         any one digit generate both a "MOO!" and a "moo." as a result.
	 */
	public int getLittleMooCount(int guess) {
		for ( int i = 0; i < NUM_DIGITS; i++) {
			if (guessFlagArray[i]) {
				continue; 
			}
			for ( int j = 0; j < NUM_DIGITS; j++) {
				if (secretFlagArray[j]) {
					continue; 
				}
				if (guessArray[i] == secretArray[j]) {
					guessFlagArray[i] = true; 
					secretFlagArray[j] = true; 
					numberOfLittleMoos++; 
					break; 
				}
			}
		}
		return numberOfLittleMoos;
	}

	/** Access the secret value that the user is trying to guess, primarily to show
	 * the user after running out of guesses.
	 * @return the secret value that the user is/was attempting to guess.
	 */
	public int getSecretValue() {
		return secretValue;
	}

	/**
	 * Determines if the user correctly guessed the secret value.
	 * @param guess - The number that the user guessed.
	 * @return true if the guess is correct, false otherwise.
	 */
	public boolean isCorrectGuess(int guess) {
		if (numberOfBigMoos == 4) {
			return true;
		}
		else {
			return false; 
		}
	}
}
