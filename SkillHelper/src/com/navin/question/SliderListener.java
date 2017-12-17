package com.navin.question;

import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;

/**
 * This panel slide listener updates the action bar accordingly for each
 * panel state.
 */
class SliderListener extends
		SlidingPaneLayout.SimplePanelSlideListener {
	
	private ActionBarHelper mActionBar;
	
	public SliderListener(ActionBarHelper actionBar) {
		mActionBar = actionBar;
	}
	
	@Override
	public void onPanelOpened(View panel) {
		mActionBar.onPanelOpened();
	}

	@Override
	public void onPanelClosed(View panel) {
		mActionBar.onPanelClosed();
	}
}

