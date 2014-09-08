package org.gopro.core;

import java.util.HashMap;

public class CameraOperations
{

    public CameraOperations()
    {
    }

    public static HashMap createOperationMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("camera/PT", Integer.valueOf(-1));
        hashmap.put("camera/DM", Integer.valueOf(-1));
        hashmap.put("camera/OB", Integer.valueOf(-1));
        hashmap.put("camera/VM", Integer.valueOf(-1));
        hashmap.put("camera/DS", Integer.valueOf(-1));
        hashmap.put("camera/LB", Integer.valueOf(-1));
        hashmap.put("camera/BS", Integer.valueOf(-1));
        hashmap.put("camera/AO", Integer.valueOf(-1));
        hashmap.put("camera/UP", Integer.valueOf(-1));
        hashmap.put("camera/EX", Integer.valueOf(-1));
        hashmap.put("camera/PN", Integer.valueOf(-1));
        hashmap.put("camera/LO", Integer.valueOf(-1));
        hashmap.put("camera/WB", Integer.valueOf(-1));
        hashmap.put("camera/VR", Integer.valueOf(-1));
        hashmap.put("camera/VV", Integer.valueOf(-1));
        hashmap.put("camera/FS", Integer.valueOf(-1));
        hashmap.put("camera/FV", Integer.valueOf(-1));
        hashmap.put("camera/PR", Integer.valueOf(-1));
        hashmap.put("camera/CS", Integer.valueOf(-1));
        hashmap.put("camera/BU", Integer.valueOf(-1));
        hashmap.put("camera/TI", Integer.valueOf(-1));
        hashmap.put("camera/TS", Integer.valueOf(-1));
        hashmap.put("camera/SS", Integer.valueOf(-1));
        hashmap.put("camera/BU", Integer.valueOf(-1));
        hashmap.put("camera/BR", Integer.valueOf(-1));
        hashmap.put("camera/PS", Integer.valueOf(-1));
        hashmap.put("camera/BX", Integer.valueOf(-1));
        hashmap.put("camera/LV", Integer.valueOf(-1));
        hashmap.put("camera/LN", Integer.valueOf(-1));
        hashmap.put("camera/LS", Integer.valueOf(-1));
        hashmap.put("video_loop_counter", Integer.valueOf(-1));
        hashmap.put("external_battery", Integer.valueOf(-1));
        hashmap.put("bombie_attached", Integer.valueOf(-1));
        hashmap.put("is_boradcasting", Integer.valueOf(-1));
        hashmap.put("lcd_attached", Integer.valueOf(-1));
        hashmap.put("is_uploading", Integer.valueOf(-1));
        return hashmap;
    }

    public static final String AUTO_POWER_OFF = "camera/AO";
    public static final String BRACKETING_MODE = "camera/BR";
    public static final String BROADCAST_SETTING = "camera/BX";
    public static final String BURST_MODE = "camera/BU";
    public static final String CONTINUOUS_SHOT = "camera/CS";
    public static final String DEFAULT_AT_POWER_UP = "camera/DM";
    public static final String EXTERNAL_BATTERY_LEVEL = "external_battery";
    public static final String FIELD_OF_VIEW = "camera/FV";
    public static final String FRAME_RATE = "camera/FS";
    public static final String HLS_SEGMENT_SIZE = "camera/SS";
    public static final String IS_BOMBIE_ATTACHED = "bombie_attached";
    public static final String IS_BROADCASTING = "is_boradcasting";
    public static final String IS_LCD_ATTACHED = "lcd_attached";
    public static final String IS_UPLOADING = "is_uploading";
    public static final String LCD_BRIGHTNESS = "camera/LN";
    public static final String LCD_SLEEP_TIMER = "camera/LS";
    public static final String LCD_VOLUME = "camera/LV";
    public static final String LED = "camera/LB";
    public static final String LOOPING_VIDEO_MODE = "camera/LO";
    public static final String NTSC_PAL = "camera/VM";
    public static final String ONE_BUTTON_MODE = "camera/OB";
    public static final String ON_SCREEN_DISPLAY_OSD = "camera/DS";
    public static final String PHOTO_IN_VIDEO = "camera/PN";
    public static final String PHOTO_RESOLUTION = "camera/PR";
    public static final String PROTUNE = "camera/PT";
    public static final String SLIDESHOW_SETTING = "camera/PS";
    public static final String SOUND = "camera/BS";
    public static final String SPOT_METER = "camera/EX";
    public static final String TIME_LAPSE = "camera/TI";
    public static final String TIME_LAPSE_STYLE = "camera/TS";
    public static final String UP_DOWN = "camera/UP";
    public static final String VIDEO_LOOP_COUNTER = "video_loop_counter";
    public static final String VIDEO_RESOLUTION = "camera/VV";
    public static final String VIDEO_RESOLUTION_AND_RATE = "camera/VR";
    public static final String WHITE_BALANCE = "camera/WB";
}
