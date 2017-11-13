package uk.co.pped.specialfitness.activities.settings;

import uk.co.pped.specialfitness.activities.ProfileActivity;
import uk.co.pped.specialfitness.fragments.settings.SettingsFragment.SettingsFragmentTypes;
import uk.co.pped.specialfitness.components.widgets.HeaderAdapter;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.TextView;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.components.widgets.HeaderAdapter;
import uk.co.pped.specialfitness.fragments.settings.AbstractBaseSettingsFragment;
import uk.co.pped.specialfitness.activities.settings.support.AppCompatPreferenceActivity;
import uk.co.pped.specialfitness.fragments.settings.SettingsFragment;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 08/09/2017.
 */
public class SettingsActivity extends AppCompatPreferenceActivity implements AbstractBaseSettingsFragment.OnFragmentInteractionListener {

    private static final String BUILD_VERSION_PREFIX_STR = "Build Version: ";

    private HeaderAdapter headerAdapter;

    public SettingsActivity() {}

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        setContentView(R.layout.settings_activity);
        setSupportedActionBar();
        loadHeadersFromResource(R.xml.preference_headers, target);

        headerAdapter = new HeaderAdapter(this, target);
        setListAdapter(headerAdapter);
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(headerAdapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        TextView buildVersionTextView = (TextView) findViewById(R.id.buildVersion);
        if (buildVersionTextView != null) {
            String buildVersionStr = BUILD_VERSION_PREFIX_STR + ApplicationHelper.getBuildVersion();
            buildVersionTextView.setText(buildVersionStr);
        }
    }

    @Override
    public void onHeaderClick(Header header, int position) {
        super.onHeaderClick(header, position);
        if (header != null) {
            final Bundle extras = header.fragmentArguments;

            if (extras.containsKey(HeaderAdapter.HEADER_TYPE_KEY)
                    && extras.get(HeaderAdapter.HEADER_TYPE_KEY).equals(HeaderAdapter.HEADER_TYPE_INTENT)) {

                long headerId = header.id;
                Intent intent = new Intent(this, SettingsFragmentHandler.class);


                if (headerId == R.id.units) {
                    intent.putExtra(SettingsFragmentTypes.FRAGMENT_TYPE_KEY, SettingsFragmentTypes.FRAGMENT_TYPE_UNITS_PREFERENCES);
                } else if (headerId == R.id.your_week_preferences) {
                    intent.putExtra(SettingsFragmentTypes.FRAGMENT_TYPE_KEY, SettingsFragmentTypes.FRAGMENT_TYPE_WEEK_PREFERENCES);
                } else if (headerId == R.id.your_profile) {
                    // For "Your Profile" we'll override the intent and send the user to their profile.
                    intent = new Intent(this, ProfileActivity.class);
                } else if (headerId == R.id.your_workout_preferences) {
                    intent.putExtra(SettingsFragmentTypes.FRAGMENT_TYPE_KEY, SettingsFragmentTypes.FRAGMENT_TYPE_WORKOUT_PREFERENCES);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public boolean isValidFragment(String fragmentName) {

        if (StringUtils.equals(SettingsFragment.class.getName(), fragmentName)) {
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
