package uk.co.pped.specialfitness;

import java.text.SimpleDateFormat;

import uk.co.pped.specialfitness.utility.ApplicationHelper;

/**
 * Created by matthewi on 08/09/2017.
 */

public final class Conf {

    public static final String PACKAGE_MAIN = "uk.co.pped.specialfitness";

    public static final String PACKAGE_FREE = PACKAGE_MAIN + ".free";

    public static final String PACKAGE_PAID = PACKAGE_MAIN + ".paid";

    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static final String APPLICATION_FLAVOR = BuildConfig.FLAVOR;

    public static final String PACKAGE = getPackage();

    private static final String getPackage() {
        if (APPLICATION_FLAVOR.equals(ApplicationHelper.FLAVOR_PAID)) {
            return PACKAGE_PAID;
        } else if (APPLICATION_FLAVOR.equals(ApplicationHelper.FLAVOR_FREE)) {
            return PACKAGE_FREE;
        }
        return PACKAGE_MAIN;
    }
}
