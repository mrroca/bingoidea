package cn.com.bsoft.wsm.abstractfactorymethod;

public class MacWidgetFactory implements IWidgetFactory
{
    public IButton getButton()
    {
        return new MacButton();
    }


    public ITextField getTextField()
    {
        return new MacTextField();
    }
}
