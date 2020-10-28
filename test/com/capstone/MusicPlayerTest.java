/**
package com.capstone;

import org.junit.Test;

import static org.junit.Assert.*;

// unit test written by victor

public class MusicPlayerTest {
    MusicPlayer music = new MusicPlayer();

    @Test
    public void PlaySounds_WrongFileName_returns_FileNotFound() {
        // No input returns false
        assertEquals("File Not Found", music.PlaySounds("wrongFile.wav"));
        //assertFalse(parser.inputValidation(noInput));
    }

    @Test
    public void PlaySounds_InvalidFormat_returns_UnsupportedMediaFormat() {
        // No input returns false
        assertEquals("Unsupported Media Format", music.PlaySounds("InvalidFormat.mp3"));
        //assertFalse(parser.inputValidation(noInput));
    }

    @Test
    public void PlaySounds_ValidFile_returns_null() {
        // No input returns false
        assertEquals(null, music.PlaySounds("intro.wav"));
        //assertFalse(parser.inputValidation(noInput));
    }

    @Test
    public void stopMusic_ValidOp_returns_true() {
        // No input returns false
        music.PlaySounds("intro.wav");
        assertTrue(music.stopMusic());
        //assertFalse(parser.inputValidation(noInput));
    }
}


 **/