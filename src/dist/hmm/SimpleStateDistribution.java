package dist.hmm;

import dist.DiscreteDistribution;
import shared.Copyable;
import shared.DataSet;
import shared.Instance;

/**
 * A simple state functin doesn't look at the input
 * observations at all when updating it's probabilities
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class SimpleStateDistribution extends DiscreteDistribution
        implements StateDistribution, Copyable {

    
    /**
     * Make a new simple state distribution
     * @param probabilities the probabilities
     */
    public SimpleStateDistribution(double[] probabilities) {
        super(probabilities);
    }
    

    /**
     */
    @Override
    public double p(int nextState, Instance observation) {
        return p(new Instance(nextState));
    }

    /**
     */ 
    @Override
    public void estimate(double[][] expectations, DataSet observations) {
        double sum = 0;
        double[] probabilities = getProbabilities();
        for (int i = 0; i < probabilities.length; i++) {
           probabilities[i] = 0;
        }
        // sum up expectations
        for (final double[] expectation : expectations) {
            for (int j = 0; j < expectation.length; j++) {
                probabilities[j] += expectation[j];
                sum += expectation[j];
            }
        }
        // probability = expected / sum of expected
        for (int j = 0; j < probabilities.length; j++) {
            probabilities[j] = (probabilities[j] + getM() * getPrior()[j]) / (sum + getM());
        }
    }

    /**
     */
    @Override
    public int generateRandomState(Instance o) {
        return sample(o).getDiscrete();
    }

    /**
     */
    @Override
    public int mostLikelyState(Instance o) {
        return mode(o).getDiscrete();
    }
    
    @Override
    public Copyable copy() {
        DiscreteDistribution copy = (DiscreteDistribution) super.copy();
        SimpleStateDistribution sscopy = new SimpleStateDistribution(copy.getProbabilities());
        sscopy.setM(getM());
        sscopy.setPrior(getPrior());
        return sscopy;
    }
    
    
}
