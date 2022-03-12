package cat.proven.scaperoommarco;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener listener;
    private ImageView goSettings;
    private ImageView goGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);

        initElements();
        startListener();
        addListenerElements();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_game:
                openGame();
                break;
            case R.id.item_game2:
                openGame();
                break;
            case R.id.item_settings:
                openSettingsActivity();
                break;
            case R.id.item_settings2:
                openSettingsActivity();
                break;
            case R.id.item_exit2:
                finish();
                break;
        }
        return true;
    }

    private void startListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.iv_ajustes:
                        openSettingsActivity();
                        break;
                    case R.id.iv_goGame:
                        openGame();
                        break;
                }
            }
        };
    }

    private void addListenerElements() {
        goSettings.setOnClickListener(listener);
        goGame.setOnClickListener(listener);
    }


    private void initElements() {
        goSettings=(ImageView) findViewById(R.id.iv_ajustes);
        goGame=(ImageView) findViewById(R.id.iv_goGame);
    }

    private void openGame() {
        Intent intentGame= new Intent(this,Game_activity.class);
        startActivity(intentGame);
    }

    private void openSettingsActivity() {
        Intent intentSettings= new Intent(this,Activity_settings.class);
        startActivity(intentSettings);
    }
}