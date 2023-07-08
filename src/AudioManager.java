import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class AudioManager
{
    public AudioManager()
    {
    }

    public void PlayAudio(File AudioFile)
    {
        try
        {
            if(AudioFile.exists())
            {
                if(!(getFileExtension(AudioFile).equals("wav")))
                {
                    throw new IncorrectFileExtension("file extension is not correct, must be .wav");
                }

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(AudioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                System.out.println("Cannot find file");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");

        if (lastIndexOf == -1)
        {
            return ""; // empty extension
        }

        return name.substring(lastIndexOf);
    }
}
