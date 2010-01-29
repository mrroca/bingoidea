package cn.com.bsoft.wsm.abstractfactorymethod;

public class CustomDialog
{
    private IButton button;
    private ITextField textField;


    public CustomDialog(IWidgetFactory widgetFactory)
    {
        setWidgetFactory(widgetFactory);
    }


    public void setWidgetFactory(IWidgetFactory widgetFactory)
    {
        setButton(widgetFactory.getButton());
        setTextField(widgetFactory.getTextField());
    }


    public void layoutAllComponents()
    {

    }


    public void setButton(IButton button)
    {
        this.button = button;
    }


    public void setTextField(ITextField textField)
    {
        this.textField = textField;
    }


    public void showDialog()
    {
        this.paintDialog();
        button.paintButton();
        textField.paintTextField();
    }


    public void paintDialog()
    {
        System.out.println("custom dialog paints....");
    }
}
