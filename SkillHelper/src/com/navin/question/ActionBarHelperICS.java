package com.navin.question;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.widget.SlidingPaneLayout;


/**
 * Action bar helper for use on ICS and newer devices.
 */
class ActionBarHelperICS extends ActionBarHelper {
	private final ActionBar mActionBar;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private Activity mActivity;
	private SlidingPaneLayout mSlidingPaneLayout;
	
	ActionBarHelperICS(ActionBar ationBar, SlidingPaneLayout slidingLayout) {
		mActionBar = ationBar;
		mSlidingPaneLayout = slidingLayout;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void init() {
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		mTitle = mDrawerTitle = mActivity.getTitle();
	}

	@SuppressLint("NewApi")
	@Override
	public void onPanelClosed() {
		super.onPanelClosed();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setTitle(mTitle);
	}
	

	@SuppressLint("NewApi")
	@Override
	public void onPanelOpened() {
		super.onPanelOpened();
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setTitle(mDrawerTitle);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onFirstLayout() {
		if (mSlidingPaneLayout.canSlide() && ! mSlidingPaneLayout.isOpen()) {
			onPanelClosed();
		} else {
			onPanelOpened();
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
	}
}