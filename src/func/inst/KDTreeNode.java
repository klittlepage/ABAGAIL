package func.inst;

import shared.Instance;

import java.io.Serializable;

/**
 * A node in a KDTree
 */
public class KDTreeNode implements Serializable, Comparable<KDTreeNode> {
    /**
     * The key corresponding to this node
     */
    private Instance instance;
    
    /**
     * The dimension along which this node is split
     */
    private int dimension;
    
    /**
     * The tree that is smaller along the chosen dimension
     */
    private KDTreeNode left;
    
    /**
     * The tree that is bigger along the chosen dimension
     */
    private KDTreeNode right;
    
    /**
     * Create a new KDTreeNode
     * @param key the key for the node
     */
    public KDTreeNode(Instance key) {
        this.instance = key;
    }
    
    /**
     * Get the value of this node in the split dimension
     * @return the value
     */
    public double getSplitValue() {
        return instance.getContinuous(dimension);
    }
    
    /**
     * Get the key for this node
     * @return the key 
     */
    public Instance getInstance() {
        return instance;
    }
    
    /**
     * Set the dimension
     * @param dimension the dimension
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    /**
     * Get the dimension along which this node is split
     * @return the dimension
     */
    public int getDimension() {
        return dimension;
    }
    
    /**
     * Get the left node
     * @return the left node
     */
    public KDTreeNode getLeft() {
        return left;
    }
    
    /**
     * Set the left node
     * @param node the left node
     */
    public void setLeft(KDTreeNode node) {
        left = node;
    }
    
    /**
     * Get the right node
     * @return the right node
     */
    public KDTreeNode getRight() {
        return right;
    }
    
    /**
     * Set the right node 
     * @param node the node
     */
    public void setRight(KDTreeNode node) {
        right = node;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(KDTreeNode o) {
        double value = getSplitValue();
        double otherValue = o.getInstance().getContinuous(dimension);
        if (value < otherValue) {
            return -1;
        } else if (value > otherValue) {
            return 1;
        } else {
            return 0;
        }
    }


}
