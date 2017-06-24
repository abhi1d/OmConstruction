package com.example.abhi.omconstruction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class GraphThisMonth extends AppCompatActivity {

    FirebaseDatabase mDatabase;

    DatabaseReference rNoneSell, rNoneBuy, rJanSell, rJanBuy, rFebSell, rFebBuy, rMarchSell, rMarchBuy, rAprSell, rAprBuy,
            rMaySell, rMayBuy, rJuneSell, rJuneBuy, rJulySell, rJulyBuy, rAugSell, rAugBuy, rSeptSell, rSeptBuy, rOctSell, rOctBuy, rNovSell, rNovBuy, rDecSell, rDecBuy, rBuy, rSell, rTotalBuy, rTotalSell;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_this_month);
        /***********************************
         *
         */
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
        /*************
         *
         */


        Intent i = getIntent();
        final int month = i.getIntExtra("month",0);
        TextView textView = (TextView) findViewById(R.id.textView_month);
        //getting data from firebase
        if (month == 0) {
            Toast.makeText(this, "select any month", Toast.LENGTH_SHORT).show();
        } else if (month == 1 ) {
            reference(rJanSell);

        } else if (month == 2 ) {
            reference(rFebSell);

        } else if (month == 3 ) {
            reference(rMarchSell);

        } else if (month == 4 ) {
            reference(rAprSell);
        } else if (month == 5) {
            reference(rMaySell);

        } else if (month == 6 ) {

            reference(rJuneSell);
        } else if (month == 7 ) {
            reference(rJulySell);

        } else if (month == 8 ) {
            reference(rAugSell);

        } else if (month == 9 ) {

            reference(rSeptSell);
        } else if (month == 10 ) {
            reference(rOctSell);

        } else if (month == 11 ) {

            reference(rNovSell);
        } else if (month == 12 ) {

            reference(rDecSell);

        }



// styling


    }

    public void reference(final DatabaseReference mDataRef) {


        mDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Data_sell = dataSnapshot.child("Lafarege").getValue().toString();
                int data_sell_laf = Integer.parseInt(dataSnapshot.child("Lafarge").getValue().toString());
                int data_sell_BG = Integer.parseInt(dataSnapshot.child("Birla Gold").getValue().toString());
                int data_sell_sh = Integer.parseInt(dataSnapshot.child("Shree").getValue().toString());
                int data_sell_maha = Integer.parseInt(dataSnapshot.child("Mahashakti").getValue().toString());
                int data_sell_ko = Integer.parseInt(dataSnapshot.child("Konark").getValue().toString());
                int data_sell_acc = Integer.parseInt(dataSnapshot.child("Acc").getValue().toString());

                GraphView graph = (GraphView) findViewById(R.id.graph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(0,data_sell_laf),
                        new DataPoint(1, data_sell_BG),
                        new DataPoint(2, data_sell_sh),
                        new DataPoint(3, data_sell_maha),
                        new DataPoint(4, data_sell_ko),
                        new DataPoint(5,data_sell_acc)
                });
                graph.addSeries(series);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });

                series.setSpacing(30);

// draw values on top
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);
                // use static labels for horizontal and vertical labels
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                staticLabelsFormatter.setHorizontalLabels(new String[] {"Lafarge", "Birla Gold", "Shree","Mahashakti","Konark","Acc"});
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



