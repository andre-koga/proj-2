package com.example.spotify_ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.spotify_ui.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AppBarConfiguration appBarConfiguration;
    public EditText signupEmail, signupPassword, signupPassword2;
    Button btnSignUp;
    TextView loginRedirectText;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        firebaseAuth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.edEmail);
        signupPassword = findViewById(R.id.ConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        loginRedirectText = findViewById(R.id.txt);
        signupPassword2 = findViewById(R.id.ConfirmPassword2);
        final String[] emailID = new String[1];
        final String[] paswd = new String[1];
        final String[] paswd2 = new String[1];
        if (firebaseAuth.getCurrentUser() != null) {
            Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
            Intent I = new Intent(MainActivity.this, Content.class);
            startActivity(I);
        }

        //
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailID[0] = signupEmail.getText().toString();
                paswd[0] = signupPassword.getText().toString();
                paswd2[0] = signupPassword2.getText().toString();

                if (emailID[0].isEmpty()) {
                    signupEmail.setError("Provide your Email first!");
                    signupEmail.requestFocus();
                } else if (paswd[0].isEmpty()) {
                    signupPassword.setError("Set your password");
                    signupPassword.requestFocus();
                } else if (paswd2[0].isEmpty()) {
                    signupPassword.setError("Confirm your password");
                    signupPassword.requestFocus();
                } else if (passwordMatch(paswd[0], paswd2[0])) {
                    signupPassword.setError("Passwords must be equal");
                    signupPassword.requestFocus();
                } else if (emailID[0].isEmpty() && paswd[0].isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID[0].isEmpty() && paswd[0].isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID[0], paswd[0]).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, UserActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(I);
            }
        });

    }
    private boolean passwordMatch(String paswd, String paswd2) {
        return !paswd.equals(paswd2);
    }
}
