package com.example.healthapp._activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPwd;
    ImageButton btnSignUp;
    FirebaseAuth mailAuth;
    TextView textViewSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (TextInputEditText) findViewById(R.id.textInputET_EmailText);
        editTextPwd = (TextInputEditText) findViewById(R.id.textInputET_PasswordText);
        btnSignUp = findViewById(R.id.btn_SignUp);
        mailAuth = FirebaseAuth.getInstance();
        textViewSignIn = findViewById(R.id.textView_SignIn);
        textViewSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email, pwd;
                email = editTextEmail.getText().toString();
                pwd = editTextPwd.getText().toString();
                if(TextUtils.isEmpty((email)))
                {

                    return;
                }
                if(TextUtils.isEmpty(pwd))
                {

                    return;
                }
                mailAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if(task.isSuccessful())
                                {
                                    FirebaseUser user = mailAuth.getCurrentUser();
                                    Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), CreateProfileActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }


                        });

            }
        });

    }
}