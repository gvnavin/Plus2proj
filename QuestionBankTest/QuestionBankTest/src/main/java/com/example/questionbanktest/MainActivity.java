package com.example.questionbanktest;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = "void main()<br/>" +
                "{\n" +
                "&nbsp;&nbsp;&nbsp;&nbsp;int const *p=5; <br/>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;printf(&quot;%d&quot;,++(*p));<br/>" +
                "}";
        //str = str.replace("\\\n", System.getProperty("line.separator"));
        //Spanned spanned = Html.fromHtml(str);
        ((TextView)findViewById(R.id.text)).setText(str);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
