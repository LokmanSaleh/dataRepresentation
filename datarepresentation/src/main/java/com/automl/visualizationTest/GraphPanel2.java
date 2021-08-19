package com.automl.visualizationTest;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
 
//https://stackoverflow.com/questions/14731898/jung-graph-with-gui-jframe-as-node
public class GraphPanel2 extends Container
{
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Number, Number> graph = null;
    VisualizationViewer<Number, Number> vv = null;
    PickedState<Number> pickedState = null;
    Point2D center ;
    JPanel panel = new JPanel();
    
    public GraphPanel2(Number[][] nodes_list)
    {
        try
        {
            graph = new DirectedSparseGraph<Number, Number>();
            construct_graph(nodes_list);

            vv = new VisualizationViewer<Number, Number>
                        (new CircleLayout<Number, Number>(graph), new Dimension(400, 400));
            
            MyRenderer mr = new MyRenderer();
           
            vv.getRenderer().setVertexRenderer(mr);
            center = mr.center;
            
            panel.add(new JCheckBox("adadad"));
            panel.add(new Checkbox("adffsgsf"));
            panel.setLayout(new GridLayout((int)center.getX(), (int)center.getY()));
            
            // The vertex pick listener
            pickedState = vv.getPickedVertexState();
            pickedState.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    Object subject = e.getItem();
                    if (subject instanceof Number)
                    {
                        Number vertex = (Number) subject;
                        if (pickedState.isPicked(vertex))
                        {
                            System.out.println("Vertex " + vertex + " is now selected");
                        }
                        else
                        {
                            System.out.println("Vertex " + vertex + " no longer selected");
                        }
                    }
                }
            });

            // The following code adds capability for mouse picking of
            // vertices/edges. Vertices can even be moved!
            final DefaultModalGraphMouse<Number, Number> graphMouse = new DefaultModalGraphMouse<Number, Number>();
            vv.setGraphMouse(graphMouse);
            graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
        }
        catch (Exception e)
        {
            System.err.println("Failed to construct graph!\n");
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

    /*Attach the graph panel/container to a specified frame*/
    public void attach_to_frame(JFrame frame)
    {
        frame.setContentPane(vv);
    }

    /*This one should be reimplemented*/
    private void construct_graph(Number[][] nodes_list)
    {
        int i = 0;
        /*add the nodes*/
        for(i=0; i<nodes_list.length; i++)
        {
            graph.addVertex(i);
            graph.addEdge(nodes_list[i][0], nodes_list[i][1], nodes_list[i][2]);
        }
    }

    /*re-implement the render functionality to work with internal frames(JInternalFrame)*/
    static class MyRenderer extends JPanel implements Renderer.Vertex<Number, Number>
    {	
    	public Point2D center;
        static final long serialVersionUID = 420000L;
        
        @Override
        public void paintVertex(RenderContext<Number, Number> rc,
                                Layout<Number, Number> layout, Number vertex)
        {
            try
            {
                GraphicsDecorator graphicsContext = rc.getGraphicsContext();
                
                Point2D center = layout.transform(vertex);
                this.center = center;
                Dimension size = new Dimension(200, 80);

                System.out.printf("Vertex[%d] X = %d Y = %d: Running paintVertex()\n", vertex, (int)center.getX(), (int)center.getY());

                JPanel sv = new JPanel();
                sv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                sv.setBackground(Color.GREEN);
                sv.setPreferredSize(size);
                sv.add(new JCheckBox(center.getX() + " - " + center.getY()));
                //OK
                graphicsContext.draw(sv, rc.getRendererPane(), (int)center.getX(), 
                                     (int)center.getY(), size.width, size.height, true);
            }
            catch (Exception e)
            {
                System.err.println("Failed to render images!\n");
                System.err.println("Caught Exception: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args)
    {
        /*Create the window*/
        JFrame frame = new JFrame("BLABLA");
        Number[][] list = {{0, 1, 3}, {1, 3, 1}, {2, 2, 3}, {3, 2, 0}};
        GraphPanel2 g = new GraphPanel2(list);
        g.attach_to_frame(frame);
        frame.getContentPane().setPreferredSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.add(g.panel);

    }
}/*2*/