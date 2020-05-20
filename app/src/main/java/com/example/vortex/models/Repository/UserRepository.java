package com.example.vortex.models.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;
import com.example.vortex.models.SaveDatabase;

import java.util.List;

public class UserRepository {

    private String DB_NAME="USERDB";
    private SaveDatabase saveDatabase;
    Context context;

    public UserRepository(Context context) {
        this.context = context;
        saveDatabase= Room.databaseBuilder(context,SaveDatabase.class, DB_NAME).build();

        Toast.makeText(context,"Database Created .....",Toast.LENGTH_SHORT).show();
    }

   /* public void InsertTask(final User user)
    {
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... voids) {
                saveDatabase.userDao().InsertUser(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context,"User is inserted",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    /*public void CheckPassword(User user){
        if
    }

    public void ReadStudent(String email, String password)
    {
        new AsyncTask<String, Void, Void>()
        {
            List<User> user=null;
            @Override
            protected Void doInBackground(String... strings) {
                user= saveDatabase.userDAO().findByName(email, password);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(user==null)
                {
                    Toast.makeText(context,"Read Operation Failure",Toast.LENGTH_SHORT).show();
                }
                else if(user.size()==0)
                {
                    Toast.makeText(context,"User does noy exist",Toast.LENGTH_SHORT).show();
                }

                else{
                    CheckPassword(user.get(0));
                }
            }
        }.execute();
    }*/
}

