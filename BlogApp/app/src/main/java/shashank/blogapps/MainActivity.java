package shashank.blogapps;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;

public class MainActivity extends ListActivity {

    protected String[] mBlogPostTitles ;
    public static final int NUMBER_OF_POSTS = 20;
    public static final String TAG= "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL blogFeedUrl = new URL("http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS);
            HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
            connection.connect(); //connecting to the connection


            int responseCode = connection.getResponseCode();
            Log.i(TAG,responseCode + "");
                                        /*why I'm making this string because
                                        log.i will take 2nd param as string
                                        (not int )so "" will make it string
                                        */

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

}
