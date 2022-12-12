package ca.conestogac.plu.dieball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    private Button startGameBtn;
    private Button leaderboardBtn;
    private Button settingsBtn;
    private Button exitGameBtn;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.main_activity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AssignListeners();
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void AssignListeners()
    {
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
                    break;
            }
        };

        startGameBtn.setOnClickListener(buttonListeners);
        leaderboardBtn.setOnClickListener(buttonListeners);
        settingsBtn.setOnClickListener(buttonListeners);
        exitGameBtn.setOnClickListener(buttonListeners);

    }
}