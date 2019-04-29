package com.example.demo.cmd;

public final class ZoomTeleStdCmd extends Cmd {

    private static final byte[] ptTeleStdCommandData = new byte[]{(byte) 1, (byte) 4, (byte) 7, (byte) 2};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(ptTeleStdCommandData);
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
