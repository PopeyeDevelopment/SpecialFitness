package uk.co.pped.specialfitness.model;


import android.graphics.Bitmap;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import uk.co.pped.specialfitness.model.workout.WorkoutModel;
import uk.co.pped.specialfitness.utility.Unit;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class UserModel extends AbstractBaseModel {

    private static final class UnitMapKeys {
        public static final String WEIGHT_KEY = "user_weight";
        public static final String HEIGHT_KEY = "user_height";
    }

    private static UserModel instance;

    private Bitmap profileCover;

    // TODO: Create getters and setters for this field
    /** Field for whether the user is logged in or not
     * if in offline mode than user is not logged in. */
    private boolean loggedIn;

    private static final int DEFAULT_AGE = 18;

    private String gymMember;

    /** @param gender property for holding the users Gender. Default is "Male". */
    private String gender;

    private Date dateOfBirth;

    /** Property for holding the Users Weight value */
    private Unit<String, String, Double> weight = Unit.emptyUnitForType(Unit.TYPE_WEIGHT);

    private Unit<String, String, Double> height = Unit.emptyUnitForType(Unit.TYPE_HEIGHT);

    private Map<String, List<WorkoutModel>> workouts;

    // TODO: Create NutritionModel and implement
    //private List<NutritionModel> nutrition;

    private UserModel() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to single instance of this class");
        }

    }

    public static UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel();
        }

        return instance;
    }

    public void setGymMember(String gymMember) {
        this.gymMember = gymMember;
    }

    public String getGymMember() {
        return gymMember;
    }

    public void setProfileCover(Bitmap profileCover) {
        this.profileCover = profileCover;
    }

    public Bitmap getProfileCover() {
        return profileCover;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWeight(Unit<String, String, Double> weight) {
        if (weight.getType() == Unit.TYPE_WEIGHT) {
            this.weight = weight;
        }
    }

    public Unit<String, String, Double> getWeight() {
        return weight;
    }

    public void setHeight(Unit<String, String, Double> height) {
        if (height.getType() == Unit.TYPE_HEIGHT) {
            this.height = height;
        }
    }

    public Unit<String, String, Double> getHeight() {
        return height;
    }

    /**
     * Gets the year the user was born, if no date of birth
     * has been set this it will return today's date minus
     * {@link #DEFAULT_AGE}.
     *
     * @return year born or default age year.
     */
    public int getFieldFromDateOfBirth(int field) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        if (this.dateOfBirth != null) {
            cal.setTime(this.dateOfBirth);
        }

        int result = 0;

        switch (field) {
            case Calendar.YEAR:
                result = cal.get(Calendar.YEAR);
                if (this.dateOfBirth == null) {
                    result = result - DEFAULT_AGE;
                }
                break;
            case Calendar.MONTH:
                result = cal.get(Calendar.MONTH);
                break;
            case Calendar.DAY_OF_MONTH:
                result = cal.get(Calendar.DAY_OF_MONTH);
                break;
        }


        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return null;
    }
}
