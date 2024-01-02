package com.example.healthapp._fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp._activity.SignInActivity;
import com.example.healthapp._class.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {
    private Profile profile;
    private TextView textView_Name;
    private TextView textView_DateOfBirth;
    private TextView textView_Gender;
    private TextView textView_PhoneNumber;
    private TextView textView_InsuranceID;
    private Button btn_EditProfile;
    private Button btn_SignOut;
    private View view;
    private FirebaseAuth mAuth;
    public ProfileFragment () {}
    private AlertDialog alertDialog;
    private String userUID;
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
        btn_EditProfile = view.findViewById(R.id.btn_EditProfile);
        mAuth = FirebaseAuth.getInstance();

        // Lắng nghe sự kiện đăng nhập thành công
        mAuth.addAuthStateListener(auth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                userUID= user.getUid();
                FirebaseFirestore.getInstance().collection("UserProfile")
                        .whereEqualTo("uid", userUID)
                        .addSnapshotListener((queryDocumentSnapshots, e) -> {
                            if (e != null) {
                                // Xử lý khi có lỗi xảy ra trong quá trình lắng nghe
                                return;
                            }

                            if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
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
                        });
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
        btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setEditableTextView(textView_Name);
//                setEditableTextView(textView_InsuranceID);
//                setEditableTextView(textView_DateOfBirth);
//                setEditableTextView(textView_Gender);
//                setEditableTextView(textView_PhoneNumber);
                showEditDialog();
            }
        });
        return view;
    }
    private void updateProfile(String uid) {
        // Lấy reference đến collection "profile" và document có ID là UID
        FirebaseFirestore.getInstance().collection("UserProfile")
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

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_profile_dialog, null);
        builder.setView(dialogView);

        EditText editText_NewName = dialogView.findViewById(R.id.editText_NewName);
        EditText editText_NewInsuranceID = dialogView.findViewById(R.id.editText_NewInsuranceID);
        EditText editText_NewDoB = dialogView.findViewById(R.id.editText_NewDoB);
        EditText editText_NewGender = dialogView.findViewById(R.id.editText_NewGender);
        EditText editText_NewPhoneNumber = dialogView.findViewById(R.id.editText_NewPhoneNumber);
        Button btn_Save = dialogView.findViewById(R.id.btn_Save);
        Button btn_Cancel = dialogView.findViewById(R.id.btn_Cancel);

        FirebaseFirestore.getInstance().collection("UserProfile")
                .whereEqualTo("uid", userUID)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots  -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Lấy document đầu tiên (do đã giới hạn chỉ lấy 1 document)
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);

                        // Lấy dữ liệu từ document và cập nhật TextViews
                        editText_NewName.setText(documentSnapshot.getString("name"));
                        editText_NewInsuranceID.setText(documentSnapshot.getString("insuranceID"));
                        editText_NewDoB.setText(documentSnapshot.getString("dateOfBirth"));
                        editText_NewGender.setText(documentSnapshot.getString("gender"));
                        editText_NewPhoneNumber.setText(documentSnapshot.getString("phoneNumber"));


                    } else {
                        // Không tìm thấy document phù hợp, xử lý theo ý muốn
                    }
                })
                .addOnFailureListener(e -> {
                    // Xử lý khi có lỗi xảy ra
                });

        // Xử lý sự kiện click nút Save
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cập nhật thông tin của học sinh
                String newName = editText_NewName.getText().toString();
                String newInsuranceID = editText_NewInsuranceID.getText().toString();
                String newNewDoB = editText_NewDoB.getText().toString();
                String newGender = editText_NewGender.getText().toString();
                String newPhoneNumber = editText_NewPhoneNumber.getText().toString();

                FirebaseFirestore.getInstance().collection("UserProfile")
                        .whereEqualTo("uid", userUID)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    // Tìm thấy document, cập nhật giá trị
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("name", newName);
                                    updates.put("insuranceID", newInsuranceID);
                                    updates.put("dateOfBirth", newNewDoB);
                                    updates.put("gender", newGender);
                                    updates.put("phoneNumber", newPhoneNumber);
                                    // Thêm các cặp key-value cần cập nhật

                                    document.getReference().update(updates)
                                            .addOnSuccessListener(aVoid -> {
                                                // Cập nhật thành công
                                                // TODO: Xử lý khi cập nhật thành công (nếu cần)
                                            })
                                            .addOnFailureListener(e -> {
                                                // Xử lý khi cập nhật thất bại
                                                // e.printStackTrace(); để in ra lỗi
                                            });
                                }
                            } else {
                                // Xử lý khi tìm kiếm không thành công
                                // task.getException().printStackTrace(); để in ra lỗi
                            }
                        });
                alertDialog.dismiss();

                updateProfile(userUID);
            }
        });
        // Xử lý sự kiện click nút Cancel
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đóng dialog khi nhấn Cancel
                alertDialog.dismiss();
            }
        });

        // Tạo AlertDialog
        alertDialog = builder.create();
        // Hiển thị AlertDialog
        alertDialog.show();
    }
}