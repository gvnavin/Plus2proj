package com.navin.question;

import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * This list item click listener implements very simple view switching by
 * changing the primary content text. The slider is closed when a selection
 * is made to fully reveal the content.
 */
class ListItemClickListener implements ListView.OnItemClickListener {
	
	private ActionBarHelper mActionBar;
	private SlidingPaneLayout mSlidingLayout;
	private View mContent;
	private QuestionView mQuesView;
	
	public ListItemClickListener(ActionBarHelper actionBarHelper, SlidingPaneLayout slidingPaneLayout, View content, QuestionView quesView) {
		mActionBar = actionBarHelper;
		mSlidingLayout = slidingPaneLayout;
		mContent = content;
		mQuesView = quesView;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mActionBar.setTitle("");
		((ScrollView) mContent).removeAllViews();
		((ScrollView) mContent).addView(mQuesView.getQuestionsView());
		mSlidingLayout.smoothSlideClosed();
	}
}
