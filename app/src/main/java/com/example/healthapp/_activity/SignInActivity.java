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

public class SignInActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPwd;
    private ImageButton btnSignIn;
    private FirebaseAuth mailAuth;
    private TextView textViewSignUp;
    private TextView textViewForgotPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editTextEmail = findViewById(R.id.textInputET_EmailText);
        editTextPwd = findViewById(R.id.textInputET_PasswordText);
        btnSignIn = findViewById(R.id.btn_SignIn);
        mailAuth = FirebaseAuth.getInstance();
        textViewSignUp = findViewById(R.id.textView_SignUp);
        textViewForgotPwd = findViewById(R.id.textView_ForgotPassword);

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
                                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                    //intent.putExtra("email", editTextEmail.getText());
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(SignInActivity.this, "Đăng nhập thất bại, Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        textViewForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(email)) {
                    sendPasswordResetEmail(email);
                } else {
                    Toast.makeText(SignInActivity.this, "Vui lòng nhập địa chỉ email của bạn.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendPasswordResetEmail(String email) {
        mailAuth = FirebaseAuth.getInstance();
        mailAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignInActivity.this, "Email đặt lại mật khẩu đã được gửi!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignInActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}