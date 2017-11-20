package uk.co.pped.specialfitness.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.Set;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.components.NextPreviousControl;
import uk.co.pped.specialfitness.utility.ApplicationHelper;
import uk.co.pped.specialfitness.utility.PrefManager;

/**
 * Created by matthewi on 31/10/2017.
 */

public class WorkoutActivity extends AbstractBaseActivity {

    private PrefManager prefManager;
    private SharedPreferences sharedPreferences;

    private NextPreviousControl workoutDateController;
    private TextView workoutDayLabel;

    public WorkoutActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_activity);
        setSupportedActionBar();
        prefManager = new PrefManager(this);
        sharedPreferences = prefManager.getSharedPreferences();
        init();
    }

    private void init() {
        this.workoutDateController = findViewById(R.id.workoutDateController);
        this.workoutDayLabel = findViewById(R.id.workoutDayLabel);
        this.workoutDayLabel.setText(workoutDateController.getDisplayDayName());

        this.workoutDateController.setOnChangeListener(new NextPreviousControl.OnChangeLisener() {
            @Override
            public void onChange() {
                Set<String> availableDays = sharedPreferences.getStringSet(ApplicationHelper.getStringFromResId(R.string.available_workout_days), null);
                String displayVal = workoutDateController.getDisplayDayName();
                if (!availableDays.contains(workoutDateController.getDisplayDayName())) {
                    displayVal = displayVal + ": Rest Day";
                }
                workoutDayLabel.setText(displayVal);
            }
        });
    }
}
