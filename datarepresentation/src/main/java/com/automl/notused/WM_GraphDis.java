package com.automl.notused;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class WM_GraphDis
{
WM_Project project;
String msg;
ResultSet res;
Graph<WM_Entity, WM_EConnection> g;
VisualizationViewer<WM_Entity, WM_EConnection> vv;
JFrame frame;
//--------------------------------------------------------------------------
public void drawGraph(WM_Project pr)
{
    int glaytype;
    project = pr;
    if(project == null)
    {
        msg = "Project must be set before \n graph can be shown.";
        JOptionPane.showMessageDialog(null, msg);
        return;
    }

    //Choose Graph Layout
    WM_Glayout glay = new WM_Glayout(null, true);
    glay.setLocationRelativeTo(null);
    glay.showDialog();
    glaytype = glay.getValue();
    if( glaytype == WM_Glayout.VOID)
    {
        return;
    }


    //Create an instance of the Graph
    g = new SparseMultigraph<WM_Entity, WM_EConnection>(); 

    //Add Vertices to Graph
    //Get the instances of Entities from the database and
    //add each one as a vertex;
    try
    {
        res = WM_DBI.getEntities(project.projectID);
        res.beforeFirst();
        while(res.next())
        {
            WM_Entity ent = new WM_Entity();
            ent = WM_DBI.getEntity(res.getInt("entityID"));
            g.addVertex(ent);
        }

    }
    catch(SQLException se)
    {
        msg = "Problem getting Vertices. \n" +
                se.getLocalizedMessage();
        ILog.dbFail(msg );
        JOptionPane.showMessageDialog(null, msg);
        return;
    }

    //Add Edges to Graph
    //Get the instances of EConnection from the database and
    //then get the verticies for each connection and an instance
    //of the connection. Add each as an edge.
    try
    {
        res = WM_DBI.getAllConnections(project.projectID);
        res.beforeFirst();
        while(res.next())
        {
            WM_Entity orval = WM_DBI.getEntity(res.getInt("origin"));
            WM_Entity taval = WM_DBI.getEntity(res.getInt("target"));
            WM_EConnection con = WM_DBI.getConnection(res.getInt("econnectionID"));

            g.addEdge(con,orval,taval);
        }

    }
    catch(SQLException se)
    {
        msg = "Problem geting Edges. \n" +
                se.getLocalizedMessage();
        ILog.dbFail(msg );
        JOptionPane.showMessageDialog(null, msg);
        return;
    }


    //Now make the Graph Visible

    //Get Screen Dimensions
    // java - get screen size using the Toolkit class
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenX = screenSize.width;
    int screenY = screenSize.height;
    //now set the graph dimensions to be a fraction 10% smaller
   int dimx = screenX - (screenX/10);
   int dimy = screenY - (screenY/10);
   Layout<WM_Entity, WM_EConnection> layout;
   layout = layout = new edu.uci.ics.jung.algorithms.layout.CircleLayout(g);
   if( glaytype == WM_Glayout.CIRCULAR)
   {
       layout = new edu.uci.ics.jung.algorithms.layout.CircleLayout(g);
   }
   else if( glaytype == WM_Glayout.SPRING)
   {
       layout = new edu.uci.ics.jung.algorithms.layout.SpringLayout(g);
   }
   else if( glaytype == WM_Glayout.FORCER)
   {
       layout = new edu.uci.ics.jung.algorithms.layout.FRLayout<>(g);
   }
   else if( glaytype == WM_Glayout.SOM)
   {
       layout = new edu.uci.ics.jung.algorithms.layout.ISOMLayout<>(g);
   }
   else if( glaytype == WM_Glayout.RAD)
   {
       //layout = new edu.uci.ics.jung.algorithms.layout.RadialTreeLayout<>(g, 50, 50);

   }
   else
   {
       layout = layout = new edu.uci.ics.jung.algorithms.layout.CircleLayout(g);
   }
   layout.setSize(new Dimension(dimx-10,dimy-10));
   //BasicVisualizationServer<WM_Entity, WM_EConnection> vv = 
   //        new BasicVisualizationServer<WM_Entity, WM_EConnection>(layout);
   vv = new VisualizationViewer<WM_Entity, WM_EConnection>(layout);

   vv.setPreferredSize(new Dimension(dimx-12, dimy-12));
   vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
   vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
   vv.getRenderer().setVertexRenderer(new MyRenderer());

   DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
   gm.setMode(ModalGraphMouse.Mode.TRANSFORMING); 
   vv.addKeyListener(gm.getModeKeyListener()); 
   vv.addGraphMouseListener(new GraphClicked());
   vv.setGraphMouse(gm);

   frame = new JFrame("Graph of " + project.name);
   frame.setPreferredSize(new Dimension( dimx, dimy));
   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   frame.getContentPane().add(vv);
   frame.setLocationRelativeTo(null);
   frame.pack();
   frame.setVisible(true);
}


//--------------------------------------------------------------------------
private class GraphClicked implements GraphMouseListener
{

    @Override
    public void graphClicked(Object v, MouseEvent me)
    {

        System.out.println(me.getButton() + "  :  " + v.toString());
        int mx = me.getX();
        int my = me.getY();
        WM_Entity ent = (WM_Entity) v;
        WM_GraphInfo gi = new WM_GraphInfo(frame, true, ent, mx, my );
        gi.showDialog();
    }

    @Override
    public void graphPressed(Object v, MouseEvent me)
    {
        //Do Nothing
    }

    @Override
    public void graphReleased(Object v, MouseEvent me)
    {
        //Do Nothing
    }

}

//--------------------------------------------------------------------------
private class MyRenderer implements Renderer.Vertex<WM_Entity, WM_EConnection> 
{
    public void paintVertex(RenderContext<WM_Entity, WM_EConnection> rc, Layout<WM_Entity, WM_EConnection> layout, WM_Entity vertex) 
    {
        GraphicsDecorator graphicsContext = rc.getGraphicsContext();
        Point2D center = layout.transform(vertex);
        Shape shape = null;
        Color color = null;
        if(vertex.type.name.equals("Title")) 
        {
          shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 10, 30, 30);
          color = new Color(255,204,51); //Gold
        } 
        else if(vertex.type.name.equals("Character")) 
        {
            shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 20, 40);
            color = new Color(255,255,0);//Yellow
        } 
        else if(vertex.type.name.equals("Event")) 
        {
            shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 20, 20);
            color = new Color(51, 204, 255);//Light Blue
        }
        else if (vertex.type.name.equals("Place"))
        {
            shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 30, 30);
            color = new Color(204,204,204); //Light Grey
        }
        else if (vertex.type.name.equals("Item"))
        {
            shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 30, 30 );
            color = new Color( 102, 0, 153 ); // Purple
        }
        else if ( vertex.type.name.equals("Occupation"))
        {
            shape = new Ellipse2D.Double(center.getX() - 10, center.getY() - 10, 30, 30 );
            color = new Color( 255, 102, 0 ); // Orange
        }  
        else
        {
            shape = new Rectangle((int) center.getX() - 10, (int) center.getY() - 20, 30, 30);
            color = new Color(255,0,0);//Red
        }
        graphicsContext.setPaint(color);
        graphicsContext.fill(shape);
    } //end PaintVertex

  }


}