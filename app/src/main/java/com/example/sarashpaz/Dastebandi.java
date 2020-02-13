package com.example.sarashpaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Dastebandi extends AppCompatActivity {

    private ImageView imgBack, imgTanagholat, imgSonati, imgModern, imgSoap, imgRejimi, imgDaneshjuee, imgTazeenat, imgnoshidani, imgTorshi, imgdeser, imgmahali, imgsalad, imgcake, imgkoodak;
    private TextView txtsoup, txtmodern, txtsonaty, txttazeenat, txtdaneshjuee, txtrejimi, txtnoshidani, txttorshi, txtdeser, txtmahali, txtsalad,
            txtcake, txttanaqolat, txtkoodak;
    public static boolean tanagholat, sonati, modern, soap, rejimi, daneshjuee = false;
//    public static String tanagholat, sonati, modern, soap, rejimi, daneshjuee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dastebandi);
        txtsoup = findViewById(R.id.txtsoup);
        txtmodern = findViewById(R.id.txtmodern);
        txtsonaty = findViewById(R.id.txtsonati);
        txttazeenat = findViewById(R.id.txttazeenat);
        txtdaneshjuee = findViewById(R.id.txtdaneshjuee);
        txtrejimi = findViewById(R.id.txtrejimi);
        txtnoshidani = findViewById(R.id.txtnushidani);
        txttorshi = findViewById(R.id.txttorshi);
        txtdeser = findViewById(R.id.txtdeser);
        txtmahali = findViewById(R.id.txtmahali);
        txtsalad = findViewById(R.id.txtsalad);
        txtcake = findViewById(R.id.txtcake);
        txttanaqolat = findViewById(R.id.txttanaqolat);
        txtkoodak = findViewById(R.id.txtkoodak);
        imgSoap = findViewById(R.id.img_soup);
        imgModern = findViewById(R.id.img_modern);
        imgSonati = findViewById(R.id.img_sonati);
        imgTanagholat = findViewById(R.id.img_tazeenat);
        imgRejimi = findViewById(R.id.img_rejimi);
        imgDaneshjuee = findViewById(R.id.img_daneshjuee);
        imgTazeenat = findViewById(R.id.img_tanagholat);
        imgnoshidani = findViewById(R.id.img_nushidani);
        imgTorshi = findViewById(R.id.img_torshi);
        imgdeser = findViewById(R.id.img_deser);
        imgsalad = findViewById(R.id.img_salad);
        imgcake = findViewById(R.id.img_cake);
        imgkoodak = findViewById(R.id.img_koodak);
        imgmahali = findViewById(R.id.img_mahali);



        /*set  item imagview click listener*/
        imgSoap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soap = true;
                Intent intent  = new Intent(Dastebandi.this, Menu.class);
                intent.putExtra("soap",soap);
                startActivity(intent);
                Menu.titleMenu = txtsoup.getText().toString();
            }
        });
        imgModern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modern = true;
                Intent intent  = new Intent(Dastebandi.this, Menu.class);
                intent.putExtra("modern",modern);
                startActivity(intent);
                Menu.titleMenu = txtmodern.getText().toString();
            }
        });
        imgSonati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonati = true;
                Intent intent  = new Intent(Dastebandi.this, Menu.class);
                intent.putExtra("sonati",sonati);
                startActivity(intent);
                Menu.titleMenu = txtsonaty.getText().toString();
            }
        });
        imgTanagholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanagholat = true;
                Intent intent  = new Intent(Dastebandi.this, Menu.class);
                intent.putExtra("tanagholat",tanagholat);
                startActivity(intent);
                Menu.titleMenu = txttanaqolat.getText().toString();
            }
        });
        imgRejimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rejimi = true;
                Intent intent  = new Intent(Dastebandi.this, Menu.class);
                intent.putExtra("rejimi",rejimi);
                startActivity(intent);
                Menu.titleMenu = txtrejimi.getText().toString();
            }
        });
        imgDaneshjuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daneshjuee = true;
                Intent intent  = new Intent(Dastebandi.this, Menu.class);
                intent.putExtra("daneshjuee",daneshjuee);
                startActivity(intent);
                Menu.titleMenu = txtdaneshjuee.getText().toString();
            }
        });
        imgTazeenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgnoshidani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgTorshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgdeser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgmahali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgsalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        imgkoodak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });


        ///////////back to home
        imgBack = findViewById(R.id.video_backhome);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public  void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Dastebandi.this);
        builder.setCancelable(false).setMessage(" خرید درون برنامه ای...")
                .setPositiveButton(
                        "خرید؟ ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                )
                .setNeutralButton("خروج", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog _alert = builder.create();
        _alert.setTitle(" ");
        _alert.setIcon(R.drawable.ic_cancel);
        _alert.show();
    }
}
