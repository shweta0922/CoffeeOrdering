package com.example.shweta.myfirstapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view){
        EditText editText= (EditText)findViewById(R.id.input_field);
        String name= editText.getText().toString();
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        CheckBox ChocolateCreamCheckBox= (CheckBox) findViewById(R.id.chocolateCream);
        boolean hasChocolateCream = ChocolateCreamCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolateCream);
        String message =orderSummary(hasChocolateCream,hasWhippedCream,price,name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"just java app for" +name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

     //   displayMessage(message);
    }
    public int calculatePrice(boolean addWhippedCream,boolean addChocolateCream){
        int basePrice =5;
        if(addWhippedCream)
            basePrice+=2;
        if(addChocolateCream)
            basePrice+=1;

        return basePrice*quantity;
    }
    public String orderSummary(boolean chocolate,boolean whipped,int number,String n){
        String priceMessage="coffee ordered by:" +n;
        priceMessage+="\nWant Whipped Cream?" + whipped;
        priceMessage+="\nWant Chocolate Cream?" + chocolate;
        priceMessage+="\nQuantity "+ quantity;
        priceMessage+="\nTotal:$" +number;
        return priceMessage;
    }
    public void increment(View view){
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view){
        quantity=quantity-1;
        display(quantity);
    }
    public void display(int Number){
        TextView quantityTextView=(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+Number);
       // quantityTextView.setText(NumberFormat.getCurrencyInstance().format(Number));
    }
//    public void displayPrice(int Number){
//        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(Number));
//        priceTextView.setText(Number);
//    }
    public void displayMessage(String message){
        TextView messageView = (TextView) findViewById(R.id.price_text_view);
         messageView.setText(message);
    }

}
