package com.capstone;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Path;
import java.applet.*;
import java.io.*;

public class MusicPlayer {
    private String fileName;
    private Clip clip = null;

    public MusicPlayer() {

    }

    public void PlaySounds(String fileName)
    {
        stopMusic();

        try
        {
            this.fileName = fileName;
            File musicFile = new File(String.valueOf(Path.of("music", fileName)));
            //AudioStream musicAudio =  new AudioStream(musicFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();

            clip.open(audioIn);
            clip.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic(){
        if(clip != null && clip.isActive())
        {
            try {
                Thread.sleep(100);
                this.clip.stop();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
