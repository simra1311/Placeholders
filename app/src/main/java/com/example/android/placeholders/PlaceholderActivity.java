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
    public final int noOfWords = 5;
    public final int totalNoOfChar = 11;  //ideally to be 26 to include all letters
    //for simplicity using 11 as there are only 5 words
    boolean correct = false;
    String words[] = {"wood","ball","cats","dogs","food"};
    char alpha[] = {'w','o','d','b','a','l','c','t','s','g','f'};

    ImageView next,pic;

    //temporary variables
    TextView option1,option2,option3,option4,choice1,choice2,choice3,choice4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);

        Intent intent = getIntent();
        intent.getIntExtra("WORD",0);
        next = (ImageView)findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);

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


        setDefaults();

        if (currentWord == 0) {
            String uri = "@drawable/wood";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            option1.setText("W");
            choice1.setText("W");
            choice1.setTextColor(getResources().getColor(R.color.colorAccent));
            option2.setText("O");
            choice2.setText("O");
            choice2.setTextColor(getResources().getColor(R.color.colorAccent));
            option3.setText("O");
            choice3.setText("O");
            choice3.setTextColor(getResources().getColor(R.color.colorAccent));
            option4.setText("D");
            choice4.setText("D");
            choice4.setTextColor(getResources().getColor(R.color.colorAccent));

        }
        else if (currentWord == 1){
            String uri = "@drawable/ball";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            option1.setText("B");
            choice1.setText("B");
            choice1.setTextColor(getResources().getColor(R.color.colorAccent));
            option2.setText("A");
            choice2.setText("A");
            choice2.setTextColor(getResources().getColor(R.color.colorAccent));
            option3.setText("L");
            choice3.setText("L");
            choice3.setTextColor(getResources().getColor(R.color.colorAccent));
            option4.setText("L");
            choice4.setText("L");
            choice4.setTextColor(getResources().getColor(R.color.colorAccent));

        }
        else if (currentWord == 2){
            String uri = "@drawable/cats";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            option1.setText("C");
            choice1.setText("C");
            choice1.setTextColor(getResources().getColor(R.color.colorAccent));
            option2.setText("A");
            choice2.setText("A");
            choice2.setTextColor(getResources().getColor(R.color.colorAccent));
            option3.setText("T");
            choice3.setText("T");
            choice3.setTextColor(getResources().getColor(R.color.colorAccent));
            option4.setText("S");
            choice4.setText("S");
            choice4.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else if (currentWord == 3){
            String uri = "@drawable/dogs";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            option1.setText("D");
            choice1.setText("D");
            choice1.setTextColor(getResources().getColor(R.color.colorAccent));
            option2.setText("O");
            choice2.setText("O");
            choice2.setTextColor(getResources().getColor(R.color.colorAccent));
            option3.setText("G");
            choice3.setText("G");
            choice3.setTextColor(getResources().getColor(R.color.colorAccent));
            option4.setText("S");
            choice4.setText("S");
            choice4.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else if (currentWord == 4){
            String uri = "@drawable/food";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            option1.setText("F");
            choice1.setText("F");
            choice1.setTextColor(getResources().getColor(R.color.colorAccent));
            option2.setText("O");
            choice2.setText("O");
            choice2.setTextColor(getResources().getColor(R.color.colorAccent));
            option3.setText("O");
            choice3.setText("O");
            choice3.setTextColor(getResources().getColor(R.color.colorAccent));
            option4.setText("D");
            choice4.setText("D");
            choice4.setTextColor(getResources().getColor(R.color.colorAccent));
        }


        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());
        option4.setOnTouchListener(new ChoiceTouchListener());
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());
        choice4.setOnDragListener(new ChoiceDragListener());
    }

    private void setDefaults() {

        next.setVisibility(View.INVISIBLE);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
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
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
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
                        //stop displaying the view where it was before it was dragged
                       view.setVisibility(View.INVISIBLE);
                        //update the text in the target view to reflect the data being dropped
                        dropTarget.setTextColor(getResources().getColor(R.color.colorAccent));
                        //make it bold to highlight the fact that an item has been dropped
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
                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        //dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);
                    }

                    else
                        //displays message if first character of dropTarget is not equal to first character of dropped
                        Toast.makeText(PlaceholderActivity.this, dropTarget.getText().toString() + "is not " + dropped.getText().toString(), Toast.LENGTH_LONG).show();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
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
