package com.victorszeto.dmcalc;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by victor on 2014-06-18.
 */
public class AboutActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView cr = (TextView) findViewById(R.id.credits);
        String credits = "Made with <font color=\"#ff8800\">&hearts;</font> by<br>" +
                "<a href=\"http://victorszeto.com\"><font color=\"#ff8800\">Victor Szeto</font></a> and <a href=\"http://timothytong.com\"><font color=\"#ff8800\">Timothy Tong</font></a>.";
        cr.setMovementMethod(LinkMovementMethod.getInstance());
        cr.setText(Html.fromHtml(credits));

        TextView twoTv = (TextView) findViewById(R.id.two);
        String two = "Based on the iOS app by Timothy Tong: <a href=\"https://itunes.apple.com/us/app/discrete-math-calculator/id846170613\">DMCalc</a>.";
        twoTv.setMovementMethod(LinkMovementMethod.getInstance());
        twoTv.setText(Html.fromHtml(two));

        TextView gh = (TextView) findViewById(R.id.github);
        String github = "We're open source! Check us out on <a href=\"http://www.github.com/victorvation/dmcalc\">GitHub</a>.";
        gh.setMovementMethod(LinkMovementMethod.getInstance());
        gh.setText(Html.fromHtml(github));

    }

    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), MyActivity.class);
//        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    public static void Launch(Context c) {
        Intent about = new Intent(c, AboutActivity.class);
        c.startActivity(about);
    }
}
