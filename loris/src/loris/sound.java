package loris;

import java.io.IOException;  


import android.app.Activity;  
import android.content.res.AssetFileDescriptor;  
import android.media.AudioManager;  
import android.media.MediaPlayer;  

public class sound {

	protected static MediaPlayer mediaPlayer;  
    protected static boolean mustResume = false;  
    private static Activity act;  
      
    public sound(Activity act){  
        mediaPlayer = new MediaPlayer();  
        sound.act=act;  
    }  
      
    public static void prepareMediaPlayer() {  
        if(mediaPlayer == null) {  
            mediaPlayer = new MediaPlayer();  
        }  
          
        AssetFileDescriptor afd = null;  
        try {  
            afd = act.getAssets().openFd("background.mp3");  
        } catch (Exception e) {   
            e.printStackTrace();  
            System.exit(0);  
            return;  
        }  
        try {  
            mediaPlayer.reset();  
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());  
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);  
            afd.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.exit(0);  
            return;  
        } catch (IllegalStateException e) {  
            e.printStackTrace();  
            return;  
        }  
        try {  
            mediaPlayer.prepare();    
        } catch (Exception e) {   
            e.printStackTrace();  
            System.exit(0);  
            return;  
        }  
    }  
      
      
    public static void play(boolean loop) {  
          
        if(mediaPlayer == null) {  
           // Logger.log("error:MediaPlayer hasn't prepared.");  
            return;  
        }  
        mediaPlayer.setLooping(loop);  
        mediaPlayer.start();  
    }  
      
      
    public static void stop() {  
        if(mediaPlayer == null) {  
           // Logger.log("error:No mediaPlayer is playing.");  
            return;  
        }  
        mediaPlayer.stop();  
    }  
      
      
    public static void pause() {  
        if(mediaPlayer == null) {  
           // Logger.log("error:No mediaPlayer is playing.");  
            return;  
        }  
        mediaPlayer.pause();  
    }  
    public static void start(){  
        if(mediaPlayer == null) {  
         //   Logger.log("error:No mediaPlayer is prepared.");  
            return;  
        }  
        mediaPlayer.start();  
    }  
      
    public static void onPause() {  
        if(mediaPlayer == null) {  
          //  Logger.log("error:No mediaPlayer is playing.");  
            return;  
        }  
        if(mediaPlayer.isPlaying()) {  
            mediaPlayer.pause();  
            mustResume = true;  
        }  
    }  
      
      
    public static void onResume() {  
        if(mediaPlayer == null) {  
          //  Logger.log("error:No mediaPlayer is playing.");  
            return;  
        }  
        if(mustResume) {  
            mediaPlayer.start();  
            mustResume = false;  
        }  
    }  
      
    public static MediaPlayer getMediaPlayer() {  
        return mediaPlayer;  
    }  
}

