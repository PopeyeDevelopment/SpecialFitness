package uk.co.pped.specialfitness.model;


import android.app.Application;
import android.graphics.Bitmap;

import com.google.android.gms.plus.model.people.Person;

import uk.co.pped.specialfitness.Conf;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class UserModel extends AbstractBaseModel {

    private static UserModel instance;

    private Bitmap profileCover;

    /** @param gender property for holding the users Gender. Default is "Male". */
    private String gender = "Male";

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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
