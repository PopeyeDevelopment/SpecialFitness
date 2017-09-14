package uk.co.pped.specialfitness.utility;

import android.graphics.LinearGradient;
import android.graphics.Shader;

import uk.co.pped.specialfitness.components.CurvedImageView;

/**
 * Created by matthewi on 14/09/2017.
 */

public class GradientProviderHelper {

    public static Shader getShader(int gradientStartColor, int gradientEndColor, int gradientDirection, int width, int height) {
        switch (gradientDirection) {
            case CurvedImageView.Gradient.TOP_TO_BOTTOM:
                return new LinearGradient(0, 0, 0, height, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            case CurvedImageView.Gradient.BOTTOM_TO_TOP:
                return new LinearGradient(0, height, 0, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            case CurvedImageView.Gradient.LEFT_TO_RIGHT:
                return new LinearGradient(0, 0, width, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            case CurvedImageView.Gradient.RIGHT_TO_LEFT:
                return new LinearGradient(width, 0, 0, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
            default:
                return new LinearGradient(0, 0, height, 0, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
        }
    }
}
