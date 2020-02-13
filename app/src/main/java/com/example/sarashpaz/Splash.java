package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.sarashpaz.model.userApp;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Splash extends AppCompatActivity {

    userApp user = new userApp();
    public String StoredValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       StoredValue = returnExit();
       if(StoredValue == "exit"){
//           Toast.makeText(Splash.this, "OKKKKKKKKKKK!", Toast.LENGTH_SHORT).show();

       }
       else if(StoredValue == null){
//           Toast.makeText(Splash.this, "nulllllllllll!", Toast.LENGTH_SHORT).show();

       }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(Splash.this);
                user = sharedPreferencesManager.get_shared_preferences();


                if (user.getFirst_time_run() == true || StoredValue =="exit") {
                    Intent i = new Intent(Splash.this, Logins.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public String returnExit(){
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("Exit", MODE_PRIVATE);
        String val = myPrefs.getString("exit", null);
        return val;
    }
}
