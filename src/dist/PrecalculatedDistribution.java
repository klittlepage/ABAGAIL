package dist;

import shared.Copyable;
import shared.DataSet;
import shared.Instance;

/**
 * A distribution who's  probabilities
 * are precalculated and stored in the observation
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class PrecalculatedDistribution extends AbstractDistribution implements Copyable {
    /**
     * The index at which the precalculated probability is stored
     */
    private int i;
    
    /**
     * Make a new precalculated output distribution
     * @param i the index
     */
    public PrecalculatedDistribution(int i) {
        this.i = i;
    }

    /**
     */
    @Override
    public Instance sample(Instance input) {
        return null;
    }

    /**
     */
    @Override
    public Instance mode(Instance input) {
        return null;
    }

    /**
     */
    @Override
    public double p(Instance inst) {
        return inst.getContinuous(i);
    }
    

    /**
     */
    @Override
    public double logp(Instance i) {
        return Math.log(p(i));
    }

    /**
     * @see dist.Distribution#estimate(shared.DataSet)
     */
    @Override
    public void estimate(DataSet observations) { }
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Precalculated " + i;
    }

    /**
     * @see shared.Copyable#copy()
     */
    @Override
    public Copyable copy() {
        return this;
    }




}
