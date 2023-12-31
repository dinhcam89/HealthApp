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

       /*bottomNavigationView = findViewById(R.id.bottomNavigationView);*/


       /* replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.booking:
                    replaceFragment (new BookingFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new Profileragment());
                    break;

            }

            return true;
        });*/

       /* private void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentTransaction.replace(R.id.frame_layout, fragment);
            FragmentTransaction.commit();

        }*/
        /*private void replaceFragment(Fragment fragment){

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.frame_layout, fragment);

            fragmentTransaction.commit();

        }*/



        /*setContentView(R.layout.activity_main_menu);*/

//        Bacsi = findViewById(R.id.bacsi);
//        intent_bacsi = new Intent(this, DoctorChoosingActivity.class);
//      //  Tracuu = findViewById(R.id.tracuu);
//
//        datlich1 = findViewById(R.id.btn_book1);
//        intent_datlich1 = new Intent(this, AppointmentDateChoosingActivity.class);
//        datlich2 = findViewById(R.id.btn_book2);
//
//        //intent_tracuu = new Intent(this, TraCuuActivity.class);
//        //intent_danhmuc = new Intent(this, DanhMucActivity.class);
//        Bacsi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(intent_bacsi);
//            }
//        });
//
//        datlich1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(intent_datlich1);
//            }
//        });
//        datlich2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(intent_datlich1);
//            }
//        });
        initElement();
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