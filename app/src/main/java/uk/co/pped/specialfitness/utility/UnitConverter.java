package uk.co.pped.specialfitness.utility;

import android.content.Context;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 26/10/2017.
 */

public class UnitConverter {

    private static final Context context = ApplicationHelper.getContext();

    private static double POUNDS_PER_KILOGRAM = 2.20462;
    private static double POUNDS_PER_STONE = 14;
    private static double CENTIMETRES_PER_FOOT = 30.48;

    private static final String KG_STR = context.getResources().getString(R.string.weight_kg);
    private static final String LBS_STR = context.getResources().getString(R.string.weight_lbs);
    private static final String STONE_STR = context.getResources().getString(R.string.weight_stone);

    private static final String CM_STR = context.getResources().getString(R.string.height_cm);
    private static final String FOOT_STR = context.getResources().getString(R.string.height_ft);



    public static double convertWeight(Double valueToConvert, String fromType, String tooType) {
        double newValue = valueToConvert;

        if (StringUtils.equals(fromType, KG_STR)
                && StringUtils.equals(tooType, LBS_STR)) {
            newValue = convertWeightKGToPounds(newValue);
        } else if (StringUtils.equals(fromType, LBS_STR)
                && StringUtils.equals(tooType, KG_STR)) {
            newValue = convertWeightPoundsToKG(newValue);
        } else if (StringUtils.equals(fromType, LBS_STR)
                && StringUtils.equals(tooType, STONE_STR)) {
            newValue = convertWeightPoundsToStone(newValue);
        } else if (StringUtils.equals(fromType, STONE_STR)
                && StringUtils.equals(tooType, LBS_STR)) {
            convertWeightStoneToPounds(newValue);
        } else if (StringUtils.equals(fromType, KG_STR)
                && StringUtils.equals(tooType, STONE_STR)) {
            double pounds = convertWeightKGToPounds(newValue);
            newValue = convertWeightPoundsToStone(pounds);
        } else if (StringUtils.equals(fromType, STONE_STR)
                && StringUtils.equals(tooType, KG_STR)) {
            double pounds = convertWeightStoneToPounds(newValue);
            newValue = convertWeightPoundsToKG(pounds);
        }

        return round(newValue, 1);
    }

    public static double convertHeight(Double valueToConvert, String fromType, String tooType) {
        double newValue = valueToConvert;

        if (StringUtils.equals(fromType, CM_STR)
                && StringUtils.equals(tooType, FOOT_STR)) {
            newValue = convertHeightCMToFoot(newValue);
        } else if (StringUtils.equals(fromType, FOOT_STR)
                && StringUtils.equals(tooType, CM_STR)) {
            newValue = convertHeightFootToCM(newValue);
        }

        return round(newValue, 1);
    }

    private static double convertHeightCMToFoot(double valueToConvert) {
        return (valueToConvert / CENTIMETRES_PER_FOOT);
    }

    private static double convertHeightFootToCM(double valueToConvert) {
        return (valueToConvert * CENTIMETRES_PER_FOOT);
    }

    private static double convertWeightKGToPounds(double valueToConvert) {
        return (valueToConvert * POUNDS_PER_KILOGRAM);
    }

    private static double convertWeightPoundsToKG(double valueToConvert) {
        return (valueToConvert / POUNDS_PER_KILOGRAM);
    }

    private static double convertWeightPoundsToStone(double valueToConvert) {
        return (valueToConvert / POUNDS_PER_STONE);
    }

    private static double convertWeightStoneToPounds(double valueToConvert) {
        return (valueToConvert * POUNDS_PER_STONE);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}

