package net.sakuramilk.TweakGS3;

import net.sakuramilk.util.SystemCommand;

public class Config {
    public static final String TGS3_BACKUP_DIR = "/TweakGS3/backup";
    
    public static boolean checkDevice() {
        String model = SystemCommand.get_prop("ro.product.model");
        String device = SystemCommand.get_prop("ro.product.device");
        if ("SC-06D".equals(model) || "d2dcm".equals(model)) {
        	return true;
        }
        if ("SC-06D".equals(device) || "d2dcm".equals(device)) {
        	return true;
        }
        return false;
    }
}
