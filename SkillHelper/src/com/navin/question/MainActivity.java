package com.navin.question;

import java.util.ArrayList;

import com.android.debug.hv.ViewServer;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	private SlidingPaneLayout mSlidingPaneLayout;
	private ListView mList;
	private Question[] mQuestions;
	private SparseIntArray mChosenAnswers;
	private View mContent;
	private QuestionView mQuesView;
	private ActionBarHelper mActionBarHelper;
	private ActionBar mActionBar;
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewServer.get(this).addWindow(this);
		 
		setContentView(R.layout.sliding_pane_layout);
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		mActionBar.setIcon(null);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayUseLogoEnabled(false);
		
		mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
		mActionBarHelper = createActionBarHelper();
		mSlidingPaneLayout.setPanelSlideListener(new SliderListener(mActionBarHelper));
		mSlidingPaneLayout.openPane();
		mSlidingPaneLayout.getViewTreeObserver().addOnGlobalLayoutListener(new FirstLayoutListener(mActionBarHelper, mSlidingPaneLayout));
		mContent = findViewById(R.id.scrollView1);
		mList = (ListView) findViewById(R.id.left_pane);
		
		ArrayAdapter<CharSequence> chapterAdapter = new ArrayAdapter<CharSequence>(this, R.layout.simple_list_item, R.id.text1);
		mList.setAdapter(chapterAdapter);
		
		ArrayList<CharSequence> al = new ArrayList<CharSequence>();
		al.add("Physics");
		al.add("Chemistry");
		ArrayAdapter<CharSequence> dropDownAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, al);
		mActionBar.setListNavigationCallbacks(dropDownAdapter, new DropDownNavigationListener(chapterAdapter, mSlidingPaneLayout));
		
		mQuestions = SkillHelperApplication.getQuestions();
		mChosenAnswers = new SparseIntArray();
		mQuesView = new QuestionView(MainActivity.this, mQuestions); 
		mList.setOnItemClickListener(new ListItemClickListener(mActionBarHelper, mSlidingPaneLayout, mContent, mQuesView));
		
		((ScrollView) mContent).removeAllViews();
		((ScrollView) mContent).addView(mQuesView.getQuestionsView());	
	}

	
	/**
	 * Create a compatible helper that will manipulate the action bar if
	 * available.
	 */
	private ActionBarHelper createActionBarHelper() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return new ActionBarHelperICS(mActionBar, mSlidingPaneLayout);
		} else {
			return new ActionBarHelper();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar up action should open the slider if it is currently
		 * closed, as the left pane contains content one level up in the
		 * navigation hierarchy. && !mSlidingPaneLayout.isOpen()
		 */
		if (item.getItemId() == android.R.id.home) {
			if (mSlidingPaneLayout.isOpen()) {
				mSlidingPaneLayout.smoothSlideClosed();
			} else {
				mSlidingPaneLayout.smoothSlideOpen();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressLint("InlinedApi")
	public void onSubmit(View view) {
		mQuesView.validateAnswers(mChosenAnswers, mContent, MainActivity.this);
	}
	
	public void onRefresh(View view) {
		UIUtil.makeViewGone(MainActivity.this, R.id.total_no_of_questions_holder);
		UIUtil.makeViewGone(MainActivity.this, R.id.no_of_questions_answered_holder);
		UIUtil.makeViewGone(MainActivity.this, R.id.no_of_correct_answers_holder);
		mChosenAnswers.clear();
		((ScrollView) mContent).removeAllViews();
		((ScrollView) mContent).addView(mQuesView.getQuestionsView());	
	}

	public void onRadioButtonClicked(View view) {
		ChosenAnswer chosenAnswer = new ChosenAnswer((String) view.getTag());
		mChosenAnswers.put(chosenAnswer.getmQuestionNumber(), chosenAnswer.getmChosenAnswer());
	}
	
	public void onDestroy() {
		super.onDestroy();
		ViewServer.get(this).removeWindow(this);
	}

	public void onResume() {
		super.onResume();
		ViewServer.get(this).setFocusedWindow(this);
	}

}
