package com.yaniv.student.move;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;

import android.view.View;



public class Circle extends View
{
 Handler handler;
 boolean isFalling = true;
Context context;
double Y_a = 101 , X_a = 101 , R_a = 100 , adderY_a = 0, adderX_a = 2  , X_b = 250 , adderX_b = 10 , R_b = 50 , Y_b = 900 , FallAx = 20;
    Paint p = new Paint();

    public Circle(Context context)
    {
        super(context);
        this.context=context;
//        animationPic= BitmapFactory.decodeResource(getResources(), R.drawable.ball4);
//        back = BitmapFactory.decodeResource(getResources(),R.drawable.universe1);
//        back10 = BitmapFactory.decodeResource(getResources(),R.drawable.back10);
        //   ball = new Ball(50,50);

        handler = new Handler(new Handler.Callback()
        {
            @Override
            public boolean handleMessage(Message msg)
            {

                invalidate();
                return true;
            }
            });



    }
    public void StartMovment()
    {
        new Thread(new GameThead(handler)).start();
    }







    @Override
    protected  void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(X_a + R_a >= canvas.getWidth()  || X_a - R_a <= 0)
        {
            adderX_a = -1 * adderX_a;
        }
        if(Y_a + R_a >= canvas.getHeight())
        {

            isFalling = false;
        }
        if( Y_a - R_a <= 0)
        {
           isFalling = true;
        }
        if(isFalling)
        {
            adderY_a = Y_a / FallAx;
        }
        else {

                adderY_a = -1 * Y_a / FallAx;

        }
        if(X_b  <=  R_b || canvas.getWidth()  <= X_b + R_b)
        {
            adderX_b = -1 * adderX_b;
        }


        X_a = X_a + adderX_a;
        Y_a = Y_a + adderY_a;
        X_b += adderX_b;

       // double a =  (Math.atan((y/x)) - Math.atan(yb/xb));
       // a = Math.abs(a);     old method detacde teach
       // a = Math.cos(a);
       // double b = x*x + xb*xb + y*y + yb*yb - (r+rb)*(r+rb);
       // b = b / (2 * Math.sqrt((x*x + y*y) * (xb*xb + yb*yb)));
       // if(a <= b + 0.0095 && a >= b - 0.0095)
       // {
       //     x = y = 101;
       // }

        double d = (X_a - X_b)*(X_a - X_b) + (Y_a - Y_b)*(Y_a - Y_b);
        d = Math.sqrt(d);
        if(d - 1 < R_a + R_b)
        {

            isFalling = false;
            adderX_a += (X_a - X_b) / 20;
           // double k = 2 * Y_a * Y_b + R_a * R_a + 2 * R_a * R_b + R_b * R_b - Y_b * Y_b - Y_a * Y_a;
           // k = Math.sqrt(k);
           // double w = X_b - X_a;
           // if(X_a < X_b)
           // {
           //     X_a += w - k;
           // }
           // else {
            //    X_a += w + k;
           // }



        }
        FallAx  = FallAx - 0.0001 * (FallAx - 5);

        p.setColor(Color.RED);
        canvas.drawCircle((float)X_a , (float)Y_a , (float)R_a , new Paint());
        canvas.drawCircle((float)X_b , (float)Y_b , (float)R_b , p);

    }
}
