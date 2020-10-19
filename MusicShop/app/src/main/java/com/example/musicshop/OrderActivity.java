package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"mrhankey@mail.ru"};
    String subject = "Order from Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Customer name: " + userName + "\n" +
                "Goods name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price + "\n" +
                "Order price: " + orderPrice;
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);

        orderTextView.setTextSize(18);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        orderTextView.setTypeface(boldTypeface);
        orderTextView.setTextColor(Color.parseColor("#000000"));
    }

    public void submitOrder(View view) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, emailText);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
    }
}
