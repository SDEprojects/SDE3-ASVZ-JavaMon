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

    public String PlaySounds(String fileName)
    {
        if(!stopMusic())
            return "Error stopping music";

        try
        {
            this.fileName = fileName;
            File musicFile = new File(String.valueOf(Path.of("music", fileName)));
            //AudioStream musicAudio =  new AudioStream(musicFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();

            clip.open(audioIn);
            clip.start();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "File Not Found";
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return "Line Unavailable";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error Reading File";
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            return "Unsupported Media Format";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "Unknown Error";
        }
    }

    public boolean stopMusic(){
        if(clip != null && clip.isActive())
        {
            try {
                Thread.sleep(100);
                this.clip.stop();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
