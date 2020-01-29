package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class DetailActivity extends AppCompatActivity {

    Button RtnButton;
    Button PerformButton;
    Spinner nSpiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Display data sent to us
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String datavalue = extras.getString("keyForSending");
            if (datavalue != null){
                Toast.makeText(this,datavalue,Toast.LENGTH_SHORT).show();
            }
        }

        nSpiner = findViewById(R.id.spinnerSelection);

        RtnButton = findViewById(R.id.ReturnButton4);
        RtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rtnIntent = new Intent();
                String mySelection = nSpiner.getSelectedItem().toString();
                rtnIntent.putExtra("KeyForReturning",mySelection);
                setResult(RESULT_OK,rtnIntent);
                finish();
            }
        });

        PerformButton = findViewById(R.id.myButton5);
        PerformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = nSpiner.getSelectedItemPosition();
                Intent implicitIntent = null;
                switch(position){
                    case 0:
                        // if Nothing Selected
                        break;
                    case 1:
                        //Visiting Site
                        implicitIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://google.com"));
                        break;
                    case 2:
                        // Call someone
                        implicitIntent = new Intent(Intent.ACTION_DIAL,
                                Uri.parse("tel: 7037368036"));
                        break;
                    case 3:
                        // Map
                        implicitIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("gMaps:WQQ4+69Sirchandi"));
                        break;
                    case 4:
                        // Taking picture
                        implicitIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                        break;
                    case 5:
                        // Edit first contact
                        implicitIntent = new Intent(Intent.ACTION_EDIT,
                                Uri.parse("contents://Contacts"));
                        break;


                }
                // is intent available
                if (implicitIntent != null){

                    if (isIntentAvailable(implicitIntent) == true) {
                        startActivity(implicitIntent);
                    }else{
                        Toast.makeText(view.getContext(),"no application is avlble",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
    public boolean isIntentAvailable(Intent intent){
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent,0);{
            boolean isIntentSafe = activities.size() > 0;
            return isIntentSafe;
        }

    }
}
