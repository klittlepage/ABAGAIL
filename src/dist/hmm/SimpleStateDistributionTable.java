package dist.hmm;

import dist.DiscreteDistribution;
import dist.DiscreteDistributionTable;
import shared.Copyable;
import shared.DataSet;
import shared.Instance;

/**
 * A look up table distribution for transitions
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class SimpleStateDistributionTable extends DiscreteDistributionTable
         implements StateDistribution, Copyable {

    
    /**
     * Create a new look up table transition distribution
     * @param probabilities the array of probabilities
     */
    public SimpleStateDistributionTable(double[][] probabilities) {
        super(probabilities);
    }
    
    /**
     * Make a new look up table of transition probabilities
     *
     */
    public SimpleStateDistributionTable(DiscreteDistribution[] distributions) {
    	    super(distributions);
    }

    /**
     */
    @Override
    public double p(int nextState, Instance o) {
        Instance instance = new Instance(o.getData(), new Instance(nextState));
        return p(instance);
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
    public void estimate(double[][] expectations, DataSet observations) {
        double[][] matrix = getProbabilityMatrix();
        double[] sums = new double[getInputRange()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
            	    matrix[i][j] = 0;
            }
        }
        // sum up expectations
        for (int t = 0; t < expectations.length; t++) {
            int input = observations.get(t).getDiscrete();
            for (int j = 0; j < expectations[t].length; j++) {
                matrix[input][j] += expectations[t][j];
                sums[input] += expectations[t][j];
            }
        }

        // probability = expected / sum of expected
        for (int i = 0; i < matrix.length; i++) {
        	    double[] prior = getDistributions()[i].getPrior();
        	    double m = getDistributions()[i].getM();
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (matrix[i][j] + m * prior[j]) /  (sums[i] + m);
            }   
           
        }
    }

    /**
     */
    @Override
    public int mostLikelyState(Instance o) {
        return mode(o).getDiscrete();
    }
    
    @Override
    public Copyable copy() {
        DiscreteDistributionTable copy = (DiscreteDistributionTable) super.copy();
        return new SimpleStateDistributionTable(copy.getDistributions());
    }

}
