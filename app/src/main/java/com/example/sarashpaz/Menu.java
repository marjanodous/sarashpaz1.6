package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.AdapterMenu;
import com.example.sarashpaz.model.ItemRecycler;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Menu extends AppCompatActivity {
//    public static boolean tanagholat, sonati, modern, soap, rejimi, daneshjuee = false;
    private ImageView imgBackDastebandi;
    public static String titleMenu;
    TextView txtTitle;
    GridView gridView;

    //    private ArrayList<ItemRecycler> foodGroups = new ArrayList<>();
    public ArrayList<ItemRecycler> listsoap,listmidern,listsonati,listtanagholat,listrejimi,listdaneshjoee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        /* Get object from xml file*/
        txtTitle = findViewById(R.id.txttitledastehbandi);
        txtTitle.setText(titleMenu);
        imgBackDastebandi = findViewById(R.id.img_backdastehbandi);
        gridView = findViewById(R.id.grid_menu);

        Bundle bundle = getIntent().getExtras();
        Boolean soap=  bundle.getBoolean("soap");

        Bundle bundle1 = getIntent().getExtras();
        Boolean modern=  bundle1.getBoolean("modern");

        Bundle bundle2 = getIntent().getExtras();
        Boolean sonati=  bundle2.getBoolean("sonati");

        Bundle bundle3 = getIntent().getExtras();
        Boolean tanagholat=  bundle3.getBoolean("tanagholat");

        Bundle bundle4 = getIntent().getExtras();
        Boolean rejimi=  bundle4.getBoolean("rejimi");

        Bundle bundle5 = getIntent().getExtras();
        Boolean daneshjuee=  bundle5.getBoolean("daneshjuee");

        /*set adapter to gridview */
        if (soap) {
            setSoap();
            AdapterMenu adapter = new AdapterMenu(Menu.this, R.layout.item_menu, listsoap);
            gridView.setAdapter(adapter);
        } else if (modern) {
            setModern();
            AdapterMenu adapter = new AdapterMenu(Menu.this, R.layout.item_menu, listmidern);
            gridView.setAdapter(adapter);
        }
       else if (sonati) {
            setSonati();
            AdapterMenu adapter = new AdapterMenu(Menu.this, R.layout.item_menu, listsonati);
            gridView.setAdapter(adapter);
            Dastebandi.sonati =false;
        }
        else if (tanagholat) {
            setTangholat();
            AdapterMenu adapter = new AdapterMenu(Menu.this, R.layout.item_menu, listtanagholat);
            gridView.setAdapter(adapter);
            Dastebandi.tanagholat =false;
        }
       else if (rejimi) {
            setRejimi();
            AdapterMenu adapter = new AdapterMenu(Menu.this, R.layout.item_menu, listrejimi);
            gridView.setAdapter(adapter);
            Dastebandi.rejimi =false;
        }
        else if (daneshjuee) {
            setDaneshjuee();
            AdapterMenu adapter = new AdapterMenu(Menu.this, R.layout.item_menu, listdaneshjoee);
            gridView.setAdapter(adapter);
            Dastebandi.daneshjuee =false;
        }

        /*back to dastebandi*/
        imgBackDastebandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Dastebandi.tanagholat = false;
                Dastebandi.daneshjuee = false;
                Dastebandi.rejimi = false;
                Dastebandi.sonati = false;
                Dastebandi.modern = false;
                Dastebandi.soap = false;

            }
        });
    }

    /*methods set item list grid*/

    //for sonati
    public void setSonati() {
        listsonati = new ArrayList<>();
        listsonati.add(new ItemRecycler(R.drawable.img5, "مرصع پلو",
                "مرغ کامل :  1 عدد\n" +
                        "پیاز :  2 عدد متوسط\n" +
                        "زعفران :   1/3 ق چ\n" +
                        "مغز گرد :  1/2 لیوان\n" +
                        "خلال پسته :  1/2 لیوان \n" +
                        "خلال بادام:   1/2 لیوان\n" +
                        " خلال پوست پرتقال یا نارنج :   1/3 لیوان\n" +
                        " کشمش :  1 لیوان \n" +
                        "زرشک :  1 لیوان\n" +
                        "زردچوبه :  1/2 ق چ\n" +
                        "نمک :  به میزان لازم\n" +
                        "شکر  در صورت نیاز:به میزان لازم",
                "ابتدا  برنج را بشویید و با کمی نمک و آب چند ساعت خیس کنید.\n" +
                        "سپس موادی نظیر (مرغ ،خلال پرتقال ، زرشک و کشمش ) را که برای تزیین  برنج استفاده میشوند آماده کنید.\n" +
                        "برای این کار هم باید زرشک و کشمش را از 2 ساعت قبل در آب سرد به صورت جداگانه خیس کنید تا اگر احیانا گل و یا سنگ ریزه ای داشت ته نشین شود.سپس مرغ را به چهار قسمت تقسیم کنید و به همراه مقداری نمک ،زردچوبه و 1 عدد پیاز و مقداری آب بپزید تا گوشت مرغ کاملا پخته شود .\n" +
                        "مرغ پخته شده را از استخوان جدا کنید و به قطعات کوچکتر برش دهید.\n" +
                        " همچنین اگر خلال پرتقال یا نارنج شیرین شده تهیه کردید که نیازی نیست برای شیرین شدنش کاری بکنید ولی در غیر این صورت باید خلال ها را چند بار در آب خیس کنید و آب آن را خالی کنید و در مرحله بعد در آب جوش یکی دو قول جوش بزنید و  آب آن را خالی کنید.این کار را 3 دفعه انجام دهید تا تلخی خلال های پرتقال کاملا از بین برود.بعد از شیرین شدن خلال ها آن ها را در مقداری روغن داغ بر روی حرارت ملایم به اندازه یکی دو چرخ تفت دهید .\n" +
                        "و در پایان به اندازه یک قاشق سوپ خوری شکر روی آن بریزید و خوب مخلوط کنید و وقتی خلال ها یکدست شد آنرا از روی حرارت بردارید تا در اثر حرارت شکر ذوب شود.\n" +
                        "زرشک و کشمش را هم بعد از اینکه خوب خیس خوردند از آب بیرون میاوریم. و نصف پیاز را با روغن تفت میدهیم و وقتی رنگ پیاز ها طلایی شد زرشک ها را داخل آن میریزیم و یک تفت کوچک میدهیم تا زرشک ها نسوزند.اگر هم زرشک ها ترش مزه بودند میتوانید یک دو قاشق سوپخوری شکر به آن اضافه کنید و مزه آن را شیرین کنید.\n" +
                        " همچنین نصف دیگر پیاز ها را هم با مقداری روغن تفت میدهیم و بعد از اینکه رنگ پیاز طلایی شد کشمش رادر آن کمی تفت میدهیم و آماده میکنیم.\n" +
                        " بعد از آماده سازی مواد تزیین پلو ، برنج را که از قبل خیس خورده شده آبکش میکنیم و دم میدهیم .\n" +
                        " مرغ پخته و آماده شده را با زرشک و کشمش مخلوط کنید و در هنگام سرو برنج با نصف چلو مخلوط کنید و مقداری از برنج را هم با زعفران دم کرده مخلوط کنید و به همراه خلال ها روی برنج بریزید . در آخر هم میتوانید مقداری روغن داغ کنید و روی برنج بکشید."));
        listsonati.add(new ItemRecycler(R.drawable.kabab, "برای کباب کوبیده زعفرانی",
                "گوشت چرخ کرده :یک کیلو\n" +
                        "پیاز متوسط :4 عدد\n" +
                        "نمک و فلفل:به مقدار لازم\n" +
                        "زعفران :به مقدار لازم",
                "در تهیه این کباب، از گوشت چرخ کرده ای که 30 در صد چربی داشته باشد استفاده کنید. گوشت را در یک ظرف مسی بریزید و پیاز را داخل آن رنده کنید.\n" +
                        "نمک، فلفل و زعفران را اضافه کنید و تا جایی که می توانید، گوشت را ورز دهید تا حدی که گوشت، حالت چسبندگی پیدا کند. می توان برای ایجاد چسبدگی بیشتر، از زرده یک تخم مرغ استفاده کرد.\n" +
                        "برای سیخ کردن کباب هم باید توجه داشته باشید که از سیخ های پهن برای پخت این کباب باید استفاده کنید.\n" +
                        "قبل از اینکه شروع به کار کنید، مقدماتی لازم است. ابتدا در کاسه کوچکی مقداری آب سرد بریزید و کنار دست تان قرار دهید. حالا پیش از برداشتن مایه کباب دست تان را در آب خیس کرده، یک مشت از مایه را بردارید و به شکل دوک درآورید؛ سپس همان طور که مواد در دست راست تان قرار دارد، سیخ را روی مایه دوکی بگذارید و شروع به پوشاندن سطح سیخ با مایه کباب کنید.\n" +
                        "در پایان دوباره دست تان را در آب سرد فرو کنید و روی سیخ فشار دهید تا اثر انگشتان شما بر گوشت باقی بماند. حالا ابتدا و انتهای کباب را به سیخ بچسبانید و کناری بگذارید. همین کار را درباره همه مواد و سیخ ها انجام دهید."));
        listsonati.add(new ItemRecycler(R.drawable.qeime, "خورش قیمه",
                "گوشت :200 گرم\n" +
                        "لپه :70 گرم\n" +
                        "رب :1-2 قاشق \n" +
                        "پیاز عسلی :3-4 قاشق\n" +
                        "چوب دارچین :1عدد\n" +
                        "هل سبز :5 -6 عدد\n" +
                        "گلاب :1 قاشق\n" +
                        "زرشک :برای تزیین  2 قاشق  \n" +
                        "روغن :2قاشق",
                "ابتدا باید درون قابلمه مناسب پیاز عسلی از قبل سرخ شده را به همراه گوشت و کمی روغن به مدت 10 الی 12 دقیقه با حرارت پایین تفت میدهیم .نکته مهم و قابل توجه در مورد این خورش طرد بودن گوشت خورش است که برای این کار بهتر است گوشت مان را از شب قبل با کمی فلفل و روغن مخلوط کرده و استراحت دهیم.\n" +
                        "بعد از تفت گوشت و پیاز با حرارت بالا لپه و رب را به مواد ما اضافه میکنیم و حرارت را کمی بیشتر میکنیم و شروع میکنیم به تفت دادن تا مواد کاملاً در هم مخلوط شوند و رنگ رب در روغن جذب شود و خورش ما خوشرنگ تر شود و به اندازه 2 لیوان آب ولرم به غذا اضافه میکنیم.\n" +
                        "تا به جوش بیاید و بعد حرارت را کم میکنیم تا خورش ما آرام آرام بپزد . بخاطر اینکه به گوشتمان فلفل اضافه کردیم گوشت خورش سریعتر می پزد. بقیه مواد مثل گلاب و لیمو عمانی ،نمک و ادویه جات را در 10 دقیقه آخر به خورشتان اضافه کنید و حتما هل و دارچین را از قبل بجوشانید و با کمی آب به خورشتتان اضافه کنید.\n" +
                        "سپس می توانید سیب زمینی ها را به صورت خلالی و یا شکل دلخواه در آورده و برای تزیین به خورش قیمه دلچسپتان اضافه کنید.\n"));
        listsonati.add(new ItemRecycler(R.drawable.img6, "قورمه سبزی ",
                "گوشت سر دست با مغز ران بدون استخوان :نیم کیلو گرم\n" +
                        "پیاز سرخ کرده :3 قاشق سوپخوری\n" +
                        "روغن (حیوانی یا نباتی) :250 گرم\n" +
                        "لیمو عمانی (یا گرد لیمو) :5 تا 6 عدد\n" +
                        "لوبیا قرمز یا لوبیای چیتی (بسته به سلیقه) :نیم لیوان\n" +
                        "سبزی خورش (تره ، جعفری ، گشنیز ، شنبلیله و نعناع ) :1 کیلو گرم\n" +
                        "نمک :به مقدار لازم\n" +
                        "زردچوبه :به مقدار لازم\n" +
                        "فلفل : به مقدار لازم" ,
                "ابتدا پس از تمیز کردن گوشت و بعد از خرد کردن پیاز این دو را با هم در یک جا تفت میدهیم . در مرحله بعدی زردچوبه را به این گوشت و پیاز افزوده و همراه با آب و لوبیا قرمز مخلوط کرده و آن را حرارت میدهیم. این حرارت را تا جایی ادامه می دهیم که گوشت نیم پز شود.\n" +
                        "حال سبزیجات خود که شامل تره ، جعفری ، گشنیز ، شنبلیله و چند پر نعناع میباشد را تمیز کرده و پس از شستشو دادن آنها را خرد میکنیم\n" +
                        "وقت آن رسیده است که این سبزی ها را نیز به غذای خود اضافه کنیم . در مرحله بعد ادویه های خود را به آن اضافه کنیم . به صورت پیشفرض فلفل و نمک برای قورمه سبزی کافیست . اما شما بر حسب تجربه می توانید از ادویه های دیگر استفاده کنید.\n" +
                        "بعد از اینکه غذای ما چند بار جوش خورد ، وقت آن می رسد که لیمو عمانی را به غذا اضافه کنیم . دقت داشته باشید که بر روی لیمو عمانی چند سوراخ کوچک ایجاد کنید تا غذا پخش شود . به یاد داشته باشید که اگر لیمو عمانی شما تلخ باشد تمام قرمه سبزی شما تلخ خواهد شد.\n" +
                        "پس اگر از کیفیت لیمو عمانی خود اطمینان ندارید سعی کنید از گرد لیمو استفاده کنید . شما می توانید یک سوم پیمانه آب غوره هم به خورشت اضافه کنید البته این دلبخواهی و بستگی به مزاج شما دارد . حال منتظر می مانیم تا غذا پخته شود . به یاد داشته باشید زمانی متوجه می شویم که غذا پخته است که یک سطح روغنی روی غذا به وجود بیاید.\n"));
        listsonati.add(new ItemRecycler(R.drawable.img2, "مرغ سلطانی",
                "مرغ :1400 گرم\n" +
                        "سیب زمینی سرخ شده :700 گرم\n" +
                        "لیمو ترش :2 عدد\n" +
                        "سیر، زنجفیل تازه، فلفل قرمز چیلی :یک قاشق سوپ خوری\n" +
                        "گوجه فرنگی فیله شده :یک عدد\n" +
                        "پودر گشنیز، زردچوبه، گرام ماسالا و زیره سبز :یک قاشق سوپ خوری\n" +
                        "ماست :2 قاشق چایخوری\n" +
                        "نمک :2 قاشق چای خوری",
                "برای درست کردن ترکیبات چاشنی:سیر، فلفل قرمز و زنجفیل را رنده می کنیم تا ترکیب آنها 1 قاشق سوپ خوری پر شود.\n" +
                        "گوجه فرنگی را داخل میکسر پوره کنید 1 قاشق سوپ خوری از آن برای مزه دار کردن مرغ کافی است.\n" +
                        "از ترکیب پودر گشنیز، زردچوبه، گرام ماسالا و زیره سبز هم 1 قاشق سوپ خوری پُر نیاز است.\n" +
                        "در ابتدا بافت زیر استخوان ران مرغ را چند بار برش می زنیم.\n" +
                        "یک تابه سرخ کن بزرگ (کمی بزرگ تر از اندازه مرغ) آماده کرده، مرغ و ترکیب چاشنی را در آن می ریزیم و به خوبی مخلوط می کنیم.\n" +
                        "با استفاده از دستکش، مرغ را به خوبی ماساژ می دهیم تا چاشنی کاملا داخل و روی مرغ را بپوشاند، در صورت امکان مرغ را به مدت یک شب در یخچال نگه می داریم.\n" +
                        "فر را از قبل با حرارت 200 درجه سانتی گراد گرم می کنیم.\n" +
                        "لیمو ترش را با چاقو چند برش کوچک زده و داخل شکم مرغ قرار می دهیم. پس از آن مرغ را بر روی سینی فر قرار می دهیم.\n" +
                        "  2 پیمانه آب یا در صورت تمایل آب مرغ را بر روی مرغ ریخته و سینی را در طبقه پایین فر جا می دهیم. حدود 1 ساعت و 20 دقیقه مواد را می پزیم.\n" +
                        "پس از پخته شدن مرغ، آن را بر روی یک سطح صاف و تمیز قرار داده و با دقت تکه های سیاه و سوخته مرغ را جدا می کنیم تا سطح شفاف بافت گوشت مرغ مشخص شود.\n" +
                        "سپس مرغ را همراه با سیب زمینی و آب مرغ (اگر دوست داشتید) روی میز می چینیم.\n" +
                        "غذا را با برگ های گشنیز تزیین کرده و در کنار ادویه انتخابی خود سرو می کنیم."));
        listsonati.add(new ItemRecycler(R.drawable.nesar1, "قیمه نثار ",
                "برنج خیس کرده3 پیمانه\n" +
                        "گوشت قیمه خرد شده350 گرم\n" +
                        "پیاز خرد کرده ریز2 عدد\n" +
                        "لپه خیس کرده1/2 پیمانه\n" +
                        "روغن1/2 پیمانه\n" +
                        "رب گوجه فرنگی2 قاشق س\n" +
                        "ادویه خورشتی1 قاشق چ\n" +
                        "ادویه پلویی1 قاشق چ\n" +
                        "گلاب1 قاشق س\n" +
                        "زعفران دم کرده1 قاشق س\n" +
                        "زرشک خشک شده1/2 پیمانه\n" +
                        "گرد لیمو عمانی مقداری\n" +
                        "نمک مقداری\n" +
                        "خلال پسته و بادام ",
                "طرز تهیه سنتی"));
    }

    //for modern
    public void setModern() {
        listmidern = new ArrayList<>();
        listmidern.add(new ItemRecycler(R.drawable.img3, "مدرن1", "مواد لازم مدرن", "طرز تهیه مدرن"));
        listmidern.add(new ItemRecycler(R.drawable.img3, "مدرن2", "مواد لازم مدرن", "طرز تهیه مدرن"));
        listmidern.add(new ItemRecycler(R.drawable.img3, "مدرن3", "مواد لازم مدرن", "طرز تهیه مدرن"));
        listmidern.add(new ItemRecycler(R.drawable.img3, "مدرن4", "مواد لازم مدرن", "طرز تهیه مدرن"));
        listmidern.add(new ItemRecycler(R.drawable.img3, "مدرن5", "مواد لازم مدرن", "طرز تهیه مدرن"));
        listmidern.add(new ItemRecycler(R.drawable.img3, "مدرن6", "مواد لازم مدرن", "طرز تهیه مدرن"));
    }

    //for soap
    public void setSoap() {
        listsoap = new ArrayList<>();
        listsoap.add(new ItemRecycler(R.drawable.img3, "سوپ1", "مواد لازم سوپ", "طرز تهیه سوپ"));
        listsoap.add(new ItemRecycler(R.drawable.img3, "سوپ2", "مواد لازم سوپ", "طرز تهیه سوپ"));
        listsoap.add(new ItemRecycler(R.drawable.img3, "سوپ3", "مواد لازم سوپ", "طرز تهیه سوپ"));
        listsoap.add(new ItemRecycler(R.drawable.img3, "سوپ4", "مواد لازم سوپ", "طرز تهیه سوپ"));
        listsoap.add(new ItemRecycler(R.drawable.img3, "سوپ5", "مواد لازم سوپ", "طرز تهیه سوپ"));
        listsoap.add(new ItemRecycler(R.drawable.img3, "سوپ6", "مواد لازم سوپ", "طرز تهیه سوپ"));
    }

    public void setRejimi() {
        listrejimi = new ArrayList<>();
        listrejimi.add(new ItemRecycler(R.drawable.img3, "رژیمی1", "مواد لازم رژیمی", "طرز تهیه رژیمی"));
        listrejimi.add(new ItemRecycler(R.drawable.img3, "رژیمی2", "مواد لازم رژیمی", "طرز تهیه رژیمی"));
        listrejimi.add(new ItemRecycler(R.drawable.img3, "رژیمی3", "مواد لازم رژیمی", "طرز تهیه رژیمی"));
        listrejimi.add(new ItemRecycler(R.drawable.img3, "رژیمی4", "مواد لازم رژیمی", "طرز تهیه رژیمی"));
        listrejimi.add(new ItemRecycler(R.drawable.img3, "رژیمی5", "مواد لازم رژیمی", "طرز تهیه رژیمی"));
        listrejimi.add(new ItemRecycler(R.drawable.img3, "رژیمی6", "مواد لازم رژیمی", "طرز تهیه رژیمی"));
    }

    public void setDaneshjuee() {
        listdaneshjoee = new ArrayList<>();
        listdaneshjoee.add(new ItemRecycler(R.drawable.img3, "دانشجویی1", "مواد لازم دانشجویی", "طرز تهیه دانشجوی"));
        listdaneshjoee.add(new ItemRecycler(R.drawable.img3, "دانشجویی2", "مواد لازم دانشجویی", "طرز تهیه دانشجوی"));
        listdaneshjoee.add(new ItemRecycler(R.drawable.img3, "دانشجویی3", "مواد لازم دانشجویی", "طرز تهیه دانشجوی"));
        listdaneshjoee.add(new ItemRecycler(R.drawable.img3, "دانشجویی4", "مواد لازم دانشجویی", "طرز تهیه دانشجوی"));
        listdaneshjoee.add(new ItemRecycler(R.drawable.img3, "دانشجویی5", "مواد لازم دانشجویی", "طرز تهیه دانشجوی"));
        listdaneshjoee.add(new ItemRecycler(R.drawable.img3, "دانشجویی6", "مواد لازم دانشجویی", "طرز تهیه دانشجوی"));
    }

    public void setTangholat() {
        listtanagholat = new ArrayList<>();
        listtanagholat.add(new ItemRecycler(R.drawable.img3, "تنقلات1", "مواد لازم تنقلات", "طرز تهیه تنقلات"));
        listtanagholat.add(new ItemRecycler(R.drawable.img3, "تنقلات2", "مواد لازم تنقلات", "طرز تهیه تنقلات"));
        listtanagholat.add(new ItemRecycler(R.drawable.img3, "تنقلات3", "مواد لازم تنقلات", "طرز تهیه تنقلات"));
        listtanagholat.add(new ItemRecycler(R.drawable.img3, "تنقلات4", "مواد لازم تنقلات", "طرز تهیه تنقلات"));
        listtanagholat.add(new ItemRecycler(R.drawable.img3, "تنقلات5", "مواد لازم تنقلات", "طرز تهیه تنقلات"));
        listtanagholat.add(new ItemRecycler(R.drawable.img3, "تنقلات6", "مواد لازم تنقلات", "طرز تهیه تنقلات"));
    }


    @Override
    public void onBackPressed() {
        Dastebandi.tanagholat = false;
        Dastebandi.daneshjuee = false;
        Dastebandi.rejimi = false;
        Dastebandi.sonati = false;
        Dastebandi.modern = false;
        Dastebandi.soap = false;

        super.onBackPressed();
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
