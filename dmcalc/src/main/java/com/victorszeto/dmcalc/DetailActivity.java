package com.victorszeto.dmcalc;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by victor on 2014-08-02.
 */
public class DetailActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            TextView details = (TextView) findViewById(R.id.details);

            String solution = getIntent().getStringExtra("solution");
            details.setText(solution);
        }
    }
    public static void Launch(Context c) {
        Intent about = new Intent(c, AboutActivity.class);
        c.startActivity(about);
    }
}
