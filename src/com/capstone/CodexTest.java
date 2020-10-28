package com.capstone;

public class CodexTest {

    public static void main(String[] args) {
        Codex log = new Codex();
        log.updateCodex("This is me now.");
        log.readCodex();
        log.clear();
        log.readCodex();
    }
}


