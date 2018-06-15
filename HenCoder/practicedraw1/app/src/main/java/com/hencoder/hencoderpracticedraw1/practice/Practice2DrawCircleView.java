package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Paint p1 = new Paint();
    Paint p2 = new Paint();
    Paint p3 = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        p1.setColor(Color.BLACK);
        p1.setAntiAlias(true);
        canvas.drawCircle(230, 110, 100, p1);

        p2.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(460, 110, 100, p2);

        p3.setColor(Color.BLUE);
        p3.setStyle(Paint.Style.FILL);
        canvas.drawCircle(230, 220, 100, p3);



    }
}
