package uk.co.pped.specialfitness.model.workout;

import java.util.Calendar;

import uk.co.pped.specialfitness.model.AbstractBaseModel;

/**
 * Created by matthewi on 14/11/2017.
 */

public class WorkoutModel extends AbstractBaseModel {

    /** Field to hold the name of the workout. */
    private String name;

    /** Field to hold the Date & Time of the workout */
    private Calendar workoutTime;

    // TODO: Workout Type (Cardio, Weights, etc)
    // TODO: Exercise type.

    public WorkoutModel() {
        super();
    }


}
