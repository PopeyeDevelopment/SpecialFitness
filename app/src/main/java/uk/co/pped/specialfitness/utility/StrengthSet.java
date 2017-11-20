package uk.co.pped.specialfitness.utility;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by matthewi on 16/11/2017.
 */

public final class StrengthSet {

    /** Field for holding the set number */
    private int set;

    /** Field for holding the number of reps per set*/
    private int reps;

    /** Field for holding the weight details */
    private Unit<String, String, Double> weight;

    private Date timeBetweenReps;

    public StrengthSet(@NonNull int set, @NonNull int reps, @NonNull Unit<String, String, Double> weight, Calendar timeBetweenReps) {
        this.set = set;
        this.reps = reps;
        this.weight = weight;
        this.timeBetweenReps = timeBetweenReps.getTime();
    }

    public void setSetNumber(int set) {
        this.set = set;
    }

    public int getSetNumber() {
        return this.set;
    }


    public void setReps() {
        this.reps = reps;
    }

    public int getReps() {
        return this.reps;
    }

    public void setWeight(Unit<String, String, Double> weight) {
        this.weight = weight;
    }

    public Unit<String, String, Double> getWeight() {
        return this.weight;
    }

    public void setTimeBetweenReps() {
        this.timeBetweenReps = timeBetweenReps;
    }

    public Date getTimeBetweenReps() {
        return this.timeBetweenReps;
    }

}

