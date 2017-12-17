package com.navin.question;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UIUtil {
  
    public static void updateText(View view, int textId, int textContentId) {
        TextView textView = (TextView)view.findViewById(textId);
        textView.setText(textContentId);
    }
    
    public static void updateText(View view, int textId, String textContent) {
        TextView textView = (TextView)view.findViewById(textId);
        textView.setText(textContent);
    }

    public static void updateText(Activity activity, int textId, String textContent) {
        TextView textView = (TextView)activity.findViewById(textId);
        textView.setText(textContent);
    }
    
    public static void updateTag(View view, int textId, String tagContent) {
        view.findViewById(textId).setTag(tagContent);
    }
    
    public static void makeViewVisible(Activity activity, int id) {
    	activity.findViewById(id).setVisibility(View.VISIBLE);
    }
    
    public static void makeViewGone(Activity activity, int id) {
    	activity.findViewById(id).setVisibility(View.GONE);
    }
    
    public static void makeViewVisible(View view, String tag) {
    	view.findViewWithTag(tag).setVisibility(View.VISIBLE);
    }
    
    public static void updateImage(View view, int imgId, int image) {
        ImageView imageView = (ImageView)view.findViewById(imgId);
        imageView.setImageResource(image);
    }

}
