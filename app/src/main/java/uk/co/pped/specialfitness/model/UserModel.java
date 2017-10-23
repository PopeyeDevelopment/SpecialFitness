package uk.co.pped.specialfitness.model;


import android.graphics.Bitmap;


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class UserModel extends AbstractBaseModel {

    private static UserModel instance;

    private Bitmap profileCover;

    private static final int DEFAULT_AGE = 18;

    private String gymMember;

    /** @param gender property for holding the users Gender. Default is "Male". */
    private String gender;

    private Date dateOfBirth;

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

    /**
     * Gets the year the user was born, if no date of birth
     * has been set this it will return today's date minus
     * {@link #DEFAULT_AGE}.
     *
     * @return year born or default age year.
     */
    public int getFromDateOfBirth(int field) {
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

}
