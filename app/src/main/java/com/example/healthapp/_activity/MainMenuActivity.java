package com.example.healthapp._activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.BoringLayout;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp._activity.DoctorChoosingActivity;
import com.example.healthapp._adapter.ViewPagerAdapter;
import com.example.healthapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainMenuActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    /*Button btnSignOut;
    FirebaseAuth emailAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnSignOut = findViewById(R.id.button);
        emailAuth = FirebaseAuth.getInstance();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }*/
    private CardView Doctors;
    private CardView Find;
    private CardView ClinicInformation;

    private Button datlich1;

    private Button datlich2;
    private Intent intent_bacsi;
    private Intent intent_tracuu;
    private Intent intent_danhmuc;
    private Intent intent_datlich1;
    private Intent intent_datlich2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        setContentView(R.layout.home2);

        initElement();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference appointmentsRef = db.collection("Booking");

        // Get current timestamp
        long currentTimeMillis = System.currentTimeMillis();

        long cutoff = currentTimeMillis - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS);

        appointmentsRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            String appointmentDateString = documentSnapshot.getString("appointmentDate");

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            try {
                                Date appointmentDate = sdf.parse(appointmentDateString);
                                if (appointmentDate != null && appointmentDate.getTime() < currentTimeMillis) {
                                    // Xóa document
                                    appointmentsRef.document(documentSnapshot.getId()).delete();
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    // Xử lý khi có lỗi xảy ra trong quá trình truy vấn
                });
    }

    private void initElement()
    {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menuItem_Home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menuItem_Booking).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menuItem_Profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuItem_Home) {
                    // Handle Profile item
                    viewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.menuItem_Booking) {
                    // Handle Booking item
                    viewPager.setCurrentItem(1);

                } else if (item.getItemId() == R.id.menuItem_Profile) {
                    // Handle Home item
                    viewPager.setCurrentItem(2);

                }
                return true;
            }
        });
    }
}