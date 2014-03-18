package opt;

import shared.Instance;
import shared.Trainer;

/**
 * An abstract class for optimzation algorithms
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public abstract class OptimizationAlgorithm<T extends OptimizationProblem>
        implements Trainer {
    /**
     * The problem to optimize
     */
    private T op;
    
    /**
     * Make a new optimization algorithm
     * @param op the problem to optimize
     */
    public OptimizationAlgorithm(T op) {
        this.op = op;
    }
    
    /**
     * Get an optimization problem
     * @return the problem
     */
    public T getOptimizationProblem() {
        return op;
    }
    
    /**
     * Get the optimal data
     * @return the data
     */
    public abstract Instance getOptimal();

}
