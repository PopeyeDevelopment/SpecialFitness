package uk.co.pped.specialfitness.fragments.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.pped.specialfitness.R;

/**
 * Created by matthewi on 26/09/2017.
 */

public class SettingsUnitsFragment extends AbstractBaseSettingsFragment {


    public SettingsUnitsFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }



    //    protected void setSupportedActionBar() {
//        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
//        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
//        toolbar.setFocusableInTouchMode(true);
//        toolbar.setTitle("Unit");
//        settingsActivity.setSupportActionBar(toolbar);
//        settingsActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileActivity.
     */
    public static SettingsUnitsFragment newInstance() {
        SettingsUnitsFragment fragment = new SettingsUnitsFragment();
        return fragment;
    }

}
