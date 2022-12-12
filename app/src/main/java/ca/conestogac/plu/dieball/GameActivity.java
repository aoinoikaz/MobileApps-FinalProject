package ca.conestogac.plu.dieball;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
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
    private static final int ORB_COUNT = 9;

    // The number of starting lives for the user
    private static final int LIVES = 3;

    // The colors of the nets
    private static final int[] NET_COLORS = {
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.YELLOW
    };

    // The colors of the orbs
    private static final int[] ORB_COLORS = {
            R.mipmap.blue_orb,
            R.mipmap.green_orb,
            R.mipmap.red_orb,
            R.mipmap.yellow_orb
    };

    // The layout that contains the nets
    private LinearLayout netLayout;

    //private TableLayout orbLayout;
    private TableRow orbRow1;
    private TableRow orbRow2;
    private TableRow orbRow3;

    // The ImageViews that represent the orbs
    private ImageView[] orbs;

    // The current color of the first orb
    private int currentOrbColor;

    // The current number of lives for the user
    private int lives;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //orbLayout = findViewById(R.id.orbTableLayout);

        orbRow1 = findViewById(R.id.orbRow1);
        orbRow2 = findViewById(R.id.orbRow2);
        orbRow3 = findViewById(R.id.orbRow3);

        netLayout = findViewById(R.id.netsLayout);

        // Create the orbs and add them to the layout
        orbs = new ImageView[ORB_COUNT];

        ImageView blueNet = new ImageView(this);
        blueNet.setImageResource(R.mipmap.blue_net);
        netLayout.addView(blueNet);

        ImageView greenNet = new ImageView(this);
        greenNet.setImageResource(R.mipmap.green_net);
        netLayout.addView(greenNet);

        ImageView redNet = new ImageView(this);
        redNet.setImageResource(R.mipmap.red_net);
        netLayout.addView(redNet);

        ImageView yellowNet = new ImageView(this);
        yellowNet.setImageResource(R.mipmap.yellow_net);
        netLayout.addView(yellowNet);

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
        }

        /*
        // Create the nets and add them to the layout
        for (int color : NET_COLORS)
        {
            ImageView net = new ImageView(this);
            net.setImageResource(R.mipmap.blue_orb);
            //net.setColorFilter(color);
        }*/

        // Set the initial number of lives for the user
        lives = LIVES;

        /*
        // Set the touch listener for the first orb
        orbs[0].setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    // Get the color of the first orb
                    //currentOrbColor = orbs[0].getColorFilter().getColor();

                    // Shift the orbs up and respawn a new one at the end
                    for (int i = 0; i < ORB_COUNT - 1; i++)
                    {
                        orbs[i] = orbs[i + 1];
                    }
                    orbs[ORB_COUNT - 1] = new ImageView(GameActivity.this);
                    ///orbs[ORB_COUNT - 1].setImageResource(R.drawable.orb);
                    orbs[ORB_COUNT - 1].setColorFilter(ORB_COLORS[(ORB_COUNT - 1) % ORB_COLORS.length]);
                    orbLayout.addView(orbs[ORB_COUNT - 1]);

                    // Set the touch listener for the new first orb
                    orbs[0].setOnTouchListener(this);
                }
                return true;
            }
        });*/
    }
}