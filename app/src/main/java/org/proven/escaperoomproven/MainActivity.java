package org.proven.escaperoomproven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String tag = "";
    Button forward;
    View.OnClickListener listener;
    Button settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        //Instance element
        instantiateElements();
        // Create listener
        prepareListener();
        //Add elements to listener
        addElementsToListener();
        //Call Airplane Mode receiver:
        MainActivity.this.registerReceiver(mMessageReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));


    }


    private void openSecondActivity(){
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
    }

    private void addElementsToListener(){
        forward.setOnClickListener(listener);
        settings.setOnClickListener(listener);

    }


    //Instance element
    private void instantiateElements(){

        forward = (Button) findViewById(R.id.forward);
        settings = (Button) findViewById(R.id.settings);

    }



    private void prepareListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(view.getId()){
                    case R.id.forward:
                        openSecondActivity();
                        break;
                    case R.id.settings:
                        openConfigActivity();
                        break;

                }




            }

        };

    }

    private void openConfigActivity(){
        Intent intent = new Intent(MainActivity.this,ConfigActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.opcion1:
                openSecondActivity();

                break;
            case R.id.opcion2:
                openConfigActivity();

                break;
            case R.id.opcion3:
                finishAffinity();
                break;
            default:
                return false;
        }
        return true;
    }

    /*******************AIRPLANE MODE****************/

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

        IntentFilter intentFilter = new
                IntentFilter("android.intent.action.AIRPLANE_MODE_CHANGED");


        @Override
        public void onReceive(Context context, Intent intent) {


                boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                if(isAirplaneModeOn){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Device airplane mode is on", Toast.LENGTH_SHORT);

                    toast1.show();


                } else {

                    Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Device airplane mode is off", Toast.LENGTH_SHORT);

                    toast2.show();

                }

        }





    };

}

