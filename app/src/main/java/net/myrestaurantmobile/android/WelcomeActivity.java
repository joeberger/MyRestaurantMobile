package net.myrestaurantmobile.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
public class WelcomeActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 1;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = false;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    //private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    //private SystemUiHider mSystemUiHider;
    private String resourceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        /*resourceUrl = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("resource_url", "");
        loadResources();*/

        final View contentView = findViewById(R.id.fullscreen_content);
        final View emailButton =  findViewById(R.id.enter_email_button);

        contentView.setOnClickListener(mClickListener);
        emailButton.setOnClickListener(mClickListener);

        /*contentView.setOnLongClickListener(mLongClickListener);
        emailButton.setOnLongClickListener(mLongClickListener);*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        // TODO: Check if resource URL was changed. Reload the images
    }

    private void loadResources(){
        new LoadResources().execute();
    }

    private class LoadResources extends AsyncTask<Void, Void, Void> {
        Bitmap img;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                InputStream in = new URL(resourceUrl + "SplashScreen.gif").openStream();
                img = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.d("WelcomeActivity", " error");
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

    ImageView.OnClickListener mClickListener = new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(i);
            finish();

            /*Intent i = new Intent();
            i.setComponent(new ComponentName("net.myrestaurantmobile.android", "net.myrestaurantmobile.android.SignupActivity"));
            i.setAction("android.intent.action.MAIN");
            //i.addCategory("android.intent.category.LAUNCHER");
            //i.addCategory("android.intent.category.DEFAULT");
            v.getContext().startActivity(i);*/
        }
    };

    ImageView.OnLongClickListener mLongClickListener = new ImageView.OnLongClickListener() {
        @Override
        public  boolean onLongClick(View v) {
            Intent i = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(i);

            /*Intent i = new Intent();
            i.setComponent(new ComponentName("net.myrestaurantmobile.android", "net.myrestaurantmobile.android.SettingsActivity"));
            i.setAction("android.intent.action.MAIN");
            v.getContext().startActivity(i);*/
            return true;
        }
    };
}