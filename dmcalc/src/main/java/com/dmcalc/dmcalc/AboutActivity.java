package com.dmcalc.dmcalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by victor on 2014-06-18.
 */
public class AboutActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public static void Launch(Context c) {
        Intent about = new Intent(c, AboutActivity.class);
        c.startActivity(about);
    }
}
