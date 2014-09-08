package org.gopro.core.model;

import java.util.HashMap;

public class CameraSettings extends HashMap
{

    public CameraSettings()
    {
    }

    private String getValue(String s)
    {
        if(containsKey(s))
            return (String)get(s);
        else
            return "";
    }

    public String getMode()
    {
        return getValue("key_mode");
    }

    public String getPreview()
    {
        return getValue("key_preview");
    }

    public String getShutter()
    {
        return getValue("key_shutter");
    }

    public void putPreview(String s)
    {
        put("key_preview", s);
    }

    public void setMode(String s)
    {
        put("key_mode", s);
    }

    public void setShutter(String s)
    {
        put("key_shutter", s);
    }

    public static final String KEY_MODE = "key_mode";
    public static final String KEY_PREVIEW = "key_preview";
    public static final String KEY_SHUTTER = "key_shutter";
    private static final long serialVersionUID = 1L;
}
