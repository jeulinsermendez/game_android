package org.proven.escaperoomproven;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ConfigActivity extends AppCompatActivity {


    View.OnClickListener listener;
    SeekBar sbTime;
    CheckBox cbOnOffBackGround;
    EditText etNameField;
    TextView tvTime;
    int timeValue;
    RadioButton rbEasy;
    RadioButton rdMedium;
    RadioButton rbHard;
    String difficulty;
    LinearLayout configLayout;
    Button btnPreferences;

    SeekBar.OnSeekBarChangeListener seekBarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_activity);


        instantiateElements();
        prepareListener();
        addElementsToListener();


    }







    private void prepareListener(){


        cbOnOffBackGround.setOnClickListener(listener);
        rbEasy.setOnClickListener(listener);
        rdMedium.setOnClickListener(listener);
        rbHard.setOnClickListener(listener);
        btnPreferences.setOnClickListener(listener);
    }


    private void instantiateElements(){


        sbTime = findViewById(R.id.timeSeekBar);
        sbTime.setOnSeekBarChangeListener(seekBarListener);
        cbOnOffBackGround = findViewById(R.id.backgroundCB);
        etNameField = findViewById(R.id.etName);
        tvTime = findViewById(R.id.showTime);
        rbEasy = findViewById(R.id.radioEasy);
        rdMedium = findViewById(R.id.radioMedium);
        rbHard = findViewById(R.id.radioHard);
        configLayout = findViewById(R.id.layoutConfig);
        btnPreferences = (Button) findViewById(R.id.saveButton);

        sbTime.setProgress(1);
        sbTime.setMax(15);



    }

    private void addElementsToListener() {

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(view.getId()){
                    case R.id.saveButton:
                            Toast toast = Toast.makeText(getApplicationContext(), "Hs escogido!",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                            writeFile();
                            break;

                    case R.id.backgroundCB:
                            if(cbOnOffBackGround.isChecked()){
                                configLayout.setBackgroundColor(Color.BLUE);
                            }
                            break;

                    case R.id.radioEasy:
                            difficulty = "Easy";
                            break;

                    case R.id.radioMedium:
                        difficulty = "Medium";
                        break;
                    case R.id.radioHard:
                        difficulty = "Hard";
                        break;


                }




                seekBarListener = new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        switch (seekBar.getId()){

                            case R.id.time:

                                timeValue = progress;
                                tvTime.setText("Time : "+Integer.toString(timeValue));


                                break;
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                };

            }

        };

    }

    /**********************************MENU*********************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuconfig, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.opcion1:
                openMainActivity();
                break;
            case R.id.opcion2:
                openGameActivity();

                break;
            case R.id.opcion3:
                finishAffinity();
                break;
            default:
                return false;
        }
        return true;
    }

    private void openMainActivity() {
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }

    private void openGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


    private void writeFile(){


        String valueString = etNameField.getText().toString();
        boolean isCheckBoxOn = cbOnOffBackGround.isChecked();

        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = preferences.edit();

        if(valueString.isEmpty()){
            valueString = "User1";
        }

        mEditor.putString("Name", valueString);
        mEditor.putBoolean("Background", isCheckBoxOn);
        mEditor.putInt("Time", timeValue);
        mEditor.putString("Difficulty", difficulty);

        mEditor.commit();

        Intent intent = new Intent(ConfigActivity.this,MainActivity.class);
        startActivity(intent);


    }
}
