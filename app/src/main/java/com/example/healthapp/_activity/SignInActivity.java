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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPwd;
    ImageButton btnSignIn;
    FirebaseAuth mailAuth;
    TextView textViewSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.textInputET_EmailText);
        editTextPwd = findViewById(R.id.textInputET_PasswordText);
        btnSignIn = findViewById(R.id.btn_SignIn);
        mailAuth = FirebaseAuth.getInstance();
        textViewSignUp = findViewById(R.id.textView_SignUp);

        textViewSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email, pwd;
                email = editTextEmail.getText().toString();
                pwd = editTextPwd.getText().toString();

                if(TextUtils.isEmpty((email)))
                {
                    Toast.makeText(SignInActivity.this, "Vui lòng nhập email!", Toast.LENGTH_SHORT);
                    return;
                }
                if(TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(SignInActivity.this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT);
                    return;
                }
                mailAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if(task.isSuccessful())
                                {
                                    //FirebaseUser currentUser = mailAuth.getCurrentUser();
                                    //String user_Uid = currentUser.getUid();
                                    Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                    //intent.putExtra("user_Uid", user_Uid);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(SignInActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}