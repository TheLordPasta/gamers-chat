package com.example.gamers_chat.activities;

import static android.provider.MediaStore.Images.Thumbnails.getThumbnail;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import androidx.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;   
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamers_chat.R;
import com.example.gamers_chat.models.CustomAdapterGames;
import com.example.gamers_chat.models.GameModel;
import com.example.gamers_chat.models.User;
import com.example.gamers_chat.repository.NetworkRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    StorageReference storageRef;
    LinearProgressIndicator progressIndicator;
    Uri image;
    MaterialButton selectImageButtom, uploadImageButtom;
    ImageView imageView;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    image = result.getData().getData();
                    uploadImageButtom.setEnabled(true);
                    Glide.with(getApplicationContext()).load(image).into(imageView);
                }
            } else {
                Toast.makeText(MainActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
    });
    public User currentUser;
    public String currentUserUID;

    public ArrayList<GameModel> gameList;
    private RecyclerView recyclerViewGames;
    private LinearLayoutManager layoutManager;
    private CustomAdapterGames gameAdapter;
    private NetworkRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        storageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


    }

    public void CreateGameList(View view)
    {

        recyclerViewGames =  view.findViewById(R.id.gamesSearchRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewGames.setLayoutManager(layoutManager);
        recyclerViewGames.setItemAnimator(new DefaultItemAnimator());

        repository = new NetworkRepository();


        // Call method to fetch games
        MutableLiveData<ArrayList<GameModel>> temp = repository.getGames();
        gameList = temp.getValue();
//        if (dataSetGames == null) {
//            dataSetGames = new ArrayList<>();
//            for ( int i =0 ; i < gameList.gameNames.length ; i++) {
//                dataSetGames.add(new GameModel(
//                        gameList.gameNames[i],
//                        gameList.gameDescriptions[i],
//                        gameList.gamePlatforms[i],
//                        gameList.gamePublishers[i],
//                        gameList.gameBannerDrawables[i]
//                ));
//            }
//        }




        gameAdapter = new CustomAdapterGames(gameList, new CustomAdapterGames.OnItemClickListener() {

            @Override
            public void onItemClick(GameModel gameModel) {
                System.out.println(gameModel.title);

                //navigation to game fragment instead of the bullshit on top ^^^
            }
        });
        recyclerViewGames.setAdapter(gameAdapter);

        Button button = view.findViewById(R.id.gamesListSearchButton);

        // Set onClickListener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = ((EditText) findViewById(R.id.gamesSearchInput)).getText().toString().trim();
                gameAdapter.filter(searchQuery);
            }
        });
    }

    public void registerUserData() {
            EditText userEmail = this.findViewById(R.id.emailInput);
            EditText userPass = this.findViewById(R.id.passwordInput);
            EditText userName = this.findViewById(R.id.nameinput);
            EditText userNickname = this.findViewById(R.id.nickname_input);



            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference myRef = database.getReference("users").child(userName.getText().toString());

            User user1 = new User(userEmail.getText().toString(),userPass.getText().toString(),userNickname.getText().toString(),userName.getText().toString());
            String email=userEmail.getText().toString();

            String password=userPass.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign i n success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                DatabaseReference myRef = database.getReference("users").child(firebaseUser.getUid());
                                myRef.setValue(user1);
                                Toast.makeText(MainActivity.this, "User created successfully!.",
                                        Toast.LENGTH_SHORT).show();


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        public void onSignIn(View v) {
                EditText userEmail = this.findViewById(R.id.loginEmailInput);
                EditText userPass = this.findViewById(R.id.loginPasswordInput);


                String email = userEmail.getText().toString();
                String password = userPass.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    currentUserUID = user.getUid();

                                    fetchUserDetails(user);
                                    //updateUI(user);
                                    Navigation.findNavController(v).navigate(R.id.action_login_to_mainMenu);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }

    private void fetchUserDetails(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String userId = firebaseUser.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        currentUser =user;
                        // You can use this User object as needed
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle possible errors
                }
            });
        }
    }



    public void InitProfileImage(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressIndicator = view.findViewById(R.id.progress);

        imageView = view.findViewById(R.id.profileImageView);
        selectImageButtom = view.findViewById(R.id.selectImage);
        uploadImageButtom = view.findViewById(R.id.uploadImage);
        selectImageButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        uploadImageButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage(image);
            }
        });
    }

    private void uploadImage(Uri file) {
        StorageReference ref = storageRef.child(String.format("image/%s/",currentUserUID) + "profilePic");

        ref.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressIndicator.setMax(Math.toIntExact(taskSnapshot.getTotalByteCount()));
                progressIndicator.setProgress(Math.toIntExact(taskSnapshot.getBytesTransferred()));
            }
        });
    }


    public void InitProfileImageOnLoad(View view)
    {
        StorageReference ref = storageRef.child(String.format("image/%s/",currentUserUID) + "profilePic");

        final long ONE_MEGABYTE = 1024 * 1024;
        ref.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Data for "images/island.jpg" is returns, use this as needed
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bmp);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

    }

}