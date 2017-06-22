package com.example.abhi.omconstruction;

import android.support.v7.app.AppCompatActivity;

public class Products extends AppCompatActivity {
    String Product;
    String Quantity;
    String  data;

    public String getProduct() {
        return Product;
    }

    public void setProduct(String cement) {
        Product = cement;
    }

    public String getQuantity() {
        data=Quantity;
        return Quantity;
    }

    public void setQuantity(String sack) {
        Quantity = sack;
    }


}