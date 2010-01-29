package cn.com.bsoft.wsm.factorymethod;

public class RTFEditor extends AbstractEditor
{
    public IDocument createDocument()
    {
        return new RTFDocument();
    }
}
