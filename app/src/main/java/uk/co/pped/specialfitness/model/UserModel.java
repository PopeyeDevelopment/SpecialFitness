package uk.co.pped.specialfitness.model;


import android.app.Application;

import uk.co.pped.specialfitness.Conf;
import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class UserModel extends AbstractBaseModel {

    private static UserModel instance;

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


}
