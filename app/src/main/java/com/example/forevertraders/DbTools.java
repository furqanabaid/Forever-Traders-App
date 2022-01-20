package com.example.forevertraders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DbTools extends SQLiteOpenHelper {
    public DbTools(Context context){
        super (context,"CartTitles",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTable="CREATE TABLE TITLES ("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cartTitle TEXT)";
        try{
        sqLiteDatabase.execSQL(sqlCreateTable);}
        catch (Exception e){
            e.getMessage();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addNewTitle(HashMap<String,String> value){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("_id",value.get("_id"));
        contentValues.put("cartTitle",value.get("cartTitle"));
        Log.d("ABC",value.get("cartTitle"));
        long i=db.insert("TITLES",null,contentValues);
        if (i!=-1){
            Log.d("TAG","Data Inserted Successfully");
        }
        else{
            Log.d("TAG","Data Not Inserted Successfully");
        }
    }

    public ArrayList<HashMap<String,String>> getAllTitles(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<HashMap<String,String>> titleList=new ArrayList<HashMap<String,String >>();
        String query="SELECT * FROM TITLES";
        Cursor cursor= db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> contact=new HashMap<String,String>();
                contact.put("_id",cursor.getString(0));
                contact.put("cartTitle",cursor.getString(1));
                titleList.add(contact);
            }
            while (cursor.moveToNext());
        }
       return titleList;
    }
}
