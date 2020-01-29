package com.example.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final int  DETAIL_REQUIST = 1;

    Button mButton;
    Button DtlButton;
    TextView tvSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mButton = findViewById(R.id.myButton2);

        tvSelect = findViewById(R.id.TV3);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),FirstActivity.class);
                startActivity(i);

            }
        });

        DtlButton = findViewById(R.id.DetailButton3);
        DtlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),DetailActivity.class);
                i.putExtra("keyForSending","Some data from Second Activity");
                startActivityForResult(i,DETAIL_REQUIST);


            }
        });


        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         if (resultCode == RESULT_OK && requestCode == DETAIL_REQUIST){
             if (data.hasExtra("KeyForReturning")){
                 String myValue = data.getExtras().getString("KeyForReturning");
                 tvSelect.setText(myValue);
             }
         }

    }


}

