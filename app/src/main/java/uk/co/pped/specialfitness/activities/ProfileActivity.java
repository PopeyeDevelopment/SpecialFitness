package uk.co.pped.specialfitness.activities;

import static uk.co.pped.specialfitness.fragments.AbstractBaseFragment.OnFragmentInteractionListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;

import uk.co.pped.specialfitness.components.CurvedImageView;
import uk.co.pped.specialfitness.R;
import uk.co.pped.specialfitness.model.UserModel;

/**
 * Created by matthewi on 08/09/2017.
 */
public class ProfileActivity extends AbstractBaseActivity implements OnFragmentInteractionListener {

    private UserModel user;

    public ProfileActivity() {
        this.user = UserModel.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        setSupportedActionBar();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }


}
