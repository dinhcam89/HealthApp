package com.example.healthapp._fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp._activity.DoctorChoosingActivity;
import com.example.healthapp._activity.ShowClinicInfoActivity;

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
        Button datlich1;

        Button datlich2;
        Intent intent_bacsi;
        Intent intent_tracuu;
        Intent intent_danhmuc;
        Intent intent_datlich1;
        Intent intent_datlich2;
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