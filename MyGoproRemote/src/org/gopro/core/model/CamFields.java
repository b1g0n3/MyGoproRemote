package org.gopro.core.model;

import java.io.Serializable;
import java.util.HashMap;

public class CamFields
    implements Serializable
{

    public CamFields()
    {
        photos_available = 0L;
        photos_oncard = 0L;
        video_minavailable = 0L;
        video_oncard = 0L;
        playbackpos = 0L;
        mVideoLoopCounter = -1L;
        ip = "";
        version = "";
        camname = "";
        playbackstart = "";
        fov = "";
        timelaspe = "";
        locate = 0;
        busy = 0;
        usbmode = 0;
        microphone = 0;
        livepreview = 0;
        beepsound = 0;
        shutter = 0;
        protocol = 0;
        mode = 0;
        ondefault = 0;
        exposure = 0;
        autopower = 0;
        vidres = 0;
        audioinput = 0;
        playmode = 0;
        model = 0;
        majorversion = 0;
        minorversion = 0;
        buildversion = 0;
        ledblink = 0;
        battery = 0;
        photoResolution = 0;
        protuneSetting = 0;
        previewValid = 0;
        mFieldOfView = 0;
        mHlsSegmentSize = -1;
        mBurstMode = -1;
        mBurstRate = 0;
        mContinuousShot = -1;
        mWhiteBalance = -1;
        mBracketingMode = -1;
        mPhotoInVideo = -1;
        mLoopingMode = -1;
        mSlideshowSetting = -1;
        mBroadcastSettings = -1;
        mTimeLapseStyle = -1;
        mExternalBatteryLevelPercent = -1;
        mLcdVolume = -1;
        mLcdBrightness = -1;
        mLcdSleepTimer = -1;
        mVideoResolutionMav = -1;
        mFramesPerSecond = -1;
        onebutton = false;
        onscreen = false;
        ntsc = false;
        livefeed = false;
        updown = false;
        mLocateActive = false;
        mCameraBusy = false;
        mProtuneEnabled = false;
        mPreviewActive = false;
    }

    public CamFields(String s, int i)
    {
        photos_available = 0L;
        photos_oncard = 0L;
        video_minavailable = 0L;
        video_oncard = 0L;
        playbackpos = 0L;
        mVideoLoopCounter = -1L;
        ip = "";
        version = "";
        camname = "";
        playbackstart = "";
        fov = "";
        timelaspe = "";
        locate = 0;
        busy = 0;
        usbmode = 0;
        microphone = 0;
        livepreview = 0;
        beepsound = 0;
        shutter = 0;
        protocol = 0;
        mode = 0;
        ondefault = 0;
        exposure = 0;
        autopower = 0;
        vidres = 0;
        audioinput = 0;
        playmode = 0;
        model = 0;
        majorversion = 0;
        minorversion = 0;
        buildversion = 0;
        ledblink = 0;
        battery = 0;
        photoResolution = 0;
        protuneSetting = 0;
        previewValid = 0;
        mFieldOfView = 0;
        mHlsSegmentSize = -1;
        mBurstMode = -1;
        mBurstRate = 0;
        mContinuousShot = -1;
        mWhiteBalance = -1;
        mBracketingMode = -1;
        mPhotoInVideo = -1;
        mLoopingMode = -1;
        mSlideshowSetting = -1;
        mBroadcastSettings = -1;
        mTimeLapseStyle = -1;
        mExternalBatteryLevelPercent = -1;
        mLcdVolume = -1;
        mLcdBrightness = -1;
        mLcdSleepTimer = -1;
        mVideoResolutionMav = -1;
        mFramesPerSecond = -1;
        onebutton = false;
        onscreen = false;
        ntsc = false;
        livefeed = false;
        updown = false;
        mLocateActive = false;
        mCameraBusy = false;
        mProtuneEnabled = false;
        mPreviewActive = false;
        ip = s;
        port = i;
    }

    public int getAudioinput()
    {
        return audioinput;
    }

    public int getAutopower()
    {
        return autopower;
    }

    public int getBattery()
    {
        return battery;
    }

    public int getBeepSound()
    {
        return beepsound;
    }

    public int getBracketingMode()
    {
        return mBracketingMode;
    }

    public int getBroadcastSettings()
    {
        return mBroadcastSettings;
    }

    public int getBuildVersion()
    {
        return buildversion;
    }

    public int getBurstMode()
    {
        return mBurstMode;
    }

    public int getBurstRate()
    {
        return mBurstRate;
    }

    public int getBusy()
    {
        return busy;
    }

    public String getCamname()
    {
        return camname;
    }

    public int getContinuousShot()
    {
        return mContinuousShot;
    }

    public int getExposure()
    {
        return exposure;
    }

    public int getExternalBatteryLevelPercent()
    {
        return mExternalBatteryLevelPercent;
    }

    public int getFieldOfView()
    {
        return mFieldOfView;
    }

    public int getFov()
    {
        return Integer.parseInt(fov);
    }

    public int getFramesPerSecond()
    {
        return mFramesPerSecond;
    }

    public int getHlsSegmentSize()
    {
        return mHlsSegmentSize;
    }

    public String getIp()
    {
        return ip;
    }

    public int getLcdBrightness()
    {
        return mLcdBrightness;
    }

    public int getLcdSleepTimer()
    {
        return mLcdSleepTimer;
    }

    public int getLcdVolume()
    {
        return mLcdVolume;
    }

    public int getLedblink()
    {
        return ledblink;
    }

    public int getLivePreview()
    {
        return livepreview;
    }

    public Boolean getLivefeed()
    {
        return Boolean.valueOf(livefeed);
    }

    public int getLocate()
    {
        return locate;
    }

    public int getLoopingMode()
    {
        return mLoopingMode;
    }

    public int getMajorVersion()
    {
        return majorversion;
    }

    public int getMicrophone()
    {
        return microphone;
    }

    public int getMicrophoneMode()
    {
        return microphone;
    }

    public int getMinorVersion()
    {
        return minorversion;
    }

    public int getMinorversion()
    {
        return minorversion;
    }

    public int getMode()
    {
        return mode;
    }

    public int getModel()
    {
        return model;
    }

    public Boolean getNtsc()
    {
        return Boolean.valueOf(ntsc);
    }

    public int getOndefault()
    {
        return ondefault;
    }

    public Boolean getOnebutton()
    {
        return Boolean.valueOf(onebutton);
    }

    public Boolean getOnscreen()
    {
        return Boolean.valueOf(onscreen);
    }

    public int getPhotoInVideo()
    {
        return mPhotoInVideo;
    }

    public int getPhotoResolution()
    {
        return photoResolution;
    }

    public long getPhotosAvailable()
    {
        return photos_available;
    }

    public long getPhotosOncard()
    {
        return photos_oncard;
    }

    public long getPlaybackPos()
    {
        return playbackpos;
    }

    public String getPlaybackStart()
    {
        return playbackstart;
    }

    public int getPlaymode()
    {
        return playmode;
    }

    public int getPort()
    {
        return port;
    }

    public int getPreviewValid()
    {
        return previewValid;
    }

    public int getProtocol()
    {
        return protocol;
    }

    public int getProtuneSetting()
    {
        return protuneSetting;
    }

    public HashMap getSettingsBag()
    {
        return mSettingsBag;
    }

    public int getShutter()
    {
        return shutter;
    }

    public int getSlideshowSetting()
    {
        return mSlideshowSetting;
    }

    public int getTimeLapse()
    {
        return timeLapse;
    }

    public int getTimeLapseStyle()
    {
        return mTimeLapseStyle;
    }

    public String getTimelaspe()
    {
        return timelaspe;
    }

    public Boolean getUpdown()
    {
        return Boolean.valueOf(updown);
    }

    public int getUsbMode()
    {
        return usbmode;
    }

    public String getVersion()
    {
        return version;
    }

    public long getVideoAvailable()
    {
        return video_minavailable;
    }

    public long getVideoLoopCounter()
    {
        return mVideoLoopCounter;
    }

    public long getVideoOncard()
    {
        return video_oncard;
    }

    public int getVideoResolutionMav()
    {
        return mVideoResolutionMav;
    }

    public int getVidres()
    {
        return vidres;
    }

    public int getWhiteBalance()
    {
        return mWhiteBalance;
    }

    public boolean isBombieAttached()
    {
        return mBombieAttached;
    }

    public boolean isBroadcasting()
    {
        return mBroadcasting;
    }

    public boolean isCameraBusy()
    {
        return mCameraBusy;
    }

    public boolean isLcdAttached()
    {
        return mLcdAttached;
    }

    public boolean isLocateActive()
    {
        return mLocateActive;
    }

    public boolean isPreviewActive()
    {
        return mPreviewActive;
    }

    public boolean isPreviewAvailable()
    {
        return mPreviewAvailable;
    }

    public boolean isProtuneEnabled()
    {
        return mProtuneEnabled;
    }

    public boolean isUploading()
    {
        return mUploading;
    }

    public void setAudioinput(int i)
    {
        audioinput = i;
    }

    public void setAutopower(int i)
    {
        autopower = i;
    }

    public void setBattery(int i)
    {
        battery = i;
    }

    public void setBeepSound(int i)
    {
        beepsound = i;
    }

    public void setBombieAttached(boolean flag)
    {
        mBombieAttached = flag;
    }

    public void setBracketingMode(int i)
    {
        mBracketingMode = i;
    }

    public void setBroadcastSettings(int i)
    {
        mBroadcastSettings = i;
    }

    public void setBroadcasting(boolean flag)
    {
        mBroadcasting = flag;
    }

    public void setBuildVersion(int i)
    {
        buildversion = i;
    }

    public void setBurstMode(int i)
    {
        mBurstMode = i;
    }

    public void setBurstRate(int i)
    {
        mBurstRate = i;
    }

    public void setBusy(int i)
    {
        busy = i;
    }

    public void setCameraBusy(boolean flag)
    {
        mCameraBusy = flag;
    }

    public void setCamname(String s)
    {
        camname = s;
    }

    public void setContinuousShot(int i)
    {
        mContinuousShot = i;
    }

    public void setExposure(int i)
    {
        exposure = i;
    }

    public void setExternalBatteryLevelPercent(int i)
    {
        mExternalBatteryLevelPercent = i;
    }

    public void setFieldOfView(int i)
    {
        mFieldOfView = i;
    }

    public void setFov(String s)
    {
        fov = s;
    }

    public void setFramesPerSecond(int i)
    {
        mFramesPerSecond = i;
    }

    public void setHlsSegmentSize(int i)
    {
        mHlsSegmentSize = i;
    }

    public void setIp(String s)
    {
        ip = s;
    }

    public void setLcdAttached(boolean flag)
    {
        mLcdAttached = flag;
    }

    public void setLcdBrightness(int i)
    {
        mLcdBrightness = i;
    }

    public void setLcdSleepTimer(int i)
    {
        mLcdSleepTimer = i;
    }

    public void setLcdVolume(int i)
    {
        mLcdVolume = i;
    }

    public void setLedblink(int i)
    {
        ledblink = i;
    }

    public void setLivePreview(int i)
    {
        livepreview = i;
    }

    public void setLivefeed(Boolean boolean1)
    {
        livefeed = boolean1.booleanValue();
    }

    public void setLocate(int i)
    {
        locate = i;
    }

    public void setLocateActive(boolean flag)
    {
        mLocateActive = flag;
    }

    public void setLoopingMode(int i)
    {
        mLoopingMode = i;
    }

    public void setMajorVersion(int i)
    {
        majorversion = i;
    }

    public void setMicrophone(int i)
    {
        microphone = i;
    }

    public void setMicrophoneMode(int i)
    {
        microphone = i;
    }

    public void setMinorVersion(int i)
    {
        minorversion = i;
    }

    public void setMinorversion(int i)
    {
        minorversion = i;
    }

    public void setMode(int i)
    {
        mode = i;
    }

    public void setModel(int i)
    {
        model = i;
    }

    public void setNtsc(Boolean boolean1)
    {
        ntsc = boolean1.booleanValue();
    }

    public void setOndefault(int i)
    {
        ondefault = i;
    }

    public void setOnebutton(Boolean boolean1)
    {
        onebutton = boolean1.booleanValue();
    }

    public void setOnscreen(Boolean boolean1)
    {
        onscreen = boolean1.booleanValue();
    }

    public void setPhotoInVideo(int i)
    {
        mPhotoInVideo = i;
    }

    public void setPhotoResolution(int i)
    {
        photoResolution = i;
    }

    public void setPhotosAvailable(long l)
    {
        photos_available = l;
    }

    public void setPhotosOncard(long l)
    {
        photos_oncard = l;
    }

    public void setPlaybackPos(long l)
    {
        playbackpos = l;
    }

    public void setPlaybackStart(String s)
    {
        playbackstart = s;
    }

    public void setPlaymode(int i)
    {
        playmode = i;
    }

    public void setPort(int i)
    {
        port = i;
    }

    public void setPreviewActive(boolean flag)
    {
        mPreviewActive = flag;
    }

    public void setPreviewAvailable(boolean flag)
    {
        mPreviewAvailable = flag;
    }

    public void setPreviewValid(int i)
    {
        previewValid = i;
    }

    public void setProtocol(int i)
    {
        protocol = i;
    }

    public void setProtuneEnabled(boolean flag)
    {
        mProtuneEnabled = flag;
    }

    public void setProtuneSetting(int i)
    {
        protuneSetting = i;
    }

    public void setSettingsBag(HashMap hashmap)
    {
        mSettingsBag = hashmap;
    }

    public void setShutter(int i)
    {
        shutter = i;
    }

    public void setSlideshowSetting(int i)
    {
        mSlideshowSetting = i;
    }

    public void setTimeLapse(int i)
    {
        timeLapse = i;
    }

    public void setTimeLapseStyle(int i)
    {
        mTimeLapseStyle = i;
    }

    public void setTimelaspe(String s)
    {
        timelaspe = s;
    }

    public void setUpdown(Boolean boolean1)
    {
        updown = boolean1.booleanValue();
    }

    public void setUploading(boolean flag)
    {
        mUploading = flag;
    }

    public void setUsbMode(int i)
    {
        usbmode = i;
    }

    public void setVersion(String s)
    {
        version = s;
    }

    public void setVideoAvailable(long l)
    {
        video_minavailable = l;
    }

    public void setVideoLoopCounter(long l)
    {
        mVideoLoopCounter = l;
    }

    public void setVideoOncard(long l)
    {
        video_oncard = l;
    }

    public void setVideoResolutionMav(int i)
    {
        mVideoResolutionMav = i;
    }

    public void setVidres(int i)
    {
        vidres = i;
    }

    public void setWhiteBalance(int i)
    {
        mWhiteBalance = i;
    }

    private static final long serialVersionUID = 0xd2ff3210edbf2799L;
    private int audioinput;
    private int autopower;
    private int battery;
    private int beepsound;
    private int buildversion;
    private int busy;
    private String camname;
    private int exposure;
    private String fov;
    private String ip;
    private int ledblink;
    private boolean livefeed;
    private int livepreview;
    private int locate;
    private boolean mBombieAttached;
    private int mBracketingMode;
    private int mBroadcastSettings;
    private boolean mBroadcasting;
    private int mBurstMode;
    private int mBurstRate;
    private boolean mCameraBusy;
    private int mContinuousShot;
    private int mExternalBatteryLevelPercent;
    private int mFieldOfView;
    private int mFramesPerSecond;
    private int mHlsSegmentSize;
    private boolean mLcdAttached;
    private int mLcdBrightness;
    private int mLcdSleepTimer;
    private int mLcdVolume;
    private boolean mLocateActive;
    private int mLoopingMode;
    private int mPhotoInVideo;
    private boolean mPreviewActive;
    private boolean mPreviewAvailable;
    private boolean mProtuneEnabled;
    private HashMap mSettingsBag;
    private int mSlideshowSetting;
    private int mTimeLapseStyle;
    private boolean mUploading;
    private long mVideoLoopCounter;
    private int mVideoResolutionMav;
    private int mWhiteBalance;
    private int majorversion;
    private int microphone;
    private int minorversion;
    private int mode;
    private int model;
    private boolean ntsc;
    private int ondefault;
    private boolean onebutton;
    private boolean onscreen;
    private int photoResolution;
    private long photos_available;
    private long photos_oncard;
    private long playbackpos;
    private String playbackstart;
    private int playmode;
    private int port;
    private int previewValid;
    private int protocol;
    private int protuneSetting;
    private int shutter;
    private int timeLapse;
    private String timelaspe;
    private boolean updown;
    private int usbmode;
    private String version;
    private long video_minavailable;
    private long video_oncard;
    private int vidres;
}
