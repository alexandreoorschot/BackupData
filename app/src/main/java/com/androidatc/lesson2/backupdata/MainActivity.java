package com.androidatc.lesson2.backupdata;

import android.app.Activity;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private SharedPreferences prefs;
    private SharedPreferences.Editor edit;
    private BackupManager backupManager;
    private EditText enterData;
    private TextView showData;
    private Button save;
    private Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backupManager = new BackupManager(this);
        showData = (TextView) findViewById(R.id.load_data);
        enterData = (EditText) findViewById(R.id.enter_data);
        save = (Button) findViewById(R.id.save);
        show = (Button) findViewById(R.id.show);
        save.setOnClickListener(this);
        show.setOnClickListener(this);
        show.setEnabled(false);
        prefs = getSharedPreferences(BackupClass.PREFS_TEST, Context.MODE_PRIVATE);
        edit = prefs.edit();

        if (ShowData().length()> 0)
            show.setEnabled(true);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                SaveData("save", enterData.getText().toString());
                show.setEnabled(true);
                break;
            case R.id.show:
                showData.setText(ShowData());
                break;
        }
    }
    private void SaveData(String key, String value) {
        edit.putString(key, value);
        edit.commit();
        Log.d("Test", "Calling backup...");
        backupManager.dataChanged();
    }
    private String ShowData(){
        String savedData = prefs.getString("save", "");
        return savedData;
    }
}
