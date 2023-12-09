package com.example.healthapp;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentDoctorChoosingActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            // code trong phương thức
        setContentView(R.layout.activity_appointment);
    }

    public void showPopup(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_khoa1);
        popup.show();
    }

    @Override
    /*
    public boolean onMenuItemClick (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item1:
                Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this, "Item 4 clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    } */
    public boolean onMenuItemClick(MenuItem item) {

        if(item.getItemId() == R.id.item1) {
            Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(item.getItemId() == R.id.item2){
            Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(item.getItemId() == R.id.item3){
            Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(item.getItemId() == R.id.item4){
            Toast.makeText(this, "Item 4 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }

    }



