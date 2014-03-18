package opt;

import dist.Distribution;
import shared.Instance;


/**
 * A generic continuous optimization problem
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class GenericOptimizationProblem implements OptimizationProblem {
    /**
     * The evaluation function
     */
    private EvaluationFunction eval;
    
    /**
     * The intial distribution
     */
    private Distribution initial;

    
    /**
     * Make a new generic optimization problem
     * @param dist the initial distribution
     * @param eval the evaluation function
     */
    public GenericOptimizationProblem(EvaluationFunction eval, Distribution dist) {
        this.initial = dist;
        this.eval = eval;
    }
    

    /**
     */
    @Override
    public double value(Instance d) {
        return eval.value(d);
    }


    /**
     * @see opt.OptimizationProblem#random()
     */
    @Override
    public Instance random() {
        return initial.sample(null);
    }

}
