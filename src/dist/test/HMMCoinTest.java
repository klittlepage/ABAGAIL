package dist.test;

import dist.DiscreteDistribution;
import dist.Distribution;
import dist.hmm.ForwardBackwardProbabilityCalculator;
import dist.hmm.HiddenMarkovModelReestimator;
import dist.hmm.SimpleHiddenMarkovModel;
import dist.hmm.StateSequenceCalculator;
import shared.DataSet;
import shared.Instance;

/**
 * A simple coin flipping test
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class HMMCoinTest {
    
    /**
     * The main method
     * @param args ignored
     */
    public static void main(String[] args) {
        // simple coin flip test
        SimpleHiddenMarkovModel model = new SimpleHiddenMarkovModel(2);
        model.setInitialStateProbabilities(new double[] {
            .1, .9
        });
        model.setOutputDistributions(new Distribution[] {
            new DiscreteDistribution(new double[] { .2, .8}),
            new DiscreteDistribution(new double[] { .2, .8})
        });
        model.setTransitionProbabilities(new double[][] {
            { .2, .8 },
            { .9, .1 }
        });
        DataSet sequence = new DataSet(new Instance[] {
            new Instance(1), new Instance(0)
        });
        DataSet[] sequences = new DataSet[] {
            sequence,
            new DataSet(new Instance[] { new Instance(1), new Instance(0) }),
            new DataSet(new Instance[] { new Instance(1), new Instance(0) }),
            new DataSet(new Instance[] { new Instance(1), new Instance(0) })
        };
        System.out.println(model + "\n");
        System.out.println("Observation Sequences: ");
        for (final DataSet sequence1 : sequences) {
            System.out.println(sequence1);
        }
        System.out.println();
        ForwardBackwardProbabilityCalculator fbc = 
            new ForwardBackwardProbabilityCalculator(model, sequence);
        System.out.println("Probability of first sequence: ");
        System.out.println(fbc.calculateProbability());
        System.out.println();
        StateSequenceCalculator vc =
            new StateSequenceCalculator(model, sequence);
        int[] states = vc.calculateStateSequence();
        System.out.println("Most likely state sequence of first sequence: ");
        for (final int state : states) {
            System.out.print(state + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Reestimations of model based on sequences: ");
        HiddenMarkovModelReestimator bwr = new HiddenMarkovModelReestimator(model, sequences);
        bwr.train();
        System.out.println(model + "\n");
        bwr.train();
        System.out.println(model + "\n");      
        for (int i = 0; i < 1000; i++) {
            bwr.train();     
        }
        System.out.println(model + "\n");
        fbc = new ForwardBackwardProbabilityCalculator(model, sequence);
        System.out.println("Probability of first sequence: ");
        System.out.println(fbc.calculateProbability());
        System.out.println("Probabilities of other sequences: ");
        for (int i = 1; i < sequences.length; i++) {
            fbc = new ForwardBackwardProbabilityCalculator(model, sequences[i]);
            System.out.println(fbc.calculateProbability());
        }
            
    }

}
