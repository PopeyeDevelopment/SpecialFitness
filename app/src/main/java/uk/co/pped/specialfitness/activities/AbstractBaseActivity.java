package uk.co.pped.specialfitness.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import uk.co.pped.specialfitness.Conf;
import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 11/09/2017.
 */

public abstract class AbstractBaseActivity extends AppCompatActivity  {

    protected void setSupportedActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setFocusableInTouchMode(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void removeAdsFromContext() {
        if (Conf.APPLICATION_FLAVOR.equals(ApplicationHelper.FLAVOR_PAID)) {
            View ads = (View) findViewById(R.id.ads_banner);
        }
    }

    protected void showBannerAd(AdView adView) {
        AdRequest request = new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build();
        adView.loadAd(request);
    }

}
