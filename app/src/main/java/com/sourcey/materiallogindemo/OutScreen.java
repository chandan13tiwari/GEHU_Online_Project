package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by HP on 08-May-19.
 */

public class OutScreen extends AppCompatActivity {

    Thread splashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outscreen);

        StartAnimations();
    }

    private void StartAnimations()
    {



        splashThread = new Thread(){
            @Override
            public void run() {
                try{
                    int waited = 0;
                    //spalsh screen pause time
                    while(waited < 5000){
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(OutScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                    OutScreen.this.finish();
                }
                catch(InterruptedException e)
                {
                    //do nothing
                }
                finally{
                    OutScreen.this.finish();
                }
            }
        };
        splashThread.start();
    }
}
