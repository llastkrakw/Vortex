package com.example.vortex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connectivity extends BroadcastReceiver {

    public  static Connectivity.ConnectivityListenner connecti;

    public Connectivity() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo network= manager.getActiveNetworkInfo();


        boolean isConnected = network!=null && network.isConnectedOrConnecting();

        if(connecti !=null)
        {
            connecti.onNetWorkConnectionChanged(isConnected);
        }
    }

    // Create a method to check manually like click on button

    public  static  boolean isConnected()
    {
        ConnectivityManager cm=(ConnectivityManager) Appli
                .getInstance().
                        getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo network= cm.getActiveNetworkInfo();
        boolean NisConnected = network != null && network.isConnected();
        if (NisConnected) {
            //  if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
            if (network.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else if (network.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
            else
                return false;
        }
        return false;
    }
    public interface ConnectivityListenner
    {
        void  onNetWorkConnectionChanged(boolean isConnected);
    }
}
