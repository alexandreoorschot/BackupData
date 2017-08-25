package com.androidatc.lesson2.backupdata;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import android.util.Log;

public class BackupClass extends BackupAgentHelper {
    static final String PREFS_TEST = "testprefs";
    static final String MY_PREFS_BACKUP_KEY = "myprefs";

    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PREFS_TEST);
        addHelper(MY_PREFS_BACKUP_KEY, helper);
        Log.d("Test", "Adding backupagent...");
    }
}
