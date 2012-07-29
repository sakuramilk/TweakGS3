/*
 * Copyright (C) 2011-2012 sakuramilk <c.sakuramilk@gmail.com>
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

package net.sakuramilk.TweakGS3.CpuControl;

import java.util.ArrayList;

import net.sakuramilk.TweakGS3.Common.Misc;
import net.sakuramilk.TweakGS3.Common.SettingManager;
import net.sakuramilk.TweakGS3.Common.SysFs;
import android.content.Context;

public class CpuVoltageSetting extends SettingManager {

    public static final String KEY_CPU_VOLT_ROOT_PREF = "root_pref";
    public static final String KEY_CPU_VOLT_CTRL_BASE = "cpu_vc_";

    private final SysFs mSysFsVddLevels = new SysFs("/sys/devices/system/cpu/cpufreq/vdd_table/vdd_levels");

    public CpuVoltageSetting(Context context) {
        super(context);
    }

    public boolean isEnableVoltageControl() {
        return mSysFsVddLevels.exists();
    }

    public String[] getVoltageTable() {
        ArrayList<String> ret = new ArrayList<String>();
        String[] values = mSysFsVddLevels.readMuitiLine(mRootProcess);
        for (String value : values) {
            String v = value.replace(" ", "");
            String voltage = v.substring(v.indexOf(":") + 1);
            ret.add(voltage);
        }
        return ret.toArray(new String[0]);
    }

    public void setVoltageTable(String[] voltageTable) {
        CpuControlSetting cpuSetting = new CpuControlSetting(mContext);
        String[] availableFrequencies = cpuSetting.getAvailableFrequencies();
        for (int i = 0; i < voltageTable.length; i++) {
            String value = availableFrequencies[i] + " " + voltageTable[i];
            mSysFsVddLevels.write(value, mRootProcess);
        }
    }

    public String loadVoltage(String key) {
        return getStringValue(key);
    }

    @Override
    public void setOnBoot() {
        String[] voltTable = getVoltageTable();
        if (voltTable != null) {
        	CpuControlSetting cpuSetting = new CpuControlSetting(mContext);
        	String[] availableFrequencies = cpuSetting.getAvailableFrequencies();
        	for (int i = 0; i < voltTable.length; i++) {
        		String freq = String.valueOf(Integer.parseInt(availableFrequencies[i]) / 1000);
        		String volt = loadVoltage(KEY_CPU_VOLT_CTRL_BASE + freq);
        		if (!Misc.isNullOfEmpty(volt)) {
        			voltTable[i] = volt;
        		}
        	}
        	setVoltageTable(voltTable);
        }
    }

    @Override
    public void setRecommend() {
        // noop
    }

    @Override
    public void reset() {
        String[] voltTable = getVoltageTable();
        if (voltTable != null) {
            CpuControlSetting cpuSetting = new CpuControlSetting(mContext);
            String[] availableFrequencies = cpuSetting.getAvailableFrequencies();
            for (int i = 0; i < voltTable.length; i++) {
                String freq = String.valueOf(Integer.parseInt(availableFrequencies[i]) / 1000);
                clearValue(KEY_CPU_VOLT_CTRL_BASE + freq);
            }
        }
    }
}
