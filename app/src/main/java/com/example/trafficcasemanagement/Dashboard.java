package com.example.trafficcasemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView ed_text;
    Button viewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout=findViewById(R.id.drawer_layout);
        ed_text=findViewById(R.id.id_result);
//        viewResult=findViewById(R.id.id_btn_view);

        Database db=new Database(getApplicationContext(),"traffic",null,1);
        Cursor res=db.getAllCases();
        StringBuffer stringBuffer=new StringBuffer();
        if (res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("case number: "+res.getString(0)+"\n");
                stringBuffer.append("Driver name: "+res.getString(1)+"\n");
                stringBuffer.append("age: "+res.getString(2)+"\n");
                stringBuffer.append("gender: "+res.getString(3)+"\n");
                stringBuffer.append("plate no: "+res.getString(4)+"\n");
                stringBuffer.append("Case name: "+res.getString(5)+"\n");
                stringBuffer.append("Location: "+res.getString(6)+"\n");
                stringBuffer.append("Traffic name: "+res.getString(7)+"\n");
                stringBuffer.append("Rank: "+res.getString(8)+"\n"+"\n");
            }
            ed_text.setText(stringBuffer.toString());
            Toast.makeText(this, "Data retrieve success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No data inserted", Toast.LENGTH_SHORT).show();
        }
    }
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
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
        redirectActivity(this, Home.class);
    }

    public  void ClickDelete(View view){
        redirectActivity(this, Delete.class);
    }

    public void ClickDashboard(View view){
        redirectActivity(this, Dashboard.class);
        recreate();
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