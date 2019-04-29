package com.example.demo.cmd;

public final class PanTiltRightCmd extends Cmd {

    private static final byte[] ptRightCommandData = new byte[]{(byte) 1, (byte) 6, (byte) 1, (byte) 0, (byte) 0, (byte) 2, (byte) 3};


    public byte[] createCommandData() {
        byte[] cmdData = duplicateArray(ptRightCommandData);
        cmdData[3] = 4;
        cmdData[4] = 1;
        return cmdData;
    }

    private static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
