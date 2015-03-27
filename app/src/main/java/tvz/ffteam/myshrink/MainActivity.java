package tvz.ffteam.myshrink;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectionDetector connection = new ConnectionDetector(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Internet is: " + connection.isConnectingToInternet() + ".", Toast.LENGTH_LONG).show();

   //Privremeno, SAMO ZA TESTIRANJE. Inače ide timer
       final Intent intent = new Intent(this, ImageShowActivity.class);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        startActivity(intent);
                    }
                },
                2000
        );



    }



}
