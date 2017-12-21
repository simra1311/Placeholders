package com.example.android.placeholders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.analytics.FirebaseAnalytics;
public class MainActivity extends AppCompatActivity {

    Button button;
    EditText word1,word2,word3,word4,word5;
    String one,two,three,four,five;

    DatabaseReference databaseReference;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word1 = (EditText)findViewById(R.id.word1);
        word2 = (EditText)findViewById(R.id.word2);
        word3 = (EditText)findViewById(R.id.word3);
        word4 = (EditText)findViewById(R.id.word4);
        word5 = (EditText)findViewById(R.id.word5);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Words");

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        button = (Button) findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                addData();
                Intent intent = new Intent(MainActivity.this, PlaceholderActivity.class);
                intent.putExtra("word1",one);
                intent.putExtra("word2",two);
                intent.putExtra("word3",three);
                intent.putExtra("word4",four);
                intent.putExtra("word5",five);
                startActivity(intent);
            }
        });

    }

    public void addData(){
        one = word1.getEditableText().toString().trim();
        if (one.matches(""))    one = "woodlog";
        two = word2.getEditableText().toString().trim();
        if (two.matches(""))    two = "balls";
        three = word3.getEditableText().toString().trim();
        if (three.matches(""))  three = "cats";
        four = word4.getEditableText().toString().trim();
        if (four.matches(""))   four = "dogs";
        five = word5.getEditableText().toString().trim();
        if (five.matches(""))   five = "food";

        SaveData saveData = new SaveData(one,two,three,four,five);
        databaseReference.push().setValue(saveData);
    }
}
