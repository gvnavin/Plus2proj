package com.navin.question;
/**
 * 
 */

/**
 * @author gnavin
 *
 */
public class Question {

	private String question;
	private String[] answers;
	private boolean[] answers_flag;
	private int correct_answer;

	public Question() {
	}

	public String getQuestion() {
		return question;
	}
	
	public String getOption(int i) {
		return answers[i];
	}
	
	public String getOption_0() {
		return answers[0];
	}
		
	public String getOption_1() {
		return answers[1];
	}
	
	public String getOption_2() {
		return answers[2];
	}
	
	public String getOption_3() {
		return answers[3];
	}
	
	public int getCorrectAnswerIndex() {
		return correct_answer;
	}
	
	public String getCorrectAnswerText() {
		return answers[correct_answer];
	}
}
