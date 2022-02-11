package com.automl.datarepresentation.externalFunctions;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
 

/**
 * Source : //https://stackoverflow.com/questions/14731898/jung-graph-with-gui-jframe-as-node
 * @author Modified by lokman
 */
public class GraphBuilderPositions extends Container
{
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Number, Number> graph = null;
    VisualizationViewer<Number, Number> vv = null;
    PickedState<Number> pickedState = null;
    ArrayList<Point2D> listLocationOfTables = new ArrayList<Point2D>();
    
    
    /**
	 * @return the listLocationOfTables
	 */
	public ArrayList<Point2D> getListLocationOfTables() {
		return listLocationOfTables;
	}

	/**
	 * @param listLocationOfTables the listLocationOfTables to set
	 */
	public void setListLocationOfTables(ArrayList<Point2D> listLocationOfTables) {
		this.listLocationOfTables = listLocationOfTables;
	}

	public GraphBuilderPositions(Number[][] nodes_list, int numberOfNodes)
    {
        try
        {
            graph = new DirectedSparseGraph<Number, Number>();
            construct_graph(nodes_list, numberOfNodes);

            vv = new VisualizationViewer<Number, Number>
                        (new CircleLayout<Number, Number>(graph), new Dimension(600, 400));
            
            Point2D center ;
            for (int i = 0; i< numberOfNodes; i ++ ) {
            	center =  vv.getGraphLayout().transform(i) ;
            	listLocationOfTables.add(center);
            }
 
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
    private void construct_graph(Number[][] EdgeList, int numberOfNodes)
    {
    	 int i = 0;
         /*add the nodes*/
         for(i=0; i<numberOfNodes; i++)
         {
             graph.addVertex(i);
         }
    	
         i = 0;
        /*add the nodes*/
        for(i=0; i<EdgeList.length; i++)
        {
            graph.addEdge(EdgeList[i][0], EdgeList[i][1], EdgeList[i][2]);
        }
    }

    public static void main(String[] args)
    {
        /*Create the window*/
        Number[][] list = {{0, 1, 3}, {1, 3, 1}, {2, 2, 3}, {3, 2, 0}};
        GraphBuilderPositions g = new GraphBuilderPositions(list, 4);
        System.out.println(g.listLocationOfTables);

    }
}/*2*/