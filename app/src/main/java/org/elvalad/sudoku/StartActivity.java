package org.elvalad.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


public class StartActivity extends Activity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private GameView gameView;
    private TextView textView;
    private Chronometer timer;
    private Button pauseButton;
    private long timeWhenStopped = 0;
    private static int pauseOrStart;
    public static int DIFFICULTY = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        gameView = (GameView) findViewById(R.id.game_view);
        gameView.setSelectX(-1);
        gameView.setSelectY(-1);
        gameView.startGame(this.DIFFICULTY);
        button1 = (Button) findViewById(R.id.num_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "1", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(1, x, y);
                gameView.invalidate();
            }
        });

        button2 = (Button) findViewById(R.id.num_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "2", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(2, x, y);
                gameView.invalidate();
            }
        });

        button3 = (Button) findViewById(R.id.num_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "3", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(3, x, y);
                gameView.invalidate();
            }
        });

        button4 = (Button) findViewById(R.id.num_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "4", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(4, x, y);
                gameView.invalidate();
            }
        });

        button5 = (Button) findViewById(R.id.num_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "5", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(5, x, y);
                gameView.invalidate();
            }
        });

        button6 = (Button) findViewById(R.id.num_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "6", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(6, x, y);
                gameView.invalidate();
            }
        });

        button7 = (Button) findViewById(R.id.num_7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "7", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(7, x, y);
                gameView.invalidate();
            }
        });

        button8 = (Button) findViewById(R.id.num_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "8", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(8, x, y);
                gameView.invalidate();
            }
        });

        button9 = (Button) findViewById(R.id.num_9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "9", Toast.LENGTH_SHORT).show();
                int x = gameView.getSelectX();
                int y = gameView.getSelectY();
                gameView.drawNum(9, x, y);
                gameView.invalidate();
            }
        });

        textView = (TextView) findViewById(R.id.time_text);
        timer = (Chronometer) findViewById(R.id.chronometer);
        timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
        timer.start();

        pauseButton = (Button) findViewById(R.id.pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseOrStart++;
                if (pauseOrStart % 2 == 1) {
                    Toast.makeText(StartActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                    timeWhenStopped = timer.getBase() - SystemClock.elapsedRealtime();
                    timer.stop();
                } else {
                    Toast.makeText(StartActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                    timer.start();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            pauseOrStart = 0;
            timeWhenStopped = 0;
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
