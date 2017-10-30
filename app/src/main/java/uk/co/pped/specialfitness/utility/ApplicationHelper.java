package uk.co.pped.specialfitness.utility;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;

import uk.co.pped.specialfitness.BuildConfig;
import uk.co.pped.specialfitness.utils.KeyboardUtils;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class ApplicationHelper extends Application {

    private static final String TAG = ApplicationHelper.class.getName();

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

    public static String getStringFromResId(int resId) {
        return getContext().getString(resId);
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

    public static String getBuildVersion() {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return Integer.toString(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    public static String getBuildName() {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    public static Object getService(String service) {
        return context.getSystemService(service);
    }

    public static KeyboardUtils getKeyboardUtils() {
        return KeyboardUtils.getInstance();
    }
}
