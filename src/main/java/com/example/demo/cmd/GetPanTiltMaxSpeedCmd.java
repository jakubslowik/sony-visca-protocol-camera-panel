package com.example.demo.cmd;

public final class GetPanTiltMaxSpeedCmd extends Cmd {

    private static final byte[] maxSpeedCommandData = new byte[]{(byte) 9, (byte) 6, (byte) 11};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(maxSpeedCommandData);
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
