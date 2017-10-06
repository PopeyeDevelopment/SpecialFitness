package uk.co.pped.specialfitness.utils;

import static uk.co.pped.specialfitness.utility.ApplicationHelper.getStringFromResId;


import uk.co.pped.specialfitness.Conf;
import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 08/09/2017.
 */

public interface KeyUtils {

    public static final String DATE_OF_BIRTH = Conf.PACKAGE + ".key.DATE_OF_BIRTH";
    public static final String GENDER = Conf.PACKAGE + ".key.GENDER";
    public static final String NICKNAME = Conf.PACKAGE + ".key.NICKNAME";

    public static final String WEEK_DEFAULTS_KEY = getStringFromResId(R.string.week_defaults);
    public static final String WEEKDAY_STARTS_KEY = getStringFromResId(R.string.week_day_start);
    public static final String AVAILABLE_WORKOUT_DAYS_KEY = getStringFromResId(R.string.available_workout_days);

}
