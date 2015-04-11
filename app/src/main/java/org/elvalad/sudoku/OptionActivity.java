package org.elvalad.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class OptionActivity extends PreferenceActivity {
    private Button deleteButton;
    private GameSQLiteOpenHelper gameSQLiteOpenHelper;
    private SQLiteDatabase gamesql;
    private Preference cleanHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_option);
        addPreferencesFromResource(R.layout.activity_option);

        cleanHistory = findPreference("cleanHistory");
        /*
        deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameSQLiteOpenHelper = new GameSQLiteOpenHelper(OptionActivity.this);
                gamesql = gameSQLiteOpenHelper.getWritableDatabase();
                gamesql.delete("sudoku_table", null, null);
                Toast.makeText(OptionActivity.this, "删除游戏记录", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == cleanHistory) {
            new AlertDialog.Builder(this)
                    .setTitle("清除游戏记录")
                    .setMessage("是否真的要清除历史记录？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameSQLiteOpenHelper = new GameSQLiteOpenHelper(OptionActivity.this);
                            gamesql = gameSQLiteOpenHelper.getWritableDatabase();
                            gamesql.delete("sudoku_table", null, null);
                            Toast.makeText(OptionActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(OptionActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
