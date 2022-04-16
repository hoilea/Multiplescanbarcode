package com.muchlish.scan_ai.activity.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.google.android.material.navigation.NavigationView;
import com.muchlish.scan_ai.R;
import com.muchlish.scan_ai.activity.listdownload.ListDownloadActivity;
import com.muchlish.scan_ai.activity.main.MainActivity;
import com.muchlish.scan_ai.activity.singlechooseactivity.SingleChooseActivity;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private PopupWindow mPopupWindow;
    protected PowerManager.WakeLock mWakeLock;

    private CardView singleactcv,multiactcv,listdownloadactcv;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        drawerLayout = findViewById(R.id.topLayout);
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        singleactcv = findViewById(R.id.singleactcv);
        multiactcv = findViewById(R.id.multiactcv);
        listdownloadactcv = findViewById(R.id.listdownloadactcv);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        singleactcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingleChooseActivity.class);
                startActivity(intent);
            }
        });
        multiactcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        listdownloadactcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListDownloadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
//            super.onBackPressed();
            finish();
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_view_list_download:
                Intent listdownload=new Intent(this, ListDownloadActivity.class);
                startActivity(listdownload);
                break;
            case R.id.single_scan:
                Intent singlescan=new Intent(this, SingleChooseActivity.class);
                startActivity(singlescan);
                break;
            case R.id.multi_scan:
                Intent multiscan=new Intent(this, MainActivity.class);
                startActivity(multiscan);
                break;
            case R.id.homescanai:
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setTitle("Information")
                        .setMessage("You are in Home Activity")
                        .setCancelable(false)
                        .setPositiveButton("Oke",null).create().show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);;
        return true;
    }
}
