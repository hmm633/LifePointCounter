package cs371m.lifepointcounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Andy Tang on 4/1/16.
 *
 * Splash Screen
 *
 * Reference1:  http://stackoverflow.com/questions/5486789/how-do-i-make-a-splash-screen
 * Reference2:  https://www.youtube.com/watch?v=XwOuTjUFexE
 */

public class Splash extends Activity {

    // Duration of splash screen's waiting time
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this, ScoreScreen.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}
