package com.yaniv.student.move;

import android.os.Handler;

public class GameThead implements Runnable {
    Handler handler;
     public GameThead(Handler handler)
     {
         this.handler = handler;
     }


    @Override
    public void run() {
        while (true)
        {

            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
