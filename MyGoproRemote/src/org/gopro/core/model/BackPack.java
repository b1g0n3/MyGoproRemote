package org.gopro.core.model;


public class BackPack
{

    public BackPack()
    {
        model = 0;
        pass = 1;
        version = 0;
        majorversion = 0;
        minorversion = 0;
        buildversion = 0;
        wifimode = 0;
        networktype = 0;
        encryption = 0;
        power = 0;
        bluetooth = 0;
        autooff = 0;
        battery = 0;
        ssid = "";
        id = "";
        wifi = "";
        rev = "";
        name = "";
        ip = "";
        gps = "";
        pair = "";
        signal = "";
        notices = "";
        sdrcname = "";
    }

    public int getAutooff()
    {
        return autooff;
    }

    public int getBattery()
    {
        return battery;
    }

    public int getBluetooth()
    {
        return bluetooth;
    }

    public int getBootLoaderBuild()
    {
        return mBootLoaderBuild;
    }

    public int getBootLoaderMajor()
    {
        return mBootLoaderMajor;
    }

    public int getBootLoaderMinor()
    {
        return mBootLoaderMinor;
    }

    public int getBuildversion()
    {
        return buildversion;
    }

    public int getEncryption()
    {
        return encryption;
    }

    public String getGps()
    {
        return gps;
    }

    public String getId()
    {
        return id;
    }

    public String getIp()
    {
        return ip;
    }

    public int getMajorversion()
    {
        return majorversion;
    }

    public int getMinorversion()
    {
        return minorversion;
    }

    public int getModel()
    {
        return model;
    }

    public String getName()
    {
        return name;
    }

    public int getNetworkType()
    {
        return networktype;
    }

    public String getNotices()
    {
        return notices;
    }

    public String getPair()
    {
        return pair;
    }

    public int getPass()
    {
        return pass;
    }

    public int getPower()
    {
        return power;
    }

    public String getRev()
    {
        return rev;
    }

    public int getRevision()
    {
        return mRevision;
    }

    public String getSSID()
    {
        return ssid;
    }

    public String getSdrcName()
    {
        return sdrcname;
    }

    public String getSignal()
    {
        return signal;
    }

    public int getVersion()
    {
        return version;
    }

    public int getWifiMode()
    {
        return wifimode;
    }

    public String getWifimac()
    {
        return wifi;
    }

    public void setAutooff(int i)
    {
        autooff = i;
    }

    public void setBattery(int i)
    {
        battery = i;
    }

    public void setBluetooth(int i)
    {
        bluetooth = i;
    }

    public void setBootLoaderBuild(int i)
    {
        mBootLoaderBuild = i;
    }

    public void setBootLoaderMajor(int i)
    {
        mBootLoaderMajor = i;
    }

    public void setBootLoaderMinor(int i)
    {
        mBootLoaderMinor = i;
    }

    public void setBuildversion(int i)
    {
        buildversion = i;
    }

    public void setEncryption(int i)
    {
        encryption = i;
    }

    public void setGps(String s)
    {
        gps = s;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setIp(String s)
    {
        ip = s;
    }

    public void setMajorversion(int i)
    {
        majorversion = i;
    }

    public void setMinorversion(int i)
    {
        minorversion = i;
    }

    public void setModel(int i)
    {
        model = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setNetworkType(int i)
    {
        networktype = i;
    }

    public void setNotices(String s)
    {
        notices = s;
    }

    public void setPair(String s)
    {
        pair = s;
    }

    public void setPass(int i)
    {
        pass = i;
    }

    public void setPower(int i)
    {
        power = i;
    }

    public void setRev(String s)
    {
        rev = s;
    }

    public void setRevision(int i)
    {
        mRevision = i;
    }

    public void setSSID(String s)
    {
        ssid = s;
    }

    public void setSdrcName(String s)
    {
        sdrcname = s;
    }

    public void setSignal(String s)
    {
        signal = s;
    }

    public void setVersion(int i)
    {
        version = i;
    }

    public void setWifiMode(int i)
    {
        wifimode = i;
    }

    public void setWifimac(String s)
    {
        wifi = s;
    }

    private int autooff;
    private int battery;
    private int bluetooth;
    private int buildversion;
    private int encryption;
    private String gps;
    private String id;
    private String ip;
    private int mBootLoaderBuild;
    private int mBootLoaderMajor;
    private int mBootLoaderMinor;
    private int mRevision;
    private int majorversion;
    private int minorversion;
    private int model;
    private String name;
    private int networktype;
    private String notices;
    private String pair;
    private int pass;
    private int power;
    private String rev;
    private String sdrcname;
    private String signal;
    private String ssid;
    private int version;
    private String wifi;
    private int wifimode;
}
