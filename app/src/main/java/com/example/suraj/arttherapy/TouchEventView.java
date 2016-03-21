package com.example.suraj.arttherapy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.content.Context;
import android.widget.Button;

import java.text.AttributedCharacterIterator;

public class TouchEventView extends View{

 public Paint paint= new Paint();
public  Path  path =new Path();//this is the brush


    public TouchEventView(Context context,AttributeSet attrs) {
        super(context, attrs);
        //for smooth drawing
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        this.setBackgroundColor(Color.BLACK);



    }
    //on draw is called repeatedly
    @Override
    protected void onDraw( Canvas canvas){

           // canvas.drawColor( 0, PorterDuff.Mode.CLEAR );
           // canvas.drawColor(Color.BLACK);
            // flag=0;

        canvas.drawPath(path, paint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
      float xPos=event.getX();
      float yPos=event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: path.moveTo(xPos,yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);//when dragging
                break;
            case MotionEvent.ACTION_UP:
                break;//nothing finger lifted
            default:
                return false;

        }
        //scedule repaint
        invalidate();
        return true;
    }

    public void clear ()
    {
        path = new Path();
        invalidate();
    }



}

