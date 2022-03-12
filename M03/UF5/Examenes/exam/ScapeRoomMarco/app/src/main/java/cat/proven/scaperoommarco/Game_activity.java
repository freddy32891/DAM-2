package cat.proven.scaperoommarco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cat.proven.scaperoommarco.model.Diamond;
import cat.proven.scaperoommarco.model.Drawer;
import cat.proven.scaperoommarco.model.Game;
import cat.proven.scaperoommarco.utilities.Chronometer;
import cat.proven.scaperoommarco.utilities.NotificationReceiver;

public class Game_activity extends AppCompatActivity {

    private static int SIZE =4;
    ImageView cajon1, cajon2, cajon3, cajon4;
    View.OnClickListener listener;
    Game game;
    ImageView drawer1, drawer2, drawer3, drawer4;
    private View screenView;
    private TextView playerName,level,gameTime;
    private String name,background,levelGame;
    boolean win = false,  lose= true, verify = true;
    private int maxTime, timeResult;
    ImageView[] ivDiamonds = new ImageButton[SIZE];
    TextView[] tvDiamonds = new TextView[SIZE];


    private void init() {
        game = new Game();
        game.init4Drawers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        // these action is necesary to use toolbar in menu
        Toolbar toolbar= findViewById(R.id.toolb_game);
        setSupportActionBar(toolbar);
        initElements();
        // set values to start the game_activity
        setSettings();
        initChrono();
    }

    private void setSettings() {
        playerName.setText(name);
        level.setText(levelGame);
        gameTime.setText(maxTime);

        //la imagen va pero solo en algunos moviles y no se porque
        if(background.equals("image")) {
            screenView.setBackgroundResource(R.drawable.fondoimage);
        }else if(background.equals("purple")){
            screenView.setBackgroundResource(R.color.purple_bck);
        }else if(background.equals("red")){
            screenView.setBackgroundResource(R.color.red_bck);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.game_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_settings:
                openSettingsActivity();
                break;
            case R.id.item_settings2:
                openSettingsActivity();
                break;
            case R.id.item_exit:
                exitApp();
                break;
            case R.id.item_exit2:
                exitApp();
                break;
        }
        return true;
    }

    private void openSettingsActivity() {
        Intent intentSettings= new Intent(this,Activity_settings.class);
        startActivity(intentSettings);
    }

    private void exitApp(){
        finishAffinity();
    }

    /**
     * Init service to chronometer for countdown
     */
    private void initChrono() {
        //init intent service to chrono
        Chronometer.setUpdateListener(this);
        Intent initChrono = new Intent(Game_activity.this, Chronometer.class);
        startService(initChrono);
    }

    @Override
    protected void onDestroy() {
        //Close the service before close app
        Intent iChronoStop = new Intent(Game_activity.this, Chronometer.class);
        stopService(iChronoStop);
        super.onDestroy();
    }

    public void startChronometer(double valueChrono) {
        // Aquí controleu el temps des de la Activity
        int tempsDefinitConfig = maxTime;  // Això seria el temps que definiu vosaltres
        if (!lose && verify){
            Intent iStart = new Intent(this, MainActivity.class);
            startActivity(iStart);

            verify = false;
            this.finish();
        }
        if (valueChrono >= tempsDefinitConfig){
            gameTime.setText("Game Over");
            Intent iChrono = new Intent(Game_activity.this, Chronometer.class);
            stopService(iChrono);
            if (lose){
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                lose = false;
            }
        }else {
            timeResult = (int) valueChrono;
            gameTime.setText(String.format("%.2f", (maxTime- valueChrono)) + " seconds.");
            if (win){
                gameTime.setText("Has ganado");
                Intent iChrono = new Intent(Game_activity.this, Chronometer.class);
                stopService(iChrono);
            }
        }
    }

    /**
     * Add elements to Listener
     */
    private void addElementsToListener() {
        cajon1.setOnClickListener(listener);
        cajon2.setOnClickListener(listener);
        cajon3.setOnClickListener(listener);
        cajon4.setOnClickListener(listener);
        for (int i = 0; i < SIZE; i++) {
            tvDiamonds[i].setOnClickListener(listener);
        }
        for (int i = 0; i < SIZE; i++) {
            ivDiamonds[i].setOnClickListener(listener);
        }
    }

    private void initElements() {

        //batteryLowReciver = new BatteryLowReciver(this);

        ivDiamonds[0] = findViewById(R.id.iv_blueDiamond);
        ivDiamonds[1] = findViewById(R.id.iv_greenDiamond);
        ivDiamonds[2] = findViewById(R.id.iv_redDiamond);
        ivDiamonds[3] = findViewById(R.id.iv_greyDiamond);
        tvDiamonds[0] = findViewById(R.id.tv_blueDiamond);
        tvDiamonds[1] = findViewById(R.id.tv_greenDiamond);
        tvDiamonds[2] = findViewById(R.id.tv_redDiamond);
        tvDiamonds[3] = findViewById(R.id.tv_greyDiamond);

        playerName = findViewById(R.id.tv_playerName);
        level=findViewById(R.id.tv_levelGame);
        gameTime=findViewById(R.id.tv_crono);
        screenView=findViewById(R.id.backgorund_layout);

        drawer1 = findViewById(R.id.iv_drawer1);
        drawer2 = findViewById(R.id.iv_drawer2);
        drawer3 = findViewById(R.id.iv_drawer3);
        drawer4 = findViewById(R.id.iv_drawer4);

        //Get data with config
        gameConfig();
        setSettings();
    }

    // make alarm to do notifications cause i couldnt make with another tool
    private void launchNotification() {
        int i = 5;
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()  + (i * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();

    }

    /**
     * Prepare Listener
     */
    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Onclick to game
                Drawer b = null;
                Diamond d;
                if (view.getId() == R.id.iv_drawer1) {
                    b = game.getDraw(0);
                    if (!game.checkOpen()) {
                        b.setOpen(true);
                        cajon1.setImageResource(R.drawable.calaix4blau);
                    } else {
                        if (b.isOpen()) {
                            b.setOpen(false);
                            cajon1.setImageResource(R.drawable.calaixtancat);
                        }
                    }
                } else if (view.getId() == R.id.iv_drawer2) {
                    b = game.getDraw(1);
                    if (!game.checkOpen()) {
                        b.setOpen(true);
                        cajon2.setImageResource(R.drawable.calaix4red);
                    } else {
                        if (b.isOpen()) {
                            b.setOpen(false);
                            cajon2.setImageResource(R.drawable.calaixtancat);
                        }
                    }

                } else if (view.getId() == R.id.iv_drawer3) {
                    b = game.getDraw(2);
                    if (!game.checkOpen()) {
                        b.setOpen(true);
                        cajon3.setImageResource(R.drawable.calaix4varis);
                    } else {
                        if (b.isOpen()) {
                            b.setOpen(false);
                            cajon3.setImageResource(R.drawable.calaixtancat);
                        }
                    }
                } else if (view.getId() == R.id.iv_drawer4) {
                    b = game.getDraw(3);
                    if (!game.checkOpen()) {
                        b.setOpen(true);
                        cajon4.setImageResource(R.drawable.calaix4verd);
                    } else {
                        if (b.isOpen()) {
                            b.setOpen(false);
                            cajon4.setImageResource(R.drawable.calaixtancat);
                        }
                    }
                }
                for (int i = 0; i < SIZE; i++) {
                    if (ivDiamonds[i].getId() == view.getId()) {

                        d = game.getDiamond(i);
                        d.incrementCiclic();
                        textDiamonds();
                    }
                }
                win = game.codeSuccessful();
                if (win) {
                    Toast.makeText(Game_activity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    launchNotification();
                }
            }
        };
    }

    /**
     * Method draw text diamonds
     */
    private void textDiamonds() {
        for (int i = 0; i < tvDiamonds.length; i++) {
            Diamond d = game.getDiamond(i);
            tvDiamonds[i].setText(new Integer(d.getValue()).toString());
        }
    }

    /**
     * Load data config to user
     */
    private void gameConfig() {
        SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);
        name = prefs.getString("playerName", "Player Name");
        background = prefs.getString("background", "Image");
        levelGame = prefs.getString("level", "Easy");
        maxTime = Integer.parseInt(prefs.getString("gametime", "30"));
    }
}