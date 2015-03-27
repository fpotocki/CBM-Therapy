package tvz.ffteam.myshrink;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ImageShowActivity extends Activity {
    //Elements declaration
    ImageView leftImageView=null;
    ImageView rightImageView=null;
    ImageView finalImageView=null;

    TextView leftImageText=null;
    TextView rightImageText=null;

    EditText userInputText=null;

    Button submitBtn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        //Layout elements definition
       leftImageView = (ImageView)findViewById(R.id.leftImage);
       rightImageView = (ImageView)findViewById(R.id.rightImage);
       finalImageView = (ImageView)findViewById(R.id.finalImage);

       leftImageText=(TextView)findViewById(R.id.leftImageText);
       rightImageText=(TextView)findViewById(R.id.rightImageText);

       userInputText=(EditText) findViewById(R.id.UserInputText);

        submitBtn=(Button) findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inptxt=userInputText.getText().toString();
                if (inptxt.matches("")) {
                    Toast.makeText(ImageShowActivity.this, "You did not enter anything", Toast.LENGTH_SHORT).show();
                    return;
                }
               // Toast.makeText(ImageShowActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }});


        //Timer
        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    public void run() {
                        TimerMethod();

                    }
                },
                5000
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

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here
            leftImageView.setVisibility(View.INVISIBLE);
            rightImageView.setVisibility(View.INVISIBLE);
            leftImageText.setVisibility(View.INVISIBLE);
            rightImageText.setVisibility(View.INVISIBLE);
            finalImageView.setVisibility(View.VISIBLE);
            userInputText.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);

        }
    };

    //Disablean back button za ovu aktivnost
    @Override
    public void onBackPressed() {
    }
}
