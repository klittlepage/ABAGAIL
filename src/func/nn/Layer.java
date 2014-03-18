package func.nn;

import util.linalg.DenseVector;
import util.linalg.Vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A layer is a collection of nodes
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class Layer implements Serializable {

	/**
	 * The list of nodes in this layer
	 */
	private List<Neuron> nodes;

	/**
	 * Make a new empty layer
	 */
	public Layer() {
		nodes = new ArrayList<Neuron>();
	}

	/**
	 * Get the node count
	 * @return the number of nodes
	 */
	public int getNodeCount() {
		return nodes.size();
	}
	
	/**
	 * Get the node i
	 * @param i the node to get
	 * @return the node
	 */
	public Neuron getNode(int i) {
		return nodes.get(i);
	}
	
	/**
	 * Add a node
	 * @param node the node to add
	 */
	public void addNode(Neuron node) {
		nodes.add(node);
	}
	
	/**
	 * Set the values
	 * @param values the values
	 */
	public void setActivations(Vector values) {
		for (int i = 0; i < values.size(); i++) {
			getNode(i).setActivation(values.get(i));
		}
	}
	
	/**
	 * Get the list of values in this layer
	 * @return the list of values
	 */
	public Vector getActivations() {
		double[] values = new double[getNodeCount()];
		for (int i = 0; i < values.length; i++) {
			values[i] = getNode(i).getActivation();
		}
		return new DenseVector(values);
	}

    
    /**
     * Get the index of the node with the largest activation
     * @return the index
     */
    public int getGreatestActivationIndex() {
        int largest = 0;
        double largestValue = getNode(largest).getActivation();
        for (int i = 1; i < getNodeCount(); i++) {
            if (getNode(i).getActivation() > largestValue) {
                largest = i;
            }
        }
        return largest;
    }

	/**
	 * Connect to another layer
	 * @param layer the layer to connect to
	 */
	public void connect(Layer layer) {
		for (int i = 0; i < getNodeCount(); i++) {
			Neuron node = getNode(i);
			for (int j = 0; j < layer.getNodeCount(); j++) {
				node.connect(layer.getNode(j));
			}
		}
	}

	/**
	 * Disconnect with another layer
	 * @param layer the layer to disconnect with
	 */
	public void disconnect(Layer layer) {
		for (int i = 0; i < getNodeCount(); i++) {
			Neuron node = getNode(i);
			for (int j = 0; j < layer.getNodeCount(); j++) {
				node.disconnect(layer.getNode(i));
			}
		}
	}
    
    /**
     * Get all of the links going into this layer
     * @return all of the links
     */
    public List<Link> getLinks() {
        List<Link> links = new ArrayList<Link>();
        for (Neuron n : nodes) {
            links.addAll(n.getInLinks());
        }
        return links;
    }

}
