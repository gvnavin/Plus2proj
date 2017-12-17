package com.example.android.supportv4.widget;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewClients extends WebViewClient {
	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		try {
			ProgressBar mbar = (ProgressBar) view.getTag();
			mbar.setVisibility(View.GONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		try {
			ProgressBar mbar = (ProgressBar) view.getTag();
			mbar.setVisibility(View.VISIBLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
