package com.example.healthapp._fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthapp.R;
import com.example.healthapp._adapter.AppointmentAdapter;
import com.example.healthapp._class.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //          NEW STUFF
    private RecyclerView rcv_Appointments;
    private AppointmentAdapter adapter_Appointment;
    private List<Appointment> list_Appointments;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    public String userUID;
    public BookingListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingListFragment newInstance(String param1, String param2) {
        BookingListFragment fragment = new BookingListFragment();
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
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(auth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                userUID = user.getUid();
                getData(); // Gọi getData() sau khi xác định được userUID
            } else {
                // Đăng xuất, xử lý theo ý muốn
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_list, container, false);


        initElement(view);
        getData();
        return view;
    }
    private void initElement(View view) {

        db = FirebaseFirestore.getInstance();
        rcv_Appointments = view.findViewById(R.id.rcv_Appointments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv_Appointments.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        rcv_Appointments.addItemDecoration(dividerItemDecoration);

        list_Appointments = new ArrayList<>();
        // Chuyển interface để lắng nghe sự kiện chọn bác sĩ
        adapter_Appointment = new AppointmentAdapter(list_Appointments, getActivity());

        rcv_Appointments.setAdapter(adapter_Appointment);
    }

    private void getData() {
        CollectionReference appointmentsRef = db.collection("Booking");

        // Lắng nghe sự kiện thay đổi dữ liệu trong collection "Booking"
        appointmentsRef.whereEqualTo("patientID", userUID)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        // Xử lý khi có lỗi xảy ra trong quá trình lắng nghe
                        // error.printStackTrace(); để in ra lỗi
                        return;
                    }

                    // Xóa dữ liệu cũ trong danh sách trước khi thêm dữ liệu mới
                    list_Appointments.clear();

                    // Lặp qua từng document và thêm vào danh sách
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        if (documentSnapshot.exists()) {
                            Appointment appointment = documentSnapshot.toObject(Appointment.class);
                            list_Appointments.add(appointment);
                        }
                    }

                    // Cập nhật Adapter hoặc thực hiện các bước cần thiết để xử lý dữ liệu
                    adapter_Appointment.notifyDataSetChanged();
                });
    }
}