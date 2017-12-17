package com.example.android.supportv4.widget;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appdata.DataUnit;
import com.example.android.supportv4.R;

public class SlidingPaneLayoutActivity extends Activity {
	private SlidingPaneLayout mSlidingLayout;
	private ListView mList;
	private WebView mContent;

	private ActionBarHelper mActionBar;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sliding_pane_layout);

		mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
		mList = (ListView) findViewById(R.id.left_pane);
		mContent = (WebView) findViewById(R.id.content_text);
		mContent.setTag(findViewById(R.id.progressBar1));
		mContent.getSettings().setJavaScriptEnabled(true);
		mSlidingLayout.setPanelSlideListener(new SliderListener());
		mSlidingLayout.openPane();

		mList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.simple_list_item, DataUnit.TITLES));
		mList.setOnItemClickListener(new ListItemClickListener());

		mActionBar = createActionBarHelper();
		mActionBar.init();
		mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(
				new FirstLayoutListener());
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar up action should open the slider if it is currently
		 * closed, as the left pane contains content one level up in the
		 * navigation hierarchy.
		 */
		if (item.getItemId() == android.R.id.home && !mSlidingLayout.isOpen()) {
			mSlidingLayout.smoothSlideOpen();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This list item click listener implements very simple view switching by
	 * changing the primary content text. The slider is closed when a selection
	 * is made to fully reveal the content.
	 */
	private class ListItemClickListener implements ListView.OnItemClickListener {
		@SuppressWarnings("deprecation")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mContent.loadUrl(DataUnit.DIALOGUE[position]);
			mActionBar.setTitle(DataUnit.TITLES[position]);
			mSlidingLayout.smoothSlideClosed();
		}
	}

	/**
	 * This panel slide listener updates the action bar accordingly for each
	 * panel state.
	 */
	private class SliderListener extends
			SlidingPaneLayout.SimplePanelSlideListener {
		@Override
		public void onPanelOpened(View panel) {
			mActionBar.onPanelOpened();
		}

		@Override
		public void onPanelClosed(View panel) {
			mActionBar.onPanelClosed();
		}
	}

	/**
	 * This global layout listener is used to fire an event after first layout
	 * occurs and then it is removed. This gives us a chance to configure parts
	 * of the UI that adapt based on available space after they have had the
	 * opportunity to measure and layout.
	 */
	private class FirstLayoutListener implements
			ViewTreeObserver.OnGlobalLayoutListener {
		@SuppressLint("NewApi")
		@Override
		public void onGlobalLayout() {
			mActionBar.onFirstLayout();
			mSlidingLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		}
	}

	/**
	 * Create a compatible helper that will manipulate the action bar if
	 * available.
	 */
	private ActionBarHelper createActionBarHelper() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return new ActionBarHelperICS();
		} else {
			return new ActionBarHelper();
		}
	}

	/**
	 * Stub action bar helper; this does nothing.
	 */
	private class ActionBarHelper {
		public void init() {
		}

		public void onPanelClosed() {
		}

		public void onPanelOpened() {
		}

		public void onFirstLayout() {
		}

		public void setTitle(CharSequence title) {
		}
	}

	/**
	 * Action bar helper for use on ICS and newer devices.
	 */
	private class ActionBarHelperICS extends ActionBarHelper {
		private final ActionBar mActionBar;
		private CharSequence mDrawerTitle;
		private CharSequence mTitle;

		@SuppressLint("NewApi")
		ActionBarHelperICS() {
			mActionBar = getActionBar();
		}

		@SuppressLint("NewApi")
		@Override
		public void init() {
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
			mTitle = mDrawerTitle = getTitle();
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
			mActionBar.setHomeButtonEnabled(false);
			mActionBar.setDisplayHomeAsUpEnabled(false);
			mActionBar.setTitle(mDrawerTitle);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onFirstLayout() {
			if (mSlidingLayout.canSlide() && !mSlidingLayout.isOpen()) {
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

}
