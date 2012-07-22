/*
 * Copyright (C) 2012 sakuramilk <c.sakuramilk@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sakuramilk.TweakGS3;

import java.io.File;

import net.sakuramilk.TweakGS3.Common.Constant;
import net.sakuramilk.TweakGS3.Common.Misc;
import net.sakuramilk.TweakGS3.Common.SystemCommand;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class MainPreferenceActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         addPreferencesFromResource(R.xml.main_pref);

        // check rooted
        if (!Misc.isSuperUserEnabled()) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(android.R.string.dialog_alert_title);
            alertDialogBuilder.setMessage(R.string.not_rooted_message);
            alertDialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialogBuilder.show();
        }

        // check build target
        if (Misc.getBuildTarget() == Misc.BUILD_TARGET_MULTI) {
            Preference pref = (Preference)findPreference("multi_boot_pref");
            pref.setEnabled(true);
            pref.setSelectable(true);
        } else {
            File xdataDir = new File("/xdata");
            if (xdataDir.exists()) {
                Preference pref = (Preference)findPreference("multi_boot_pref");
                pref.setEnabled(true);
                pref.setSelectable(true);
            }
        }

        // auto backup
        String backupDir = Misc.getSdcardPath(true) + Constant.TGS3_BACKUP_DIR;
        File file = new File(backupDir);
        if (!file.exists()) {
            String backupPath = backupDir + "/" + Misc.getDateString();
        	SystemCommand.partition_backup(backupPath);
        	Toast.makeText(this, getText(R.string.backup_completed) + "\n" + backupPath, Toast.LENGTH_LONG).show();
        }
    }
}
