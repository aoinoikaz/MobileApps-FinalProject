package ca.conestogac.plu.dieball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Orb
{
    private int orbColor;
    private ImageView orbImageView;

    public Orb(int orb, ImageView orbImage)
    {
        this.orbColor = orbColor;
        this.orbImageView = orbImage;
    }

}
