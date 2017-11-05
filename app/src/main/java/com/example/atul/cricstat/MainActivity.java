package com.example.atul.cricstat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.titletext);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/bunga melati putih.ttf");
        title.setTypeface(type);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,TeamActivity.class);
                startActivity(it);
            }
        });

        Button sot = (Button) findViewById(R.id.sot);
        sot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,SortActivity.class);
                startActivity(in);
            }
        });

        Button news1 = (Button) findViewById(R.id.news);
        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(MainActivity.this,NewsActivity.class);
                startActivity(inte);
            }
        });

        Button series1 = (Button) findViewById(R.id.series);
        series1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(MainActivity.this, SeriesActivity.class);
                startActivity(itn);
            }
        });
    }
}
