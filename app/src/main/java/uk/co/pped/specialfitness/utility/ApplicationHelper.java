package uk.co.pped.specialfitness.utility;

import android.app.Application;
import android.content.Context;

/**
 * Created by matthewi on 08/09/2017.
 */

public class ApplicationHelper extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationHelper.context = getApplicationContext();
    }

    public static Context getContext() {
        return ApplicationHelper.context;
    }
}
