package com.example.trafficcasemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    EditText ed_name,ed_age,ed_gender,ed_plate,ed_location,ed_traffic_name,ed_rank,ed_casename;
    Button ed_btn;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout=findViewById(R.id.drawer_layout);
        ed_name=findViewById(R.id.id_name);
        ed_name=findViewById(R.id.id_student);
        ed_age=findViewById(R.id.id_age);
        ed_gender=findViewById(R.id.id_gender);
        ed_plate=findViewById(R.id.id_plate);
        ed_location=findViewById(R.id.id_location);
        ed_traffic_name=findViewById(R.id.id_traffic_name);
        ed_rank=findViewById(R.id.id_rank);
        ed_btn=findViewById(R.id.id_btn_submit);
        ed_casename=findViewById(R.id.id_casename);

        ed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ed_name.getText().toString();
                String age=ed_age.getText().toString();
                String gender=ed_gender.getText().toString();
                String plate=ed_plate.getText().toString();
                String case_name=ed_casename.getText().toString();
                String location=ed_location.getText().toString();
                String traffic_name=ed_traffic_name.getText().toString();
                String rank=ed_rank.getText().toString();

                Database db=new Database(getApplicationContext(),"traffic",null,1);
                db.addCase(name,age,gender,plate,case_name,location,traffic_name,rank);
                Toast.makeText(Home.this, "new records inserted success", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }
    public  void ClickDelete(View view){
        redirectActivity(this, Delete.class);
    }

    public void ClickEdit(View view){
        redirectActivity(this, EditCase.class);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }

    public void ClickDashboard(View view){
        redirectActivity(this, Dashboard.class);
    }

    public void ClickAbout(View view){
        redirectActivity(this, About.class);
    }

    public void ClickLogout(View view){
        //closeApp
        logout(this);

    }

    private static void logout(Activity activity) {

        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        builder.setTitle("Logout");

        builder.setMessage("Are you sure you want to logout?");
        //positive button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();

                System.exit(0);
            }
        });
        //negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //show dialog
        builder.show();

    }

    private static void redirectActivity(Activity activity,Class aClass) {

        Intent intent=new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //START ACTIVITY
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}