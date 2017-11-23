package uk.co.pped.specialfitness.activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.components.FloatingActionMenuHelper;
import uk.co.pped.specialfitness.components.NextPreviousControl;
import uk.co.pped.specialfitness.utility.ApplicationHelper;
import uk.co.pped.specialfitness.utility.NavigationHelper;
import uk.co.pped.specialfitness.utility.PrefManager;

/**
 * Created by matthewi on 31/10/2017.
 */

public class WorkoutActivity extends AbstractBaseActivity implements View.OnClickListener {

    private PrefManager prefManager;
    private SharedPreferences sharedPreferences;

    private NextPreviousControl workoutDateController;
    private TextView workoutDayLabel;

    private FloatingActionMenu floatingActionMenu;

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
        this.floatingActionMenu = findViewById(R.id.workout_action_menu);
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

        // TODO: Determine if a workout is already in progress or if it has been finished.
        reloadWorkoutActionMenuButtons();
    }

    private void reloadWorkoutActionMenuButtons() {
        final List<FloatingActionButton> buttons = new ArrayList<>();
        FloatingActionButton startWorkoutBtn = FloatingActionMenuHelper.getButtonBuilder(this)
                                                .setId(NavigationHelper.WORKOUT_ACTION_BTN_START_ID)
                                                .setLabelText("Start New Workout")
                                                .setButtonImageSource(R.drawable.ic_create_icon)
                                                .setOnClickListener(this)
                                                .build();
        buttons.add(startWorkoutBtn);
        FloatingActionButton editWorkoutBtn = FloatingActionMenuHelper.getButtonBuilder(this)
                                                .setId(NavigationHelper.WORKOUT_ACTION_BTN_EDIT_ID)
                                                .setLabelText("Start New Workout")
                                                .setOnClickListener(this)
                                                .build();
        buttons.add(editWorkoutBtn);
        FloatingActionMenuHelper.addAllMenuButtons(buttons, this.floatingActionMenu);
    }

    @Override
    public void onClick(View view) {

        if (view instanceof FloatingActionButton) {
            onFloatingActionMenuButtonSelect((FloatingActionButton) view);
        }
    }

    private boolean onFloatingActionMenuButtonSelect(@NonNull FloatingActionButton btn) {

        boolean result = false;

        if (btn == null) {
            return result;
        }

        int id = btn.getId();

        if (NavigationHelper.isValidWorkoutNavigation(id)) {

        }

        return result;
    }
}
