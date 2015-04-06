package org.elvalad.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
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
    private Chronometer timer;
    private Button pauseButton;
    private long timeWhenStopped = 0;
    private static int pauseOrStart;
    public static int DIFFICULTY = 70;
    private Button backButton;
    private Button diffButton;
    private static final int DIFFICULTY_DIALOG_LIST = 1;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIFFICULTY_DIALOG_LIST:
                return new AlertDialog.Builder(StartActivity.this)
                    .setTitle(R.string.difficulty)
                    .setItems(R.array.select_dialog_items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    gameView.setSelectX(-1);
                                    gameView.setSelectY(-1);
                                    gameView.startGame(30);
                                    gameView.invalidate();
                                    timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                                    timer.start();
                                    diffButton.setText(R.string.easy);
                                    break;
                                case 1:
                                    gameView.setSelectX(-1);
                                    gameView.setSelectY(-1);
                                    gameView.startGame(50);
                                    gameView.invalidate();
                                    timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                                    timer.start();
                                    diffButton.setText(R.string.normal);
                                    break;
                                case 2:
                                    gameView.setSelectX(-1);
                                    gameView.setSelectY(-1);
                                    gameView.startGame(70);
                                    gameView.invalidate();
                                    timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                                    timer.start();
                                    diffButton.setText(R.string.hard);
                                    break;
                                case 3:
                                    gameView.setSelectX(-1);
                                    gameView.setSelectY(-1);
                                    gameView.startGame(90);
                                    gameView.invalidate();
                                    timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                                    timer.start();
                                    diffButton.setText(R.string.master);
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .create();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // …Ë÷√”Œœ∑≤ª–›√ﬂ
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
                    pauseButton.setText("Start");
                } else {
                    Toast.makeText(StartActivity.this, "Start", Toast.LENGTH_SHORT).show();
                    timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                    timer.start();
                    pauseButton.setText("Pause");
                }
            }
        });

        backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                pauseOrStart = 0;
                timeWhenStopped = 0;
                finish();
            }
        });

        diffButton = (Button) findViewById(R.id.difficulty);
        diffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIFFICULTY_DIALOG_LIST);
                timer.stop();
                pauseOrStart = 0;
                timeWhenStopped = 0;
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
