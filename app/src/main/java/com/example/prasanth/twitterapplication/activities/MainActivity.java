package com.example.prasanth.twitterapplication.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasanth.twitterapplication.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button twitterSignInBtn, twitterSignOut;
    private TextView twitterUserName;
    static String TWITTER_CONSUMER_KEY = "TkvV9knHcROXTAm8kRIjJ6qYa";
    static String TWITTER_CONSUMER_SECRET = "M31sNOwp0KXcSASY3SYlZeyjRAa9XAFyLfssaX0QXChVVc4uUM";

    // Preference Constants
    static String PREFERENCE_NAME = "twitter_oauth";
    static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

    static final String TWITTER_CALLBACK_URL = "thttp://testing.info";

    // Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

    private static Twitter twitter;
    private static RequestToken requestToken;
    private static SharedPreferences mSharedPreferences;
    private TwitterAuthClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);
        Fabric.with(this, new com.twitter.sdk.android.Twitter(authConfig));
        setContentView(R.layout.activity_main);
        twitterSignInBtn = (Button) findViewById(R.id.twittedSignInBtn);
        twitterUserName = (TextView) findViewById(R.id.twitterUserName);
        twitterSignOut = (Button) findViewById(R.id.SignOut);

        twitterSignInBtn.setOnClickListener(this);
        twitterSignOut.setOnClickListener(this);


        mSharedPreferences = getApplication().getSharedPreferences("MyPref", 0);
        /*loginInToTwitter();*/
    }

    /* public void loginInToTwitter() {
         if (!isTwitterLoggedInAlready())
         {
             ConfigurationBuilder configurationBuilder=new ConfigurationBuilder();
             configurationBuilder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
             configurationBuilder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
             Configuration configuration=configurationBuilder.build();

             TwitterFactory factory=new TwitterFactory(configuration);
             twitter=factory.getInstance();

             try {
                 requestToken=twitter.getOAuthRequestToken();
                 this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
             } catch (TwitterException e) {
                 e.printStackTrace();
             }

         }
         else {
             Toast.makeText(this,"User has already logged in",Toast.LENGTH_LONG).show();
         }


     }
 */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.twittedSignInBtn:

                client = new TwitterAuthClient();
                //make the call to login
                client.authorize(this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        //feedback
                        result.data.getUserName();
                        Toast.makeText(getApplicationContext(), "Login worked", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(com.twitter.sdk.android.core.TwitterException exception) {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                    }

                });

     /*           TwitterLoginButton button = new TwitterLoginButton(this);

                button.setCallback(new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                    Toast.makeText(getApplicationContext(),"User Logged in successfully",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(com.twitter.sdk.android.core.TwitterException exception) {
                        Toast.makeText(getApplicationContext(),"Sorry, User Log in Unsuccessful",Toast.LENGTH_LONG);
                    }
                });

                button.performClick();*/
                break;
            case R.id.SignOut:
                logoutTwitter();
              /*  TwitterCore.getInstance().logOut();*/
                //com.twitter.sdk.android.Twitter.logOut();
                //Toast.makeText(this,"Logged out Successfully",Toast.LENGTH_LONG).show();
              /*  TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_CONSUMER_KEY,TWITTER_CONSUMER_SECRET);
                Fabric.with(this, new TwitterCore(authConfig));*/
              /*logoutTwitter();*/

        }


              /*  TwitterAuthClient mTwitterAuthClient= new TwitterAuthClient();
                mTwitterAuthClient.authorize(this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        //feedback
                        Toast.makeText(getApplicationContext(), "Login worked", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(com.twitter.sdk.android.core.TwitterException exception) {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                    }
                });*/

            /*    @Override
                protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
                mTwitterAuthClient.onActivityResult(requestCode, responseCode, intent);
            }*/

               /* loginInToTwitter();*/
               /* new NetworkOperations().execute();
                if (!isTwitterLoggedInAlready())
                {
                    Uri uri=getIntent().getData();
                    if (uri!=null && uri.toString().startsWith(TWITTER_CALLBACK_URL))
                    {
                        String verifier=uri.getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);
                        try {
                            AccessToken accessToken=twitter.getOAuthAccessToken(requestToken,verifier);
                            SharedPreferences.Editor e=mSharedPreferences.edit();

                            e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
                            e.putString(PREF_KEY_OAUTH_SECRET,
                                    accessToken.getTokenSecret());

                            e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
                            e.commit();

                            long userID=accessToken.getUserId();
                            User user=twitter.showUser(userID);
                            String userName=user.getName();
                            twitterUserName.setText(userName);
                        } catch (TwitterException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        client.onActivityResult(requestCode, resultCode, data);
    }

    private void logoutTwitter() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        /*TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_CONSUMER_KEY,TWITTER_CONSUMER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig));*/
        if (twitterSession != null) {
            ClearCookies(this);
            com.twitter.sdk.android.Twitter.getSessionManager().clearActiveSession();
            com.twitter.sdk.android.Twitter.logOut();
            Toast.makeText(this, "Logged Out successfull", Toast.LENGTH_LONG).show();
        }

        /*CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeSessionCookie();
        Twitter.getSessionManager().clearActiveSession();
        Twitter.logOut();*/
    }

    private void ClearCookies(Context applicationContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(applicationContext);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }


  /*  public boolean isTwitterLoggedInAlready() {
        // return twitter login status from Shared Preferences
        return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
    }*/

 /*   public class NetworkOperations extends AsyncTask{


        @Override
        protected Void doInBackground(Object[] params) {
            if (!isTwitterLoggedInAlready())
            {
                ConfigurationBuilder configurationBuilder=new ConfigurationBuilder();
                configurationBuilder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
                configurationBuilder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
                Configuration configuration=configurationBuilder.build();

                TwitterFactory factory=new TwitterFactory(configuration);
                twitter=factory.getInstance();

                try {
                    requestToken=twitter.getOAuthRequestToken();

                   *//* getApplicationContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));*//*
                } catch (TwitterException e) {
                    e.printStackTrace();
                }

            }
            else {
                Toast.makeText(getApplicationContext(),"User has already logged in",Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(requestToken.getAuthenticationURL()));
            startActivity(intent);
        }
    }*/
}
