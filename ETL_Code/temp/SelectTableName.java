 

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.sql.*;

public class SelectTableName extends JFrame implements ActionListener
{
    int ip;
  JButton b1,b2;
  JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,ld;
  JTextField t1,t2,t3,t4,t5,t6 ;
  JTextField p1,p2;
  JComboBox ts1,ts2;
  String tableNam[] ; 
   int i; 
   String tbname;
   public void SelectTableName()
   { 
   
      setTitle("Select-Table-Name");
	  move(50,50);
      l=new JLabel("SELECT TABLE NAME ");
      l.setBounds(175,30,850,20);
      l.setFont(new Font("convecta",Font.BOLD,20));
      l.setForeground(Color.black);
      // String tablenames[] ;
       
            
      l1=new JLabel("TABLE_NAME");
     l1.setBounds(210,100,200,20);
     l1.setFont(new Font("convecta",Font.BOLD,12));
     l1.setForeground(Color.black);

      
       
      b1=new JButton("SUBMIT");
      b1.setBounds(200,400,100,20);
      b1.setFont(new Font("convecta",Font.BOLD,12));
      b1.setForeground(Color.black);

      b2=new JButton("ABORT");
      b2.setBounds(300,400,100,20);
      b2.setFont(new Font("convecta",Font.BOLD,12));
      b2.setForeground(Color.black);

       
     
       
      
     

      ts1=new JComboBox(tableNam);
      ts1.setBounds(300,100,200,20);
      ts1.setFont(new Font("convecta",Font.BOLD,12));
      ts1.setForeground(Color.black);

       


      Container c=getContentPane();
      c.setLayout(null);
      c.add(l);   
      c.add(l1);   
      
      
      
      
     
      c.add(ts1);
       
      c.add(b1);  c.add(b2);
       
	 
      c.setBackground(Color.gray);
      setSize(650,500);
      setVisible(true);
      b1.addActionListener(this);
      b2.addActionListener(this);
      }

      public void selectTableNames(String[] tablenam)
    {       


      for(i=0;i<tablenam.length;i++)
      {  tableNam[i]=tablenam[i];
       }
      new SelectTableName();
     }
      
   public  void submit()
      {
            
 
        }
      
   
        
      public void actionPerformed(ActionEvent evt)
      {
         Object o=evt.getSource();
         if (o==b1)
         {  
            tbname=(String)ts1.getSelectedItem();
             
			 
		}
         else
         if(o==b2)
         {    //p1.setText("");
               
             // p2.setText("");t3.setText("");
              //t4.setText("");//t5.setText("");
              //t6.setText(""); 
			  setVisible(false);
			  new ETLStart();
	     
         }
      }

} 



