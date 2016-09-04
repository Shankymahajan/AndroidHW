package shashank.blogapps;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;

public class MainActivity extends ListActivity {

    protected String[] mBlogPostTitles ;
    public static final int NUMBER_OF_POSTS = 20;
    public static final String TAG= "MainActivity";
    int responseCode =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if isNetworkAvailable() method will
        // return true only then this statement will work .
        if (isNetworkAvailable()) {

            GetBlogPostTask getBlogPostTask = new GetBlogPostTask();
            getBlogPostTask.execute();
        }
        else{
            Toast.makeText(this,"Network is unavailable "
                    ,Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false ; //by default it is false
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }


    return isAvailable;

    }

    /*Params <1st as the parameter that will go in doInBackground,
        <2nd Progress unit (void)
        3rd return type
        */
        private class  GetBlogPostTask extends AsyncTask<Object,Void,String> {

        @Override
        protected String doInBackground(Object... arg0) {
            try {
                URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS);
                HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
                connection.connect(); //connecting to the connection


                responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    Reader reader = new InputStreamReader(inputStream);
                    // Reader will read char by char so we can store it in an array

                    int contentLegth = connection.getContentLength();
                    char[] charArray = new char[contentLegth];
                    reader.read(charArray);
                    //read will be char by char through one more reader object
                    String responseData = new String(charArray);
                    Log.d(TAG, responseData);

                } else {
                    Log.d(TAG, "Unsuccessful HTTP response code : " + responseCode);

                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Code : " + responseCode;
            //HTTP_OK = 200


        }
    }
}
