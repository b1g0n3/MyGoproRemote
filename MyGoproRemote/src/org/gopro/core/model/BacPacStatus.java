package org.gopro.core.model;


public class BacPacStatus
{

    public BacPacStatus()
    {
    }

    public int getAutoPowerOff()
    {
        return mAutoPowerOff;
    }

    public int getBOSSReady()
    {
        return mBOSSReady;
    }

    public int getBacPacBattery()
    {
        return mBacPacBattery;
    }

    public int getBlueToothAudioChannel()
    {
        return mBlueToothAudioChannel;
    }

    public int getBlueToothMode()
    {
        return mBlueToothMode;
    }

    public int getCameraAttached()
    {
        return mCameraAttached;
    }

    public int getCameraI2CError()
    {
        return mCameraI2CError;
    }

    public int getCameraModel()
    {
        return mCameraModel;
    }

    public int getCameraPower()
    {
        return mCameraPower;
    }

    public int getCameraProtocolVersion()
    {
        return mCameraProtocolVersion;
    }

    public int getCameraReady()
    {
        return mCameraReady;
    }

    public int getFileServer()
    {
        return mFileServer;
    }

    public int getRSSI()
    {
        return mRSSI;
    }

    public int getShutterStatus()
    {
        return mShutterStatus;
    }

    public int getWifiLevel()
    {
        return mWifiLevel;
    }

    public int getWifiMode()
    {
        return mWifiMode;
    }

    public boolean isBOSSReady()
    {
        return mBOSSReady != 0;
    }

    public boolean isCameraAttached()
    {
        return mCameraAttached != 0;
    }

    public boolean isCameraPowerOn()
    {
        return mCameraPower != 0;
    }

    public boolean isCameraReady()
    {
        return mCameraReady != 0;
    }

    public void setAutoPowerOff(int i)
    {
        mAutoPowerOff = i;
    }

    public void setBOSSReady(int i)
    {
        mBOSSReady = i;
    }

    public void setBacPacBattery(int i)
    {
        mBacPacBattery = i;
    }

    public void setBlueToothAudioChannel(int i)
    {
        mBlueToothAudioChannel = i;
    }

    public void setBlueToothMode(int i)
    {
        mBlueToothMode = i;
    }

    public void setCameraAttached(int i)
    {
        mCameraAttached = i;
    }

    public void setCameraI2CError(int i)
    {
        mCameraI2CError = i;
    }

    public void setCameraModel(int i)
    {
        mCameraModel = i;
    }

    public void setCameraPower(int i)
    {
        mCameraPower = i;
    }

    public void setCameraProtocolVersion(int i)
    {
        mCameraProtocolVersion = i;
    }

    public void setCameraReady(int i)
    {
        mCameraReady = i;
    }

    public void setFileServer(int i)
    {
        mFileServer = i;
    }

    public void setRSSI(int i)
    {
        mRSSI = i;
    }

    public void setShutterStatus(int i)
    {
        mShutterStatus = i;
    }

    public void setWifiMode(int i)
    {
        mWifiMode = i;
    }

    private int mAutoPowerOff;
    private int mBOSSReady;
    private int mBacPacBattery;
    private int mBlueToothAudioChannel;
    private int mBlueToothMode;
    private int mCameraAttached;
    private int mCameraI2CError;
    private int mCameraModel;
    private int mCameraPower;
    private int mCameraProtocolVersion;
    private int mCameraReady;
    private int mFileServer;
    private int mRSSI;
    private int mShutterStatus;
    private int mWifiLevel;
    private int mWifiMode;
}
