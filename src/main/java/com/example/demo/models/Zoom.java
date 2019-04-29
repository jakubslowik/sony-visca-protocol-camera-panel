package com.example.demo.models;

public enum Zoom {
    POS("pos"),
    STOP("stop"),
    TELE("tele"),
    WIDE("wide");

    private String zoom;

    Zoom(String zoom) {
        this.zoom = zoom;
    }
}
