package com.example.vortex.dataAcess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vortex.models.DTO.User;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String query = "CREATE TABLE "+Constants.TABLE_NAME+"("+
               Constants.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
               Constants.KEY_NAME+" TEXT,"+
               Constants.KEY_PASSWORD+" TEXT,"+
               Constants.KEY_EMAIL+" TEXT,"+
               Constants.KEY_PHONE+" TEXT,"+
               Constants.KEY_CREATE_AT+" LONG)";

       sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME, user.getUsername());
        values.put(Constants.KEY_PASSWORD, Encrypt.md5(user.getPassword()));
        values.put(Constants.KEY_EMAIL, user.getEmail());
        values.put(Constants.KEY_PHONE, user.getPhone());
        values.put(Constants.KEY_CREATE_AT, System.currentTimeMillis());

        db.insert(Constants.TABLE_NAME, null, values);
        db.close();
    }

    public User getUser(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID,
                Constants.KEY_NAME,
                Constants.KEY_PASSWORD,
                Constants.KEY_EMAIL,
                Constants.KEY_CREATE_AT
        }, Constants.KEY_ID+" =?", new String[]{
                String.valueOf(id)
        }, null, null, null, null );

        User user = new User();
        if(cursor != null){
            cursor.moveToFirst();

            user.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(Constants.KEY_PASSWORD)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(Constants.KEY_EMAIL)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(Constants.KEY_PHONE)));

            DateFormat format = DateFormat.getInstance();
            String date = format.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_CREATE_AT))));
            user.setCreateAt(date);

            cursor.close();
        }

        db.close();
        return user;
    }

    public List<User> getAll(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Constants.TABLE_NAME, null);

        List<User> list = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Constants.KEY_PASSWORD)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Constants.KEY_EMAIL)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(Constants.KEY_PHONE)));

                DateFormat format = DateFormat.getInstance();
                String date = format.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_CREATE_AT))));
                user.setCreateAt(date);

                list.add(user);
            }while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return list;
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID+" =?", new String[]{
                String.valueOf(id)
        });

        db.close();
    }

    public void update(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME, user.getUsername());
        values.put(Constants.KEY_PASSWORD, Encrypt.md5(user.getPassword()));
        values.put(Constants.KEY_EMAIL, user.getEmail());
        values.put(Constants.KEY_PHONE, user.getPhone());
        values.put(Constants.KEY_CREATE_AT, System.currentTimeMillis());

        db.update(Constants.TABLE_NAME, values, Constants.KEY_ID+ " =?", new String[]{
                String.valueOf(user.getId())
        });

        db.close();
    }

}
