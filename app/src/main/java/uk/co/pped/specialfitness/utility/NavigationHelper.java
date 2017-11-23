package uk.co.pped.specialfitness.utility;

import java.util.ArrayList;
import java.util.List;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 23/11/2017.
 */

public final class NavigationHelper {

    public static final int WORKOUT_ACTION_BTN_START_ID = R.id.workout_start_new;
    public static final int WORKOUT_ACTION_BTN_EDIT_ID = R.id.workout_edit_old;
    private static final List<Integer> VALID_WORKOUT_NAVIGATION_IDS;

    static {
        VALID_WORKOUT_NAVIGATION_IDS = new ArrayList<>();
        VALID_WORKOUT_NAVIGATION_IDS.add(WORKOUT_ACTION_BTN_START_ID);
        VALID_WORKOUT_NAVIGATION_IDS.add(WORKOUT_ACTION_BTN_EDIT_ID);
    }

    public static boolean isValidWorkoutNavigation(int id) {
        return VALID_WORKOUT_NAVIGATION_IDS.contains(id);
    }
}
