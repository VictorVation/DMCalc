package com.dmcalc.dmcalc;

import com.dmcalc.dmcalc.LDESolver;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(aboutIntent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
            Fragment fragment = AboutFragment.newInstance();
            switch(position) {
                case 0:
                    fragment = new DivModFragment();
                    fragment.setRetainInstance(true);
                    break;
                case 1:
                    fragment = new LDEFragment();
                    fragment.setRetainInstance(true);
                    break;
                case 2:
                    fragment = new RepeatSqFragment();
                    fragment.setRetainInstance(true);
                    break;
                default: break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                default: break;
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class AboutFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static AboutFragment newInstance() {
            AboutFragment fragment = new AboutFragment();
            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public AboutFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Hello!");
            return rootView;
        }
    }

    public class DivModFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {
        public DivModFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_divmod, container, false);
            Button calculateDivMod = (Button) rootView.findViewById(R.id.calculateDivMod);
            calculateDivMod.setOnClickListener(this);
            EditText divisText = (EditText) rootView.findViewById(R.id.divisor);
            divisText.setOnKeyListener(this);
            return rootView;
        }

        @Override
        public void onClick(View v) {
            hideKeyboard();

            EditText dividText = (EditText) findViewById(R.id.dividend);
            EditText divisText = (EditText) findViewById(R.id.divisor);
            System.out.println(dividText.getText());
            String dividStr = dividText.getText().toString();
            String divisStr = divisText.getText().toString();

            if (dividStr.length() == 0 || divisStr.length() == 0) {
                sendToast("You forgot a number!");
                return;
            }

            if (divisStr.equals("0")) {
                sendToast("Can't divide by zero!");
                return;
            }

            long dividend = Long.valueOf(dividStr);
            long divisor = Long.valueOf(divisStr);

            long quotient = dividend / divisor;
            long remainder = dividend % divisor;

            TextView quotView = (TextView) findViewById(R.id.quotient);
            TextView remView = (TextView) findViewById(R.id.remainder);

            quotView.setText(String.valueOf(quotient));
            remView.setText(String.valueOf(remainder));
        }

        // Submit on enter
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                this.onClick(v);
            }
            return false;
        }
    }

    public class LDEFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {
        public LDEFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lde, container, false);
            Button calculate = (Button) rootView.findViewById(R.id.calculateLDE);
            calculate.setOnClickListener(this);

            EditText modText = (EditText) rootView.findViewById(R.id.c);
            modText.setOnKeyListener(this);

            return rootView;
        }

        @Override
        public void onClick(View v) {
            hideKeyboard();

            EditText a = (EditText) findViewById(R.id.a);
            EditText b = (EditText) findViewById(R.id.b);
            EditText c = (EditText) findViewById(R.id.c);

            String aStr = a.getText().toString();
            String bStr = b.getText().toString();
            String cStr = c.getText().toString();

            if (aStr.length() == 0 || bStr.length() == 0 || cStr.length() == 0) {
                sendToast("You forgot a coefficient!");
                return;
            }

            int aInt = Integer.valueOf(aStr);
            int bInt = Integer.valueOf(bStr);
            int cInt = Integer.valueOf(cStr);

            if (aInt == 0 || bInt == 0 || cInt == 0) {
                sendToast("One of your coefficients was invalid!");
                return;
            }

            int[] answer = LDESolver.solveLDE(aInt, bInt, cInt);

            String xStr = String.valueOf(answer[0]) + "\u00B1" + String.valueOf(bInt);
            String yStr = String.valueOf(answer[1]) + "\u2213" + String.valueOf(aInt);

            TextView xOut = (TextView) findViewById(R.id.xOut);
            TextView yOut = (TextView) findViewById(R.id.yOut);

            xOut.setText(xStr);
            yOut.setText(yStr);
        }

        // Submit on enter
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                this.onClick(v);
            }
            return false;
        }
    }

    public class RepeatSqFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {
        public RepeatSqFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_repeatsq, container, false);
            Button calculateRSq = (Button) rootView.findViewById(R.id.calculateRSq);
            calculateRSq.setOnClickListener(this);

            EditText modText = (EditText) rootView.findViewById(R.id.mod);
            modText.setOnKeyListener(this);

            return rootView;
        }

        @Override
        public void onClick(View v) {
            hideKeyboard();

            EditText baseText = (EditText) findViewById(R.id.base);
            EditText expText = (EditText) findViewById(R.id.exp);
            EditText modText = (EditText) findViewById(R.id.mod);

            if(modText.getText().toString().equals("0") || modText.getText().toString().equals("1")) {
                sendToast("Invalid modulus.");
                return;
            }

            if(baseText.getText().length() == 0 ||
               expText.getText().length() == 0 ||
               modText.getText().length() == 0 ) {
                sendToast("You forgot a number!");
                return;
            }

            LDESolver ldeSolver = new LDESolver();

            BigInteger base = new BigInteger(baseText.getText().toString());
            BigInteger exp = new BigInteger(expText.getText().toString());
            BigInteger mod = new BigInteger(modText.getText().toString());

            //BigInteger rem = base.modPow(exp, mod);   BACKUP METHOD
            BigInteger rem = RSSolver.repeatedSquare(base, exp, mod);

            TextView baseView = (TextView) findViewById(R.id.baseOut);
            TextView expView = (TextView) findViewById(R.id.expOut);
            TextView remView = (TextView) findViewById(R.id.remOut);
            TextView modView = (TextView) findViewById(R.id.modOut);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) remView.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_RIGHT, R.id.base);
            remView.setLayoutParams(params);

            baseView.setText(base.toString());
            expView.setText(exp.toString());
            remView.setText(rem.toString());
            modView.setText('(' + mod.toString() + ')');
        }

        // Submit on enter
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                this.onClick(v);
            }
            return false;
        }
    }

    // Helper methods used in all fragments
    protected void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void sendToast(String msg) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

}
