package com.navin.question;

public class ChosenAnswer {
	private int mQuestionNumber;
	private int mChosenAnswer;
	
	public ChosenAnswer(int questionNumber , int chosenAnswer) {
		mQuestionNumber = questionNumber;
		mChosenAnswer = chosenAnswer;
	}

	public int getmQuestionNumber() {
		return mQuestionNumber;
	}

	public int getmChosenAnswer() {
		return mChosenAnswer;
	}
	
	public ChosenAnswer(String str) {
		String[] ans = str.split("_");
		mQuestionNumber = Integer.parseInt(ans[0]);
		mChosenAnswer = Integer.parseInt(ans[1]);
	}
	
}
