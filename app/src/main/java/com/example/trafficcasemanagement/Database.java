package com.example.trafficcasemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1="CREATE TABLE users(username text,email text,password text)";
        String qry2="CREATE TABLE cases(id integer primary key autoincrement, name text,age integer,gender text,plate text,case_name text,location text,traffic_name text,rank text)";
        db.execSQL(qry1);
        db.execSQL(qry2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCase(String name, String age,String gender,String plate,String case_name,String location,String traffic_name,String rank){
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("age",age);
        cv.put("gender",gender);
        cv.put("plate",plate);
        cv.put("case_name",case_name);
        cv.put("location",location);
        cv.put("traffic_name",traffic_name);
        cv.put("rank",rank);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cases",null,cv);
        db.close();

    }
    public Cursor  getAllCases(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM cases",null);
        return res;

    }
    public Integer delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=db.delete("cases","id=?",new String[]{id});
        return i;
    }
    public void register(String username,String email,String password){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);

        SQLiteDatabase db=getReadableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password=?",str);
        if (c.moveToFirst()){
            result=1;
        }
        return  result;
}
public boolean updateData(String id,String name,String age,String gender,String plate,String case_name,String location,String traffic_name,String rank){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
         cv.put("name",name);
         cv.put("age",age);
         cv.put("gender",gender);
         cv.put("plate",plate);
         cv.put("case_name",case_name);
         cv.put("location",location);
         cv.put("traffic_name",traffic_name);
         cv.put("rank",rank);
         int result=db.update("cases",cv,"id=?",new String[]{id});
            if (result>0)return true;return false;
}

}
