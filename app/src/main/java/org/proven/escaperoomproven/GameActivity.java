package org.proven.escaperoomproven;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.proven.escaperoomproven.model.Bin;
import org.proven.escaperoomproven.model.Diamond;
import org.proven.escaperoomproven.model.Game;

public class GameActivity extends Activity {

    private static int _SIZE = 3;
    ImageButton[] ibDiamonds = new ImageButton[_SIZE];
    TextView[] tvDiamonds = new TextView[_SIZE];


    String tag = "";
    ImageView calaix1, calaix2, calaix3, calaix4;
    View.OnClickListener listener;
    Game game;
    Button button;
    String apDifficult;
    TextView nameUserSt;
    TextView timeUserSt;

    LinearLayout linearLayout;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);



        init();

        instantiateElements();

        prepareListener();

        addElementsToListener();



    }

    private void init() {
        game = new Game();
        game.initGame();
        Chrono.setUpdateListener(this);

        inicializarChronometro();

    }

    private void drawTextDiamonds() {

        for(int i = 0; i < tvDiamonds.length; i++){
            Diamond d = game.getDiamond(i);
            tvDiamonds[i].setText(new Integer(d.getValue()).toString());
        }

    }

    private void instantiateElements(){

        ibDiamonds[0] = findViewById(R.id.reddiamond);
        ibDiamonds[1] = findViewById(R.id.greendiamond);
        ibDiamonds[2] = findViewById(R.id.bluediamond);
        tvDiamonds[0] = findViewById(R.id.textRD);
        tvDiamonds[1] = findViewById(R.id.textGD);
        tvDiamonds[2] = findViewById(R.id.textBD);
        calaix1 = findViewById(R.id.calaix1);
        calaix2 = findViewById(R.id.calaix2);
        calaix3 = findViewById(R.id.calaix3);
        calaix4 = findViewById(R.id.calaix4);
        linearLayout = findViewById(R.id.idlayout);
        button = findViewById(R.id.button);
        timeUserSt = findViewById(R.id.timeTextView);
        nameUserSt = findViewById(R.id.nameTextView);

        checkSMSStatePermission();
        leerArchivo();

    }

    private void addElementsToListener(){
        calaix1.setOnClickListener(listener);
        calaix2.setOnClickListener(listener);
        calaix3.setOnClickListener(listener);
        calaix4.setOnClickListener(listener);
        button.setOnClickListener(listener);
        for(int i = 0; i < _SIZE; i++){
            tvDiamonds[i].setOnClickListener(listener);
        }

        for(int i= 0; i < _SIZE; i++){
            ibDiamonds[i].setOnClickListener(listener);
        }



    }


    private void prepareListener() {

        drawTextDiamonds();

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bin b = null;
                Diamond d;
                if(view.getId() == R.id.calaix1){
                    b = game.getBin(0);
                    if(game.checkOpen()==false){
                       b.setOpen(true);
                       calaix1.setImageResource(R.drawable.calaix4red);
                    }else{
                        if(b.isOpen()==true){
                            b.setOpen(false);
                            calaix1.setImageResource(R.drawable.calaixtancat);
                        }
                    }
                } else if(view.getId()== R.id.calaix2){
                    b = game.getBin(1);
                    if(game.checkOpen()==false) {
                        b.setOpen(true);
                        calaix2.setImageResource(R.drawable.calaix4blau);
                    }else{
                        if(b.isOpen()==true) {
                            b.setOpen(false);
                            calaix2.setImageResource(R.drawable.calaixtancat);
                        }
                    }

                }else if(view.getId()==R.id.calaix3){
                    b = game.getBin(2);
                    if(game.checkOpen()==false) {
                        b.setOpen(true);
                        calaix3.setImageResource(R.drawable.calaix4varis);
                    }else{
                        if(b.isOpen()==true) {
                            b.setOpen(false);
                            calaix3.setImageResource(R.drawable.calaixtancat);
                        }
                    }
                }else if(view.getId()==R.id.calaix4){
                    b = game.getBin(3);
                    if(game.checkOpen()==false) {
                        b.setOpen(true);
                        calaix4.setImageResource(R.drawable.calaix4verd);
                    }else{
                        if(b.isOpen()==true) {
                            b.setOpen(false);
                            calaix4.setImageResource(R.drawable.calaixtancat);
                        }
                    }
                }




                for(int i = 0; i < _SIZE;i++) {
                    if (ibDiamonds[i].getId() == view.getId()) {

                        d = game.getDiamond(i);
                        d.incValue();
                        drawTextDiamonds();
                    }
                }

                if(button.getId()==view.getId()){

                    if(game.codeSuccesful()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!",Toast.LENGTH_LONG);

                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrecto!",Toast.LENGTH_LONG);

                        toast.show();
                    }

                }
                


            }
        };

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menugame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.opcion1:
                openMainActivity();
                finish();
                break;
            case R.id.opcion2:
                openConfigActivity();
                finish();
                break;
            case R.id.opcion3:
                finishAffinity();
                break;
            default:
                return false;
        }
        return true;
    }

    private void stopCronometro() {
        Intent service = new Intent(this, Chrono.class);
        stopService(service);
        Toast.makeText(getApplicationContext(),"Final countdown: "+ timeUserSt.getText(),Toast.LENGTH_SHORT).show();
    }


    private void inicializarChronometro() {
        Intent service = new Intent(this, Chrono.class);
        startService(service);
    }
    public void actualizarCronometro(double cronometro) {

        timeUserSt.setText(String.format("%.2f",cronometro)+"s");

    }






    private void checkSMSStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECEIVE_SMS);
        //
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "Permiso denegado:  SMS.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 225);
        } else {
            Log.i("Mensaje", "Permiso aceptado: SMS");
        }
    }
    private void escribirConfiguracion(String userName, int time, String difficulty, boolean background) {

        nameUserSt.setText(userName.toString());
        timeUserSt.setText("Minutes" +new Integer(time).toString());

        apDifficult = difficulty;

        if(background){
            linearLayout.setBackgroundColor(Color.CYAN);
        }
    }


    private void leerArchivo(){

        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String userName = preferences.getString("Name", "User1");
        int tiempo = preferences.getInt("Time",15);
        String dificultad = preferences.getString("Difficulty", "Easy");
        boolean background = preferences.getBoolean("Background",false);

        escribirConfiguracion(userName, tiempo, dificultad, background);


    }




    private void openConfigActivity(){
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }
    private void openMainActivity() {
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }





    @Override
    protected void onDestroy() {
       
        stopCronometro();
        super.onDestroy();
    }
}
