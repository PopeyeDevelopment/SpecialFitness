package uk.co.pped.specialfitness.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.utility.GradientProviderHelper;
import uk.co.pped.specialfitness.utility.PathProviderHelper;

/**
 * Created by matthewi on 13/09/2017.
 */

public class CurvedImageView extends AppCompatImageView {

    static public class Gravity {
        public static final int TOP = 0;
        public static final int BOTTOM = 1;
    }

    static public class TintMode {
        public static final int AUTOMATIC = 0;
        public static final int MANUAL = 1;
    }

    static public class Gradient {
        public static final int TOP_TO_BOTTOM = 0;
        public static final int BOTTOM_TO_TOP = 1;
        public static final int LEFT_TO_RIGHT = 2;
        public static final int RIGHT_TO_LEFT = 4;
    }

    static public class CurvatureDirection {
        public static final int OUTWARD = 0;
        public static final int INWARD = 1;
    }

    static public class Styleable {
        static final int CURVED_IMAGE_VIEW[] = R.styleable.CurvedImageView;
        static final int CURVATURE = R.styleable.CurvedImageView_curvature;
        static final int CURVATURE_DIRECTION = R.styleable.CurvedImageView_curvatureDirection;
        static final int TINT_ALPHA = R.styleable.CurvedImageView_curvedTintAlpha;
        static final int TINT_MODE = R.styleable.CurvedImageView_curvedTintMode;
        static final int TINT_COLOR = R.styleable.CurvedImageView_curvedTintColor;
        static final int GRADIENT_DIRECTION = R.styleable.CurvedImageView_gradientDirection;
        static final int GRADIENT_COLOR_START = R.styleable.CurvedImageView_gradientStartColor;
        static final int GRADIENT_COLOR_END = R.styleable.CurvedImageView_gradientEndColor;
        static final int GRAVITY = R.styleable.CurvedImageView_gravity;

    }

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TYPE_IMAGE = "image/*";

    Path clipPath;

    int curvatureDirection = CurvatureDirection.OUTWARD;

    int width = 0;
    int height = 0;

    Paint tintPaint;

    /** @param tintPaint color of the tint to be applied */
    int tintColor;

    /** @param gravity whether TOP or BOTTOM */
    int gravity = Gravity.TOP;

    /** @param tintAlpha varies from 0-255 */
    int tintAlpha = 0;

    /** @param tintMode whether manual or automatic. Default is TintMode.AUTOMATIC*/
    int tintMode = TintMode.AUTOMATIC;

    int gradientDirection = 0;
    int gradientStartColor = Color.TRANSPARENT;
    int gradientEndColor = Color.TRANSPARENT;

    Paint shadeColor;
    Paint paint;

    int curvatureHeight = 50;

    private final Context context;

    private PorterDuffXfermode porterDuffXfermode;
    private String TAG = "CURVED_IMAGE_VIEW";

    CurvedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        porterDuffXfermode = new PorterDuffXfermode((PorterDuff.Mode.CLEAR));

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        shadeColor = new Paint(Paint.ANTI_ALIAS_FLAG);

        clipPath = new Path();

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, Styleable.CURVED_IMAGE_VIEW, 0, 0);
        if (styledAttrs.hasValue(Styleable.CURVATURE)) {
            curvatureHeight = (int) styledAttrs.getDimension(Styleable.CURVATURE, getDpForPixel(context.getResources().getDisplayMetrics(), curvatureHeight));
        }

        if (styledAttrs.hasValue(Styleable.CURVATURE_DIRECTION)
                && styledAttrs.getInt(Styleable.CURVATURE_DIRECTION, 0) == CurvatureDirection.INWARD) {
            curvatureDirection = CurvatureDirection.INWARD;
        }

        if (styledAttrs.hasValue(Styleable.TINT_ALPHA)) {
            if (styledAttrs.getInt(Styleable.TINT_ALPHA, 0) <= 255
                && styledAttrs.getInt(Styleable.TINT_ALPHA, 0) >= 0) {
                tintAlpha = styledAttrs.getInt(Styleable.TINT_ALPHA, 0);
            }
        }

        if (styledAttrs.hasValue(Styleable.TINT_MODE)
                && styledAttrs.getInt(Styleable.TINT_MODE, 0) ==  TintMode.MANUAL) {
            tintMode = TintMode.MANUAL;
        }

        if (styledAttrs.hasValue(Styleable.TINT_COLOR)) {
            tintColor = styledAttrs.getColor(Styleable.TINT_COLOR, 0);
        }

        if (styledAttrs.hasValue(Styleable.GRAVITY)
                && styledAttrs.getInt(Styleable.GRAVITY, 0) == Gravity.BOTTOM) {
            gravity = Gravity.BOTTOM;
        }

        if (styledAttrs.hasValue(Styleable.GRADIENT_DIRECTION)) {
            gradientDirection = styledAttrs.getInt(Styleable.GRADIENT_DIRECTION, 0);
        }

//                /* Default start color is transparent*/
//        gradientStartColor = styledAttrs.getColor(Styleable.GRADIENT_COLOR_START, Color.TRANSPARENT);
//
//        /* Default end color is transparent*/
//        gradientEndColor = styledAttrs.getColor(Styleable.GRADIENT_COLOR_END, Color.TRANSPARENT);

        styledAttrs.recycle();

        BitmapDrawable bitmapDrawable = null;
        if (getDrawable() != null) {
            bitmapDrawable = (BitmapDrawable) this.getDrawable();
        } else if (getBackground() != null) {
            bitmapDrawable = (BitmapDrawable) getBackground();
        }

        if (bitmapDrawable != null) {
            pickColorFromBitmap(bitmapDrawable.getBitmap());
        }
    }

    private void pickColorFromBitmap(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            private int defaultColor = 0x000000;

            @Override
            public void onGenerated(Palette palette) {
                tintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                if (tintMode == TintMode.AUTOMATIC) {
                    tintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    if (palette.getDarkVibrantColor(defaultColor) != 0) {
                        tintPaint.setColor(Color.parseColor("#" + Math.abs(palette.getDarkVibrantColor(defaultColor))));
                    } else if (palette.getDarkMutedColor(defaultColor) != 0) {
                        tintPaint.setColor(Color.parseColor("#" + Math.abs(palette.getDarkMutedColor(defaultColor))));
                    } else {
                        tintPaint.setColor(Color.WHITE);
                    }
                } else {
                    tintPaint.setColor(tintColor);
                    tintPaint.setAlpha(tintAlpha);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        clipPath = PathProviderHelper.getClipPath(width, height, curvatureHeight, curvatureDirection, gravity);

        ViewCompat.setElevation(this, ViewCompat.getElevation(this));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                setOutlineProvider(getOutlineProvider());
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                try {
                    outline.setConvexPath(PathProviderHelper.getOutlinePath(width, height, curvatureHeight, curvatureDirection, gravity));
                } catch (Exception e) {
                    Log.d("Outline Path", e.getMessage());
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        paint.setXfermode(porterDuffXfermode);
        if (tintPaint != null) {
            canvas.drawColor(tintPaint.getColor());
        }

        Shader mShader = GradientProviderHelper.getShader(gradientStartColor, gradientEndColor, gradientDirection, canvas.getWidth(), canvas.getHeight());
        shadeColor.setShader(mShader);
        canvas.drawPaint(shadeColor);

        canvas.drawPath(clipPath, paint);
        canvas.restoreToCount(saveCount);
        paint.setXfermode(null);
    }

    private int getDpForPixel(DisplayMetrics displayMetrics, int pixel) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, displayMetrics);
    }

}
