 

import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;
public class QueryForm extends JFrame 
	implements ActionListener, TreeSelectionListener
{
	private JTextField tfSqlStr;
	private JTextArea status;
	String s;
	private JMenuBar menubar;
	private JMenu filemenu;
	private JMenuItem exititem, clearitem,impitem,expitem;

	private JScrollPane tableScroll;
	private JTable dbTable;
	ResultsModel model;
	private JScrollPane treeScroll;
	private DefaultMutableTreeNode dbNode;      // Root node for the database tree
  	private DefaultTreeModel dbTreeModel;
	private JTree dbTree;

	private JPanel pN;
	
	private JSplitPane split;

	String userid = "scott";
	String pass = "tiger";
	String url;
	String driver = "sun.jdbc.odbc.JdbcOdbcDriver";

	Connection conn;
	Statement state;


	public QueryForm()
	{
		super("Query Form");
		setSize(500, 400);
        url= "jdbc:odbc:Oracle";
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
		pN.add(new JLabel("Enter SQL String:"));
		pN.add(tfSqlStr = new JTextField(35));
		tfSqlStr.addActionListener(this);	//register ActionListener
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
		connectaccess();
		tfSqlStr.requestFocus();
		setSize(750,400);
		setVisible(true);
	}
public void connectaccess(){

try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, pass);
			state = conn.createStatement();

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
			tfSqlStr.setText("Driver load failed" + cnfe);
		}
		
}

	public void actionPerformed(ActionEvent evt)
	{
		//String actionCommand = evt.getActionCommand();
		Object source = evt.getSource();
				String msg=null;
		if(source.equals(tfSqlStr))
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
		else if(source.equals(clearitem))
		{
			tfSqlStr.setText("");
		}
		else if(source.equals(exititem))
		{
			dispose();
			new ETLStart();
		}

	}

	private void setupTree(DatabaseMetaData metadata) throws SQLException
  	{
    	String[] tableTypes = { "TABLE"};                   // We want only tables
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
    	}
    	
    	if(message != null)
    		status.setText(message);
  	}
}
							