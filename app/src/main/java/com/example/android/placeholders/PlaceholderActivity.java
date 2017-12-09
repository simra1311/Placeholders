package com.example.android.placeholders;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceholderActivity extends AppCompatActivity {

    int currentWord = 0 , currentCharacter = 0;
    String currentString;
    public final int noOfWords = 5;  //this is the count of the letters
    public final int totalNoOfChar = 11;  //ideally to be 26 to include all letters
    //for simplicity using 11 as there are only 5 words
    boolean correct = false;
    String words[] = {"WOODLOGS","BALL","CAT","DOGFISH","FOOD"};
    //max length of  word cannot exceed 8 and it should be a proper word can;t be null
  //  char alpha[] = {'w','o','d','b','a','l','c','t','s','g','f'};

    ImageView next,pic;

    //temporary variables
    TextView option1,option2,option3,option4,option5,option6,option7,option8;  //we can also create an array
    TextView choice1,choice2,choice3,choice4,choice5,choice6,choice7,choice8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);

        Intent intent = getIntent();
        next = (ImageView)findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);

        words[0] = intent.getStringExtra("word1");
        words[1] = intent.getStringExtra("word2");
        words[2] = intent.getStringExtra("word3");
        words[3] = intent.getStringExtra("word4");
        words[4] = intent.getStringExtra("word5");

        pic = (ImageView)findViewById(R.id.pic);
        setTextViews();
    }

    public void setTextViews(){

        option1 = (TextView)findViewById(R.id.option_1);
        choice1 = (TextView)findViewById(R.id.choice_1);
        option2 = (TextView)findViewById(R.id.option_2);
        choice2 = (TextView)findViewById(R.id.choice_2);
        option3 = (TextView)findViewById(R.id.option_3);
        choice3 = (TextView)findViewById(R.id.choice_3);
        option4 = (TextView)findViewById(R.id.option_4);
        choice4 = (TextView)findViewById(R.id.choice_4);
        option5 = (TextView)findViewById(R.id.option_5);
        choice5 = (TextView)findViewById(R.id.choice_5);
        option6 = (TextView)findViewById(R.id.option_6);
        choice6 = (TextView)findViewById(R.id.choice_6);
        option7 = (TextView)findViewById(R.id.option_7);
        choice7 = (TextView)findViewById(R.id.choice_7);
        option8 = (TextView)findViewById(R.id.option_8);
        choice8 = (TextView)findViewById(R.id.choice_8);


        setDefaults();

        if (currentWord == 0) {
            int i = 0;
            int j = 0;
            String uri = "@drawable/wood";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            //  option1.setText("W"); //to make it generic we can also use
            setFormat(i,j);
        }
        else if (currentWord == 1){
            int i = 0, j = 1;
            String uri = "@drawable/ball";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
           setFormat(i,j);

        }
        else if (currentWord == 2){
            int i = 0, j = 2;
            String uri = "@drawable/cats";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            setFormat(i,j);
        }
        else if (currentWord == 3){
            int i = 0, j =3;
            String uri = "@drawable/dogs";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            setFormat(i,j);
        }
        else if (currentWord == 4){
            int i = 0, j=4 ;
            String uri = "@drawable/food";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
           setFormat(i,j);
        }


        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());
        option4.setOnTouchListener(new ChoiceTouchListener());
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());
        choice4.setOnDragListener(new ChoiceDragListener());
        option5.setOnTouchListener(new ChoiceTouchListener());
        option6.setOnTouchListener(new ChoiceTouchListener());
        option7.setOnTouchListener(new ChoiceTouchListener());
        option8.setOnTouchListener(new ChoiceTouchListener());
        choice5.setOnDragListener(new ChoiceDragListener());
        choice6.setOnDragListener(new ChoiceDragListener());
        choice7.setOnDragListener(new ChoiceDragListener());
        choice8.setOnDragListener(new ChoiceDragListener());
    }

    private void setFormat(int i,int j){
        option1.setText(words[j].charAt(i) + "");
        choice1.setText(words[j].charAt(i) + "");
        option1.setTextColor(getResources().getColor(R.color.colorAccent));
        i++;
        if (words[j].length() > i) {
            option2.setText(words[j].charAt(i) + "");
            choice2.setText(words[j].charAt(i) + "");
            option2.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            option2.setText("");
            choice2.setText("");
        }
        i++;
        if (words[j].length() > i) {
            option3.setText(words[j].charAt(i) + "");
            choice3.setText(words[j].charAt(i) + "");
            option3.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            option3.setText("");
            choice3.setText("");
        }
        i++;
        if (words[j].length() > i) {
            option4.setText(words[j].charAt(i) + "");
            choice4.setText(words[j].charAt(i) + "");
            option4.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            option4.setText("");
            choice4.setText("");
        }
        i++;
        if (words[j].length() > i) {
            option5.setText(words[j].charAt(i) + "");
            choice5.setText(words[j].charAt(i) + "");
            option5.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            option5.setText("");
            choice5.setText("");
        }
        i++;
        if (words[j].length() > i) {
            option6.setText(words[j].charAt(i) + "");
            choice6.setText(words[j].charAt(i) + "");
            option6.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            option6.setText("");
            choice6.setText("");
        }
        i++;
        if (words[j].length() > i) {
            option7.setText(words[j].charAt(i) + "");
            choice7.setText(words[j].charAt(i) + "");
            option7.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            option7.setText("");
            choice7.setText("");
        }
        i++;
        if (words[j].length() > i) {
            option8.setText(words[j].charAt(i) + "");
            choice8.setText(words[j].charAt(i) + "");
            option8.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else {
            option8.setText("");
            choice8.setText("");
        }
    }

    private void setDefaults() {

        next.setVisibility(View.INVISIBLE);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
        option5.setVisibility(View.VISIBLE);
        option6.setVisibility(View.VISIBLE);
        option7.setVisibility(View.VISIBLE);
        option8.setVisibility(View.VISIBLE);
        choice1.setTextColor(getResources().getColor(R.color.grey));
        choice2.setTextColor(getResources().getColor(R.color.grey));
        choice3.setTextColor(getResources().getColor(R.color.grey));
        choice4.setTextColor(getResources().getColor(R.color.grey));
        choice5.setTextColor(getResources().getColor(R.color.grey));
        choice6.setTextColor(getResources().getColor(R.color.grey));
        choice7.setTextColor(getResources().getColor(R.color.grey));
        choice8.setTextColor(getResources().getColor(R.color.grey));
        choice1.setTextAppearance(R.style.AppTheme);
        choice2.setTextAppearance(R.style.AppTheme);
        choice3.setTextAppearance(R.style.AppTheme);
        choice4.setTextAppearance(R.style.AppTheme);
        choice5.setTextAppearance(R.style.AppTheme);
        choice6.setTextAppearance(R.style.AppTheme);
        choice7.setTextAppearance(R.style.AppTheme);
        choice8.setTextAppearance(R.style.AppTheme);

//        option1.setTag(null);
//        option2.setTag(null);
//        option3.setTag(null);
//        option4.setTag(null);
    }

    /**
     * ChoiceTouchListener will handle touch events on draggable views
     *
     */
    private final class ChoiceTouchListener implements View.OnTouchListener {
        @SuppressLint("NewApi")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            /*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             */
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * DragListener will handle dragged views being dropped on the drop area
     * - only the drop action will have processing added to it as we are not
     * - amending the default behavior for other parts of the drag process
     *
     */
    @SuppressLint("NewApi")
    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            View view1 = (View) event.getLocalState();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //stop displaying at the original location
                    view1.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view1.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //checking whether first character of dropTarget equals first character of dropped
                    if(dropTarget.getText().toString().charAt(0) == dropped.getText().toString().charAt(0))
                    {
                        view.setVisibility(View.INVISIBLE);
                        dropTarget.setTextColor(getResources().getColor(R.color.colorAccent));
                        dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                        //if an item has already been dropped here, there will be a tag
                        Object tag = dropTarget.getTag();
                        //if there is already an item here, set it back visible in its original place
                        if(tag!=null)
                        {
                            //the tag is the view id already dropped here
                            int existingID = (Integer)tag;
                            Toast.makeText(PlaceholderActivity.this,"Invalid operation",Toast.LENGTH_SHORT).show();
                            //set the original view visible again
                            findViewById(existingID).setVisibility(View.VISIBLE);
                        }
                        //check if all the characters have been placed properly

                        currentCharacter++;
                        if (currentCharacter == words[currentWord].length()){
                            Log.i("TAG",currentCharacter+" "+currentString+" ");
                            next.setVisibility(View.VISIBLE);
                            Toast.makeText(PlaceholderActivity.this,"Congrats,you got it right! Press NEXT to continue",Toast.LENGTH_SHORT).show();
                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        //dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);
                    }

                    else {
                        //displays message if first character of dropTarget is not equal to first character of dropped
                        Toast.makeText(PlaceholderActivity.this, dropTarget.getText().toString() + " is not " + dropped.getText().toString(), Toast.LENGTH_LONG).show();
                        //display back if not matched
                        view1.setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (event.getResult()){  //drop succeeded
                       // view1.setVisibility(View.INVISIBLE);
                    }
                    else {
                        view1.setVisibility(View.VISIBLE);
                    }

                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public void next(View view){
        next = (ImageView)view;
        currentCharacter = 0;
        correct = false;
        currentWord++;
        if (currentWord < noOfWords){
            setTextViews();
        }
        else
            Toast.makeText(PlaceholderActivity.this,"Game over",Toast.LENGTH_SHORT).show();

//        finish();
    }
}
