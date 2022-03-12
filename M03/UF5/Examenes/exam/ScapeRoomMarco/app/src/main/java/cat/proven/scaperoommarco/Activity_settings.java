package cat.proven.scaperoommarco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Activity_settings extends AppCompatActivity {

    //elements to use
    private RadioGroup rgLevel;
    private TextView seek_time;
    private EditText etPlayer;
    private SeekBar seekBar_time;
    private CheckBox red,purple,green;
    private String chBox_selected="image",rdButton="Easy",gameTime="30",playerName;
    int valueGameTime=0;
    private Button send;

    //Listeners
    RadioGroup.OnCheckedChangeListener radioListener;
    View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // these action is necesary to use toolbar in menu
        Toolbar toolbar= findViewById(R.id.toolb_settings);
        setSupportActionBar(toolbar);
        //initialize the elemnets
        initElements();
        // Seekbar functions
        seekBarFunction();
        // checkbox unique
        startListener();
        startRadioListener();
        addListenerElements();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    /**
     * read item selected from menu
     * @param item of menu
     * @return the corresponding action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_game:
                openGame();
                break;
            case R.id.item_game2:
                openGame();
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

    /**
     * initialize all elements for this activity
     */
    private void initElements() {
        rgLevel= (RadioGroup) findViewById(R.id.rg_level);
        seek_time = (TextView) findViewById(R.id.tv_seekBar_time);
        seekBar_time = (SeekBar) findViewById(R.id.seekBar_time);
        red = (CheckBox) findViewById(R.id.chB_colour_red);
        purple = (CheckBox) findViewById(R.id.chB_colour_purple);
        green = (CheckBox) findViewById(R.id.chB_colour_image);
        etPlayer=(EditText) findViewById(R.id.editText_name);
        send=(Button)findViewById(R.id.btn_sendSettings);
    }

    /**
     * Start listener of views
     */
    private void startListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btn_sendSettings:
                        initConfig();
                        break;
                    case R.id.chB_colour_purple:
                        checkOption(purple);
                        chBox_selected="purple";
                        break;
                    case R.id.chB_colour_red:
                        checkOption(red);
                        chBox_selected="red";
                        break;
                    case R.id.chB_colour_image:
                        checkOption(green);
                        chBox_selected="image";
                        break;
                    default:
                        chBox_selected="image";
                        break;
                }
            }
        };
    }

    /**
     * prepare setting to the game activity
     */
    private void getPlayerName() {
        if(etPlayer.getText().toString().equals("")){
            playerName="Unknow Player";
        }else{
            playerName=etPlayer.getText().toString();
        }
    }

    /**
     * start group radio buttons listenner
     */
    private void startRadioListener() {
        radioListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_level_easy:
                        rdButton="Easy";
                        break;
                    case R.id.rb_level_medium:
                        rdButton="Medium";
                        break;
                    case R.id.rb_level_hard:
                        rdButton="Hard";
                        break;
                    default:
                        rdButton="Easy";
                        break;
                }
            }
        };
    }

    /**
     * this metod is to avoid multichoice checkbox
     * @param checkbox checked
     */
    private void checkOption(CheckBox checkbox) {
        if(!checkbox.isChecked()){
            checkbox.setChecked(true);
        } else if (red.equals(checkbox)){
            purple.setChecked(false);
            green.setChecked(false);
            checkbox.setChecked(true);
        } else if(purple.equals(checkbox)){
            red.setChecked(false);
            green.setChecked(false);
            checkbox.setChecked(true);
        } else if(green.equals(checkbox)){
            purple.setChecked(false);
            red.setChecked(false);
            checkbox.setChecked(true);
        }
    }

    /**
     * add elements to listener
     */
    private void addListenerElements() {
        rgLevel.setOnCheckedChangeListener(radioListener);
        etPlayer.setOnClickListener(listener);
        red.setOnClickListener(listener);
        purple.setOnClickListener(listener);
        green.setOnClickListener(listener);
        send.setOnClickListener(listener);
    }

    /**
     * control the seekbar actions
     */
    private void seekBarFunction() {
        seekBar_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String seekTimeText = String.valueOf(progress);
                seek_time.setText(""+ seekTimeText +" s");
                if(progress==0){
                    valueGameTime=1;
                }else{
                    valueGameTime=progress;
                }
                gameTime=Integer.toString(valueGameTime);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * initialize Game_activity
     */
    private void openGame() {
        Intent intentGame= new Intent(this,Game_activity.class);
        startActivity(intentGame);
    }

    private void initConfig() {

        getPlayerName();
        //obtenim el fitxer de preferències
        SharedPreferences prefs = getSharedPreferences("configData", MODE_PRIVATE);
        //obtenim l'editor
        SharedPreferences.Editor gameEditor = prefs.edit();
        //Escrivim les dades
        gameEditor.putString("playerName", playerName);
        gameEditor.putString("level", rdButton);
        gameEditor.putString("background", chBox_selected);
        gameEditor.putString("gametime", gameTime);
        //fem un commit
        gameEditor.commit();
        Intent i = new Intent(this, Game_activity.class);
        startActivity(i);
    }

    /**
     * Close the application
     */
    private void exitApp(){
        finishAffinity();
    }
}