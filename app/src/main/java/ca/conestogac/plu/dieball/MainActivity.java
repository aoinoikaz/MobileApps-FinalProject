package ca.conestogac.plu.dieball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button startGameBtn;
    private Button leaderboardBtn;
    private Button settingsBtn;
    private Button exitGameBtn;

    private SharedPreferences sharedPref;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(getApplicationContext(), BatteryService.class));

        drawerLayout = findViewById(R.id.main_activity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        startGameBtn = findViewById(R.id.startGameBtn);
        leaderboardBtn = findViewById(R.id.leaderboardBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        exitGameBtn = findViewById(R.id.exitGameBtn);

        View.OnClickListener buttonListeners = view ->
        {
            switch (view.getId())
            {
                case R.id.startGameBtn:
                    startActivity(new Intent(getApplicationContext(), GameActivity.class));
                    break;
                case R.id.leaderboardBtn:
                    startActivity(new Intent(getApplicationContext(), LeaderboardActivity.class));
                    break;
                case R.id.settingsBtn:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    break;
                case R.id.exitGameBtn:
                    System.exit(0);
                    break;
            }
        };

        startGameBtn.setOnClickListener(buttonListeners);
        leaderboardBtn.setOnClickListener(buttonListeners);
        settingsBtn.setOnClickListener(buttonListeners);
        exitGameBtn.setOnClickListener(buttonListeners);


    }

    @Override
    protected void onStop() {
        startService(new Intent(getApplicationContext(), BatteryService.class));
        super.onStop();
    }

    @Override
    protected void onResume() {
        Boolean darkMode = sharedPref.getBoolean("darkMode", false);
        if(darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onResume();
    }
}