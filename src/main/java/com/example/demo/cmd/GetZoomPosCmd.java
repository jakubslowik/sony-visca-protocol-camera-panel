package com.example.demo.cmd;

public final class GetZoomPosCmd extends Cmd {

    private static final byte[] zoomPosCommandData = new byte[]{(byte) 9, (byte) 4, (byte) 47};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(zoomPosCommandData);
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
