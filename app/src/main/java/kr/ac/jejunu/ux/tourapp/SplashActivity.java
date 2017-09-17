package kr.ac.jejunu.ux.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by HJ on 2017-09-17.
 *
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
