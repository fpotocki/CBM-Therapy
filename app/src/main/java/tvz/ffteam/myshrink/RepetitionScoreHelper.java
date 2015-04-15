package tvz.ffteam.myshrink;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Filip on 15.4.2015..
 */
public class RepetitionScoreHelper {

    /**
     * Number of test repetitions
     */
    private final int NUMBER_OF_REPETITION=2;

    /**
     * Returns score in percentage
     * @param results
     */
    public float getScore(ArrayList<Boolean> results) {
        Log.d("REZULTATI","Pokrece se racunanje rezultata");
        int occurrences=0;
        for(int i = 0; i < results.size(); i++) {
            if (results.get(i).toString().equals("true")){occurrences++;}
        }
        Log.d("TEST VARIJABLA","pojavljivanje "+occurrences);
        float score = ((float)occurrences/(float)NUMBER_OF_REPETITION)*100;
        Log.d("TEST VARIJABLA","rezultat " +score);
        return score;
    }

    public int getNUMBER_OF_REPETITION() {
        return NUMBER_OF_REPETITION;
    }



}
