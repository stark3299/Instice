package com.example.android.instice1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.Objects;

public class Profile extends AppCompatActivity {
    public static Profile INSTANCE ;
    TextView nameP , emailP ;
    Button done ;
    ImageButton mImageButton;
    ImageView profileImg , mImageView ;
    String imageUrl1 , emailP1 , userId , nameP1 ;
    private final int PICK_IMAGE_REQUEST = 22;
    StorageReference mStorageReference = FirebaseStorage.getInstance().getReference("Profile");
    Uri filePath ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameP = findViewById(R.id.nameP);
        emailP = findViewById(R.id.emailP);
        done = findViewById(R.id.done);
        mImageView = findViewById(R.id.arrow_backPr);
        mImageView.setOnClickListener(view -> {
            Intent intent = new Intent(Profile.this , Setting.class);
            startActivity(intent);
            finish();
        });
        nameP1 = nameP.toString();
        mImageButton = findViewById(R.id.imageButton);
        profileImg = findViewById(R.id.profileImg);
        dr = FirebaseDatabase.getInstance().getReference("Profile");
        emailP1 = user.getEmail();
        userId = user.getUid();
        emailP.setText(emailP1);
        emailP.setEnabled(false);
        mImageButton.setOnClickListener(view -> SelectImage());
        done.setOnClickListener(view -> {
            if (filePath != null) {
                StorageReference ref = mStorageReference.child(user.getEmail() + ".jpg");
                final UploadTask uploadTask = ref.putFile(filePath);
                uploadTask.addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(Profile.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                    if (downloadUri.isSuccessful()) {
                        Toast.makeText(Profile.this, "Url Successful", Toast.LENGTH_SHORT).show();
                        imageUrl1 = Objects.requireNonNull(downloadUri.getResult()).toString();
                    }

                }).addOnFailureListener(e -> Toast.makeText(this, "Profile Update failed...", Toast.LENGTH_SHORT).show());
            }
            Profile1 profile = new Profile1(nameP1 , imageUrl1);
            dr.child(userId).setValue(profile);
            Intent intent = new Intent(Profile.this , Navigation.class);
            startActivity(intent);
            finish();
        });
    }
    private void SelectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profileImg.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static Profile getActivityInstance() {
        return INSTANCE;
    }
    public String getData(){
        return this.nameP1;
    }
}
