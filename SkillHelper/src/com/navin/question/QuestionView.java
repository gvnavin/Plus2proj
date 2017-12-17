package com.navin.question;

import java.util.Arrays;
import java.util.Collections;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionView {

	private Context mContext;
	private Question[] mQuestions;
	private LayoutInflater inflater; 
	private static final int[] mOptions = { R.id.option_0, R.id.option_1, R.id.option_2, R.id.option_3 };
	
	public QuestionView(Context context, Question[] questions) {
		mContext = context;
		mQuestions = questions;
		inflater = LayoutInflater.from(mContext);
	}
	
	private LinearLayout getQuestionHolder() {
		LinearLayout holder = new LinearLayout(mContext);
		holder.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT); 
		holder.setLayoutParams(layoutParams);
		return holder;
	}
	
	private void shuffle() {
		Collections.shuffle(Arrays.asList(mQuestions));
	}
	
	public View getQuestionsView() {
		LinearLayout holder = getQuestionHolder();
		shuffle();
		for (int i = 0; i < mQuestions.length; i++) {
			View question = inflater.inflate(R.layout.question, null);
			UIUtil.updateText(question, R.id.question_no, Integer.toString(i + 1)); 
			UIUtil.updateText(question, R.id.question_text, mQuestions[i].getQuestion());

			for (int j = 0; j < 4; j++) {
				((RadioButton) question.findViewById(mOptions[j])).setText(mQuestions[i].getOption(j));
				UIUtil.updateTag(question, mOptions[j], i + "_" + j);
			}
			
			UIUtil.updateTag(question, R.id.chosen_answer_label, i + "_chosen_answer_label");
			UIUtil.updateTag(question, R.id.chosen_answer, i + "_chosen_answer");
			UIUtil.updateTag(question, R.id.correct_answer_label, i + "_correct_answer_label");
			UIUtil.updateTag(question, R.id.correct_answer, i + "_correct_answer");

			holder.addView(question);
		}
		return holder;
	}
	
	private static final int valueIfKeyNotFound = -1;
	
	@SuppressLint("InlinedApi")
	public void validateAnswers(SparseIntArray mChosenAnswers , View content, Activity actvity) {
		int noOfQuestionsAnswered = 0;
		int noOfCorrectAnswers = 0;
		for (int i = 0; i < mQuestions.length; i++) {
			int chosenAnswerIndex = mChosenAnswers.get(i, valueIfKeyNotFound);
			if (chosenAnswerIndex != valueIfKeyNotFound) {
				
				noOfQuestionsAnswered ++;
				
				((TextView) content.findViewWithTag(i + "_chosen_answer")).setText(mQuestions[i].getOption(chosenAnswerIndex));
				((TextView) content.findViewWithTag(i + "_correct_answer")).setText(mQuestions[i].getCorrectAnswerText());
				
				UIUtil.makeViewVisible(content, i + "_chosen_answer");
				UIUtil.makeViewVisible(content, i + "_chosen_answer_label");
				UIUtil.makeViewVisible(content, i + "_correct_answer");
				UIUtil.makeViewVisible(content, i + "_correct_answer_label");
							
				int color ;
				if ( chosenAnswerIndex == mQuestions[i].getCorrectAnswerIndex() ) {
					noOfCorrectAnswers ++;
					color = android.R.color.holo_green_dark;
					
				} else {
					color = android.R.color.holo_red_dark;
				}
				((TextView) content.findViewWithTag(i + "_chosen_answer")).setTextColor(mContext.getResources().getColor(color));
				((TextView) content.findViewWithTag(i + "_chosen_answer_label")).setTextColor(mContext.getResources().getColor(color));
			} 
			
			UIUtil.makeViewVisible(actvity, R.id.total_no_of_questions_holder);
			UIUtil.makeViewVisible(actvity, R.id.no_of_questions_answered_holder);
			UIUtil.makeViewVisible(actvity, R.id.no_of_correct_answers_holder);
			
			UIUtil.updateText(actvity, R.id.total_no_of_questions, "" + mQuestions.length);
			UIUtil.updateText(actvity, R.id.no_of_questions_answered, "" + noOfQuestionsAnswered);
			UIUtil.updateText(actvity, R.id.no_of_correct_answers, ""+ noOfCorrectAnswers);
			
		}
	}
	
}
