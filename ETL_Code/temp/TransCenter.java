 

import java.sql.*;
import java.sql.Types.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.*;
import java.io.*;
public class TransCenter extends JFrame 
	implements ActionListener, TreeSelectionListener
{
	//   enum type { VARCHAR,NUMERIC, DATE  }    

       //private JTextField tfSqlStr;
	private JTextArea status;
	String s;
	private JMenuBar menubar;
	private JMenu filemenu;
	private JMenuItem exititem, clearitem,impitem,expitem;

	private JScrollPane tableScroll;
	private JTable dbTable;
	ResultsModel model;
   
        //Transform model2;

	private JScrollPane treeScroll;
	private DefaultMutableTreeNode dbNode;      // Root node for the database tree
  	private DefaultTreeModel dbTreeModel;
	private JTree dbTree;

	private JPanel pN,p2;
	private JButton b1,b2;
	private JSplitPane split;

	String userid = "";
	String pass = "";
	String url;
	String driver = "sun.jdbc.odbc.JdbcOdbcDriver";

	Connection conn;
	Statement state;

         String ctype1,password1,username1;	
         String ctype2,password2,username2;

         String snode;	
         String tableParam2 = null;

         int rowcnt=0;

         String column2; 
         String columnsParam2 = null;   
         String strQuery;  
         String  strQuery2;

         Vector dataRows=new Vector(); 
         Enumeration vEnum;   
        // String  strQuery3="insert into "+tableParam2+"values(";

          int cnt=0; 

       

	public TransCenter()//String ctype1,String password1,String username1,String ctype1,String password1,String username1)//String bname,String tname)
	{
		super("TRANSFORMATION CENTER");
		setSize(500, 400);
        url= "jdbc:odbc:Access";
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					dispose();
				}
			});
		
		pN = new JPanel();
		pN.setLayout(new FlowLayout());
		pN.add(new JLabel("The Database of   "));// + bname+" Branch"));
		pN.add(new JLabel("The Table Name   : "));// + tname));
		getContentPane().add(pN, BorderLayout.NORTH);

		menubar = new JMenuBar();
		filemenu = new JMenu("File");
		filemenu.setMnemonic('F');
		exititem = new JMenuItem("Exit");
		exititem.setMnemonic('x');
		clearitem = new JMenuItem("Clear");
		exititem.addActionListener(this);	//register ActionListener
		clearitem.addActionListener(this);	//register ActionListener
		filemenu.add(clearitem);
		filemenu.add(exititem);
		menubar.add(filemenu);
		setJMenuBar(menubar);

		// Create tree to go in left split pane
    	dbNode = new DefaultMutableTreeNode("No database");
    	dbTreeModel = new DefaultTreeModel(dbNode);
    	dbTree = new JTree(dbTreeModel);
		dbTree.addTreeSelectionListener(this);

    	// Create table to go in right split pane
    	model = new ResultsModel();
    	dbTable = new JTable(model);
    	dbTable.setPreferredSize(new Dimension(800,400));
    	dbTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

		treeScroll = new JScrollPane(dbTree);
    	treeScroll.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		tableScroll = new JScrollPane(dbTable);
		tableScroll.setBorder(BorderFactory.createLineBorder(Color.darkGray));

		//split = new JSplitPane(HORIZONTAL_SPLIT, true, treeScroll, tableScroll);
		split = new JSplitPane();
		split.setOrientation(1);
		split.setContinuousLayout(true);
		split.setLeftComponent(treeScroll);
		split.setRightComponent(tableScroll);

		getContentPane().add(split, BorderLayout.CENTER);

                status = new JTextArea();
		status.setEditable(false);
                getContentPane().add(status, BorderLayout.SOUTH); 

		b1 = new JButton("TRANFORMATION REQUIRED");
		b2 = new JButton("TRANFORMATION NOT REQUIRED");
		b1.addActionListener(this);
		b2.addActionListener(this);
		p2 = new JPanel();
		p2.add(b1);
		p2.add(b2);
		getContentPane().add(p2, BorderLayout.SOUTH);
		connectaccess();

		setSize(750,400);
		setVisible(true);
	}
public void connectaccess(){

try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, pass);
			state = conn.createStatement();//ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE  );

			dbNode = new DefaultMutableTreeNode(url);       // Root node is URL
      		dbTreeModel.setRoot(dbNode);                    // Set root in model
      		setupTree(conn.getMetaData());            // Set up tree with metadata

      		treeScroll.setBorder(BorderFactory.createTitledBorder(
                         		BorderFactory.createLineBorder(Color.darkGray),
                         		url,
                         		TitledBorder.CENTER,
                         		TitledBorder.DEFAULT_POSITION));
      		dbTree.setRootVisible(false);                   // Now show the root node
      		dbTreeModel.reload();                           // Get the tree redisplayed
		}
		catch(Exception cnfe)
		{
			System.err.println(cnfe);
			//tfSqlStr.setText("Driver load failed" + cnfe);
		}
		
}
        
	public void actionPerformed(ActionEvent evt)
	{
		//String actionCommand = evt.getActionCommand();
		Object source = evt.getSource();
				String msg=null;
		/**if(source.equals(tfSqlStr))
		{
			try
			{
				
				String sql = tfSqlStr.getText();
				if(sql.startsWith("insert")) msg="...table row(s) inserted...";
                if(sql.startsWith("update")) msg="...table row(s) updated...";
				if(sql.startsWith("create")) msg="...table created...";
				if(sql.startsWith("delete")) msg="...table row(s) deleted...";
				if(sql.startsWith("drop")) msg="...table droped ...";
				if(sql.startsWith("select"))msg="Resultset has " + model.getRowCount() + " rows.";

				if(sql.startsWith("select"))
					model.setResultSet(state.executeQuery(sql));
				else
				    state.executeUpdate(sql);
	
				
				state.close();
				conn.close();
				connectaccess();
				status.setText(msg);

			}
			catch(SQLException sqle)
			{
				status.setText("access err"+ sqle.getMessage());
			}
		}
		else */ if(source.equals(clearitem))
		{
			//tfSqlStr.setText("");
		}
		else if(source.equals(exititem))
		{
			dispose();
			new ETLStart();
		}
		else if(source.equals(b1))
		{
		Mcheck mc;
		mc=	new Mcheck(this,"TRANSFORMATION COMPLETED");
		mc.setVisible(false);
		Msbox m;
                 store2();
		m = new Msbox(this,""+cnt+strQuery2);
		m.setVisible(false);
		//new LoadBranch();
                
                

 

		}

	}

	private void setupTree(DatabaseMetaData metadata) throws SQLException
  	{
    	String[] tableTypes = { "TABLE" };                   // We want only tables
    	ResultSet tables = metadata.getTables(              // Get the tables info
                                          	null,
                                          	null,
                                          	null,
                                          	tableTypes);

      	String tableName;                           // Stores a table name
      	DefaultMutableTreeNode tableNode;           // Stores a tree node for a table
      	while(tables.next())                              // For each table
      	{
        	tableName = tables.getString("TABLE_NAME");     // get the table name
        	tableNode = new DefaultMutableTreeNode(tableName);
        	dbNode.add(tableNode);                          // Add the node to the tree

        	// Get all the columns for the current table
        	ResultSet columnNames = metadata.getColumns(null, null, tableName, null);

        	// Add nodes for the columns as children of the table node
        	while(columnNames.next())
          		tableNode.add(
                 	new DefaultMutableTreeNode(columnNames.getString("COLUMN_NAME")));
      	}

  	}

	public void valueChanged(TreeSelectionEvent e)
  	{
    	TreePath[] paths = dbTree.getSelectionPaths();

         tableParam2 =  dbTree.getLastSelectedPathComponent().toString();
          // tableParam2 = (String)(((DefaultMutableTreeNode)(snode.getPathComponent(1))).getUserObject());

    	if(paths == null)
      		return;

    	boolean tableSelected = false;             // Set true if a table is selected
    	String column;                             // Stores a column name from a path
    	String table;                              // Stores a table name from a path
    	String columnsParam = null;                // Column names in SQL SELECT
    	String tableParam = null;                  // Table name in SQL SELECT
    	String message = null;                     // Message for status area
    	for(int  j = 0; j < paths.length ; j++)
    	{
      		switch(paths[j].getPathCount())
      		{
        	case 2:                                // We have a table selected
          		tableParam = (String)
                          	(((DefaultMutableTreeNode)
                                  	(paths[j].getPathComponent(1))).getUserObject());
          		columnsParam = "*";               // Select all columns
          		tableSelected = true;             // Set flag for a table selected
				try{
				model.setResultSet(state.executeQuery("Select * from "+tableParam));}catch(Exception ee){}
          		message = "Complete " + tableParam + " table displayed";
          		break;

        	case 3:                               // Column selected
          		table = (String)
                      	(((DefaultMutableTreeNode)
                                  	(paths[j].getPathComponent(1))).getUserObject());
          		if(tableParam == null)
            		tableParam = table;

          		else if(tableParam != table)
            		break;
          		column = (String)
                       	(((DefaultMutableTreeNode)
                                  	(paths[j].getPathComponent(2))).getUserObject());
          		if(columnsParam == null)           // If no previous columns
            		columnsParam = column;           // add the column
          		else                               // otherwise
            		columnsParam += "," + column;    // we need a comma too
          						try{
				model.setResultSet(state.executeQuery("Select "+ columnsParam +" from "+tableParam));}catch(Exception eee){}

				message = columnsParam + " displayed from " + tableParam + " table.";
         		break;
      		}
      		if(tableSelected)                      // If a table was selected
        		break;                               // we are done
    	
    	
    	    if(message != null)
    		status.setText(message);
  	}
    
      }
   public void store2()
        {   
            Connection conn2;
	     Statement state2;
             int columns;
        
           // int rowcnt=0;
            // int cnt=0; 
                              

                 
           // ResultSet results2=(state.executeQuery("Select * from "+tableParam2)); 
                   
                     
           
            String  columnNames; 
          //  Vector dataRows;               // Empty vector of rows 

              

             
 
              try{
		      ResultSet results2=(state.executeQuery("Select * from "+tableParam2));
                            // results2.TYPE_SCROLL_SENSITIVE;
                        //while(results2.next()) { rowcnt++ ; }  
                          // results2.first(); 
                       //strQuery2 = new String[rowcnt] ; 

                     /**  if(results2 == null)
                    {
                      columnNames = "";        // Reset the columns names
                      dataRows.clear();                   // Remove all entries in the Vector
                       //fireTableChanged(null);             // Tell the table there is new model data
                    // return;
                       } */
 
                   ResultSetMetaData metadata = results2.getMetaData();

                   columns =  metadata.getColumnCount();    // Get number of columns
                    //columnNames = new String[columns];           // Array to hold names
      
                 // Get the column names

                 strQuery = "create table "+tableParam2+"(";
             
                 for(int i = 0; i < columns-1 ; i++)
              { 
                    //columnNames  = metadata.getColumnLabel(i+1);
 
                      strQuery +=  metadata.getColumnName(i+1).toString()+" " ;
                      strQuery +=  metadata.getColumnTypeName(i+1)+"(20), " ;

              }
                     strQuery +=  metadata.getColumnName(columns).toString()+" " ;
                     strQuery +=  metadata.getColumnTypeName(columns)+"(20)" ;
                      strQuery +=")";

                 
   
               //  dataRows = new Vector();                     // New Vector to store the data
                   String  []rowData;  

           
  
         
                         
             

                    // ResultSet results3=(state.executeQuery("Select * from "+tableParam2));

                 // int x[]=new int[columns];
              
                int x; 
                  

                                                             // Stores one row
             while(results2.next())                        // For each row...
            {    
                         cnt++;             
                  String strQuery2="insert into "+tableParam2+" values("; 
                  
                   for(int i = 0; i < columns-1; i++)                // For each column
                 {           

                     

                        switch(x=metadata.getColumnType(i+1))
                          { 
                             case java.sql.Types.VARCHAR   :
                                                           strQuery2  +=" ' "+results2.getString(i+1)+" ', ";break;
                              case  java.sql.Types.NUMERIC  :
                                                           strQuery2  +=results2.getInt(i+1)+",";break;

                              case  java.sql.Types.DATE     :
                                                              strQuery2  +=" ' "+results2.getDate(i+1)+" ', ";break;    
                               default :
                                                         strQuery2  +=" ' "+results2.getString(columns)+" ' ";break;
                  
                            }  
 
                       //strQuery2 +=" ' "+results2.getString(i+1)+" ', ";

                  }   
                        // strQuery2 +=" ' "+results2.getString(columns)+" ') ";
 
                       switch(x=metadata.getColumnType(columns))
                            {
                             case java.sql.Types.VARCHAR  :
                                                           strQuery2  +=" ' "+results2.getString(columns)+" ' )";break;
                                  case java.sql.Types.NUMERIC  :
                                                           strQuery2  +=results2.getInt(columns)+")";break;

                                 case java.sql.Types.DATE     :
                                                          strQuery2  +=" ' "+results2.getDate(columns)+" ') ";break;    
                               default :
                                                          strQuery2  +=" ' "+results2.getString(columns)+" ' )";break;
                              } 
                             
                            
                  
                   
                   dataRows.addElement(strQuery2);                   // Store the row in the vector
                     
               }

              

                   // Signal the table there is new model data
          }
              
               catch (SQLException sqle)
             {
                System.err.println(sqle);
              }

            try{ state.close();
                 conn.close(); 
                }
                catch(Exception cnfe1)
		{
			System.err.println(cnfe1);
			//tfSqlStr.setText("Driver load failed" + cnfe);
		} 



           url= "jdbc:odbc:Oracle";
           userid="scott";
           pass="tiger";
 
                       try{ Class.forName(driver);
			conn2 = DriverManager.getConnection(url, userid, pass);
			state2 = conn2.createStatement();

                      
                 state2.executeUpdate(strQuery);

                  

                 

                 vEnum=dataRows.elements();         
                while( vEnum.hasMoreElements()) 
                 state2.executeUpdate(vEnum.nextElement().toString());  
                    
                    
                 

               

                  

               
                /**   int l=1;    
                  while(l<=cnt)

                 { String arr[] = new String(columns);
                      String str="insert into "+tableParam2+"values("; 
                     arr=dataRows.elementAt(l);l++;
                   for(int k=0;k<columns-1;k++)
                {   switch(x[k])
                 { 
                   case java.sql.Types.VARCHAR   :
                                    str  +=" ' "+arr[k]+" ', ";
                   case  java.sql.Types.NUMERIC:
                                    strQuery2  +=arr[k]+",";

                   case  java.sql.Types.DATE   :
                                    strQuery2  +=" ' "+arr[k]+" ', ";    
          
                 
                 }    
                   
                } 
                    switch(x[k])
                 { 
                   case java.sql.Types.VARCHAR   :
                                    str  +=" ' "+arr[columns]+" ') ";
                   case  java.sql.Types.NUMERIC:
                                    strQuery2  +=arr[columns]+")";

                   case  java.sql.Types.DATE    :
                                    strQuery2  +=" ' "+arr[columns]+" ') ";    
          
                 
                 }    
                    
                      state2.executeUpdate(str);

                } */

               state2.close();
               conn2.close(); 
              }
              catch(Exception cnfe2)
		{
			System.err.println(cnfe2);
			//tfSqlStr.setText("Driver load failed" + cnfe);
		}

                 
        }


                          
                        
       
             

 }
							