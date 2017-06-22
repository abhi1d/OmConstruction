package com.example.abhi.omconstruction;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOError;

/**
 * Created by abhi on 15/6/17.
 */

public class Admin extends AppCompatActivity {

    Spinner spinnerOfProduct;
    public Button buy, sell, edit;
    EditText e1;
    FirebaseDatabase mDatabase;
    DatabaseReference mDataRef2, mDataRef1;
    DatabaseReference rNoneSell, rNoneBuy, rJanSell, rJanBuy, rFebSell, rFebBuy, rMarchSell, rMarchBuy, rAprSell, rAprBuy,
            rMaySell, rMayBuy, rJuneSell, rJuneBuy, rJulySell, rJulyBuy, rAugSell, rAugBuy, rSeptSell, rSeptBuy, rOctSell, rOctBuy, rNovSell, rNovBuy, rDecSell, rDecBuy, rBuy, rSell, rTotalBuy, rTotalSell;
    int number;
    String mData, cement, sack;
    public View view;

    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_product, menu);

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        mDatabase = FirebaseDatabase.getInstance();
        mDataRef1 = mDatabase.getReference("Products/Buy");
        mDataRef2 = mDatabase.getReference("Products/Sell");
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

        // Inflate the layout for this fragment

        spinnerOfProduct = (Spinner) findViewById(R.id.spinner1);

        String[] products = new String[]{
                "(none)", "Lafarge", "Birla Gold", "Shree", "Mahashakti", "Konark", "Acc"
        };
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, products);
        adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerOfProduct.setAdapter(adp);
        spinnerOfProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                int Hold = parent.getSelectedItemPosition();

                if (Hold == 0) {
                    Toast.makeText(getApplicationContext(), "Select any product", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        buy = (Button) findViewById(R.id.buy);

        sell = (Button) findViewById(R.id.sell);
        e1 = (EditText) findViewById(R.id.edit1);


        //spinner for month
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_month);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.month,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner2.setAdapter(staticAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                int Hold = parent.getSelectedItemPosition();

                if (Hold == 0) {
                    Toast.makeText(getApplicationContext(), "select any month", Toast.LENGTH_SHORT).show();
                } else if (Hold == 1) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rJanBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rJanSell);
                        }
                    });

                } else if (Hold == 2) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rFebBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rFebSell);
                        }
                    });

                } else if (Hold == 3) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rMarchBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rMarchSell);
                        }
                    });

                } else if (Hold == 4) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rAprBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rAprSell);
                        }
                    });

                } else if (Hold == 5) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rMayBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rMaySell);
                        }
                    });

                } else if (Hold == 6) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rJuneBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rJuneSell);
                        }
                    });

                } else if (Hold == 7) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rJulyBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rJulySell);
                        }
                    });

                } else if (Hold == 8) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rAugBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rAugSell);
                        }
                    });

                } else if (Hold == 9) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rSeptBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rSeptSell);
                        }
                    });

                } else if (Hold == 10) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rOctBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rOctSell);
                        }
                    });

                } else if (Hold == 11) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rNovBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rNovSell);
                        }
                    });

                } else if (Hold == 12) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            buy(text, rDecBuy);
                        }
                    });
                    sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            String text = e1.getText().toString();
                            sell(text, rDecSell);
                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void updateData(final String mData, final String edit_text, final DatabaseReference ref) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                  try {
                      String new_changed_sack = dataSnapshot.child(mData).getValue().toString();
                      int newValue = number + Integer.parseInt(new_changed_sack);
                      Products xyz = new Products();
                      xyz.setProduct(mData);
                      xyz.setQuantity(Integer.toString(newValue));
                      cement = xyz.getProduct();
                      sack = xyz.getQuantity();
                      ref.child(cement).setValue(sack);
                  }catch (NullPointerException e)
                  {

                          Products xyz =new Products();
                          xyz.setProduct(mData);
                          xyz.setQuantity(edit_text);
                          cement=xyz.getProduct();
                          sack=xyz.getQuantity();
                          ref.child(cement).setValue(sack);

                      }

               }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }

    public void buy(String edit_text, DatabaseReference xref) {
        if (TextUtils.isEmpty(edit_text)) {
            Snackbar.make(view, "Enter the quantity", Snackbar.LENGTH_LONG).show();
            return;
        }

        try {
            number = Integer.parseInt(edit_text);
            Log.d("", number + " is a number");
            mData = spinnerOfProduct.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), "Updating the data....", Toast.LENGTH_SHORT).show();
            updateData(mData, edit_text, xref);
            updateData(mData, edit_text, rTotalBuy);
            //after clicking the button soft keyboard will hide from window
            closeKeyboard(getApplicationContext(), e1.getWindowToken());
        } catch (NumberFormatException e) {
            Snackbar.make(view, "Please enter only Integer values", Snackbar.LENGTH_LONG).show();
        }

    }

    public void sell(String edit_text, DatabaseReference xref) {

        if (TextUtils.isEmpty(edit_text)) {
            Snackbar.make(view, "Enter the quantity", Snackbar.LENGTH_LONG).show();
            return;
        }
        try {
            number = Integer.parseInt(edit_text);
            Log.i("", number + " is a number");
            mData = spinnerOfProduct.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), "Updating the data....", Toast.LENGTH_SHORT).show();
            updateData(mData, edit_text, xref);
            updateData(mData, edit_text, rTotalSell);
            //after clicking the button soft keyboard will hide from window
            closeKeyboard(getApplication(), e1.getWindowToken());
        } catch (NumberFormatException e) {
            Snackbar.make(view, "Please enter only Integer values", Snackbar.LENGTH_LONG).show();
        }
    }


}

