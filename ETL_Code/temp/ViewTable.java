 


import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.sql.*;

public class ViewTable extends JFrame implements ActionListener
{
   int ip;
  JButton b1,b2;
  JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,ld;
  JTextField t1,t2,t3,t4,t5,t6 ;
  JTextField p1,p2;
  JComboBox ts1,ts2;
   String ctype1,password1,username1;	
    public ViewTable()
    {
      setTitle("View-Table");
	  move(50,50);
      l=new JLabel("ENTER SOURCE AND TARGET DB DETAILS");
      l.setBounds(175,30,850,20);
      l.setFont(new Font("convecta",Font.BOLD,20));
      l.setForeground(Color.black);
      String dbtype[]={"Access","Oracle","SqlServer",};
       
            
      l1=new JLabel("SOURCE DB");
     l1.setBounds(210,100,200,20);
     l1.setFont(new Font("convecta",Font.BOLD,12));
     l1.setForeground(Color.black);

     l2=new JLabel("USER NAME");
      l2.setBounds(210,130,200,20);
       l2.setFont(new Font("convecta",Font.BOLD,12));
      l2.setForeground(Color.black);

      l3=new JLabel("PASSWORD");
      l3.setBounds(210,160,200,20);
      l3.setFont(new Font("convecta",Font.BOLD,12));
      l3.setForeground(Color.black);

      l4=new JLabel("TARGET DB");
      l4.setBounds(210,190,200,20);
      l4.setFont(new Font("convecta",Font.BOLD,12));
      l4.setForeground(Color.black);

      l5=new JLabel("USER NAME");
      l5.setBounds(210,220,200,20);
      l5.setFont(new Font("convecta",Font.BOLD,12));
      l5.setForeground(Color.black);

      l6=new JLabel("PASSWORD");
      l6.setBounds(210,250,100,20);
      l6.setFont(new Font("convecta",Font.BOLD,12));
      l6.setForeground(Color.black);
       
      b1=new JButton("SUBMIT");
      b1.setBounds(200,400,100,20);
      b1.setFont(new Font("convecta",Font.BOLD,12));
      b1.setForeground(Color.black);

      b2=new JButton("ABORT");
      b2.setBounds(300,400,100,20);
      b2.setFont(new Font("convecta",Font.BOLD,12));
      b2.setForeground(Color.black);

      p1=new JTextField(15);
      p1.setBounds(300,130,200,20);
      p1.setFont(new Font("convecta",Font.BOLD,12));
      
      p2=new JTextField(15);
      p2.setBounds(300,160,200,20);
      p2.setFont(new Font("convecta",Font.BOLD,12)); 
     
      /**t1=new JTextField(20);
      t1.setBounds(300,100,200,20);
      t1.setFont(new Font("convecta",Font.BOLD,12)); 
      
      t2=new JTextField(20);
      t2.setBounds(300,190,200,20);
      t2.setFont(new Font("convecta",Font.BOLD,12)); */
      
      t3=new JTextField(20);
      t3.setBounds(300,220,200,20);
      t3.setFont(new Font("convecta",Font.BOLD,12));
      
      t4=new JTextField(20);
      t4.setBounds(300,250,50,20);
      t4.setFont(new Font("convecta",Font.BOLD,12));
      
      /*t5=new JTextField(15);
      t5.setBounds(300,280,200,20);
      t5.setFont(new Font("convecta",Font.BOLD,12));
      
      t6=new JTextField(20);
      t6.setBounds(300,310,80,20);
      t6.setFont(new Font("convecta",Font.BOLD,12));*/
      
      

      ts1=new JComboBox(dbtype);
      ts1.setBounds(300,100,200,20);
      ts1.setFont(new Font("convecta",Font.BOLD,12));
      ts1.setForeground(Color.black);

      ts2=new JComboBox(dbtype);
      ts2.setBounds(300,190,200,20);
      ts2.setFont(new Font("convecta",Font.BOLD,12));
      ts2.setForeground(Color.black);


      Container c=getContentPane();
      c.setLayout(null);
      c.add(l);   
      c.add(l1);  // c.add(t1);
      c.add(l2);  c.add(p1);
      c.add(l3);  c.add(p2);
      c.add(l4);  // c.add(t2);
      c.add(l5);  c.add(t3);
      c.add(l6);  c.add(t4);
      
    // c.add(t5);
   
   //   c.add(t6);
      
      c.add(ts1);
      c.add(ts2);    
      c.add(b1);  c.add(b2);
       
	 
      c.setBackground(Color.gray);
      setSize(650,500);
      setVisible(true);
      b1.addActionListener(this);
      b2.addActionListener(this);
      }
      
   public void submit()
      {
              ctype1=(String)ts1.getSelectedItem(); 
              username1=p1.getText();
              password1=p2.getText();
             
  
 
        }
      
   
        
      public void actionPerformed(ActionEvent evt)
      {
         Object o=evt.getSource();
         if (o==b1)
         {  
             //Mcheck  mcb;
              submit();
			 
                      //new ViewAllBranches(ctype1,username1,password1); 
			//mcb=new Mcheck(this,"OK");
			//mcb.setVisible(false);
            
			 
		}
         else
         if(o==b2)
         {    p1.setText("");
               
              p2.setText("");t3.setText("");
              t4.setText("");//t5.setText("");
              //t6.setText(""); 
			  setVisible(false);
			  new ETLStart();
	     
         }
      }

} 



