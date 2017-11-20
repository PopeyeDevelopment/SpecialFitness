package uk.co.pped.specialfitness.utility;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;


/**
 * Created by matthewi on 26/10/2017.
 */

public class Unit<A, B, C> extends Object {

    private static final Context context = ApplicationHelper.getContext();

    @Retention(SOURCE)
    @StringDef({TYPE_WEIGHT, TYPE_HEIGHT})
    private @interface UnitTypes {
    }

    public static final String TYPE_WEIGHT = "weight";
    public static final String TYPE_HEIGHT = "height";


    /**
     * Unit type: weight, height etc
     */
    A type;

    /**
     * Unit Description: kg, cm, lbs, km etc
     */
    B description;

    /**
     * Unit Value
     */
    C value;

    /**
     * Default Constructor
     */
    public Unit() {
    }

    /**
     * Constructor with objects
     *
     * @param type
     * @param description
     * @param value
     */
    public Unit(@UnitTypes A type, B description, C value) {
        this.type = type;
        this.description = description;
        this.value = value;
    }

    public static Unit<String, String, Double> emptyUnitForType(@UnitTypes String type) {
        return new Unit(type, "", 0.0);
    }

    public void setType(@UnitTypes A type) {
        this.type = type;
    }

    public A getType() {
        return type;
    }

    public void setDescription(B description) {
        this.description = description;
    }

    public B getDescription() {
        return this.description;
    }

    public C getValue() {
        return value;
    }

    public void setValue(C value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (this == obj) {
            equals = true;
        } else if (obj != null && obj instanceof Unit<?, ?, ?>) {
            Unit<?, ?, ?> unit = (Unit<?, ?, ?>) obj;
            if (((this.type == null && unit.type == null) || (this.type != null && this.type.equals(unit.type)))
                    && ((this.description == null && unit.description == null) || (this.description != null && this.description.equals(unit.description)))
                    && ((this.value == null && unit.value == null) || (this.value != null && this.value.equals(unit.value)))) {
                equals = true;
            }
        }

        return equals;
    }

    /**
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;

        result = result * prime + (this.type == null ? 0 : this.type.hashCode());
        result = result * prime + (this.description == null ? 0 : this.description.hashCode());
        result = result * prime + (this.value == null ? 0 : this.value.hashCode());

        return result;
    }

    /**
     * @return a String representation of the object incorporating type, description and value.
     */
    @Override
    public String toString() {
        return type.toString() + "_" + description.toString() + "_" + value.toString();
    }

    /**
     * @return a string display friend representation of the object incorporating value and description.
     */
    public String toDisplayString() {
        return value.toString() + " " + description.toString();
    }

    /**
     * @return a String representation of the objects value.
     */
    public String toValueString() {
        return value.toString();
    }

}
