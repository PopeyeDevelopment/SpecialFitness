package uk.co.pped.specialfitness.utility;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;

import uk.co.pped.specialfitness.BuildConfig;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class ApplicationHelper extends Application {

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

    /**
     * Helper method for gettting a valid AdRequest.
     *
     * If running in DEBUG then will add AdRequest.DEVICE_ID_EMULATOR to request
     *
     * @return a valid AdRequest
     *
     */
    public static AdRequest getAdRequest() {
        // If in DEBUG  mode than include device_id_emulator for testing ads.
        if (BuildConfig.DEBUG) {
            return new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
        }

        // Otherwise just return AdRequest with no testing devices
        return new AdRequest.Builder().build();
    }
}
