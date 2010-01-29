package cn.com.bsoft.wsm.simplefactory;

public class MusicBoxDemo
{
    public static void main(String[] args) throws Exception
    {
        playMusicBox(MusicBoxFactory.createMusicBox("cn.com.bsoft.wsm.simplefactory.PianoBox"));
        playMusicBox(MusicBoxFactory.createMusicBox("cn.com.bsoft.wsm.simplefactory.ViolinBox"));
    }


    public static void playMusicBox(IMusicBox musicBox)
    {
        musicBox.play();
    }
}
