package com.example.android.placeholders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int currentWord = 0,noOfWords = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.play);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlaceholderActivity.class);
                if (currentWord < noOfWords) {
                    intent.putExtra("WORD", currentWord);
                    startActivity(intent);
                    currentWord++;
                    Log.i("TAG", currentWord+"");
                }
            }
        });

    }
}
