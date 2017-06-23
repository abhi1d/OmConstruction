package com.example.abhi.omconstruction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by abhi on 7/5/17.
 */

public class SingleItemSelected extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefresh;
    ProgressDialog pd;
    TextView textview_sell,textview_buy,textview_total;
    String Data_buy,Data_sell;
    int data_sell,data_buy;
    FirebaseDatabase mDatabase;

    DatabaseReference rNoneSell,rNoneBuy,rJanSell,rJanBuy,rFebSell,rFebBuy,rMarchSell,rMarchBuy,rAprSell,rAprBuy,
            rMaySell,rMayBuy, rJuneSell,rJuneBuy,rJulySell,rJulyBuy,rAugSell,rAugBuy,rSeptSell,rSeptBuy
            ,rOctSell,rOctBuy,rNovSell,rNovBuy,rDecSell,rDecBuy,rBuy,rSell,rTotalBuy,rTotalSell;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.single_item_selected_layout);
        mDatabase = FirebaseDatabase.getInstance();
        rNoneSell = mDatabase.getReference("Products/None/Sell");
        rNoneBuy = mDatabase.getReference("Products/None/Buy");
        rJanSell = mDatabase.getReference("Products/Jan/Sell");
        rJanBuy = mDatabase.getReference("Products/Jan/Buy");
        rFebSell = mDatabase.getReference("Products/Feb/Sell");
        rFebBuy = mDatabase.getReference("Products/Feb/Buy");
        rMarchSell = mDatabase.getReference("Products/March/Sell");
        rMarchBuy = mDatabase.getReference("Products/March/Buy");
        rAprSell = mDatabase.getReference("Products/Apr/Sell");
        rAprBuy = mDatabase.getReference("Products/Apr/Buy");
        rMaySell = mDatabase.getReference("Products/May/Sell");
        rMayBuy = mDatabase.getReference("Products/May/Buy");
        rJuneSell = mDatabase.getReference("Products/June/Sell");
        rJuneBuy = mDatabase.getReference("Products/June/Buy");
        rJulySell = mDatabase.getReference("Products/July/Sell");
        rJulyBuy = mDatabase.getReference("Products/July/Buy");
        rAugSell = mDatabase.getReference("Products/Aug/Sell");
        rAugBuy = mDatabase.getReference("Products/Aug/Buy");
        rSeptSell = mDatabase.getReference("Products/Sept/Sell");
        rSeptBuy = mDatabase.getReference("Products/Sept/Buy");
        rOctSell = mDatabase.getReference("Products/Oct/Sell");
        rOctBuy = mDatabase.getReference("Products/Oct/Buy");
        rNovSell = mDatabase.getReference("Products/Nov/Sell");
        rNovBuy = mDatabase.getReference("Products/Nov/Buy");
        rDecSell = mDatabase.getReference("Products/Dec/Sell");
        rDecBuy = mDatabase.getReference("Products/Dec/Buy");
        rBuy = mDatabase.getReference("Products/Buy");
        rSell = mDatabase.getReference("Products/Sell");
        rTotalBuy = mDatabase.getReference("Products/Total/Buy");
        rTotalSell = mDatabase.getReference("Products/Total/Sell");

        textview_sell = (TextView) findViewById(R.id.sell_data);
        textview_buy = (TextView) findViewById(R.id.buy_data);
        Intent i = getIntent();
        final String cement = i.getStringExtra("product");
        final int month = i.getIntExtra("month", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(cement);
        textview_total = (TextView) findViewById(R.id.total_data);
        mDatabase = FirebaseDatabase.getInstance();

        if (month == 0) {
            Toast.makeText(this, "select any month", Toast.LENGTH_SHORT).show();
        } else if (month == 1) {
            reference(rJanSell, rJanBuy,cement);

        } else if (month == 2) {
            reference(rFebSell, rFebBuy,cement);

        } else if (month == 3) {
            reference(rMarchSell, rMarchBuy,cement);

        } else if (month == 4) {
            reference(rAprSell, rAprBuy,cement);
        } else if (month == 5) {
            reference(rMaySell, rMayBuy,cement);

        } else if (month == 6) {

            reference(rJuneSell, rJuneBuy,cement);
        } else if (month == 7) {
            reference(rJulySell, rJulyBuy,cement);

        } else if (month == 8) {
            reference(rAugSell,rAugBuy,cement);

        } else if (month == 9) {

            reference(rSeptSell,rSeptBuy,cement);
        } else if (month == 10) {
            reference(rOctSell, rOctBuy,cement);

        } else if (month == 11) {

            reference(rNovSell, rNovBuy,cement);
        } else if (month == 12) {

            reference(rDecSell, rDecBuy,cement);

        }
    }


    public void reference(final DatabaseReference mDataRef2,final DatabaseReference mDataRef1,final String cement)
    {
        final ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Data_buy = dataSnapshot.child(cement).getValue().toString();
                data_buy = Integer.parseInt(Data_buy);
                textview_buy.setText(Data_buy);
                //stock value
                String total = Integer.toString(data_buy - data_sell);
                textview_total.setText(total);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDataRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Data_sell = dataSnapshot.child(cement).getValue().toString();
                data_sell = Integer.parseInt(Data_sell);
                textview_sell.setText(Data_sell);
                mDataRef1.addListenerForSingleValueEvent(listener);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}