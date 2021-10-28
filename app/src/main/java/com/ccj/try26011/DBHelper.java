package com.ccj.try26011;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "userAccounts.db";

    public DBHelper(Context context)
    {
        super(context, "userAccounts.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase userAccountDB) {
        userAccountDB.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT, firstName TEXT, lastName TEXT, email TEXT, address TEXT, contactNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase userAccountDB, int oldVersion, int newVersion) {
        userAccountDB.execSQL("DROP TABLE IF EXISTS users");
    }

    //database input from registration
    public Boolean insertData(String u, String p, String f, String l,  String e, String a, String cn){
        SQLiteDatabase userAccountDB = this.getWritableDatabase();

        ContentValues contentVal = new ContentValues();
        contentVal.put("username", u);
        contentVal.put("password", p);
        contentVal.put("firstName", f);
        contentVal.put("lastName", l);
        contentVal.put("email", e);
        contentVal.put("address", a);
        contentVal.put("contactNumber", cn);

        long result = userAccountDB.insert("users", null, contentVal);
        if (result==-1) return false;
        else return true;
    }

    public Boolean checkUsername(String u){
        SQLiteDatabase userAccountDB = this.getWritableDatabase();
        Cursor cur=userAccountDB.rawQuery("Select * FROM users where username=?", new String[]{u});
        if (cur.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUserAccount(String u, String p){
        SQLiteDatabase userAccountDB = this.getWritableDatabase();
        Cursor cur=userAccountDB.rawQuery("Select * FROM users where username=? AND password=?", new String[]{u,p});
        if (cur.getCount()>0) return true;
        else return false;
    }
}
