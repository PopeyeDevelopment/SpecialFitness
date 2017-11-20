package uk.co.pped.specialfitness.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import uk.co.pped.specialfitness.utils.KeyUtils;

/**
 * Created by matthewi on 14/11/2017.
 */

public final class PrefManager  {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Context context;

    public PrefManager(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTimeLaunch) {
        editor.putBoolean(KeyUtils.FIRST_TIME_LAUNCH_KEY, isFirstTimeLaunch);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(KeyUtils.FIRST_TIME_LAUNCH_KEY, true);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
