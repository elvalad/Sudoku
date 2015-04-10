package org.elvalad.sudoku;

import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CountActivity extends TabActivity {

    private GameSQLiteOpenHelper gameSQLiteOpenHelper;
    private SQLiteDatabase gamesql;
    private ArrayList<GameResult> easyList = new ArrayList<>();
    private ArrayList<GameResult> normalList = new ArrayList<>();
    private ArrayList<GameResult> hardList = new ArrayList<>();
    private ArrayList<GameResult> masterList = new ArrayList<>();
    private Button deleteButton;

    Comparator<GameResult> comparator = new Comparator<GameResult>(){
        public int compare(GameResult g1, GameResult g2) {
            if (g1.score != g2.score) {
                return g2.score - g1.score;
            } else {
                if (!g1.time.equals(g2.time)) {
                    return g1.time.compareTo(g2.time);
                } else {
                    return 0;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_count, tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("简单")
                .setContent(R.id.view1));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("普通")
                .setContent(R.id.view2));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("困难")
                .setContent(R.id.view3));
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("大师")
                .setContent(R.id.view4));

        gameSQLiteOpenHelper = new GameSQLiteOpenHelper(CountActivity.this);
        gamesql = gameSQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = gamesql.rawQuery("SELECT * FROM " + GameSQLiteOpenHelper.TABLE_NAME, null);
        if (cursor != null) {
            GameResult gameResult = new GameResult();
            int diffIndex = cursor.getColumnIndex(gameSQLiteOpenHelper.DIFFICULTY);
            int scoreIndex = cursor.getColumnIndex(gameSQLiteOpenHelper.SCORE);
            int timeIndex = cursor.getColumnIndex(gameSQLiteOpenHelper.TIME);

            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                gameResult.difficulty = cursor.getInt(diffIndex);
                gameResult.score = cursor.getInt(scoreIndex);
                gameResult.time = cursor.getString(timeIndex);
                if (gameResult.difficulty == 30) {
                    GameResult tmp = new GameResult();
                    tmp.difficulty = gameResult.difficulty;
                    tmp.score = gameResult.score;
                    tmp.time = gameResult.time;
                    easyList.add(tmp);
                    Collections.sort(easyList, comparator);
                } else if (gameResult.difficulty == 50) {
					GameResult tmp = new GameResult();
                    tmp.difficulty = gameResult.difficulty;
                    tmp.score = gameResult.score;
                    tmp.time = gameResult.time;
                    normalList.add(gameResult);
                    Collections.sort(easyList, comparator);
                } else if (gameResult.difficulty == 70) {
					GameResult tmp = new GameResult();
                    tmp.difficulty = gameResult.difficulty;
                    tmp.score = gameResult.score;
                    tmp.time = gameResult.time;
                    hardList.add(gameResult);
                    Collections.sort(easyList, comparator);
                } else if (gameResult.difficulty == 90) {
					GameResult tmp = new GameResult();
                    tmp.difficulty = gameResult.difficulty;
                    tmp.score = gameResult.score;
                    tmp.time = gameResult.time;
                    masterList.add(gameResult);
                    Collections.sort(easyList, comparator);
                }
            }
        }

        ListView listView1 = (ListView) findViewById(R.id.view1);
        ListView listView2 = (ListView) findViewById(R.id.view2);
        ListView listView3 = (ListView) findViewById(R.id.view3);
        ListView listView4 = (ListView) findViewById(R.id.view4);

        List<HashMap<String, Object>> easyData = new ArrayList<>();
        List<HashMap<String, Object>> normalData = new ArrayList<>();
        List<HashMap<String, Object>> hardData = new ArrayList<>();
        List<HashMap<String, Object>> masterData = new ArrayList<>();

        for (int i = 0; i < easyList.size(); i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("rank", i + 1);
            item.put("score", easyList.get(i).score);
            item.put("time", easyList.get(i).time);
            easyData.add(item);
        }
        SimpleAdapter easyAdapter = new SimpleAdapter(CountActivity.this, easyData, R.layout.count_item,
                new String[] {"rank", "score", "time"}, new int[] {R.id.rank, R.id.score, R.id.time});
        listView1.setAdapter(easyAdapter);

        for (int i = 0; i < normalList.size(); i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("rank", i + 1);
            item.put("score", normalList.get(i).score);
            item.put("time", normalList.get(i).time);
            normalData.add(item);
        }
        SimpleAdapter normalAdapter = new SimpleAdapter(CountActivity.this, normalData, R.layout.count_item,
                new String[] {"rank", "score", "time"}, new int[] {R.id.rank, R.id.score, R.id.time});
        listView2.setAdapter(normalAdapter);

        for (int i = 0; i < hardList.size(); i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("rank", i + 1);
            item.put("score", hardList.get(i).score);
            item.put("time", hardList.get(i).time);
            hardData.add(item);
        }
        SimpleAdapter hardAdapter = new SimpleAdapter(CountActivity.this, hardData, R.layout.count_item,
                new String[] {"rank", "score", "time"}, new int[] {R.id.rank, R.id.score, R.id.time});
        listView3.setAdapter(hardAdapter);

        for (int i = 0; i < masterList.size(); i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("rank", i + 1);
            item.put("score", masterList.get(i).score);
            item.put("time", masterList.get(i).time);
            masterData.add(item);
        }
        SimpleAdapter masterAdapter = new SimpleAdapter(CountActivity.this, masterData, R.layout.count_item,
                new String[] {"rank", "score", "time"}, new int[] {R.id.rank, R.id.score, R.id.time});
        listView4.setAdapter(masterAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(CountActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count, menu);
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
