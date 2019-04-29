package com.example.demo;

import com.example.demo.models.Macro;
import com.example.demo.models.OtherCommands;
import com.example.demo.models.PositionDirection;
import com.example.demo.models.Zoom;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Main.byteArrayToString;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    Main main;
    String commName = "COM3";
    SerialPort serialPort = new SerialPort(commName);

//    @GetMapping("/command")
//    public String command(@RequestParam String cmd) {
//        return main.command(cmd);
//  }


    private void openPort() {
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (RuntimeException e1) {
            e1.printStackTrace();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private String readResponse() {
        try {
            byte[] response = ViscaResponseReader.readResponse(serialPort);
            System.out.println("> " + byteArrayToString(response));
            return byteArrayToString(response);
        } catch (ViscaResponseReader.TimeoutException var11) {
            System.out.println("TIMEOUT");
            return "TIMEOUT";
        } catch (SerialPortException e) {
            e.printStackTrace();
            return "SERIAL PORT EXCEPTION";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(Model model) {
//        Integer maxSpeed = viscaCommandSender.sendGetPanTiltMaxSpeed();
        openPort();
        Integer maxPanSpeed = 18;
        Integer maxTiltSpeed = 14;
        Integer maxDestinationAddress = 7;
        model.addAttribute("maxPanSpeed", maxPanSpeed);
        model.addAttribute("maxTiltSpeed", maxTiltSpeed);
        model.addAttribute("maxDestinationAddress", maxDestinationAddress);
        return "mainPage";
    }


    @RestController
    @RequestMapping("/api/position")
    public class PositionController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity test() {
            return ResponseEntity.ok("hello from /api/position");
        }

        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity changePosition(@RequestParam String direction, @RequestParam byte panSpeed, @RequestParam byte tiltSpeed,  @RequestParam byte destinationAdr) {

            PositionDirection positionDirection = PositionDirection.valueOf(direction);
            byte[] response = null;
            switch (positionDirection) {
                case ABSOLUTE:
                    response = main.sendPanTiltAbsolutePos(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case POS:
                    response = main.sendGetPanTiltPos(destinationAdr);
                    break;
                case STOP:
                    response = main.sendPanTiltStop(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case UPLEFT:
                    response = main.sendPanTiltUpLeft(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case UP:
                    response = main.sendPanTiltUp(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case UPRIGHT:
                    response = main.sendPanTiltUpRight(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case LEFT:
                    response = main.sendPanTiltLeft(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case RIGHT:
                    response = main.sendPanTiltRight(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case DOWNLEFT:
                    response = main.sendPanTiltDownLeft(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case DOWN:
                    response = main.sendPanTiltDown(panSpeed, tiltSpeed, destinationAdr);
                    break;
                case DOWNRIGHT:
                    response = main.sendPanTiltDownRight(panSpeed, tiltSpeed, destinationAdr);
                    break;
            }
            try {
                serialPort.writeBytes(response);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(readResponse());
        }
    }

    @RestController
    @RequestMapping("/api/zoom")
    public class ZoomController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity test() {
            return ResponseEntity.ok("hello from /api/zoom");
        }

        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity changeZoom(@RequestParam String zoom, @RequestParam byte destinationAdr) {

            Zoom zoom1 = Zoom.valueOf(zoom);
            byte[] response = null;
            switch (zoom1) {
                case POS:
                    response = main.sendGetZoomPos(destinationAdr);
                    break;
                case STOP:
                    response = main.sendZoomStop(destinationAdr);
                    break;
                case WIDE:
                    response = main.sendZoomWideStd(destinationAdr);
                    break;
                case TELE:
                    response = main.sendZoomTeleStd(destinationAdr);
                    break;
            }
            try {
                serialPort.writeBytes(response);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(readResponse());
        }
    }

    @RestController
    @RequestMapping("/api/other")
    public class OtherController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity test() {
            return ResponseEntity.ok("hello from /api/other");
        }

        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity changePosition(@RequestParam String command, @RequestParam byte destinationAdr) {

            byte[] response = null;
            switch (OtherCommands.valueOf(command)) {
                case HOME:
                    response = main.sendPanTiltHome(destinationAdr);
                    break;
            }
            try {
                serialPort.writeBytes(response);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(readResponse());
        }
    }

    @RestController
    @RequestMapping("/api/macro")
    public class MacroController {

        @RequestMapping(method = RequestMethod.GET)
        private ResponseEntity test() {
            return ResponseEntity.ok("hello from /api/macro");
        }

        @RequestMapping(method = RequestMethod.POST)
        private ResponseEntity runMacro(@RequestParam String macro) {

            Macro macro1 = Macro.valueOf(macro);
            switch (macro1) {
                case MACRO:
                    main.runMacro(macro);
                    break;
            }

            return ResponseEntity.ok(readResponse());
        }
    }
}