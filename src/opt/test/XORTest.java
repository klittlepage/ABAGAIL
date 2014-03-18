package opt.test;

import func.nn.backprop.BackPropagationNetwork;
import func.nn.backprop.BackPropagationNetworkFactory;
import opt.OptimizationAlgorithm;
import opt.RandomizedHillClimbing;
import opt.example.NeuralNetworkOptimizationProblem;
import shared.*;

/**
 * Test optimization for neural networks
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class XORTest {
    
    /**
     * Tests out the perceptron with the classic xor test
     * @param args ignored
     */
    public static void main(String[] args) {
        BackPropagationNetworkFactory factory = 
            new BackPropagationNetworkFactory();
        double[][][] data = {
               { { 1, 1, 1, 1 }, { 0 } },
               { { 1, 0, 1, 0 }, { 1 } },
               { { 0, 1, 0, 1 }, { 1 } },
               { { 0, 0, 0, 0 }, { 0 } }
        };
        Instance[] patterns = new Instance[data.length];
        for (int i = 0; i < patterns.length; i++) {
            patterns[i] = new Instance(data[i][0]);
            patterns[i].setLabel(new Instance(data[i][1]));
        }
        BackPropagationNetwork network = factory.createClassificationNetwork(
           new int[] { 4, 3, 1 });
        ErrorMeasure measure = new SumOfSquaresError();
        DataSet set = new DataSet(patterns);
        NeuralNetworkOptimizationProblem nno = new NeuralNetworkOptimizationProblem(
            set, network, measure);
        OptimizationAlgorithm o = new RandomizedHillClimbing(nno);
        FixedIterationTrainer fit = new FixedIterationTrainer(o, 5000);
        fit.train();
        Instance opt = o.getOptimal();
        network.setWeights(opt.getData());
        for (final Instance pattern : patterns) {
            network.setInputValues(pattern.getData());
            network.run();
            System.out.println("~~");
            System.out.println(pattern.getLabel());
            System.out.println(network.getOutputValues());
        }
    } 

}
