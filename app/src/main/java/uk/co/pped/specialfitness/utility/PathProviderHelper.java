package uk.co.pped.specialfitness.utility;

import android.graphics.Path;

import uk.co.pped.specialfitness.components.CurvedImageView;

/**
 * Created by matthewi on 14/09/2017.
 */

public class PathProviderHelper {

    public static Path getOutlinePath(int width, int height, int curvatureHeight, int direction, int gravity) {

        Path path = new Path();

        if (direction == CurvedImageView.CurvatureDirection.OUTWARD) {
            if (gravity == CurvedImageView.Gravity.TOP) {
                path.moveTo(0, 0);
                path.lineTo(0, height - curvatureHeight);
                path.quadTo(width / 2, height + curvatureHeight,
                        width, height - curvatureHeight);
                path.lineTo(width, 0);
                path.lineTo(0, 0);
                path.close();
            } else {
                path.moveTo(0, height);
                path.lineTo(0, curvatureHeight);
                path.quadTo(width / 2, -curvatureHeight,
                        width, curvatureHeight);
                path.lineTo(width, height);
                path.close();
            }
        } else {
            if (gravity == CurvedImageView.Gravity.TOP) {
                path.moveTo(0, 0);
                path.lineTo(0, height);
                path.quadTo(width / 2, height - curvatureHeight,
                        width, height);
                path.lineTo(width, 0);
                path.lineTo(0, 0);
                path.close();
            } else {
                path.moveTo(0, height);
                path.lineTo(0, 0);
                path.cubicTo(0, 0,
                        width / 2, curvatureHeight,
                        width, curvatureHeight);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.close();
            }
        }
        return path;
    }

    public static Path getClipPath(int width, int height, int curvatureHeight, int direction, int gravity) {
        Path path = new Path();

        if (direction == CurvedImageView.CurvatureDirection.OUTWARD) {
            if (gravity == CurvedImageView.Gravity.TOP) {
                path.moveTo(0, height - curvatureHeight);
                path.quadTo(width / 2, height + curvatureHeight,
                        width, height - curvatureHeight);
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.close();
            } else {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, curvatureHeight);
                path.quadTo(width / 2, -curvatureHeight,
                        0, curvatureHeight);
                path.lineTo(0, 0);
                path.close();
            }
        } else {
            if (gravity == CurvedImageView.Gravity.TOP) {
                path.moveTo(0, height);
                path.quadTo(width / 2, height - 2 * curvatureHeight,
                        width, height);
                path.lineTo(width, height);
                path.close();
            } else {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.quadTo(width / 2, 2 * curvatureHeight,
                        0, 0);
                path.lineTo(0, 0);
                path.close();
            }
        }

        return path;
    }
}
