package com.example.healthapp._fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.healthapp.R;
import com.example.healthapp._activity.AppointmentDateChoosingActivity;
import com.example.healthapp._activity.DoctorChoosingActivity;
import com.example.healthapp._activity.ShowClinicInfoActivity;
import com.example.healthapp._class.Doctor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardView cardView_ShowDoctorList = (CardView) view.findViewById(R.id.cardView_ShowDoctorList);
        CardView cardView_ClinicInfo = (CardView) view.findViewById(R.id.cardView_ClinicInfo);
        CardView btn_Search = (CardView) view.findViewById(R.id.cardView_Search);
        CardView btn_Thread = (CardView) view.findViewById(R.id.cardView_Thread);
        CardView btn_Categories = (CardView) view.findViewById(R.id.cardView_Categories);
        CardView btn_MedicalPackage = (CardView) view.findViewById(R.id.cardView_PackageList);
        TextView textView_ShowDoctorList = (TextView) view.findViewById(R.id.textView_ShowDoctorList);
        TextView textView_StaredDoctorName = (TextView) view.findViewById(R.id.textView_StaredDoctorName);
        TextView textView_StaredDoctorSpeciality = (TextView) view.findViewById(R.id.textView_StaredDoctorSpeciality);
        Button btn_BookStaredDoctor = (Button) view.findViewById(R.id.btn_BookStaredDoctor);
        Button btn_CallHotline = (Button) view.findViewById(R.id.btn_CallHotline);
        TextView textView_CallHotline = (TextView) view.findViewById(R.id.textView_CallHotline);
        cardView_ShowDoctorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DoctorChoosingActivity.class);
                startActivity(intent);
            }
        });
        cardView_ClinicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowClinicInfoActivity.class);
                startActivity(intent);
            }
        });
        textView_ShowDoctorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DoctorChoosingActivity.class);
                startActivity(intent);
            }
        });
        btn_BookStaredDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AppointmentDateChoosingActivity.class);

                // Truyền dữ liệu nếu cần thiết
                intent.putExtra("doctorID", "doctor-1");
                intent.putExtra("doctorName", textView_StaredDoctorName.getText().toString());
                intent.putExtra("doctorSpeciality", textView_StaredDoctorSpeciality.getText().toString());
                v.getContext().startActivity(intent);

            }
        });
        btn_CallHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0815942559";

                // Tạo Intent với hành động ACTION_CALL
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                // Đặt số điện thoại cần gọi
                //dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                if(ActivityCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(dialIntent);

            }
        });
        textView_CallHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0815942559";

                // Tạo Intent với hành động ACTION_CALL
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                // Đặt số điện thoại cần gọi
                //dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                if(ActivityCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(dialIntent);
            }
        });
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

}