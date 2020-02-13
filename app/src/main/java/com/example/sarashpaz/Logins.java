package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarashpaz.model.userApp;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Logins extends AppCompatActivity {
    userApp user = new userApp();
    EditText edtUName, edtuPass;
    TextView txtGoToSingUp;
    Button btnOkSingUp;
    public String StoredValue;
    public boolean U,P;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        txtGoToSingUp = findViewById(R.id.txtgotosingup);
        btnOkSingUp = findViewById(R.id.ok_sing_up);
        edtUName = findViewById(R.id.username_login);
        edtuPass = findViewById(R.id.password_login);

        StoredValue = returnExit();
        if (StoredValue == "exit") {
//            Toast.makeText(Logins.this, "okkkkkkk Exit!", Toast.LENGTH_SHORT).show();
            txtGoToSingUp.setEnabled(false);
            txtGoToSingUp.setText("");
        } else if (StoredValue == null) {
//            Toast.makeText(Logins.this, "Noooooo Exit!", Toast.LENGTH_SHORT).show();
        }

        txtGoToSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Logins.this, SingUp.class));
                Logins.this.finish();
            }
        });
        btnOkSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(Logins.this);
                user = sharedPreferencesManager.get_shared_preferences();

                if (user.getFirst_time_run() == true) {
//
                    Toast.makeText(Logins.this, "لطفا ثبت نام کنید!", Toast.LENGTH_SHORT).show();
                } else {
                    U = edt_name_Equle();
                    P = edt_Pass_Equle();
                    if (U && P) {
                        Toast.makeText(Logins.this, "کاربر سیستم خوش آمدید ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Logins.this, MainActivity.class));
                        Logins.this.finish();
                        delExit();
                    } else if(U) {
                        Toast.makeText(Logins.this, "رمز عبور نابرابر است...", Toast.LENGTH_SHORT).show();
                    }
                    else if(P) {
                        Toast.makeText(Logins.this, "نام کاربری نابرابر است...", Toast.LENGTH_SHORT).show();
                    }
                    else if(!P&&!U) {
                        Toast.makeText(Logins.this, "نام کاربری و رمز عبور نابرابر است...", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Logins.this, "اطلاعات را کامل کنید...", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void delExit() {
        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("Exit", MODE_PRIVATE);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.remove("exit");
        edit.commit();
    }

    public String getUname() {
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("User", MODE_PRIVATE);
        String storedValue = myPrefs.getString("uname", null);
        return storedValue;
    }
    public String getUPass() {
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("User", MODE_PRIVATE);
        String storedValue = myPrefs.getString("upass", null);
        return storedValue;
    }

    public String returnExit() {
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("Exit", MODE_PRIVATE);
        String val = myPrefs.getString("exit", null);
        return val;
    }

    public Boolean edt_name_Equle() {
        boolean b = false;
        String store = getUname();
        if (store == null) {
//            Toast.makeText(Logins.this, "name nulll", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(Logins.this, "name not null", Toast.LENGTH_SHORT).show();
            String uName = edtUName.getText().toString().trim();
            b = store.equals(uName);
            if (b) {
//                Toast.makeText(Logins.this, ":)))))))))yehhhh equal", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(Logins.this, ":(((((((((nooooo equal", Toast.LENGTH_SHORT).show();
            }
        }
        return b;
    }
    public Boolean edt_Pass_Equle() {
        boolean b = false;
        String store = getUPass();
        if (store == null) {
//            Toast.makeText(Logins.this, "name nulll", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(Logins.this, "name not null", Toast.LENGTH_SHORT).show();
            String uPass = edtuPass.getText().toString().trim();
            b = store.equals(uPass);
            if (b) {
//                Toast.makeText(Logins.this, ":)))))))))yehhhh equal", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(Logins.this, ":(((((((((nooooo equal", Toast.LENGTH_SHORT).show();
            }
        }
        return b;
    }


}
