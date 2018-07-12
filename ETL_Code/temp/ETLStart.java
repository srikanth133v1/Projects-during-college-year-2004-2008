 


import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ETLStart extends JFrame implements ActionListener{

	JMenu File,mnuEtlWiz,mnuHelp,mnuQueries,menu1;
	JMenuItem New, mnuStartWizard,mnuAboutMe,menuItem1,mnuExit,mnuShortCuts;
        JMenuBar mnubar;




      




  public ETLStart()
  {
  		super("ETL SOFTWARE");
		
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
  move(180,100);
  File = new JMenu();
  //BranchMenu = new JMenu();
  mnuEtlWiz = new JMenu();
  mnuHelp = new JMenu();
  New = new JMenuItem();
  //mnuAddBranch = new JMenuItem();
  //mnuRemoveBranch = new JMenuItem();
 // mnuViewBranch = new JMenuItem();
 // mnuViewAllBranches = new JMenuItem();
  mnuStartWizard = new JMenuItem();
  mnuShortCuts = new JMenuItem();
  mnuAboutMe = new JMenuItem();
  mnuQueries = new JMenu();
  menuItem1 = new JMenuItem();
  menu1 = new JMenu();
  mnuExit = new JMenuItem();
  mnubar=new JMenuBar();
        mnubar.add(File); 
	//mnubar.add(BranchMenu);
	mnubar.add(mnuEtlWiz);
	mnubar.add(mnuQueries);
	mnubar.add(mnuHelp);
	mnubar.add(menu1);
	setJMenuBar(mnubar);
    
        File.setLabel("File");
        File.setMnemonic('F');
        File.setActionCommand("File");

    //BranchMenu.setLabel("Branch");
    //BranchMenu.setMnemonic('B');
   // BranchMenu.setActionCommand("Branch");
    mnuQueries.setLabel("QUERIES");
	mnuQueries.setMnemonic('Q');
	menuItem1.setLabel("Query");
    menu1.setLabel("Exit");
    mnuEtlWiz.setLabel("ETL Wizrd");
	mnuEtlWiz.setMnemonic('E');
    mnuHelp.setLabel("Help");
	mnuHelp.setMnemonic('H');
    mnuExit.setLabel("Exit");
	mnuExit.setMnemonic('x');

    New.addActionListener(this);
    //mnuAddBranch.addActionListener(this);
	//mnuRemoveBranch.addActionListener(this);
	//mnuViewBranch.addActionListener(this);
	mnuStartWizard.addActionListener(this);
	mnuAboutMe.addActionListener(this);
	menuItem1.addActionListener(this);
	mnuExit.addActionListener(this);
	mnuShortCuts.addActionListener(this);
	//mnuViewAllBranches.addActionListener(this);
	menuItem1.addActionListener(this);

     New.setLabel("New");
     New.setActionCommand("New");

    //mnuAddBranch.setLabel("AddBranch");
    //mnuAddBranch.setActionCommand("AddBranch");
   // mnuRemoveBranch.setLabel("RemoveBranch");
   // mnuRemoveBranch.setActionCommand("RemoveBranch");
    //mnuViewBranch.setLabel("ViewBranch");
    //mnuViewAllBranches.setLabel("viewAllBranches");
    //mnuViewAllBranches.setActionCommand("viewAllBranches");
	mnuStartWizard.setLabel("START WIZARD");
    mnuShortCuts.setLabel("ShortCuts");
    mnuAboutMe.setLabel("AboutMe");

    File.add(New);

   // BranchMenu.add(mnuAddBranch);
   // BranchMenu.add(mnuRemoveBranch);
    //BranchMenu.add(mnuViewBranch);
   // BranchMenu.add(mnuViewAllBranches);
    mnuEtlWiz.add(mnuStartWizard);
    mnuHelp.add(mnuShortCuts);
    mnuHelp.add(mnuAboutMe);
    mnuQueries.add(menuItem1);
    menu1.add(mnuExit);
	setSize(400,400);
	setVisible(true);
  }
public static void main(String ss[])
{
new ETLStart();
}
	public void actionPerformed(ActionEvent evt)
	{
		Object source = evt.getSource();
		if(source.equals(mnuExit))
		{
		 Msbox msg= new  Msbox(this,"Do U Want to Exit?");
		 System.exit(0);
		}
                else if(source.equals(New))
		{
		setVisible(false);
		new  ViewTable();
		} 
		/**else if(source.equals(mnuAddBranch))
		{
		setVisible(false);
		new AddBranch();
		}
		else if(source.equals(mnuRemoveBranch))
		{
		setVisible(false);
		new DeleteBranch();
		}
		else if(source.equals(mnuViewBranch))
		{
		setVisible(false);
		new View();
		}*/
		/**else if(source.equals(mnuViewAllBranches))
		{
		setVisible(false);
		new ViewAllBranches();
		}
		 else if(source.equals(mnuViewAllBranches))
		{
		setVisible(false);
		new ViewAllBranches();
		}*/
		else if(source.equals(mnuStartWizard))
		{
		setVisible(false);
		//new SelectBranch();
                  new  TransCenter();
                  
                  
		}
		else if(source.equals(menuItem1))
		{
		setVisible(false);
		new  QueryForm();
		}

	}
}
