package org.elvalad.sudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2015/4/4.
 */
public class GameView extends View {
    private static int[] num = new int[81];
    public static int[] tmp = new int[81];
    private static boolean[] bits = new boolean[81];
    private static Random random = new Random();
    private final float GRID_LEFT = 1;
    private final float GRID_TOP = 1;
    public static int DIFFICULTY = 70;
    private float width;
    private float height;
    private float gridSize;
    public static int selectX;
    public static int selectY;
    private int number;

    public GameView(Context context) {
        super(context);
        if(!isInEditMode()) {
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            this.width = displayMetrics.widthPixels;
            this.height = displayMetrics.heightPixels;
            this.gridSize = (this.width) / 10;
            this.selectX = -1;
            this.selectY = -1;
            this.setBackgroundColor(getResources().getColor(R.color.black));
            this.tmp = generate(this.DIFFICULTY);
        }
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()) {
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            this.width = displayMetrics.widthPixels;
            this.height = displayMetrics.heightPixels;
            this.gridSize = (this.width) / 10;
            this.setBackgroundColor(getResources().getColor(R.color.black));
            this.tmp = generate(this.DIFFICULTY);
        }
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(!isInEditMode()) {
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            this.width = displayMetrics.widthPixels;
            this.height = displayMetrics.heightPixels;
            this.gridSize = (this.width) / 10;
            this.setBackgroundColor(getResources().getColor(R.color.black));
            //this.tmp = generate(this.DIFFICULTY);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint hilightPaint = new Paint();
        hilightPaint.setColor(getResources().getColor(R.color.green));
        hilightPaint.setStrokeWidth(4);

        Paint lightPaint = new Paint();
        lightPaint.setColor(getResources().getColor(R.color.paint));
        lightPaint.setStrokeWidth(2);

        Paint numPaint = new Paint();
        numPaint.setColor(getResources().getColor(R.color.blue));
        numPaint.setTextSize((float)(this.gridSize * 0.75));

        Paint errPaint = new Paint();
        errPaint.setColor(getResources().getColor(R.color.red));
        errPaint.setTextSize((float)(this.gridSize * 0.75));

        Paint selectPaint = new Paint();
        selectPaint.setColor(getResources().getColor(R.color.gray));

        for (int i = 0; i < 10; i++) {
            float x1 = this.GRID_LEFT;
            float y1 = this.GRID_TOP + i * this.gridSize;
            float x2 = this.GRID_LEFT + 9 * this.gridSize;
            float y2 = y1;
            canvas.drawLine(x1, y1, x2, y2, lightPaint);
        }

        for (int i = 0; i < 10; i++) {
            float x1 = this.GRID_LEFT + i * this.gridSize;
            float y1 = this.GRID_TOP;
            float x2 = x1;
            float y2 = this.GRID_TOP + 9 * this.gridSize;
            canvas.drawLine(x1, y1, x2, y2, lightPaint);
        }

        for (int i = 0; i < 10; i = i + 3)
        {
            float x1 = this.GRID_LEFT;
            float y1 = this.GRID_TOP + i * this.gridSize;
            float x2 = this.GRID_LEFT + 9 * this.gridSize;
            float y2 = y1;
            canvas.drawLine(x1, y1, x2, y2, hilightPaint);
        }

        for (int i = 0; i < 10; i = i + 3)
        {
            float x1 = this.GRID_LEFT + i * this.gridSize;
            float y1 = this.GRID_TOP;
            float x2 = x1;
            float y2 = this.GRID_TOP + 9 * this.gridSize;
            canvas.drawLine(x1, y1, x2, y2, hilightPaint);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                float x = this.GRID_LEFT + this.gridSize / 4 + j * this.gridSize;
                float y = this.GRID_TOP  + this.gridSize / 2 + this.gridSize / 4 + i * this.gridSize;
                if ((0 == tmp[i * 9 + j]) || (false == bits[i * 9 + j])) {
                    continue;
                }
                canvas.drawText(String.valueOf(this.tmp[i * 9 + j]), x, y, numPaint);
            }
        }

        //Log.d("X======---------->", String.valueOf(this.selectX));
        //Log.d("Y======---------->", String.valueOf(this.selectY));
        if ((this.getSelectX() >= 0) && (this.getSelectY() >= 0)) {
            float x = this.GRID_LEFT + this.gridSize / 4 + this.getSelectX() * this.gridSize;
            float y = this.GRID_TOP + this.gridSize / 2 + this.gridSize / 4 + this.getSelectY() * this.gridSize;
            //canvas.drawRect(x - this.gridSize / 4 + 2, y - this.gridSize / 2  - this.gridSize / 4 + 2, x + this.gridSize / 4 + this.gridSize / 2 - 2, y + this.gridSize / 4 - 2, selectPaint);

            switch (this.number) {
                case 1:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 1) {
                        canvas.drawText(String.valueOf(1), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(1), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 2:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 2) {
                        canvas.drawText(String.valueOf(2), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(2), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 3:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 3) {
                        canvas.drawText(String.valueOf(3), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(3), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 4:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 4) {
                        canvas.drawText(String.valueOf(4), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(4), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 5:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 5) {
                        canvas.drawText(String.valueOf(5), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(5), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 6:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 6) {
                        canvas.drawText(String.valueOf(6), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(6), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 7:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 7) {
                        canvas.drawText(String.valueOf(7), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(7), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 8:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 8) {
                        canvas.drawText(String.valueOf(8), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(8), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                case 9:
                    if (this.num[this.getSelectY() * 9 + this.getSelectX()] == 9) {
                        canvas.drawText(String.valueOf(9), x, y, numPaint);
                        this.bits[this.getSelectY() * 9 + this.getSelectX()] = true;
                    } else {
                        canvas.drawText(String.valueOf(9), x, y, errPaint);
                    }
                    this.number = 0;
                    break;
                default:
                    break;
            }
        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }

        this.selectX = (int)(event.getX() / this.gridSize);
        this.selectY = (int)(event.getY() / this.gridSize);

        //this.invalidate();

        if (this.bits[this.getSelectY() * 9 + this.getSelectX()]) {
            this.selectX = -1;
            this.selectY = -1;
        }

        //Log.d("X---------->", String.valueOf(this.selectX));
        //Log.d("Y---------->", String.valueOf(this.selectY));

        return true;
    }

    public void drawNum(int number, int i, int j) {
        if ((number < 0) || (number > 9) || i < 0 || j < 0) {
            return;
        }
        this.number = number;
        this.tmp[j * 9 + i] = this.number;
    }

    public void setSelectX(int x) {
        this.selectX = x;
    }

    public void setSelectY(int y) {
        this.selectY = y;
    }

    public int getSelectX() {
        return this.selectX;
    }

    public int getSelectY() {
        return this.selectY;
    }

    public void startGame(int difficulty) {
        this.DIFFICULTY = difficulty;
        this.tmp = generate(this.DIFFICULTY);

    }

    public static int[] generate(int difficulty) {
        int[] tmp = new int[81];
        for (int i = 0; i < 81; i++) {
            num[i] = 0;
            bits[i] = true;
        }
        solve(0);
        remove(difficulty);
        while(true) {
            System.arraycopy(num, 0, tmp, 0, num.length);
            if (solve(0)) {
                break;
            }
        }

        return tmp;
    }

    public int[] getNum() {
        return this.num;
    }

    public static boolean solve(int i) {
        /* 如果已经填满81个格子则返回true */
        if (i == 81) {
            return true;
        }
        /* 如果位置i已经填入了合适的值则递归产生下一个位置的值 */
        else if (num[i] != 0) {
            return solve(i + 1);
        }
        /* 如果恰好需要填入位置i的值 */
        else {
            /* 用数组randOrder存储每个位置可能产生的值，即为1~9 */
            int[] randOrder = new int[10];
            for (int val = 1; val < 10; val++) {
                randOrder[val] = val;
            }

            /* 将数组randOrder变为一个随机存储1~9的数组 */
            for (int val = 1; val < 10; val++) {
                int rand = random.nextInt(10);
                int tmp = randOrder[rand];
                randOrder[rand] = randOrder[val];
                randOrder[val] = tmp;
            }

            /* 在位置i随机填入一个值，并且判断是否有效 */
            for (int val = 1; val < 10; val++) {
                /* 如果在位置i填入的1~9中的某个随机数有效 */
                if (isLegal(i, randOrder[val])) {
                    /* 则将此随机值放入位置i */
                    num[i] = randOrder[val];
                    /* 探索i的下一个位置是否能正确填入，如果可以则返回true */
                    if (solve(i + 1)) {
                        return true;
                    }
                }
            }
        }

        /* 如果在位置i不能填入1~9中的任何值，则需要回溯 */
        num[i] = 0;
        return false;
    }

    private static boolean isLegal(int i, int value) {
        /* 判断行是否有效 */
        if (!isRowLegal(i, value)) {
            return false;
        }
        /* 判断列是否有效 */
        if (!isColLegal(i, value)) {
            return false;
        }
        /* 判断小矩阵是否有效 */
        if (!isSubLegal(i, value)) {
            return false;
        }

        return true;
    }

    private static boolean isRowLegal(int i, int value) {
        int row = i / 9;
        for (int val = 0; val < 9; val++) {
            if (value == num[row * 9 + val]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColLegal(int i, int value) {
        int col = i % 9;
        for (int val = 0; val < 9; val++) {
            if (value == num[val * 9 + col]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSubLegal(int i, int value) {
        int row = i / 9;
        int col = i % 9;
        int xOff = row / 3 * 3;
        int yOff = col / 3 * 3;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (value == num[(xOff + x) * 9 + yOff + y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void remove(int amount) {
        int j;
        for (int i = 0; i < amount; i++) {
            j = random.nextInt(81);
            num[j] = 0;
            bits[j] = false;
        }
    }
}
