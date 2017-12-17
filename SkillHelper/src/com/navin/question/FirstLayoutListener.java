package com.navin.question;

import android.annotation.SuppressLint;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.ViewTreeObserver;

/**
 * This global layout listener is used to fire an event after first layout
 * occurs and then it is removed. This gives us a chance to configure parts of
 * the UI that adapt based on available space after they have had the
 * opportunity to measure and layout.
 */
class FirstLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

	private ActionBarHelper mActionBar;
	private SlidingPaneLayout mSlidingLayout;
	
	public FirstLayoutListener(ActionBarHelper actionBar, SlidingPaneLayout slidingPaneLayout) {
		mActionBar = actionBar;
		mSlidingLayout = slidingPaneLayout;
	}

	@SuppressLint("NewApi")
	@Override
	public void onGlobalLayout() {
		mActionBar.onFirstLayout();
		mSlidingLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
	}
}
