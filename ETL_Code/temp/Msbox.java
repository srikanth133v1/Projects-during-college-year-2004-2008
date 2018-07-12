 

import javax.swing.*;
import java.awt.*;

public class Msbox extends JDialog
{
       public Msbox(Frame c,String mm )
       {
              setTitle("Loader");
              Object message="Confirmation for Exiting";
              JOptionPane.showMessageDialog(c,mm);
              setVisible(true);
       }//end of Msbox constructor.
}//end of Msbox class.
