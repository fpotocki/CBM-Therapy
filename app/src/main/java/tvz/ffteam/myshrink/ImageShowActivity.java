package tvz.ffteam.myshrink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;


public class ImageShowActivity extends Activity {
    private String positiveText;

    //Elements declaration
    ImageView leftImageView=null;
    ImageView rightImageView=null;
    ImageView finalImageView=null;

    TextView leftImageText=null;
    TextView rightImageText=null;

    EditText userInputText=null;

    Button submitBtn=null;
    Timer timer = new Timer();

    ArrayList<View> leftSide = new ArrayList<>();
    ArrayList<View> rightSide = new ArrayList<>();

    ArrayList<String> PositiveTextArray = new ArrayList<>();
    ArrayList<String> NegativeTextArray = new ArrayList<>();
    ArrayList<String> PositiveImagesPath = new ArrayList<>();
    ArrayList<String> NegativeImagesPath = new ArrayList<>();
    ArrayList<Boolean> results= new ArrayList<>();

    //Od tuda dobiva broj ponavljanja
    RepetitionScoreHelper repetitionScoreHelper = new RepetitionScoreHelper();
    int repetitions=repetitionScoreHelper.getNUMBER_OF_REPETITION();
    int current_repetition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        final View activityView = this.getWindow().getDecorView();
        //DATABASE TESTING
        Context mContext = getApplicationContext();
        final DatabaseHelper mDbHelper = new DatabaseHelper(mContext);

        final StringComparator stringComparator= new StringComparator();

        Cursor c = mDbHelper.getPositiveText();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                PositiveTextArray.add(c.getString(c.getColumnIndex("Text")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < PositiveTextArray.size(); i++) {
            Log.e("Iz pozz arraya je:::: ", "-->" + PositiveTextArray.get(i));
        }

        c.close();


        c = mDbHelper.getNegativeText();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                NegativeTextArray.add(c.getString(c.getColumnIndex("Text")));
                c.moveToNext();
            }
            for (int i = 0; i < NegativeTextArray.size(); i++) {
                Log.e("Iz neg arraya je:::: ", "-->" + NegativeTextArray.get(i));
            }

        }
        c.close();

        c = mDbHelper.getPositiveImages();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                PositiveImagesPath.add(c.getString(c.getColumnIndex("Path")));
                c.moveToNext();
            }
        }
        c.close();

        c = mDbHelper.getNegativeImages();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                NegativeImagesPath.add(c.getString(c.getColumnIndex("Path")));
                c.moveToNext();
            }
        }
        c.close();

        //Layout elements definition
        leftImageView = (ImageView) findViewById(R.id.leftImage);
        rightImageView = (ImageView) findViewById(R.id.rightImage);
        finalImageView = (ImageView) findViewById(R.id.finalImage);
        rightImageText = (TextView) findViewById(R.id.rightImageText);
        leftImageText = (TextView) findViewById(R.id.leftImageText);
    //    leftImageText.setText((CharSequence) "Filip");


        leftSide.add(leftImageView);
        leftSide.add(leftImageText);

        rightSide.add(rightImageView);
        rightSide.add(rightImageText);

        userInputText = (EditText) findViewById(R.id.UserInputText);

        submitBtn = (Button) findViewById(R.id.submitButton);
        choosePositiveOrNegativeSide();
        TimerAcivation(5000);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inptxt = userInputText.getText().toString();
                if (inptxt.matches("")) {
                    Toast.makeText(ImageShowActivity.this, "You did not enter anything", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    //GOTOVO SVE, POKREĆE SE SPREMANJE I GAŠENJE APLIAKCIJE
                    current_repetition++;
                    Log.d("KOMPARACIJA STRINGOVA","REZZ JE: "+stringComparator.compare(inptxt,positiveText));
                    results.add(stringComparator.compare(inptxt, positiveText));
                    //Za tipkovnicu
                    InputMethodManager inputManager =
                            (InputMethodManager) getApplicationContext().
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (current_repetition==repetitions)
                    {
                        finalImageView.setVisibility(View.INVISIBLE);
                        submitBtn.setVisibility(View.INVISIBLE);
                        userInputText.setVisibility(View.INVISIBLE);
                        inputManager.hideSoftInputFromWindow(userInputText.getWindowToken(), 0);
                        activityView.setBackgroundColor(Color.BLUE);
                        Toast.makeText(ImageShowActivity.this, "Gotovi ste s testovima", Toast.LENGTH_LONG).show();
                        Log.d("TESTOVI GOTOVI","");
                        mDbHelper.setScoreInDatabase(repetitionScoreHelper.getScore(results));
                       // ImageShowActivity.this.finish();
                       // System.exit(0);

                    }
                    else {
                        TimerAcivation(5000);
                        inputManager.hideSoftInputFromWindow(userInputText.getWindowToken(), 0);
                        userInputText.setText("");
                        choosePositiveOrNegativeSide();
                        changeVisibility();
                    }
                }
            }
        });}


        //Timer


    private void TimerAcivation(int time) {
        Log.d("TIMER", "Pokreće se timer");
        timer.schedule(
                new java.util.TimerTask() {

                    public void run() {
                        Log.d("TIMER", "Novi timer task");
                        TimerMethod();

                    }
                },
                time
        );

    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            Log.d("TIMER", "Mijenja se visibility");
            changeVisibility();
            //This method runs in the same thread as the UI.
        }
    };

    //Disablean back button za ovu aktivnost
    @Override
    public void onBackPressed() {
    }

    private void changeVisibility()
    {

        Log.d("VISIBILITY", "Pozvana metoda changeVisibility");
            if (leftImageView.getVisibility()==View.VISIBLE){
                leftImageView.setVisibility(View.INVISIBLE);
            }
            else {
                leftImageView.setVisibility(View.VISIBLE);
            }

        if (rightImageView.getVisibility()==View.VISIBLE){
            rightImageView.setVisibility(View.INVISIBLE);
        }
        else {
            rightImageView.setVisibility(View.VISIBLE);
        }

        if (leftImageText.getVisibility()==View.VISIBLE){
            leftImageText.setVisibility(View.INVISIBLE);
        }
        else {
            leftImageText.setVisibility(View.VISIBLE);
        }

        if (rightImageText.getVisibility()==View.VISIBLE){
            rightImageText.setVisibility(View.INVISIBLE);
        }
        else {
            rightImageText.setVisibility(View.VISIBLE);
        }

        if (finalImageView.getVisibility()==View.VISIBLE){
            finalImageView.setVisibility(View.INVISIBLE);
        }
        else {
            finalImageView.setVisibility(View.VISIBLE);
        }
        if (userInputText.getVisibility()==View.VISIBLE){
            userInputText.setVisibility(View.INVISIBLE);
        }
        else {
            userInputText.setVisibility(View.VISIBLE);
        }
        if (submitBtn.getVisibility()==View.VISIBLE){
            submitBtn.setVisibility(View.INVISIBLE);
        }
        else {
            submitBtn.setVisibility(View.VISIBLE);
        }

    }
private void choosePositiveOrNegativeSide(){
    int rnd=0;
    rnd = new Random().nextInt(2)+1;
    Log.d("RANDOM NUMBER","PRINT: "+rnd);
    if (rnd==1)
    {
        setPositiveElements(leftSide);
        finalImageView.setImageDrawable(leftImageView.getDrawable());
        setNegativeElements(rightSide);
    }
    else {
        setPositiveElements(rightSide);
        finalImageView.setImageDrawable(rightImageView.getDrawable());
        setNegativeElements(leftSide);

    }


}


    private void setPositiveElements(ArrayList<View> elementList) {
        int rnd = new Random().nextInt(2)+1;
        positiveText =null;
        Collections.shuffle(PositiveImagesPath);
        Collections.shuffle(PositiveTextArray);
        setBackgroundImage(PositiveImagesPath,elementList,rnd);
        Log.d("TextView", "Postavlja pozitivni text");
        TextView tmp = (TextView)elementList.get(1);
        tmp.setText(PositiveTextArray.get(new Random().nextInt(2)+1));
        positiveText=tmp.getText().toString();
    }

    private void setNegativeElements(ArrayList<View> elementList) {
        int rnd = new Random().nextInt(3)+1;
        Collections.shuffle(NegativeImagesPath);
        Collections.shuffle(NegativeTextArray);
        setBackgroundImage(NegativeImagesPath,elementList,rnd);
        Log.d("TextView", "Postavlja negativni text");
        TextView tmp = (TextView)elementList.get(1);
        Log.d("SIZE ARRAYA", "SIZE: "+NegativeTextArray.size());
        tmp.setText(NegativeTextArray.get(new Random().nextInt(2)+1).toString());


    }

    private void setBackgroundImage(ArrayList<String> imageList,ArrayList<View> imageViewList, int rnd) {
        try {
            // get input stream
            AssetManager assetManager = getApplicationContext().getAssets();
            InputStream ims = assetManager.open(imageList.get(rnd));
            Log.d("SLIKA", "Stvara drawable iz slike");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            Log.d("SLIKA", "Postavlja sliku");
            ImageView tmp = (ImageView)imageViewList.get(0);
            tmp.setImageDrawable(d);
        } catch (IOException ex) {
            Log.e("SLIKA", " EXCEPTION!!!!!" + ex.toString());
            return;
        }
    }
}
