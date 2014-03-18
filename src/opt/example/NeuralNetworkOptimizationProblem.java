package opt.example;

import dist.Distribution;
import func.nn.NeuralNetwork;
import opt.ContinuousAddOneNeighbor;
import opt.EvaluationFunction;
import opt.HillClimbingProblem;
import opt.NeighborFunction;
import opt.ga.*;
import shared.DataSet;
import shared.ErrorMeasure;
import shared.Instance;

/**
 * A class for performing neural network optimzation
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class NeuralNetworkOptimizationProblem implements HillClimbingProblem, GeneticAlgorithmProblem {

    /**
     * The evaluation function
     */
    private EvaluationFunction eval;
    /**
     * The cross over function
     */
    private CrossoverFunction crossover;
    /**
     * The neighbor function
     */
    private NeighborFunction neighbor;
    /**
     * The mutation function
     */
    private MutationFunction mutate;
    /**
     * The distribution
     */
    private Distribution dist;
    
    /**
     * Make a new neural network optimization
     * @param examples the examples
     * @param network the neural network
     * @param measure the error measure
     */
    public NeuralNetworkOptimizationProblem(DataSet examples,
             NeuralNetwork network, ErrorMeasure measure) {
        eval = new NeuralNetworkEvaluationFunction(network, examples, measure);
        crossover = new UniformCrossOver();
        neighbor = new ContinuousAddOneNeighbor();
        mutate = new ContinuousAddOneMutation();
        dist = new NeuralNetworkWeightDistribution(network.getLinks().size());
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
        return dist.sample(null);
    }

    /**
     */
    @Override
    public Instance neighbor(Instance d) {
        return neighbor.neighbor(d);
    }
    

    /**
     */
    @Override
    public Instance mate(Instance da, Instance db) {
        return crossover.mate(da, db);
    }

    /**
     */
    @Override
    public void mutate(Instance d) {
        mutate.mutate(d);
    }

}
