package ca.conestogac.plu.dieball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity
{
    // The number of orbs to be displayed on the screen
    public static final int ORB_COUNT = 9;

    // The number of starting lives for the user
    private static final int LIVES = 3;

    // The layout that contains the nets
    private LinearLayout netLayout;

    private LinearLayout orbLayout;

    private TableRow orbRow1;
    private TableRow orbRow2;
    private TableRow orbRow3;

    private View gameArea;
    private Canvas canvas;

    // The ImageViews that represent the orbs
    private ImageView[] orbs;

    // The current color of the first orb
    private int currentOrbColor;

    // The current number of lives for the user
    private int lives;

    // The colors of the nets
    public static final int[] NET_COLORS = {
            R.mipmap.blue_net,
            R.mipmap.green_net,
            R.mipmap.red_net,
            R.mipmap.yellow_net
    };

    // The colors of the orbs
    public static final int[] ORB_COLORS = {
            R.drawable.blue_orb,
            R.drawable.green_orb,
            R.drawable.red_orb,
            R.drawable.yellow_orb
    };

    // draws all orbs to screen
    private void DrawOrbsToScreen()
    {
        //Toast.makeText(this, "InsideDrawOrbsToScreen", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < ORB_COUNT; i++)
        {
            int currentOrbColor = ORB_COLORS[i % ORB_COLORS.length];

            ImageView orbImage = new ImageView(this);
            orbImage.setBackgroundColor(Color.WHITE);
            //Orb newOrb = new Orb(currentOrbColor, orbImage);
            orbLayout.addView(orbImage);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        netLayout = findViewById(R.id.netsLayout);
        orbLayout = findViewById(R.id.orbLayout);

        // Create the orbs and add them to the layout
        orbs = new ImageView[ORB_COUNT];

        // Create the nets and add them to the layout
        for (int color : NET_COLORS)
        {
            ImageView net = new ImageView(this);
            net.setImageResource(color);
            netLayout.addView(net);
        }

        DrawOrbsToScreen();

        /*
        for (int i = 0; i < ORB_COUNT; i++)
        {
            orbs[i] = new ImageView(this);
            orbs[i].setImageResource(ORB_COLORS[i % ORB_COLORS.length]);

            if(i < 3)
            {
                orbRow1.addView(orbs[i]);
            }
            else if (i > 2 && i < 6)
            {
                orbRow2.addView(orbs[i]);
            }
            else if (i > 5 && i < 9)
            {
                orbRow3.addView(orbs[i]);
            }
        }*/

        // Set the initial number of lives for the user
        lives = LIVES;

        /*
        orbs[0].setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // Handle touch down
                        break;
                    case MotionEvent.ACTION_MOVE:

                        // Handle touch move
                        float newX = event.getX();
                        float newY = event.getY();
                        orbs[0].setX(newX);
                        orbs[0].setY(newY);
                        break;
                    case MotionEvent.ACTION_UP:
                        orbRow1.removeView(orbs[0]);
                        // Handle touch up
                        break;
                    default:
                        break;
                }
                return true;
            }
        });*/
    }
}