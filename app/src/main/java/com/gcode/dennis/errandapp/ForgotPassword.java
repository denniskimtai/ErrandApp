package com.gcode.dennis.errandapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText resetEmail;
    TextView resetButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetEmail = findViewById(R.id.resetEmail);
        resetButton = findViewById(R.id.resetButton);
        firebaseAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = resetEmail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    resetEmail.setError("Email is empty! Please enter email address to get reset link");
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Password reset email sent. Please check your email", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ForgotPassword.this, "Error sending email! Please check your internet and try again", Toast.LENGTH_LONG).show();

                        }

                    }
                });

            }
        });

    }
}
