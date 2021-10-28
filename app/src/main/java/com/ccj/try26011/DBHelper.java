package com.ccj.try26011;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "Accounts.db";

    public DBHelper(Context context)
    {
        super(context, "Accounts.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase AccountDB) {
        AccountDB.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT, lastName TEXT, firstName TEXT, lastName TEXT, email TEXT, address TEXT, contactNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase AccountDB, int i, int i1) {
        AccountDB.execSQL("DROP TABLE IF EXISTS users");
    }

    //database input from registration
    public Boolean insertData(String u, String p, String l, String f, String e, String a, String cn){
        SQLiteDatabase AccountDB = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();
        contentVal.put("username", u);
        contentVal.put("password", p);
        contentVal.put("firstName", f);
        contentVal.put("lastName", l);
        contentVal.put("email", e);
        contentVal.put("address", a);
        contentVal.put("contactNumber", cn);
        long result = AccountDB.insert("users", null, contentVal);
        if (result==-1) return false;
        else return true;
    }

    public Boolean checkUsername(String u){
        SQLiteDatabase AccountDB = this.getWritableDatabase();
        Cursor cur=AccountDB.rawQuery("Select * FROM users where username=?", new String[]{u});
        if (cur.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUserAccount(String u, String p){
        SQLiteDatabase AccountDB = this.getWritableDatabase();
        Cursor cur=AccountDB.rawQuery("Select * FROM users where username=? AND password=?", new String[]{u,p});
        if (cur.getCount()>0) return true;
        else return false;
    }
}
