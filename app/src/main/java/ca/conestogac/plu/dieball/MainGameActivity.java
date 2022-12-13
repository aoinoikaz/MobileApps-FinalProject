package ca.conestogac.plu.dieball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainGameActivity extends AppCompatActivity implements SurfaceHolder.Callback
{
    private final List<SnakePoints> snakePointsList = new ArrayList<SnakePoints>();
    private SurfaceView surfaceView;
    private TextView scoreTV;

    // surface holder to draw the snake on surface's canvas
    private SurfaceHolder surfaceHolder;

    // snake moving position Values must be right, left, top, bottom
    // By default snake move to right
    private String movingPosition = "right";

    private int score = 0;

    // max size, starting size, color of snake and speed
    private static final int maxSizeOfSnake = 8;
    private static final int startingSizeOfSnake = 2;
    private static final int snakeColor = Color.BLUE;
    // speed must be between 1 and 1000
    private static final int snakeMovingSpeed = 800;

    // random point position coordinates on the surface view
    private int positionX, positionY;

    // timer to move snake / change snake position based on the moving speed
    private Timer timer;

    // canvas to draw snake and show on surface view
    private Canvas canvas = null;

    // color of points
    private Paint pointColor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        surfaceView = findViewById(R.id.surfaceView);
        scoreTV = findViewById(R.id.scoreTextView);

        // getting imagebuttons from xml file
        final AppCompatImageButton topBtn = findViewById(R.id.topBtn);
        final AppCompatImageButton leftBtn = findViewById(R.id.leftBtn);
        final AppCompatImageButton rightBtn = findViewById(R.id.rightBtn);
        final AppCompatImageButton bottomBtn = findViewById(R.id.bottomBtn);

        // adding callback to surfaceview
        surfaceView.getHolder().addCallback(this);

        topBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movingPosition.equals("bottom")) {
                    movingPosition = "top";
                }
            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movingPosition.equals("right")) {
                    movingPosition = "left";
                }
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movingPosition.equals("left")) {
                    movingPosition = "right";
                }
            }
        });

        bottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movingPosition.equals("top")) {
                    movingPosition = "bottom ";
                }
            }
        });

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        // when surface is created then get surfaceHolder from it and assign to surfaceHolder
        this.surfaceHolder = surfaceHolder;

        // init data for snake / surfaceView
        init();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    private void init()
    {
        snakePointsList.clear();
        scoreTV.setText("0");
        score = 0;
        movingPosition = "right";

        // default starting position on the screen
        int startPositionX = (maxSizeOfSnake) * startingSizeOfSnake;

        // create snake default length
        for(int i = 0; i < startingSizeOfSnake; i++)
        {
            // adding points to snakes tail
            SnakePoints snakePoints = new SnakePoints(startPositionX, maxSizeOfSnake);
            snakePointsList.add(snakePoints);

            // increase value for next point as snakes tail
            startPositionX = startPositionX - (maxSizeOfSnake * 2);
        }

        addPoint();

        // start moving snake / start game
        moveSnake();

    }

    private void addPoint()
    {
        // getting surface view width and height to add points on the surface which
        // will be eaten by the snake
        int surfaceWidth = surfaceView.getWidth() - (maxSizeOfSnake * 2);
        int surfaceHeight = surfaceView.getHeight() - (maxSizeOfSnake * 2);

        int randomXPosition = new Random().nextInt(surfaceWidth / maxSizeOfSnake);
        int randomYPosition = new Random().nextInt(surfaceHeight / maxSizeOfSnake);

        if(randomXPosition % 2 != 0)
        {
            randomXPosition++;
        }

        if(randomYPosition % 2 != 0)
        {
            randomYPosition++;
        }

        positionX = (maxSizeOfSnake * randomXPosition) + maxSizeOfSnake;
        positionY = (maxSizeOfSnake * randomYPosition) + maxSizeOfSnake;
    }

    private void moveSnake()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                // getting head position
                int headPositionX = snakePointsList.get(0).getPositionX();
                int headPositionY = snakePointsList.get(0).getPositionY();

                // check if snake has eaten a point
                if(headPositionX == positionX && positionY == headPositionY)
                {
                    // increase size of snake
                    increaseSizeOfSnake();

                    // add another random point on the screen
                    addPoint();
                }

                // check which way the snake is facing
                switch(movingPosition)
                {
                    case "right":
                        snakePointsList.get(0).setPositionX(headPositionX + (maxSizeOfSnake * 2));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;
                    case "left":
                        snakePointsList.get(0).setPositionX(headPositionX - (maxSizeOfSnake * 2));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;
                    case "top":
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY - (maxSizeOfSnake * 2));
                        break;
                    case "bottom":
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY + (maxSizeOfSnake * 2));
                        break;
                }

                // check if game over and whether or not snake has collided with itself or outside edges
                if(isGameOver(headPositionX, headPositionY))
                {
                    // stop timer / stop moving snake
                    timer.purge();
                    timer.cancel();

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainGameActivity.this);
                    builder.setMessage("Your score = " + score);
                    builder.setTitle("Game Over");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Start Again", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            // restart game / re initialize
                            init();
                        }
                    });

                    // timer runs in background so we need to show dialog on main thread
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            builder.show();
                        }
                    });
                }
                else
                {

                    // lock canvas on surface holder so we can draw on it
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);

                    // change snake head position - other body parts will follow
                    canvas.drawCircle(snakePointsList.get(0).getPositionX(), snakePointsList.get(0).getPositionY(),
                            maxSizeOfSnake, createPointColor());

                    // draw random point circle on the surface to be eaten by the snake
                    canvas.drawCircle(positionX, positionY, maxSizeOfSnake, createPointColor());

                    // other points following snakes head - snake head is position 0 so
                    // we start at index 1
                    for(int i = 1; i < snakePointsList.size(); i++)
                    {
                        int getTempPosX = snakePointsList.get(i).getPositionX();
                        int getTempPosY = snakePointsList.get(i).getPositionY();

                        snakePointsList.get(i).setPositionX(headPositionX);
                        snakePointsList.get(i).setPositionY(headPositionY);

                        canvas.drawCircle(snakePointsList.get(i).getPositionX(), snakePointsList.get(i).getPositionY(),
                                maxSizeOfSnake, createPointColor());

                        // swap head positions
                        headPositionX = getTempPosX;
                        headPositionY = getTempPosY;
                    }

                    // update surface to apply drawings
                    surfaceHolder.unlockCanvasAndPost(canvas);

                }
            }
        }, 1000 - snakeMovingSpeed, 1000 - snakeMovingSpeed);
    }

    private void increaseSizeOfSnake()
    {
        // create new snake points
        SnakePoints snakePoints = new SnakePoints(0,0);

        // add point to snakes tail
        snakePointsList.add(snakePoints);

        // increase score
        score++;

        // setting score to text view
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTV.setText(String.valueOf(score));
            }
        });
    }

    private boolean isGameOver(int headPositionX, int headPositionY)
    {
        boolean isGameOver = false;

        // check if snakes head touches edges
        if(snakePointsList.get(0).getPositionX() < 0 || snakePointsList.get(0).getPositionY() < 0
        || snakePointsList.get(0).getPositionX() >= surfaceView.getWidth()
        || snakePointsList.get(0).getPositionY() >= surfaceView.getHeight())
        {
            isGameOver = true;
        }
        else
        {
            // check if snakes head touches itself
            for(int i = 0; i < snakePointsList.size(); i++)
            {
                if(headPositionX == snakePointsList.get(i).getPositionX() &&
                        headPositionY == snakePointsList.get(i).getPositionY())
                {
                    isGameOver = true;
                    break;
                }
            }
        }

        return isGameOver;
    }

    private Paint createPointColor()
    {
        // check if the paint exists before creation
        if(pointColor == null)
        {
            pointColor = new Paint();
            pointColor.setColor(snakeColor);
            pointColor.setStyle(Paint.Style.FILL);
            pointColor.setAntiAlias(true);
        }
        return pointColor;
    }

}