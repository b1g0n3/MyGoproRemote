package org.gopro.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.gopro.core.model.BacPacStatus;
import org.gopro.core.model.BackPack;
import org.gopro.core.model.CamFields;

public class GoProHelper {

	public static final boolean LOGGING_ENABLED = false;
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private String mCameraAddress = null;
	private final DefaultHttpClient mClient = newInstance();
	private String ipAddress;
	private Integer port;
	private String password;

	public GoProHelper() {
	}

	public GoProHelper(String ipAddress, Integer port, String password) {
		this();
		this.setIpAddress(ipAddress);
		this.setPort(port);
		this.setPassword(password);
		// this.mCamera = paramGoProCamera;
		this.mCameraAddress = ("http://" + ipAddress + ":" + port);
	}

	private void hexDump(byte[] paramArrayOfByte, String paramString) {
	}

	private boolean passFail(byte[] paramArrayOfByte) {
		boolean bool = false;
		if (paramArrayOfByte != null) {
			int i = paramArrayOfByte.length;
			bool = false;
			if (i > 0) {
				int j = paramArrayOfByte[0];
				bool = false;
				if (j == 0)
					bool = true;
			}
		}
		return bool;
	}

	private boolean sendCommand(Operations paramString) {
		try {
			sendGET(this.mCameraAddress + paramString.toString() + "?t=" + this.getToken());
			return true;
		} catch (Exception localException) {
		}
		return false;
	}

	public boolean deleteFilesOnSd() {
		return sendCommand(Operations.CAMERA_DA);
	}

	public boolean deleteLastFileOnSd() {
		return sendCommand(Operations.CAMERA_DL);
	}

	public int fromBoolean(boolean paramBoolean) {
		if (paramBoolean)
			return 1;
		return 0;
	}

	public String getBacPacPassword() {
		try {
			GoProProtocolParser localGoProProtocolParser =
					new GoProProtocolParser(sendGET(this.mCameraAddress + Operations.BACPAC_SD));
			byte[] arrayOfByte = new byte[1];
			arrayOfByte[0] = localGoProProtocolParser.extractByte();
			boolean bool = passFail(arrayOfByte);
			Object localObject = null;
			if (bool) {
				String str = localGoProProtocolParser.extractString();
				localObject = str;
			}
			return (String) localObject;
		} catch (Exception localException) {
		}
		return null;
	}

	public BackPack getBackPackInfo() throws Exception {
		BackPack localBackPack = new BackPack();
		GoProProtocolParser localGoProProtocolParser;
		try {
			byte[] arrayOfByte = sendGET("http://" + this.getIpAddress() + Operations.BACPAC_CV);
			localGoProProtocolParser = new GoProProtocolParser(arrayOfByte);
			if (localGoProProtocolParser.extractResultCode() != GoProProtocolParser.RESULT_IS_OK) {
				return null;
			}
		} catch (Exception localException) {
			throw new Exception("Fail to get backpack info", localException);
		}
		localBackPack.setVersion(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setModel(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setId(localGoProProtocolParser.extractFixedLengthString(2));
		localBackPack.setBootLoaderMajor(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setBootLoaderMinor(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setBootLoaderBuild(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setRevision(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setMajorversion(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setMinorversion(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setBuildversion(localGoProProtocolParser.extractUnsignedByte());
		localBackPack.setWifimac(localGoProProtocolParser.extractFixedLengthString(6));
		localBackPack.setSSID(localGoProProtocolParser.extractString());
		return localBackPack;
	}

	public BacPacStatus getBacpacStatus() throws Exception {
		BacPacStatus localBacPacStatus = new BacPacStatus();
		GoProProtocolParser localGoProProtocolParser;
		try {
			byte[] arrayOfByte =
					sendGET(this.mCameraAddress + Operations.BACPAC_SE + "?t=" + this.getToken());
			hexDump(arrayOfByte, "BacPac SE");
			localGoProProtocolParser = new GoProProtocolParser(arrayOfByte);
			if (localGoProProtocolParser.extractResultCode() != GoProProtocolParser.RESULT_IS_OK)
				return null;
		} catch (Exception localException) {
			throw localException;
		}
		localBacPacStatus.setBacPacBattery(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setWifiMode(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setBlueToothMode(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setRSSI(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setShutterStatus(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setAutoPowerOff(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setBlueToothAudioChannel(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setFileServer(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setCameraPower(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setCameraI2CError(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setCameraReady(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setCameraModel(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setCameraProtocolVersion(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setCameraAttached(localGoProProtocolParser.extractUnsignedByte());
		localBacPacStatus.setBOSSReady(localGoProProtocolParser.extractUnsignedByte());
		return localBacPacStatus;
	}
	public int getCameraHLSSegment() {
		try {
			byte[] arrayOfByte =
					sendGET(this.mCameraAddress + Operations.CAMERA_HS2 + "?t=" + this.getToken());
			return new GoProProtocolParser(arrayOfByte).extractUnsignedByte();
		} catch (Exception localException) {
		}
		return -1;
	}

	public String mondecode(byte[] aob) {
		char[] hexChars = new char[aob.length * 2];
		for ( int j = 0; j < aob.length; j++ ) {
		int v = aob[j] & 0xFF;
		hexChars[j * 2] = hexArray[v >>> 4];
		hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return String.valueOf(hexChars);
	}

	
	public CamFields getCameraInfo() {
		CamFields localCamFields = new CamFields();
		GoProProtocolParser localGoProProtocolParser;
		try {
			byte[] arrayOfByte =
					sendGET(this.mCameraAddress + Operations.CAMERA_CV + "?t=" + this.getToken());
			localGoProProtocolParser = new GoProProtocolParser(arrayOfByte);
			if (localGoProProtocolParser.extractResultCode() != GoProProtocolParser.RESULT_IS_OK)
				return null;
		} catch (Exception localException) {
			return null;
		}
		localCamFields.setProtocol(localGoProProtocolParser.extractUnsignedByte());
		localCamFields.setModel(localGoProProtocolParser.extractUnsignedByte());
		localCamFields.setVersion(localGoProProtocolParser.extractString());
		localCamFields.setCamname(localGoProProtocolParser.extractString());
		return localCamFields;
	}

	public String getCameraNameCN() {
		String str = this.getIpAddress();
		byte[] arrayOfByte;
		try {
			arrayOfByte =
					sendGET(this.mCameraAddress + Operations.CAMERA_CN + "?t=" + this.getToken());
			if ((arrayOfByte == null) || (arrayOfByte.length == 0) || (arrayOfByte[0] == 1))
				return str;
		} catch (Exception localException) {
			return str;
		}
		int i = arrayOfByte[1];
		int j = 0;
		for (int k = 2;; k++) {
			if (j >= i)
				return str;
			if (k < arrayOfByte.length)
				str = str + (char) arrayOfByte[k];
			j++;
		}
	}

	public CamFields getCameraSettings() throws Exception {
		try {
			byte[] arrayOfByte = sendGET(this.mCameraAddress + "/camera/se" + "?t=" + this.getToken());
//			System.out.println("         1         2         3         4         5         6         7         8         9        10        11  ");
//			System.out.println(" 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2");
//			System.out.println(""+decode(arrayOfByte));

			return getCameraSettings(new GoProProtocolParser(arrayOfByte));
		} catch (Exception localException) {
			throw new Exception("Fail to get camera settings", localException);
		}
	}

	public CamFields getCameraSettings(GoProProtocolParser paramGoProProtocolParser) {
		CamFields localCamFields = new CamFields();
		if (paramGoProProtocolParser.extractResultCode() != GoProProtocolParser.RESULT_IS_OK)
			return null;
		localCamFields.setMode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setMicrophoneMode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setOndefault(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setExposure(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setTimeLapse(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setAutopower(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setFieldOfView(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPhotoResolution(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setVidres(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setAudioinput(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPlaymode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPlaybackPos(paramGoProProtocolParser.extractInteger());
		localCamFields.setBeepSound(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setLedblink(paramGoProProtocolParser.extractUnsignedByte());
		int i = paramGoProProtocolParser.extractByte();
		localCamFields.setPreviewActive(true);
		localCamFields.setBattery(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setUsbMode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPhotosAvailable(paramGoProProtocolParser.extractShort());
		localCamFields.setPhotosOncard(paramGoProProtocolParser.extractShort());
		localCamFields.setVideoAvailable(paramGoProProtocolParser.extractShort());
		localCamFields.setVideoOncard(paramGoProProtocolParser.extractShort());
		localCamFields.setShutter(paramGoProProtocolParser.extractUnsignedByte());
		return localCamFields;
	}

	public CamFields getCameraSettingsExtended() throws Exception {
		try {
			byte[] arrayOfByte = sendGET(this.mCameraAddress + "/camera/sx" + "?t=" + this.getToken());
//			System.out.println("         1         2         3         4         5         6         7         8         9        10        11  ");
//			System.out.println(" 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2 4 6 8 0 2");
//			System.out.println(""+mondecode(arrayOfByte));
			return getCameraSettingsExtended(new GoProProtocolParser(arrayOfByte));
		} catch (Exception localException) {
			throw new Exception("Fail to get camera extended settings", localException);
		}
	}

//	byte[] toBytes(int i)
//	{
//	  byte[] result = new byte[4];

//	  result[0] = (byte) (i >> 24);
//	  result[1] = (byte) (i >> 16);
//	  result[2] = (byte) (i >> 8);
//	  result[3] = (byte) (i /*>> 0*/);

//	  return result;
//	}
	
//	public byte[] intToBytes( final int i ) {
//	    ByteBuffer bb = ByteBuffer.allocate(4); 
//	    bb.putInt(i); 
//	    return bb.array();
//	}
	
//	public static boolean[] byteArray2BitArray(byte[] bytes) {
//	    boolean[] bits = new boolean[bytes.length * 8];
//	    for (int i = 0; i < bytes.length * 8; i++) {
//	      if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
//	        bits[i] = true;
//	    }
//		System.out.println("[0]"+bits[0]+"[1]"+bits[1]+"[2]"+bits[2]+"[3]"+bits[3]+"[4]"+bits[4]+"[5]"+bits[5]+"[6]"+bits[6]+"[7]"+bits[7]+"[8]"+bits[8]);
//	    return bits;
//	}

	
	public CamFields getCameraSettingsExtended(GoProProtocolParser paramGoProProtocolParser) {
		CamFields localCamFields = new CamFields();
		int i;
		if (paramGoProProtocolParser.extractResultCode() != GoProProtocolParser.RESULT_IS_OK)
			return null;
//		i = paramGoProProtocolParser.extractUnsignedByte();
		localCamFields.setMode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setMicrophoneMode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setOndefault(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setExposure(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setTimeLapse(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setAutopower(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setFieldOfView(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPhotoResolution(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setVidres(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setAudioinput(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPlaymode(paramGoProProtocolParser.extractShort());
		localCamFields.setPlaybackPos(paramGoProProtocolParser.extractShort());
		i = paramGoProProtocolParser.extractByte();
		i = paramGoProProtocolParser.extractByte();
		i = paramGoProProtocolParser.extractByte();
		i = paramGoProProtocolParser.extractByte();
		String s1 =   String.format("%8s", Integer.toBinaryString(i & 0xFF)).replace(' ', '0').substring(7,8);
		if (s1=="1") { localCamFields.setPreviewActive(true); } else { localCamFields.setPreviewActive(false); }
		localCamFields.setBattery(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setUsbMode(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setPhotosAvailable(paramGoProProtocolParser.extractShort());
		localCamFields.setPhotosOncard(paramGoProProtocolParser.extractShort());
		localCamFields.setVideoAvailable(paramGoProProtocolParser.extractShort());
		localCamFields.setVideoOncard(paramGoProProtocolParser.extractShort());
		localCamFields.setShutter(paramGoProProtocolParser.extractUnsignedByte());
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		localCamFields.setBurstRate(paramGoProProtocolParser.extractUnsignedByte());				
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		i = paramGoProProtocolParser.extractUnsignedByte();
		localCamFields.setVidres(paramGoProProtocolParser.extractUnsignedByte());
		localCamFields.setFramesPerSecond(paramGoProProtocolParser.extractUnsignedByte());
		return localCamFields;
	}
	
	public DefaultHttpClient newInstance() {
		BasicHttpParams localBasicHttpParams = new BasicHttpParams();
		HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(localBasicHttpParams, "ISO-8859-1");
		HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, true);
		HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
		HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
		HttpConnectionParams.setSoTimeout(localBasicHttpParams, 10000);
		HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
		SchemeRegistry localSchemeRegistry = new SchemeRegistry();
		localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 1);
		return new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams,
				localSchemeRegistry), localBasicHttpParams);
	}

	public boolean sendCommand(Operations paramString, int paramInt) throws Exception {
		StringBuilder localStringBuilder = new StringBuilder("%");
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = Integer.valueOf(paramInt);
		return sendCommand(paramString, String.format("%02x", arrayOfObject));
	}

	public boolean sendCommand(Operations paramString1, String paramString2) throws Exception {
		String param = null;
		if (!paramString1.toString().startsWith("/")) {
			param = "/" + paramString1.toString();
		} else {
			param = paramString1.toString();
		}
		sendGET(this.mCameraAddress + param + "?t=" + this.getToken() + "&p=" + paramString2);
		return true;

	}

	public byte[] sendGET(String paramString) throws Exception {
		return sendGET(paramString, this.mClient);
	}

	public byte[] sendGET(String paramString, DefaultHttpClient paramDefaultHttpClient)
			throws Exception {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		HttpResponse localHttpResponse;
		try {
//			System.out.println("Cmd :" + paramString);
			System.setProperty("http.keepAlive", "true");
//			System.out.println("httpGet");
			HttpGet localHttpGet = new HttpGet(paramString);
//			System.out.println("localhttpGet");
			localHttpResponse = paramDefaultHttpClient.execute(localHttpGet);
			int statusCode = localHttpResponse.getStatusLine().getStatusCode();
//			System.out.println("statusCode="+statusCode);
			if (statusCode >= 400) {
				localHttpGet.abort();
				throw new IOException("Fail to send GET - HTTP error code = [" + statusCode + "]");
			}
		} catch (Exception localException) {
			System.out.println("httpGet exception");
			throw localException;
		}
		int j = (int) localHttpResponse.getEntity().getContentLength();
		if (j <= 0)
			j = 128;
		InputStream localInputStream = localHttpResponse.getEntity().getContent();
		byte[] arrayOfByte = new byte[j];
		while (true) {
			if (localInputStream.read(arrayOfByte, 0, arrayOfByte.length) == -1) {
				localByteArrayOutputStream.flush();
				return localByteArrayOutputStream.toByteArray();
			}
			localByteArrayOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
		}
	}

	public boolean setBacPacWifiMode(int paramInt) throws Exception {
		return sendCommand(Operations.BACPAC_WI, paramInt);
	}

	public boolean setBackPackPowerCamera(boolean paramBoolean) throws Exception {
		return sendCommand(Operations.BACPAC_PW, fromBoolean(paramBoolean));
	}

	public boolean setCamAutoPowerOff(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_AO, paramInt);
	}

	public boolean setCamDateTime(String paramString) {
		try {
			boolean bool =
					passFail(sendGET(this.mCameraAddress + "/camera/TM?t=" + this.getToken()
							+ "&p=" + paramString));
			return bool;
		} catch (Exception localException) {
		}
		return false;
	}

	public boolean setCamDefaultMode(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_DM, paramInt);
	}

	public boolean setCamExposure(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_EX, paramInt);
	}

	public boolean setCamFov(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_FV, paramInt);
	}

	public boolean setCamLEDBlink(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_LB, paramInt);
	}

	public boolean setCamLivePreview(boolean paramBoolean) throws Exception {
		/*
		 * if (paramBoolean) ; for (int i = 2;; i = 0) return
		 * sendCommand(Operations.CAMERA_PV, i);
		 */

		if (paramBoolean) {
			return sendCommand(Operations.CAMERA_PV, 2);
		} else {
			// return sendCommand(Operations.CAMERA_PV, 0);

			try {
				sendGET(this.mCameraAddress + Operations.CAMERA_PV + "?t=" + this.getToken()
						+ "&p=");
				return true;
			} catch (Exception localException) {
			}
			return false;
		}
	}

	public boolean setCamLocate(boolean paramBoolean) throws Exception {
		return sendCommand(Operations.CAMERA_LL, fromBoolean(paramBoolean));
	}

	public boolean setCamMode(String strmode) throws Exception {
		return sendCommand(Operations.CAMERA_CM, strmode);
	}

	public boolean setCamNtscPal(boolean paramBoolean) throws Exception {
		if (paramBoolean)
			;
		for (int i = 0;; i = 1)
			return sendCommand(Operations.CAMERA_VM, i);
	}

	public boolean setCamOnScreenDisplay(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_DS, paramInt);
	}

	public boolean setCamPhotoResolution(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_PR, paramInt);
	}

	public boolean setCamProtune(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_PT, paramInt);
	}

	public boolean setCamShutter(boolean paramBoolean) throws Exception {
		return sendCommand(Operations.BACPAC_SH, fromBoolean(paramBoolean));
	}

	public boolean setCamSound(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_BS, paramInt);
	}

	public boolean setCamTimeLapseTI(String paramString) throws Exception {
		return sendCommand(Operations.CAMERA_TI, paramString);
	}

	public boolean setCamUpDown(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_UP, paramInt);
	}

	public boolean setCamVideoResolution(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_VR, paramInt);
	}

	public boolean setCameraHLSSegment(int paramInt) throws Exception {
		return sendCommand(Operations.CAMERA_HS, paramInt);
	}

	public boolean setCameraName(String paramString) {
		if ((paramString.length() > 31) || (paramString.length() == 0))
			return false;
		Object[] arrayOfObject = new Object[2];
		arrayOfObject[0] = Integer.valueOf(paramString.length());
		arrayOfObject[1] = paramString;
		String str =
				URLEncoder.encode(String.format("%x%s", arrayOfObject).replaceAll("\\s+", "%20"));
		try {
			boolean bool =
					passFail(sendGET(this.mCameraAddress + Operations.CAMERA_CN + "?t="
							+ this.getToken() + "&p=%0" + str));
			return bool;
		} catch (Exception localException) {
		}
		return false;
	}

	public boolean toBoolean(int paramInt) {
		return paramInt != 0;
	}

	public String getToken() {
		return getPassword();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	private void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	private Integer getPort() {
		return port;
	}

	private void setPort(Integer port) {
		this.port = port;
	}

	private String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public boolean startRecord() throws Exception {
		return sendCommand(Operations.BACPAC_SH, "%01");
	}

	public boolean stopRecord() throws Exception {
		return sendCommand(Operations.BACPAC_SH, "%00");
	}

	public boolean turnOnCamera() throws Exception {
		return sendCommand(Operations.BACPAC_PW, "%01");
	}

	public boolean turnOffCamera() throws Exception {
		return sendCommand(Operations.BACPAC_PW, "%00");
	}

	public boolean changeModeCamera() throws Exception {
		return sendCommand(Operations.BACPAC_PW, "%02");
	}

	public boolean modeCamera() throws Exception {
		return sendCommand(Operations.CAMERA_CM, "%00");
	}

	public boolean modePhoto() throws Exception {
		return sendCommand(Operations.CAMERA_CM, "%01");
	}

	public boolean modeBurst() throws Exception {
		return sendCommand(Operations.CAMERA_CM, "%02");
	}

	public boolean timelapse1() throws Exception {
		return sendCommand(Operations.CAMERA_CM, "%03");
	}

	public boolean timelapse2() throws Exception {
		return sendCommand(Operations.CAMERA_CM, "%04");
	}

}