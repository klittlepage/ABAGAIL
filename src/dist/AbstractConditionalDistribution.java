package dist;

import shared.Instance;

/**
 * An abstract condtional distribution
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public abstract class AbstractConditionalDistribution extends AbstractDistribution implements ConditionalDistribution {

    /**
     * Generate a output given the input
     * @param i the input
     * @return the output
     */
    @Override
    public Instance sample(Instance i) {
        return distributionFor(i).sample();
    }
    
    /**
     * Generate a output that is most likely given the input
     * @param i the input
     * @return the output
     */
    @Override
    public Instance mode(Instance i) {
        return distributionFor(i).sample();
    }
    
    /**
     * Probability of an instance
     * @param i the instance
     *
     * @return the probability
     */
    @Override
    public double p(Instance i) {
        return distributionFor(i).p(i.getLabel());
    }
}
