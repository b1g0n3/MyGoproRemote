package org.gopro.core;

import java.nio.ByteBuffer;

public class GoProProtocolParser
{


	
	public GoProProtocolParser(byte abyte0[])
    {
        mPosition = 0;
        if(abyte0 == null)
            abyte0 = new byte[0];
        mBytes = abyte0;
    }

    private void padBuffer(ByteBuffer bytebuffer, int i)
    {
        int j = 0;
        do
        {
            if(j >= i)
                return;
            bytebuffer.put((byte)0);
            j++;
        } while(true);
    }

    public boolean extractBoolean()
    {
        return extractByte() == 0;
    }

    public byte extractByte()
    {
        byte abyte0[] = mBytes;
        int i = mPosition;
        mPosition = i + 1;
        return abyte0[i];
    }

    public String extractFixedLengthString(int i)
    {
        byte abyte0[] = new byte[i];
        System.arraycopy(mBytes, mPosition, abyte0, 0, i);
        String s = new String(abyte0);
        mPosition = i + mPosition;
        return s;
    }

    public long extractInteger()
    {
        bb = ByteBuffer.allocate(8);
        padBuffer(bb, 4);
        bb.put(extractByte());
        bb.put(extractByte());
        bb.put(extractByte());
        bb.put(extractByte());
        return bb.getLong(0);
    }

    public byte extractResultCode()
    {
        if(mBytes == null)
            return 1;
        else
            return extractByte();
    }

    public int extractShort()
    {
        return (0xff & extractByte()) << 8 | 0xff & extractByte();
    }

    public String extractString()
    {
        byte byte0 = extractByte();
        if(byte0 == 0)
            return "";
        else
            return extractFixedLengthString(byte0);
    }

    public short extractUnsignedByte()
    {
        bb = ByteBuffer.allocate(2);
        padBuffer(bb, 1);
        bb.put(extractByte());
        return bb.getShort(0);
    }

    public int getPosition()
    {
        return mPosition;
    }

    public static int RESULT_IS_BUSY = 2;
    public static int RESULT_IS_OK = 0;
    private static byte ZERO_PADDING;
    private ByteBuffer bb;
    private byte mBytes[];
    private int mPosition;

}
