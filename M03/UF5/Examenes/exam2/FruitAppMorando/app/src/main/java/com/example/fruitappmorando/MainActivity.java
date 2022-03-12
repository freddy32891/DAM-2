package com.example.fruitappmorando;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fruitappmorando.model.Fruit;
import com.example.fruitappmorando.model.Game;
public class MainActivity extends AppCompatActivity {

    private static int SIZE=3;
    View background;
    Game game;
    Button enviar;
    View.OnClickListener listener;
    ImageView[] ivFruits = new ImageView[SIZE];
    TextView[] tvFruits = new TextView[SIZE];
    Fruit f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        instanceElements();
        startListener();
        addListenerElements();
    }



    public void startListener() {
        listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enviar.getId()==view.getId()){
                    cambioActivity();
                }else{
                for (int i = 0; i < SIZE; i++) {
                    if (ivFruits[i].getId() == view.getId()) {
                        f = game.getFruit(i);
                        f.incrementQuantity();
                        drawTextFruit();
                    }
                }
                }
            }

        };
    }

    public void drawTextFruit() {
        for (int i = 0; i < tvFruits.length; i++) {
            Fruit fruta = game.getFruit(i);
            tvFruits[i].setText(new Integer(fruta.getCantidad()).toString());
        }
    }

    public void getResult(){

    }

    public void addListenerElements() {
        enviar.setOnClickListener(listener);
        for (int i = 0; i < SIZE; i++) {
            tvFruits[i].setOnClickListener(listener);
        }
        for (int i = 0; i < SIZE; i++) {
            ivFruits[i].setOnClickListener(listener);
        }
    }

    public void instanceElements() {
        ivFruits[0]= findViewById(R.id.iv_apple);
        ivFruits[1]= findViewById(R.id.iv_banana);
        ivFruits[2]= findViewById(R.id.iv_pear);
        tvFruits[0]= findViewById(R.id.tv_appleCant);
        tvFruits[1]= findViewById(R.id.tv_bananaCant);
        tvFruits[2]= findViewById(R.id.tv_pearCant);

        enviar=findViewById(R.id.btn_comprar);
    }

    /**
     * Init activity
     */
    public void init() {
        game = new Game();
        game.initGame();
    }

    //Start menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.it_fondo:

                break;
            case R.id.it_guardarPref:
                break;

            case R.id.it_info:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Informacion");
                builder.setMessage("App realizada por MARCO MORANDO");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Hacer cosas aqui al hacer clic en el boton de aceptar
                    }
                }).setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
            case R.id.it_salir:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cambioActivity(){
        Intent iGame = new Intent(this, Result_Activity.class);
        startActivity(iGame);
    }
}