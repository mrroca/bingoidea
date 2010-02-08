package wsm;

import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 

public class T 
    extends Frame 
    implements ActionListener { 
  /**
     * 
     */
    private static final long serialVersionUID = 1L;
static T frm = new T(); 
  static MenuBar menubar = new MenuBar(); 
  static Menu menu1 = new Menu("File"); 
  static MenuItem item1 = new MenuItem("Open"); 
  static MenuItem item2 = new MenuItem("Save"); 

  static FileDialog dia1 = new FileDialog(frm, "Open"); 
  static FileDialog dia2 = new FileDialog(frm, "Save", FileDialog.SAVE); 
  static TextArea txa = new TextArea(); 

  static WinLis wlis = new WinLis(); 

  public static void main(String agrs[]) { 
    frm.setTitle("小记事本"); 

    menubar.add(menu1); 

    menu1.add(item1); 
    menu1.add(item2); 

    item1.addActionListener(frm); 
    item2.addActionListener(frm); 

    frm.add(txa); 

    frm.setMenuBar(menubar); 

    frm.setSize(800, 650); 
    frm.setVisible(true); 

    frm.addWindowListener(wlis); 
    frm.addWindowListener(wlis); 
  } 

  static class WinLis 
      extends WindowAdapter { 
    public void windowClosing(WindowEvent e) { 
      frm.dispose(); 
    } 

  } 

  public void actionPerformed(ActionEvent e) { 
    MenuItem item = (MenuItem) e.getSource(); 
    if (item == item1) { 
      dia1.setVisible(true); 

      String fname = dia1.getDirectory() + dia1.getFile(); 
      try { 
        FileInputStream fi = new FileInputStream(fname); 
        byte ba[] = new byte[fi.available()]; 
        fi.read(ba); 
        txa.setText(new String(ba)); 
        fi.close(); 
      } 
      catch (IOException ioe) {} 
      ; 
    } 
    if (item == item2) { 
      dia2.setVisible(true); 
      String fname2 = dia2.getDirectory(); 
      File file = new File(dia2.getFile() + ".txt"); 
      String s = txa.getText(); 

      try { 
        BufferedWriter out = new BufferedWriter(new FileWriter(fname2 + file)); 
        out.write(s); 
        out.close(); 

      } 
      catch (Exception ioe) { 
        ioe.printStackTrace(); 
      } 

    } 
  } 
}