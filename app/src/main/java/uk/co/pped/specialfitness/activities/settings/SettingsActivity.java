package uk.co.pped.specialfitness.activities.settings;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.fragments.settings.AbstractBaseSettingsFragment;
import uk.co.pped.specialfitness.activities.settings.support.AppCompatPreferenceActivity;
import uk.co.pped.specialfitness.fragments.settings.SettingsUnitsFragment;

/**
 * Created by matthewi on 08/09/2017.
 */
public class SettingsActivity extends AppCompatPreferenceActivity implements AbstractBaseSettingsFragment.OnFragmentInteractionListener {


    public SettingsActivity() {}

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        setContentView(R.layout.settings_activity);
        setSupportedActionBar();
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    @Override
    public void onHeaderClick(Header header, int position) {
        super.onHeaderClick(header, position);
        long headerId = header.id;
        Intent intent = null;
        if (headerId == R.id.units) {
            intent = new Intent(this, SettingsUnitActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean isValidFragment(String fragmentName) {

        if (StringUtils.equals(SettingsUnitsFragment.class.getName(), fragmentName)) {
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void setSupportedActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setFocusableInTouchMode(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
