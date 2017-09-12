package uk.co.pped.specialfitness.utility;

import android.app.Application;
import android.content.Context;

import uk.co.pped.specialfitness.BuildConfig;

/**
 * Created by matthewi on 08/09/2017.
 */

public class ApplicationHelper extends Application {

    public static final String FLAVOR_FREE = "free";

    public static final String FLAVOR_PAID = "paid";

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationHelper.context = getApplicationContext();
    }

    public static Context getContext() {
        return ApplicationHelper.context;
    }
}
