package dist.hmm;

import dist.ConditionalDistribution;
import shared.DataSet;
import shared.Instance;

/**
 * A wrapper class that turns a regular conditional distribution
 * into a conditional state distribution (at some computational
 * cost)
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class ConditionalStateDistributionWrapper implements StateDistribution {
    /**
     * The conditional distribution being wrapped
     */
    private ConditionalDistribution cd;
    /**
     * Make a new state distribution
     * @param dist the conditional distribution to wrap
     */
    public ConditionalStateDistributionWrapper(ConditionalDistribution dist) {
        cd = dist;
    }

    /**
     * @see dist.hmm.StateDistribution#p(int, shared.Instance)
     */
    @Override
    public double p(int nextState, Instance observ) {
        return cd.distributionFor(observ).p(new Instance(nextState));
    }

    /**
     * @see dist.hmm.StateDistribution#generateRandomState(shared.Instance)
     */
    @Override
    public int generateRandomState(Instance o) {
        return cd.sample(o).getDiscrete();
    }

    /**
     * @see dist.hmm.StateDistribution#mostLikelyState(shared.Instance)
     */
    @Override
    public int mostLikelyState(Instance o) {
        return cd.mode(o).getDiscrete();
    }

    /**
     * @see dist.hmm.StateDistribution#estimate(double[][], shared.DataSet)
     */
    @Override
    public void estimate(double[][] expectations, DataSet sequence) {
        Instance[] instances = new Instance[expectations.length * expectations[0].length];
        for (int i = 0; i < expectations.length; i++) {
            for (int j = 0; j < expectations[i].length; j++) {
                Instance instance = new Instance(sequence.get(i).getData(), new Instance(j));
                instances[i * expectations[0].length + j] = instance;
                instance.setWeight(expectations[i][j]);
            }
        }
        cd.estimate(new DataSet(instances));
    }

}
