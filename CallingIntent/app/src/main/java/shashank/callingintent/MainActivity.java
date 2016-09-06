package shashank.callingintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 100;
    private static String[] PERMISSIONS_PHONECALL = {Manifest.permission.CALL_PHONE};
    Button btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall =(Button) findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == btnCall) {
            //permission check and request call function
            call();
        }
    }

    private void call() {
        //check the SDK version and whether the permission is already granted or not.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}
                    , PERMISSIONS_REQUEST_PHONE_CALL);

        }
        else {
            //open call function
            String number ="9582138828";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: "+ number));
            startActivity(intent);
        }

    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode, @NonNull String[] permissions,
             @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_PHONE_CALL){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permission is granted
                call();
            }
            else {
                Toast.makeText(this,"Sorry !! permission denied",Toast.LENGTH_SHORT).show();
                
            }
        }
       }
}
