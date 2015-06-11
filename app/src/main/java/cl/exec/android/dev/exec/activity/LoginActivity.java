package cl.exec.android.dev.exec.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import cl.exec.android.dev.exec.R;
import io.fabric.sdk.android.Fabric;


public class LoginActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /**
     *  UI
     */
    private TextView textViewWelcomeMessage;
    private Button loginButton;
    private Button buttonLayoutGoogleLogin;
    private Button buttonLayoutFacebookLogin;
    private Button buttonLayoutTwitterLogin;
    private ImageView imageViewSwipeIcon;
    private LinearLayout linearLayoutLoginOptions;

    private ProgressDialog mProgressDialog;


    private String userName;
    private String userEmail;
    private String userPhotoURL;

    /**
     *
     */
    private CallbackManager callbackManager;
    public TwitterAuthClient twitterAuthClient;


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "1ol9I0IzOa9dtRw1sAuuaubtP";
    private static final String TWITTER_SECRET = "0nKge16Cg2wCOUvJY2iDW1OayvUH2Y7AYOL245x0AmkRzumzea";

    private final String LOG_TAG = LoginActivity.class.getSimpleName();
    private static final String KEY_IN_RESOLUTION = "is_in_resolution";

    private final static int PROFILE_PIC_SIZE = 300;


    /**
     * Request code for auto Google Play Services error resolution.
     */
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    /**
     * Google API client.
     */
    private GoogleApiClient mGoogleApiClient;

    /**
     * Determines if the client is in a resolution state, and
     * waiting for resolution intent to return.
     */
    private boolean mIsInResolution;

    /**
     * Called when the activity is starting. Restores the activity state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Twitter initialize
         */
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        twitterAuthClient= new TwitterAuthClient();

        /**
         * Facebook initialize
         */
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(R.layout.activity_login);

        mProgressDialog = initializeProgressDialog();

        //change actionbar default color
        ActionBar actionBar = getActionBar();

        if(actionBar!=null){
            //TODO: change actionBar color
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));
        }

        // welcome message
        textViewWelcomeMessage =(TextView) findViewById(R.id.textViewWelcomeMessage);
        textViewWelcomeMessage.setText(WelcomeText(new Date()));

        linearLayoutLoginOptions = (LinearLayout) findViewById(R.id.linearLayoutLoginOptions);
        linearLayoutLoginOptions.setVisibility(View.INVISIBLE);

        //swipe icon up/down
        imageViewSwipeIcon = (ImageView) findViewById(R.id.imageViewSwipeIcon);
        imageViewSwipeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearLayoutLoginOptions.getVisibility()==View.INVISIBLE){
                    imageViewSwipeIcon.setImageResource(R.drawable.ic_action_swipe_down_light);
                    linearLayoutLoginOptions.setVisibility(View.VISIBLE);
                }else {
                    imageViewSwipeIcon.setImageResource(R.drawable.ic_action_swipe_up_light);
                    linearLayoutLoginOptions.setVisibility(View.INVISIBLE);
                }
            }
        });


        /**
         * Simple login
         */
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: validate against something before start new activity

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /**
         * Google account login
         */
        buttonLayoutGoogleLogin = (Button) findViewById(R.id.buttonLayoutGoogleLogin);
        buttonLayoutGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleApiClient.connect();
            }
        });

        /**
         * Twitter login
         */
        buttonLayoutTwitterLogin = (Button) findViewById(R.id.buttonLayoutTwitterLogin);
        buttonLayoutTwitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterAuthClient.authorize(LoginActivity.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.e(LOG_TAG, "ERROR: " + e.toString());
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        /**
         * Facebook login
         */
        buttonLayoutFacebookLogin = (Button) findViewById(R.id.buttonLayoutFacebookLogin);
        buttonLayoutFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin();
            }
        });

        if (savedInstanceState != null) {
            mIsInResolution = savedInstanceState.getBoolean(KEY_IN_RESOLUTION, false);
        }
    }

    /**
     * Called when the Activity is made visible.
     * A connection to Play Services need to be initiated as
     * soon as the activity is visible. Registers {@code ConnectionCallbacks}
     * and {@code OnConnectionFailedListener} on the
     * activities itself.
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Plus.API)
                    .addScope(Plus.SCOPE_PLUS_LOGIN)
                    .addScope(Plus.SCOPE_PLUS_PROFILE)
                    // Optionally, add additional APIs and scopes if required.
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    /**
     * Called when activity gets invisible. Connection to Play Services needs to
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismissProgressDialog();
    }

    /**
     * Saves the resolution state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IN_RESOLUTION, mIsInResolution);
    }

    /**
     * Handles Google Play Services resolution callbacks.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_RESOLUTION:
                retryConnecting();
                break;
        }
        // twitter onActivityResult
        twitterAuthClient.onActivityResult(requestCode, resultCode, data);
        // facebook onActivityResult
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void retryConnecting() {
        mIsInResolution = false;
        if (!mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
            showProgressDialog();
        }
    }

    /**
     * Called when {@code mGoogleApiClient} is connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // TODO: Start making API requests.
        if (mGoogleApiClient.isConnected()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Called when {@code mGoogleApiClient} connection is suspended.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        retryConnecting();
    }

    /**
     * Called when {@code mGoogleApiClient} is trying to connect but failed.
     * Handle {@code result.getResolution()} if there is a resolution
     * available.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(LOG_TAG, "GoogleApiClient connection failed: " + result.toString());
        if (!result.hasResolution()) {
            // Show a localized error dialog.
            GooglePlayServicesUtil.getErrorDialog(
                    result.getErrorCode(), this, 0, new OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            retryConnecting();
                        }
                    }).show();
            return;
        }
        // If there is an existing resolution error being displayed or a resolution
        // activity has started before, do nothing and wait for resolution
        // progress to be completed.
        if (mIsInResolution) {
            return;
        }
        mIsInResolution = true;
        try {
            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
        } catch (SendIntentException e) {
            Log.e(LOG_TAG, "Exception while starting resolution activity", e);
            retryConnecting();
        }
    }

    /**
     * @param date
     * @return welcome message
     */
    private String WelcomeText(Date date){

        String welcomeMessage ="";

        date = new Date();
        String currentTime = new SimpleDateFormat("HH").format(date);

        Integer currentTimeInt = Integer.parseInt(currentTime);


        if (currentTimeInt > 12 && currentTimeInt <21){
            welcomeMessage = "Buenas tardes";
        }

        if (currentTimeInt >= 21 && currentTimeInt <=23){
            welcomeMessage = "Buenas noches";
        }

        if (currentTimeInt >= 0 && currentTimeInt <=5){
            welcomeMessage = "Buenas noches";
        }

        if (currentTimeInt >= 6 && currentTimeInt <=12){
            welcomeMessage = "Buenos dias";
        }
        return welcomeMessage;
    }

    /**
     * Facebook login, first logout, then login
     */
    private void facebookLogin(){
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    private ProgressDialog initializeProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setMessage(getString(R.string.progress_dialog));
        return dialog;
    }

    private void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
