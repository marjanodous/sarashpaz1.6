package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarashpaz.model.userApp;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SingUp extends AppCompatActivity {
    userApp user = new userApp();
    private Button btnSingUp;
    private EditText edtUName, edtPasword, edtEmail, edtPhone;
    TextView txtGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        btnSingUp = findViewById(R.id.ok_sing_up);
        edtUName = findViewById(R.id.edtunamesingup);
        edtPasword = findViewById(R.id.edtpnamesingup);
        edtEmail = findViewById(R.id.edtemailsingup);
        edtPhone = findViewById(R.id.edtphonesingup);
        txtGoToLogin = findViewById(R.id.txtgotologin);
        txtGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingUp.this,Logins.class));
                SingUp.this.finish();
            }
        });

        ////set true first time to SharedPreferences
        final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this);
        user = sharedPreferencesManager.get_shared_preferences();
        user.setFirst_time_run(true);
        sharedPreferencesManager.set_false_first_time(user);


        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = edtUName.getText().toString();
                String password = edtPasword.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();

                if (!userName.equals("") && !password.equals("") && !email.equals("") && !phone.equals("")) {

                    ///////////////Save UserName with SharedPreferences
                    SharedPreferences userDetails = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);
                    SharedPreferences.Editor edit = userDetails.edit();
                    edit.putString("uname", userName);
                    edit.putString("upass", password);
                    edit.apply();
//                    edit.commit();
                    //////////////////////////
                    startActivity(new Intent(SingUp.this, MainActivity.class));
                    SingUp.this.finish();

                    ////set false More than once to SharedPreferences
                    final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(SingUp.this);
                    user = sharedPreferencesManager.get_shared_preferences();
                    user.setFirst_time_run(false);
                    sharedPreferencesManager.set_false_first_time(user);
                } else {
                    Toast.makeText(SingUp.this, "لطفا اطلاعات را کامل کنید!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
