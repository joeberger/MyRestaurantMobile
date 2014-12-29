package net.myrestaurantmobile.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;

import net.myrestaurantmobile.android.util.SystemUiHider;

import java.io.InputStream;
import java.net.URL;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class ThankYouActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    //private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    //private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    //private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    //private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private String resourceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thank_you);

        /*resourceUrl = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("resource_url", "");
        loadResources();*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        goToWelcome();
    }

    private void loadResources(){
        new LoadResources().execute();
    }

    private class LoadResources extends AsyncTask<Void, Void, Void> {
        Bitmap img;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                InputStream in = new URL(resourceUrl + "ThankYou.gif").openStream();
                img = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.d("ThankYouActivity", " error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (img != null){
                ImageView view = (ImageView)findViewById(R.id.fullscreen_content);
                view.setImageBitmap(img);
            }
        }
    }

    protected void goToWelcome(){
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }
}
