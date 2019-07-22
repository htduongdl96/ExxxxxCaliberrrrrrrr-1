package com.example.exxxxxcaliberrrrrrrr;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.widget.Chronometer;

class GameThread extends Thread {
    private boolean running;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;
    private MainActivity mainActivity = new MainActivity();

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder)  {
        this.gameSurface= gameSurface;
        this.surfaceHolder= surfaceHolder;

    }

    @Override
    public void run()  {
        long startTime = System.nanoTime();

        while(running) {
            Canvas canvas= null;
            try {
                // Lấy ra đối tượng Canvas và khóa nó lại.
                canvas = this.surfaceHolder.lockCanvas();


                // Đồng bộ hóa.
                synchronized (canvas)  {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            }catch(Exception e)  {

            } finally {
                if(canvas!= null)  {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
//            long now = System.nanoTime() ;
            // Thời gian cập nhập lại giao diện Game
//            // (Đổi từ Nanosecond ra milisecond).
//            long waitTime = (now - startTime)/1000000;
//            if(waitTime < 10)  {
//                waitTime= 10; // Millisecond.
//            }
//            System.out.print(" Wait Time="+ waitTime);
//////
//            try {
//                // Ngừng chương trình một chút.
//                this.sleep(waitTime);
//            } catch(InterruptedException e)  {
//
//            }
//            startTime = System.nanoTime();
//            System.out.print(".");
        }

        if(!running){
            mainActivity.ChangeActivity(mainActivity.getBaseContext());
        }
    }


    public void setRunning(boolean running)  {
        this.running= running;
    }
}