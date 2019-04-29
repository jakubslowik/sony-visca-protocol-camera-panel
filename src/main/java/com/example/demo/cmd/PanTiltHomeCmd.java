package com.example.demo.cmd;

public final class PanTiltHomeCmd extends Cmd {

    private static final byte[] ptHomeCommandData = new byte[]{(byte) 1, (byte) 6, (byte) 4};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(ptHomeCommandData);
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}