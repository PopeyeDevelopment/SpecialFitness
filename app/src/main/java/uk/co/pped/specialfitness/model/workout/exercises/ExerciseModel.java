package uk.co.pped.specialfitness.model.workout.exercises;

import java.util.Date;

/**
 * Created by matthewi on 16/11/2017.
 */

public abstract class ExerciseModel  {

    /** Start time of the exercise, this isn't necessarily the same time as inputting. */
    private Date start;

    /** Finish time of the exercise, this isn't necessarily the same time as inputting. */
    private Date finish;

    public static ExerciseModel startNewExercise(ExerciseType type) {

        if (ExerciseType.AEROBIC.equals(type)) {

        } else if (ExerciseType.BALANCE.equals(type)) {

        } else if (ExerciseType.STRENGTH.equals(type)) {
            return new StrengthExerciseModel();
        } else if (ExerciseType.STRETCH.equals(type)) {

        }

        // In theory this should never be hit, it is just here as
        // a precautionary measure.o
        return new ExerciseModel() {
            @Override
            public ExerciseType getExerciseType() {
                return ExerciseType.UNKNOWN;
            }
        };
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return this.start;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Date getFinish() {
        return this.finish;
    }

    public abstract ExerciseType getExerciseType();
}
