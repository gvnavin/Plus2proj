package com.navin.question;

import android.app.ActionBar.OnNavigationListener;
import android.support.v4.widget.SlidingPaneLayout;
import android.widget.ArrayAdapter;

public class DropDownNavigationListener implements OnNavigationListener {

	private static final String[] mPhysics = { "Phy Chapter 1", "Phy Chapter 2", "Phy Chapter 3", "Phy Chapter 4", "Phy Chapter 5", "Phy Chapter 6" };
	private static final String[] mChemistry = { "Che Chapter 1", "Che Chapter 2", "Che Chapter 3", "Che Chapter 4", "Che Chapter 5", "Che Chapter 6" };
	private ArrayAdapter<CharSequence> mChapterAdapter;
	
	private SlidingPaneLayout mSlidingPaneLayout;
	
	public DropDownNavigationListener(ArrayAdapter<CharSequence> chapterAdapter, SlidingPaneLayout slidingPaneLayout) {
		mChapterAdapter = chapterAdapter;
		mSlidingPaneLayout = slidingPaneLayout;
	}

	public boolean onNavigationItemSelected(int itemPosition, long itemId) {

		switch (itemPosition) {
		case 0:
			modifyData(mPhysics);
			break;
		case 1:
			modifyData(mChemistry);
			break;
		}
		
		if (!mSlidingPaneLayout.isOpen())
			mSlidingPaneLayout.openPane();
		return true;
	}
	
	private void modifyData(String[] data) {
		mChapterAdapter.clear();
		mChapterAdapter.addAll(data);
		mChapterAdapter.notifyDataSetChanged();
	}
		
}
