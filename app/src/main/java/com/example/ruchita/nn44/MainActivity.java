package com.example.ruchita.nn44;

import android.content.Intent;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int c = 0;

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5 ,8}, {0, 4, 8}, {2, 4, 6}};

    public void retro(View v){

        Log.i("here", "Entered Retro");

        Button bq = (Button) findViewById(R.id.b1) ;

        bq.setVisibility(Button.VISIBLE);

        bq.animate().alpha(1f).setDuration(2000);

    }

    public void sett(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f);
        int r = Integer.parseInt(counter.getTag().toString());
        if(c%2 == 0){
        counter.setImageResource(R.drawable.yellow);
        counter.animate().translationYBy(1000f).setDuration(300);
            state[r] = 1;
            Log.i("Yello", "Hopefully something is doen");
            view.setClickable(false);
            c++;
        } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).setDuration(300);
                state[r] = 0;
            Log.i("Red", "Hopefully something is doen");
            view.setClickable(false);
            c++;
        }

        for(int[] winn : win){

            if(state[winn[0]] == state[winn[1]] && state[winn[1]] == state[winn[2]] && state[winn[0]] != 2){

                Log.i("Info", "Somebody won!");

                if(state[winn[0]] == 1){

                    Toast.makeText(MainActivity.this, "Yellow won!", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(MainActivity.this, "Red won!", Toast.LENGTH_SHORT).show();

                }

                Button bq = (Button) findViewById(R.id.b1) ;
                retro(bq);

            }
        }

        int x;

        boolean flag = false;

        for( x = 0; x <= 8; x= x + 1) {

            if(state[x] == 2){

                flag=true;
                break;

            }
        }

        if(!flag)
        {
            Button bq = (Button) findViewById(R.id.b1) ;
            retro(bq);
        }
    }


    public void mark(View view){

        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000f);

        sett(view);
    }


    public void restart(View v){

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
