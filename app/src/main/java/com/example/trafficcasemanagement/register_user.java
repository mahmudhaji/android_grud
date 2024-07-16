package com.example.trafficcasemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register_user extends AppCompatActivity {
    EditText edUsername,edEmail,edPass1,edPass2;
    Button buttonLogin,buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        edUsername=findViewById(R.id.id_et_username);
        edEmail=findViewById(R.id.id_et_email);
        edPass1=findViewById(R.id.id_et_pass1);
        edPass2=findViewById(R.id.id_et_pass2);
        buttonLogin=findViewById(R.id.btn_submit);
        buttonRegister=findViewById(R.id.idbtn_signup);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPass1.getText().toString();
                String confirm=edPass2.getText().toString();
                Database db=new Database(getApplicationContext(),"traffic",null,1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(register_user.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
//                    if password are the same
                    if(password.compareTo(confirm )==0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(register_user.this, "Record inserted success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(register_user.this, MainActivity.class));

                        }else {
                            Toast.makeText(register_user.this, "password must contain character ddigit nd special", Toast.LENGTH_SHORT).show();
                        }

                    }else{
//                        if passwords are not the same
                        Toast.makeText(register_user.this, "password dont match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhre){
        int f1=0,f2=0,f3=0;
        if (passwordhre.length()<8){
            return false;
        }else{
            for (int p=0; p<passwordhre.length();p++){
                if (Character.isLetter(passwordhre.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwordhre.length();r++){
                if (Character.isDigit(passwordhre.charAt(r))){
                    f2=1;
                }
            }
            for (int s=0;s<passwordhre.length();s++){
                char c=passwordhre.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if (f1==1 &&f2==1 &&f3==1)
                return true;
            return false;
        }
    }

    public void login(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}