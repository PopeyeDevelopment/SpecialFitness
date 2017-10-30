package uk.co.pped.specialfitness.utils;

import android.app.Activity;
import android.app.Application;
import android.inputmethodservice.Keyboard;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 27/10/2017.
 */

public class KeyboardUtils {

    private static KeyboardUtils instance;
    private InputMethodManager inputMethodManager;

    private KeyboardUtils() {}

    public static KeyboardUtils getInstance() {
        if (instance == null) {
            instance = new KeyboardUtils();
        }
        return instance;
    }

    private InputMethodManager getInputMethodManager() {
        if (inputMethodManager == null)
            inputMethodManager = (InputMethodManager) ApplicationHelper.getService(Activity.INPUT_METHOD_SERVICE);
        return inputMethodManager;
    }


    public void hide(final Activity activity) {
        if (activity != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        getInputMethodManager().hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                        activity.getCurrentFocus().clearFocus();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void hide(final View view) {
        if (view != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        getInputMethodManager().hideSoftInputFromWindow(view.getWindowToken(), 0);
                        view.clearFocus();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void runHideKeyboard(final IBinder token) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    getInputMethodManager().hideSoftInputFromWindow(token, 0);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
