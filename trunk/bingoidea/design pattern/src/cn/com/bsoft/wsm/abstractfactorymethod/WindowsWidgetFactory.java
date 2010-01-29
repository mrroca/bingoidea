package cn.com.bsoft.wsm.abstractfactorymethod;

public class WindowsWidgetFactory implements IWidgetFactory
{
    public IButton getButton()
    {
        return new WindowsButton();
    }


    public ITextField getTextField()
    {
        return new WindowsTextField();
    }
}
