package com.icelabs.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Switch mEnableSharedPrefs;
    AppCompatButton mSave, mRetrieve;
    EditText fname, lname;
    SharedPreferences sharedPrefs;
    TextView tFname, tLname;

    public static final String mPREFERENCES = "myPrefs";
    public static final String F_NAME = "fnameKey";
    public static final String L_NAME = "lnameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        mSave = findViewById(R.id.btn_save);
        mRetrieve = findViewById(R.id.btn_retrieve);
        fname = findViewById(R.id.edt_fname);
        lname = findViewById(R.id.edt_lname);
        tFname = findViewById(R.id.tv_fname);
        tLname = findViewById(R.id.tv_lname);

        mEnableSharedPrefs = findViewById(R.id.chk_enable);
        mEnableSharedPrefs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enableSharedPrefs();
                } else {
                    Toast.makeText(MainActivity.this, "Shared preferences NOT enabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveInformation();
            }
        });

    }

    private void enableSharedPrefs() {
        Toast.makeText(this, "Shared preferences enabled", Toast.LENGTH_SHORT).show();
        sharedPrefs = getSharedPreferences(mPREFERENCES, Context.MODE_PRIVATE);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEnableSharedPrefs.isChecked()) {
                    Toast.makeText(MainActivity.this, "Unable to save: Shared preferences not enabled", Toast.LENGTH_SHORT).show();
                } else {

                    String f = fname.getText().toString();
                    String l = lname.getText().toString();

                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString(F_NAME, f);
                    editor.putString(L_NAME, l);

                    editor.apply();

                    Toast.makeText(MainActivity.this, "Information saved", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void retrieveInformation() {
        sharedPrefs = getSharedPreferences(mPREFERENCES, Context.MODE_PRIVATE);
        String f2 = sharedPrefs.getString(F_NAME, "");
        String l2 = sharedPrefs.getString(L_NAME, "");

        tFname.setText(f2);
        tLname.setText(l2);

        Toast.makeText(this, "SUCCESS: Retrieved Information", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
