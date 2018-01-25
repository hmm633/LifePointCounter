package cs371m.lifepointcounter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Monolith on 3/27/2016.
 */
public class GraphicsView extends View {

    private static final String TAG = "Graphic View";

    public GraphicsView(Context context, AttributeSet attrs){
        super(context, attrs);
        Log.d(TAG, "in 2 param constructor");
    }

    public GraphicsView(Context context) {
        super(context);
        Log.d(TAG, "in 1 param constructor");
    }

    @Override
    protected void onDraw(Canvas canvas){
        Log.d(TAG, "in onDraw");
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        // Converting from px to dp
//        DisplayMetrics dm = new DisplayMetrics();
//        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
//        float density = dm.density;

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        Log.d(TAG, "height = " + height);
        Log.d(TAG, "width = " + width);

        LinearGradient rg = new LinearGradient(0, 0, width, height, Color.DKGRAY, Color.BLACK, Shader.TileMode.CLAMP);
        p.setShader(rg);

        // Rectangle dimensions need to scale to resolution
        final RectF rect = new RectF(); // set to "final" for some reason
//        rect.set(0, 0, 223, 223);
            rect.set(0, 0, height, width);

        canvas.drawRoundRect(rect, 30, 30, p);
//                ();Rect(0, 0, 300, 300, p);

    }
}
