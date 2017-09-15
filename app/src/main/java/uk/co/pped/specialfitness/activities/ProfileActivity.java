package uk.co.pped.specialfitness.activities;

import static uk.co.pped.specialfitness.fragments.AbstractBaseFragment.OnFragmentInteractionListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;

import uk.co.pped.specialfitness.components.CurvedImageView;
import uk.co.pped.specialfitness.fragments.ProfileFragment;
import uk.co.pped.specialfitness.R;

public class ProfileActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TYPE_IMAGE = "image/*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CurvedImageView imageView = findViewById(R.id.profile_cover);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.profile_cover)) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType(TYPE_IMAGE);
                    startActivityForResult(Intent.createChooser(intent, "Select Profile Cover"), PICK_IMAGE_REQUEST);
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                CurvedImageView imageView = (CurvedImageView) findViewById(R.id.profile_cover);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
