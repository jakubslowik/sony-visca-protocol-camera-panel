package com.example.demo;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.Iterator;

public class ViscaResponseReader {

    private static final long TIMEOUT_MS = 5000L;


    public static byte[] readResponse(SerialPort serialPort) throws ViscaResponseReader.TimeoutException, SerialPortException {
        ArrayList data = new ArrayList();
        long startTime = System.currentTimeMillis();

        long var10;
        do {
            while (serialPort.getInputBufferBytesCount() != 0) {
                byte[] responseData = serialPort.readBytes(1);
                byte idx = responseData[0];
                data.add(Byte.valueOf(idx));
                if (idx == -1) {
                    responseData = new byte[data.size()];
                    int var9 = 0;

                    Byte b;
                    for (Iterator var7 = data.iterator(); var7.hasNext(); responseData[var9++] = b.byteValue()) {
                        b = (Byte) var7.next();
                    }

                    return responseData;
                }
            }

            long var8 = System.currentTimeMillis();
            var10 = var8 - startTime;
        } while (var10 <= 5000L);

        throw new ViscaResponseReader.TimeoutException();
    }

    public static class TimeoutException extends Exception {

        public TimeoutException() {
        }

        public TimeoutException(String message, Throwable cause) {
            super(message, cause);
        }

        public TimeoutException(String message) {
            super(message);
        }
    }
}
