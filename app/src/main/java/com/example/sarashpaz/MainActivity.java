package com.example.sarashpaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.ItemAdapter_recycler_behtarin_dastoor_pokht_ha;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_img;
import com.example.sarashpaz.adapter.ItemAdapter_recycler_myanvade;
import com.example.sarashpaz.adapter.SlidingImage_Adapter;
import com.example.sarashpaz.model.ImageModel;
import com.example.sarashpaz.model.ItemRecycler;
import com.example.sarashpaz.model.userApp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    userApp user = new userApp();
    ImageView imgMenu,imgHomeMenu,imgListMenu;
    DrawerLayout myDraw;
    TextView txthistory, video, userName, txtBackHome,txtFavorit,txtabout,txtcall_me,txtExit;
    BottomNavigationView bottomNav;

    MediaPlayer music;
    ImageView btn_play;
    Boolean flag_play = false;
    public static boolean flag_Dasteh,flag_Search=false;

    //for image slider
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};

    //for recycler myanvade
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    List<ItemRecycler> item1 = new ArrayList<>();
    ItemAdapter_recycler_myanvade mAdapter;

    //for recycler behtarin_dastoor_pokht_ha
    List<ItemRecycler> item2 = new ArrayList<>();
    ItemAdapter_recycler_behtarin_dastoor_pokht_ha mAdapter2;

    //for recycler img
    List<ItemRecycler> item3 = new ArrayList<>();
    ItemAdapter_recycler_img mAdapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*for music*/
        music = MediaPlayer.create(MainActivity.this, R.raw.music);
        music.start();
        music.setLooping(true);
        btn_play = findViewById(R.id.img_music);
        btn_play.setImageResource(R.drawable.ic_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag_play) {
                    btn_play.setImageResource(R.drawable.ic_pause);
                    music.pause();
                    flag_play = true;
                } else {
                    btn_play.setImageResource(R.drawable.ic_play);
                    music.start();
                    flag_play = false;

                }

            }
        });

        /*end music*/
        //==============open draw=============================
        imgMenu = findViewById(R.id.img_menu);
        myDraw = findViewById(R.id.myDraw);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDraw.openDrawer(Gravity.RIGHT);
            }
        });

        //for image slider
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();

        //for recycler myanvade
        recyclerView = findViewById(R.id.recycler_view_myanvade);
        mAdapter = new ItemAdapter_recycler_myanvade(item1, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = findViewById(R.id.recycler_view_myanvade);
        myList.setLayoutManager(layoutManager);
        setDeta();
        recyclerView.setNestedScrollingEnabled(true);

        //for recycler behtarin_dastoor_pokht_ha
        recyclerView2 = findViewById(R.id.recycler_view_bartarinDastoorPokht);
        mAdapter2 = new ItemAdapter_recycler_behtarin_dastoor_pokht_ha(item2, this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setAdapter(mAdapter2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList2 = findViewById(R.id.recycler_view_bartarinDastoorPokht);
        myList2.setLayoutManager(layoutManager2);
        setDeta2();
        recyclerView2.setNestedScrollingEnabled(true);

        //for recycler img
        recyclerView3 = findViewById(R.id.recycler_view_img);
        mAdapter3 = new ItemAdapter_recycler_img(item3, this);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getApplicationContext());
        recyclerView3.setLayoutManager(mLayoutManager3);
        recyclerView3.setAdapter(mAdapter3);
        setDeta3();
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList3 = findViewById(R.id.recycler_view_img);
        myList3.setLayoutManager(layoutManager3);
        //for bottom navigation
        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.item_home);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_dastebandi:
                        startActivity(new Intent(MainActivity.this,Dastebandi.class));
                        flag_Dasteh=true;
                        return true;
                    case R.id.item_home:

                        return true;
                    case R.id.item_search:
                        startActivity(new Intent(MainActivity.this, Search.class));
                        flag_Search=true;
                        return true;
                }
                return false;
            }
        });

        ///////// set menumodel UserName
        userName = findViewById(R.id.username);
        SharedPreferences myPrefs;
        myPrefs = getSharedPreferences("User", MODE_PRIVATE);
        String StoredValue = myPrefs.getString("name", null);
        userName.setText(StoredValue);

        //////////////click  to back home txt
        txtBackHome = findViewById(R.id.txt_home);
        txtBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDraw.closeDrawers();
            }
        });
        //////////////click  to back home img
        imgHomeMenu= findViewById(R.id.img_home_menu);
        imgHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDraw.closeDrawers();
            }
        });
        //txt for go to history
        txthistory = findViewById(R.id.txt_history);
        txthistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, history.class));
            }
        });
        ////// go to favorit
        txtFavorit = findViewById(R.id.txt_favorite);
        txtFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityLiked.class));
            }
        });

        //img for go to history
        imgListMenu=findViewById(R.id.img_history);
        imgListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Dastebandi.class));
            }
        });
        //for go to video Menu
        video = findViewById(R.id.text_video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Menuvideo.class));
            }
        });
        //for goto about
        txtabout = findViewById(R.id.text_about);
        txtabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityAbout.class));
            }
        });
        //for go to callme
        txtcall_me=findViewById(R.id.txt_call_me);
        txtcall_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityCallme.class));
            }
        });

        //for Exit
        txtExit = findViewById(R.id.txt_exit);
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exit="exit";
                SharedPreferences userDetails = getApplicationContext().getSharedPreferences("Exit", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.putString("exit",exit);
                edit.apply();
                finish();
            }
        });




    }

    //for image slider
    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, imageModelArrayList));
        CirclePageIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    //for recycler myanvade
    private void setDeta() {
        item1.add(new ItemRecycler(R.drawable.myan1, "شکلات دانه ای غنی از پروتئین",
                "۱۲ خرمای مجول\n" +
                        "۴/۱ فنجان دانه شاهدانه\n" +
                        "۴/۱ فنجان دانه چیا\n" +
                        "۴/۱ فنجان دانه کنجد\n" +
                        "۴/۱ فنجان پودر کاکائو\n" +
                        "½ عصاره وانیل\n" +
                        "۴/۱ قاشق چای خوری دارچین\n" +
                        "۴/۱ قاشق نمک دریا به منظور طعم دهی\n" +
                        "۴/۱ فنجان کاکائو\n",
                "۱٫ خرما را در آسیاب قرار دهید و تا زمان خمیر مانند شدن ادامه دهید.\n" +
                        "۲٫ شاهدانه، چیا، کنجد، کاکائو، وانیل، دارچین و نمک را بی افزایید. تا زمان ترکیب کامل هم بزنید. کاکائو را نیز ترکیب کنید. خمیر باید چسبنده باشد (درصورتی که چسبنده نباشد، یک یا دو قاشق چای خوری آب بی افزایید).\n" +
                        "۳٫ آن ها را به صورت توپ های کوچک درآورده و به مدت ۲۰ دقیقه در فریزر قرار دهید. باقیمانده را در فریزر نگهداری نمایید.\n"));
        item1.add(new ItemRecycler(R.drawable.myan2, "ترکیبی از آجیل و میوه جات خشک شده خانگی",
                "½ فنجان کران بری خشک شده\n" +
                        "½ فنجان بادام رنده شده\n" +
                        "½ فنجان دانه کدوتنبل\n" +
                        "½ فنجان گردو\n" +
                        "½ فنجان کشمش\n",
                "تمام موارد را ترکیب کرده و به ۶ قسمت تقسیم نمایید.\n" +
                        "\nتهیه این نوع از اسنک های سریع نیز بسیار آسان می باشد. شما می توانید این ترکیب را در بسیار از فروشگاه ها مشاهده کنید بنابراین دیگر بهانه ای برای استفاده از میان وعده های ناسالم وجود ندارد. اما من ترجیح می دهم که خودم آن را ترکیب کنم زیرا در این صورت تمام موارد موردعلاقه را ترکیب می کنم و نگران افزودنی های غیرمجاز نیستم.\n" +
                        "ازآنجایی که آجیل، دانه ها، نارگیل و توت های خشک شده همگی گزینه های بسیار عالی هستند بنابراین از موادی نظیر چوب شور و غلات یا حبوبات فراوری شده پرهیز نمایید.\n" +
                        "نوع موردعلاقه من ترکیب کرانبری و کدوتنبل می\u200Cباشد. دستورالعمل اصلی بیان دارد که ½ پیمانه از هرکدام را ترکیب کرده و در ۴ وعده مصرف نماییم اما این مقدار دارای کالری بسیار بالایی می باشد. بنابراین من آن را در ۶ وعده استفاده می کنم و درنتیجه ۶ گرم پروتئین و ۱۶۳ کالری انرژی را برای بدن خود فراهم می آورم و یک بار تهیه کردن نیز تا آخر هفته کافی می باشد.\n"));
        item1.add(new ItemRecycler(R.drawable.myan3, "لقمه بوقلمون، آووکادو و حمص",
                "یک یا دوتکه گوشت بوقلمون\n" +
                        "۲ ورقه آووکادو\n" +
                        "یک قاشق حمص\n",
                "گوشت بوقلمون را پهن کنید. اگر از دو ورقه استفاده می کنید آنها را روی هم بگذارید.\n" +
                        "حمص را بر روی گوشت قرار دهید. آووکادو را افزوده و آن را به حالت لقمه دربیاورید.\n"));
        item1.add(new ItemRecycler(R.drawable.myan4, "اسموتی سبز غنی از پروتئین",
                "یک فنجان شیر نارگیل بدون شکر\n" +
                        "۲ فنجان اسفناج تازه\n" +
                        "یک موز یخ زده\n" +
                        "۲ قاشق روغن بادام\n" +
                        "۲ قاشق چای خوری عصاره وانیل\n" +
                        "۴/۱ فنجان (۱ پیمانه) پودر پروتئین وی\n" +
                        "یک فنجان یخ\n",
                "تمام مواد را ترکیب کرده و تا زمان رقیق شدن هم بزنید.\n" ));
        item1.add(new ItemRecycler(R.drawable.myan5, "نخود سرخ کرده",
                "۱ قاشق چای خوری روغن زیتون\n" +
                        "۱٫۵ قاشق چای خوری پودر فلفل\n" +
                        "۱٫۵ قاشق چای خوری زیره\n" +
                        "۴/۱ قاشق چای خوری نمک\n" +
                        "۸/۱ قاشق چای خوری فلفل کاین\n" +
                        "۲ قوطی نخود\n",
                "۱٫ فر را در دمای ۴۰۰ فارنهایت قرار دهید. تمام مواد را در یک کاسه بزرگ مخلوط کنید.\n" +
                        "۲٫ نخودها را در ماهی تابه قرار داده و در حدود ۳۵ تا ۴۰ دقیقه بپزید. آن را گرم سرو کنید.\n"));
        item1.add(new ItemRecycler(R.drawable.myan6,
                "دسر ماست یونانی",
                "۶ اونس ماست یونانی\n" +
                        "۳/۱ فنجان جوی بدون گلوتن، به صورت خام\n" +
                        "۱ قاشق چای خوری دانه چیا\n" +
                        "۲ قاشق شیر دلخواه (من شیر بادام را ترجیح می دهم)\n" +
                        "۱ فنجان میوه و توت \n",
                "۱٫ ماست، جو، دانه چیا، و شیر را در یک کاسه ترکیب کنید. آن را در یک ظرف شیشه ای پهن کنید.\n" +
                        "۲٫ نیمی از میوه ها و توت ها را بر روی آن ریخته و سپس باقی مخلوط را بر روی آن بریزید.\n" +
                        "۳٫ شب آن را در یخچال قرار داده و یا بعد از تهیه مصرف نمایید.\n"));
        mAdapter.notifyDataSetChanged();
    }

    //for recycler behtarin_dastoor_pokht_ha
    private void setDeta2() {
        item2.add(new ItemRecycler(R.drawable.img1, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت "));
        item2.add(new ItemRecycler(R.drawable.img2, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت"));
        item2.add(new ItemRecycler(R.drawable.img3, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت"));
        item2.add(new ItemRecycler(R.drawable.img4, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت"));
        item2.add(new ItemRecycler(R.drawable.img5, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت"));
        item2.add(new ItemRecycler(R.drawable.img6, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت"));
        item2.add(new ItemRecycler(R.drawable.img7, "برترین دستور پخت","مواد لازم برترین دستور پخت","طرز تهیه مواد لازم برترین دستور پخت"));
        mAdapter2.notifyDataSetChanged();
    }

    //for recycler img
    private void setDeta3() {
        item3.add(new ItemRecycler(R.drawable.img1));
        item3.add(new ItemRecycler(R.drawable.img2));
        item3.add(new ItemRecycler(R.drawable.img3));
        item3.add(new ItemRecycler(R.drawable.img4));
        item3.add(new ItemRecycler(R.drawable.img5));
        item3.add(new ItemRecycler(R.drawable.img6));
        item3.add(new ItemRecycler(R.drawable.img7));
        mAdapter3.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if(myDraw.isDrawerOpen(GravityCompat.START)){
            myDraw.closeDrawers();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.release();
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
