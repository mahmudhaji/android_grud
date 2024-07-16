package com.example.trafficcasemanagement;

import static com.example.trafficcasemanagement.R.id.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trafficcasemanagement.R.id;

public class MainActivity extends AppCompatActivity {
    Button btn_signup,btn_login;
    EditText ed_username,ed_pass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_username=findViewById(id_username);
        ed_pass1=findViewById(id_pass);
        btn_login=findViewById(id_btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=ed_username.getText().toString();
                String password=ed_pass1.getText().toString();
                Database db=new Database(getApplicationContext(),"traffic",null,1);


                if (username.length()==0 || password.length()==0){
                    Toast.makeText(MainActivity.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(username,password)==1){
                        Toast.makeText(MainActivity.this, "logoin success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }





    public void sign_up(View view) {
        Intent intent=new Intent(this,register_user.class);
        startActivity(intent);
    }
}