package com.automl.visualizationTest;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
 
//https://stackoverflow.com/questions/14731898/jung-graph-with-gui-jframe-as-node
public class GraphPanel2 extends Container
{
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Number, Number> graph = null;
    VisualizationViewer<Number, Number> vv = null;
    PickedState<Number> pickedState = null;

    public GraphPanel2(Number[][] nodes_list)
    {
        try
        {
            graph = new DirectedSparseGraph<Number, Number>();
            construct_graph(nodes_list);

            vv = new VisualizationViewer<Number, Number>
                        (new CircleLayout<Number, Number>(graph), new Dimension(600, 400));
            
            Point2D center =  vv.getGraphLayout().transform(0) ;
            System.out.printf("Vertex[%d] X = %d Y = %d: Running paintVertex()\n", 1, (int)center.getX(), (int)center.getY());
              center =  vv.getGraphLayout().transform(1) ;
            System.out.printf("Vertex[%d] X = %d Y = %d: Running paintVertex()\n", 1, (int)center.getX(), (int)center.getY());
           // vv.getRenderer().setVertexRenderer(new MyRenderer());
 
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

    public static void main(String[] args)
    {
        /*Create the window*/
        Number[][] list = {{0, 1, 3}, {1, 3, 1}, {2, 2, 3}, {3, 2, 0}};
        GraphPanel2 g = new GraphPanel2(list);


    }
}/*2*/