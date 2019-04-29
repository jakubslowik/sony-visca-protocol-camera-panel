package com.example.demo.cmd;

public final class PanTiltAbsolutePosCmd extends Cmd {

    private static final byte[] ptAbsolutePosCommandData = new byte[]{(byte) 1, (byte) 6, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(ptAbsolutePosCommandData);
        cmdData[3] = 1;
        cmdData[5] = 0;
        cmdData[6] = 3;
        cmdData[7] = 7;
        cmdData[8] = 5;
        cmdData[9] = 0;
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
