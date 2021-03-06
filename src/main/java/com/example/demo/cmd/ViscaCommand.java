package com.example.demo.cmd;

public class ViscaCommand {

    public byte sourceAdr;
    public byte destinationAdr;
    public byte[] commandData;

    public byte[] getCommandData() {
        int cmdLen = this.commandData.length + 1 + 1;
        byte[] cmdData = new byte[cmdLen];
        byte head = (byte) (128 | (this.sourceAdr & 7) << 4 | this.destinationAdr & 15);
        byte tail = -1;
        System.arraycopy(this.commandData, 0, cmdData, 1, this.commandData.length);
        cmdData[0] = head;
        cmdData[cmdData.length - 1] = tail;
        return cmdData;
    }
}
