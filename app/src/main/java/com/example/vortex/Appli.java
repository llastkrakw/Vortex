package com.example.vortex;

import android.app.Application;

public class Appli extends Application {

    private  static Appli myInst;

    @Override
    public void onCreate() {
        super.onCreate();
        myInst=this;
    }

    public  static  synchronized Appli getInstance()
    {
        return  myInst;
    }

    public  void setConnectivity(Connectivity.ConnectivityListenner Listner)
    {
        Connectivity.connecti = Listner;
    }

}
