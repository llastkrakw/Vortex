package com.example.vortex.models.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.example.vortex.models.DTO.User;
import com.example.vortex.models.SaveDatabase;

public class UserRepository {

    private String DB_NAME="UsersDB";
    private SaveDatabase saveDatabase;
    Context context;

    public UserRepository(Context context) {
        this.context = context;
        saveDatabase= Room.databaseBuilder(context,SaveDatabase.class, DB_NAME).build();

        Toast.makeText(context,"Database Created .....",Toast.LENGTH_SHORT).show();
    }

    public void InsertTask(final User user)
    {
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... voids) {
                saveDatabase.userDAO().InsertUser(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,"User is inserted",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}

