package uk.co.pped.specialfitness.model.workout.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uk.co.pped.specialfitness.utility.StrengthSet;

/**
 * Created by matthewi on 16/11/2017.
 */

public class StrengthExerciseModel extends ExerciseModel implements IExercise {

    private final List<StrengthSet> sets = new ArrayList<StrengthSet>();

    private final Comparator<StrengthSet> strengthSetComparator = new Comparator<StrengthSet>() {
        @Override
        public int compare(StrengthSet set1, StrengthSet set2) {
            return set1.getSetNumber() - set2.getSetNumber();
        }
    };

    private String name;

    public StrengthExerciseModel() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<StrengthSet> getAllSets() {
        return Collections.unmodifiableList(sets);
    }

    public void addSet(StrengthSet newSet) {
        sets.add(newSet);
        Collections.sort(sets, strengthSetComparator);
    }

    public StrengthSet getSet(int index) {
        return sets.get(index);
    }

    public void removeSet(int index) {

        if (index > sets.size()) {
            throw new IndexOutOfBoundsException("removeSet: index has exceeded the number of sets done.");
        }

        sets.remove(index);

        for (int i = index; i < sets.size(); i++) {
            StrengthSet set = sets.get(i);
            set.setSetNumber(i);
        }

        Collections.sort(sets, strengthSetComparator);
    }

    public final ExerciseType getExerciseType() {
        return ExerciseType.STRENGTH;
    }
}
