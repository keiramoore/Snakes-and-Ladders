package sound;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

// Plays sound effects
// Citation: https://alvinalexander.com/java/java-audio-example-java-au-play-sound/
public class PlaySound {

    public PlaySound(String soundLocation) {
        try {
            InputStream in = new FileInputStream(soundLocation);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
