import java.sql.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 

public class ShowTree
{
	public static void main(String[] args) 
	{
         Tree_View myTree;
         myTree=new Tree_View();
	}
}


class Tree_View extends JFrame 
{
//odbcÇý¶¯
//      String DBDriver="sun.jdbc.odbc.JdbcOdbcDriver";
//accessÇý¶¯
      String DBDriver="com.hxtt.sql.access.AccessDriver";
//odbcÁ¬½Ó
//      String connectionStr="jdbc:odbc:CKconn";   
//jdbcÖ±Á¬
      String connectionStr="jdbc:access:////F:/tree/ck.mdb";
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      JTree myTree;

      public Tree_View()
      {super("Ä¿Â¼Ê÷ÑÝÊ¾");
       setSize( 500, 500 ); 
       if (linkDatabase())
         createTree();    
       setVisible(true);
       addWindowListener( new WindowAdapter(){ public void windowClosing(WindowEvent e) {
                                closeDatabase();
                            }
                         } 
                       );  
      }

      public boolean linkDatabase()  
      {
       try{
           Class.forName(DBDriver);	 //¼ÓÔØÇý¶¯Æ÷
           con=DriverManager.getConnection(connectionStr,"","");
	   stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);   //´´½¨Statement¶ÔÏó
          }
       //²¶»ñ¼ÓÔØÇý¶¯³ÌÐòÒì³£
       catch ( ClassNotFoundException cnfex )
          { 
           System.err.println("×°ÔØ JDBC/ODBC Çý¶¯³ÌÐòÊ§°Ü¡£" ); 
	   return(false);
          } 
      //²¶»ñÁ¬½ÓÊý¾Ý¿âÒì³£
      catch ( SQLException sqlex ) 
         { 
           System.err.println( "ÎÞ·¨Á¬½ÓÊý¾Ý¿â" ); 
	   return(false);
         } 
      return(true);
      }

  
    public void createTree()
    {
       DefaultMutableTreeNode[] aNode=new DefaultMutableTreeNode[7];
       String curHH="",curMC;
       int dotCount=0;
    try{
        rs=stmt.executeQuery("select * from clggb order by hh");  //²éÑ¯±í
  	while(rs.next())         //ÏÔÊ¾ËùÓÐ¼ÇÂ¼
	  {
           curHH=rs.getString("hh").trim();
           curMC=rs.getString("mc").trim();
           dotCount=curHH.length()/2;
           System.out.println(dotCount+"="+curHH+":"+curMC+"\n");
           if (dotCount==0)
             aNode[0]=new DefaultMutableTreeNode(curHH+":"+curMC);
           else
             {
              aNode[dotCount]=new DefaultMutableTreeNode(curHH+":"+curMC);
              aNode[dotCount-1].add(aNode[dotCount]);
             }

	  };
      }
    catch(Exception e)
      {
         System.err.println( "Êý¾Ý¿â³ö´í" ); 
         System.exit( 1 );  // terminate program 
     }
    Container c = getContentPane(); 
    myTree = new JTree(aNode[0]);
    c.add(new JScrollPane(myTree));
//    MyDefaultTreeCellRenderer x = new MyDefaultTreeCellRenderer();
//    tree.setCellRenderer(x)       //ÓÃÓÚÉèÖÃ½áµãÍ¼±ê
   }

    public void closeDatabase() 
     {
     try{
        if (stmt!=null)stmt.close();  //¹Ø±ÕÓï¾ä
        if (con!=null) con.close();   //¹Ø±ÕÁ¬½Ó
       }
     catch(Exception e)
       {
         System.err.println( "Êý¾Ý¿â³ö´í" ); 
         System.exit( 1 );  // terminate program 
       }
    }
}


/*ÉèÖÃ½áµãÍ¼±ê
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {
 public MyDefaultTreeCellRenderer() {
 }
 public Component getTreeCellRendererComponent(JTree tree, Object value,
   boolean sel, boolean expanded, boolean leaf, int row,
   boolean hasFocus) {
  super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
    row, hasFocus);  
  Icon test = new ImageIcon("./images/test.jpg");
  Icon test1 = new ImageIcon("./images/test1.jpg");
  Icon test2 = new ImageIcon("./images/test2.jpg");
  Icon test3 = new ImageIcon("./images/test3.jpg");  if (leaf) {
   DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
   String str = value.toString();
   if (str.equals("×ÔÓÉ·ÉÏè")) {
    this.setIcon(test1);
   } else if (str.equals("ÎÒÐÐÎÒËØ")) {
    this.setIcon(test2);
   } else {
    this.setIcon(test3);
   }  } else {
   if (expanded) {
    this.setIcon(test);
   } else {
    this.setIcon(test);
   }
  }
  this.setText(value.toString());
  return this;
 }
}
*/

