package com.example.demo.cmd;

public final class GetPanTiltPosCmd extends Cmd {

    private static final byte[] ptPosCommandData = new byte[]{(byte) 9, (byte) 6, (byte) 12};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(ptPosCommandData);
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
