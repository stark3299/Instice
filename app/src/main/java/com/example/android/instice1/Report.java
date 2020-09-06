package com.example.android.instice1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Report extends AppCompatActivity implements View.OnClickListener {
    TextView lname1, fname1, email2, mobno1, address1, desc1;
    String name1,imageUrl , current_date , id ;
    Button submit1, btnDatePicker, btnTimePicker, btnchoose1;
    EditText txtDate, txtTime;
    private ImageView imageView , mImageView ;
    private String mcheck = "false";
    private Uri filePath;
    FirebaseUser mUser;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private final int PICK_IMAGE_REQUEST = 22;
    AutoCompleteTextView country1,state1;
    StorageReference storageReference;
    private DatabaseReference mDatabase;
    FirebaseStorage storage;
    String[] states = {"Andhra Pradesh", "Assam", "Arunachal Pradesh", "Bihar", "Goa", "Gujarat", "Jammu and Kashmir", "Ladakh", "Jharkhand","West Bengal", "Karnataka", "Kerala",
            "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Orissa", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura", "Uttaranchal",
            "Uttar Pradesh","Haryana", "Himachal Pradesh", "Chattisgarh", "Andaman and Nicobar", "Pondicherry", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi",
            "Chandigarh", "Lakshadweep"};
    String[] country = {"USA", "Russia", "China", "India", "France", "Spain", "Australia", "England", "South Africa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getSupportActionBar();
        email2 = findViewById(R.id.email2);
        mobno1 = findViewById(R.id.mobno1);
        address1 = findViewById(R.id.address1);
        desc1 = findViewById(R.id.desc1);
        country1 = findViewById(R.id.country1);
        state1 = findViewById(R.id.state1);
        lname1 = findViewById(R.id.lname1);
        fname1 = findViewById(R.id.fname1);
        submit1 = findViewById(R.id.submit1);
        btnchoose1 = findViewById(R.id.btnChoose);
        mImageView = findViewById(R.id.arrow_backR);
        imageView = findViewById(R.id.imgView);
        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnchoose1.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        submit1.setOnClickListener(this);
        ArrayAdapter ad = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, states);
        ArrayAdapter ad2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, country);
        state1.setAdapter(ad);
        state1.setThreshold(1);
        country1.setAdapter(ad2);
        country1.setThreshold(1);
        name1 = fname1 + " " + lname1;
        storage = FirebaseStorage.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("UsersImages");
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        current_date = new SimpleDateFormat("yyyy-mm-dd" , Locale.getDefault()).format(new Date());

    }

    @Override
    public void onClick(View view) {
       switch (view.getId())
       {
           case R.id.btn_date:
               final Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);


               DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                       (view1, year, monthOfYear, dayOfMonth) -> txtDate.setText(  year + "-" + (monthOfYear + 1) + "-" + dayOfMonth), mYear, mMonth, mDay);
               datePickerDialog.show();
               break;

           case R.id.btn_time:
               final Calendar c1 = Calendar.getInstance();
               mHour = c1.get(Calendar.HOUR_OF_DAY);
               mMinute = c1.get(Calendar.MINUTE);
               // Launch Time Picker Dialog
               TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                       new TimePickerDialog.OnTimeSetListener() {
                           @Override
                           public void onTimeSet(TimePicker view, int hourOfDay,
                                                 int minute) {
                               txtTime.setText(hourOfDay + ":" + minute);
                           }
                       }, mHour, mMinute, true);
               timePickerDialog.show();
               break;

           case R.id.btnChoose:
               SelectImage();
               break;

           case R.id.submit1:
                checkDataEntered();
                break;

           case R.id.arrow_backR:
               Intent intent = new Intent(Report.this , DashBoard.class);
               startActivity(intent);
               finish();
       }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private void SelectImage()
    { // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();
            try {
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void uploadImage()
    {
        if (filePath != null) {
            // Defining the child of storageReference
            StorageReference ref = storageReference.child(Objects.requireNonNull(mUser.getEmail())).child(UUID.randomUUID().toString() + ".jpg");
            final UploadTask uploadTask = ref.putFile(filePath);
            // adding listeners on upload
            // or failure of image
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Image uploaded successfully
               // Toast.makeText(Report.this,"Image Uploaded!!",Toast.LENGTH_SHORT).show();
                Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                if(downloadUri.isSuccessful()){
                    imageUrl  = downloadUri.getResult().toString();
                }
            })
                    .addOnFailureListener(e -> {
                        // Error, Image not uploaded
                        Toast.makeText(Report.this,"Error in Image Uploading " + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }) ;
            }
        }

    public void checkDataEntered() {

        String name = name1;
        String email = email2.getText().toString().trim();
        String mobile_no = mobno1.getText().toString().trim();
        String country = country1.getText().toString().trim();
        String description = desc1.getText().toString().trim();
        String state = state1.getText().toString().trim();
        String address = address1.getText().toString().trim();
        String time = txtTime.getText().toString().trim();
        String date = txtDate.getText().toString().trim();
        String check = mcheck;

        if (isEmpty((EditText) fname1)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty((EditText) lname1)) {
            lname1.setError("Last name is required!");
        }

        if (!isEmail((EditText) email2)) {
            email2.setError("Enter valid email!");
        }

        if (isEmpty(country1)) {
            lname1.setError("Last name is required!");
        }

        if (isEmpty(state1)) {
            lname1.setError("Last name is required!");
        }

        if (isEmpty((EditText) address1)) {
            lname1.setError("Last name is required!");
        }

        if (isEmpty((EditText) desc1)) {
            lname1.setError("Last name is required!");
        }

        if (isEmpty((EditText) mobno1)) {
            lname1.setError("Last name is required!");
        }

        if (isEmpty(txtDate)) {
            txtDate.setError("Date of incident is required!");
        }

        if (isEmpty(txtTime)) {
            txtTime.setError("Time of incident is required!");
        }

        if(name!=null && email!=null && mobile_no!=null && country!=null && description!=null && state!=null && address!=null && date!=null && time!=null)
        {
            uploadImage();
            id  = mUser.getUid();
            Report1 user1 = new Report1(id , name , email , mobile_no , country , description , state , address , date , time , imageUrl , check , current_date );
            mDatabase.child(id).setValue(user1);
            startActivity(new Intent(Report.this,DashBoard.class));
            Toast.makeText(Report.this, "Report Submitted", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            Toast.makeText(Report.this, "Error !!", Toast.LENGTH_SHORT).show();
        }
    }

}

