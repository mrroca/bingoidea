package cn.com.bsoft.wsm.simplefactory;

public class MusicBoxFactory
{
    public static IMusicBox createMusicBox(String name)
        throws InstantiationException, IllegalAccessException,
        ClassNotFoundException
    {
        // 这边使用的是Java的Reflection机制来产生实例
        return (IMusicBox) Class.forName(name).newInstance();
    }
}
