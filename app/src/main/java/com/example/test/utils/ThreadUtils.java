package com.example.test.utils;
import android.os.Handler;

public class ThreadUtils {
    public static void runInThread(Runnable task){
        new Thread(task).start();
    }
    public static Handler mhandler =new Handler();
    public static void runInUiThread(Runnable task){
        mhandler.post(task);
    }
}