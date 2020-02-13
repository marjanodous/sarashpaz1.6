package com.example.sarashpaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sarashpaz.adapter.AdapterSearch;
import com.example.sarashpaz.model.ItemRecycler;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Search extends AppCompatActivity {
    ListView listViewSearch;
    ImageView imgClear;
    EditText edtNameSearch;
    public ArrayList<ItemRecycler> foodsArrayListMontakhab, foodsArrayListSearch;
    AdapterSearch adapterDefault;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listViewSearch = findViewById(R.id.listView_search);
        imgClear = findViewById(R.id.imgsearch_clear);
        edtNameSearch = findViewById(R.id.edt_name_search);
        txtResult = findViewById(R.id.result_search);

        ///// calling method defult to listmontakhab
        setItemListMontakhab();

        ////// clear text Search
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNameSearch.setText("");
            }
        });

        edtNameSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ////////// when text the length > 3 char
                String text = edtNameSearch.getText().toString().trim();
                if (text.length() >= 3) {
                    /// calling method set item Search
                    serchArray(text);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = edtNameSearch.getText().toString().trim();
                /* if text is empty show list defult*/
                if (text.equals("")) {
                    setItemListMontakhab();
                    txtResult.setText("نتیجه جستجو");
                }
            }
        });

    }
    /*method show list defult montakhab*/
    public void setItemListMontakhab() {
        foodsArrayListMontakhab = new ArrayList<>();
        adapterDefault = new AdapterSearch(Search.this, R.layout.item_search, foodsArrayListMontakhab);
        addMontakhab();
        listViewSearch.setAdapter(adapterDefault);
    }

    /* when name is equal whit list item defult*/
    public void serchArray(String name) {
        foodsArrayListSearch = new ArrayList<>();
        String sname = name;
        boolean flag=false;
        for (ItemRecycler item : foodsArrayListMontakhab) {
            int img =item.getuAvatar();
            String strlist = item.getuName();
            String mavad = item.getMavad();
            String tahaieh = item.getTahaieh();
            if (strlist.contains(sname)) {
                flag=true;
                /*add equal item to list Search*/
                foodsArrayListSearch.add(new ItemRecycler(img,strlist,mavad,tahaieh));
                /*calling method to show list Search*/
                setItemListSearch();
                txtResult.setText("نتیجه جستجو");
            } else if(flag==false){
                /*set empty item to listsearch*/
                foodsArrayListSearch = new ArrayList<>();
                setItemListSearch();
                txtResult.setText("موردی یافت نشد");
            }

        }

    }
    /*method show item Search to list*/
    public void setItemListSearch() {
        adapterDefault = new AdapterSearch(Search.this, R.layout.item_search, foodsArrayListSearch);
        listViewSearch.setAdapter(adapterDefault);
    }

    /* method add item to list defult montakhab */
    public void addMontakhab() {
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"قیمه","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"باقالی پلو","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"جوجه کباب","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"کباب برگ","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"کباب چنجه","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"قرمه","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"قلیه ماهی","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"سبزی پلوماهی","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"فسنجون","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"مرغ آلو","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"کباب کوبیده","مواد لازم سرچ","طرز تهیه سرچ"));
        foodsArrayListMontakhab.add(new ItemRecycler(R.drawable.img1,"زرشک پلو","مواد لازم سرچ","طرز تهیه سرچ"));
    }

    //======================change font===============================
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
