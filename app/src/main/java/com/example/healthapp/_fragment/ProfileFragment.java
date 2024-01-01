package com.example.healthapp._fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp._activity.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {

    private TextView textView_Name;
    private TextView textView_DateOfBirth;
    private TextView textView_Gender;
    private TextView textView_PhoneNumber;
    private TextView textView_InsuranceID;
    private Button btn_SignOut;
    private View view;
    private FirebaseAuth mAuth;
    public ProfileFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        textView_Name = view.findViewById(R.id.textView_Name);
        textView_DateOfBirth = view.findViewById(R.id.textView_DateOfBirth);
        textView_Gender = view.findViewById(R.id.textView_Gender);
        textView_PhoneNumber = view.findViewById(R.id.textView_PhoneNumber);
        textView_InsuranceID = view.findViewById(R.id.textView_InsuranceID);
        btn_SignOut = view.findViewById(R.id.btn_SignOut);
        mAuth = FirebaseAuth.getInstance();

        // Lắng nghe sự kiện đăng nhập thành công
        mAuth.addAuthStateListener(auth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                String uid = user.getUid();
                updateProfile(uid);
            } else {
                // Đăng xuất, xử lý theo ý muốn
            }
        });
        btn_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                Intent intent = new Intent(v.getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void updateProfile(String uid) {
        // Lấy reference đến collection "profile" và document có ID là UID
        FirebaseFirestore.getInstance().collection("HoSo")
                .whereEqualTo("uid", uid)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots  -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Lấy document đầu tiên (do đã giới hạn chỉ lấy 1 document)
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);

                        // Lấy dữ liệu từ document và cập nhật TextViews
                        textView_Name.setText(documentSnapshot.getString("name"));
                        textView_InsuranceID.setText(documentSnapshot.getString("insuranceID"));
                        textView_DateOfBirth.setText(documentSnapshot.getString("dateOfBirth"));
                        textView_Gender.setText(documentSnapshot.getString("gender"));
                        textView_PhoneNumber.setText(documentSnapshot.getString("phoneNumber"));


                    } else {
                        // Không tìm thấy document phù hợp, xử lý theo ý muốn
                    }
                })
                .addOnFailureListener(e -> {
                    // Xử lý khi có lỗi xảy ra
                });
    }
}