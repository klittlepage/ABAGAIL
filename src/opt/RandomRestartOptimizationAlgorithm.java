package opt;

import shared.Instance;

/**
 * @author Kelly Littlepage (indigoShift LLC)
 */
public abstract class RandomRestartOptimizationAlgorithm
            <T extends OptimizationProblem> extends OptimizationAlgorithm<T> {

    private final AlgorithmFactory<T> algorithmFactory;
    private final RestartPolicy restartPolicy;

    private OptimizationAlgorithm<T> algorithm;

    private double globalOptimal;
    private Instance globalOptimalInstance;

    public RandomRestartOptimizationAlgorithm(
            AlgorithmFactory<T> algorithmFactory,
            RestartPolicy restartPolicy) {
        super(null);
        this.algorithmFactory = algorithmFactory;
        this.restartPolicy = restartPolicy;
        algorithm = algorithmFactory.randomInstance();
        globalOptimal = Double.MIN_VALUE;
    }

    @Override
    public Instance getOptimal() {
        return globalOptimalInstance;
    }

    @Override
    public double train() {
        if(restartPolicy.shouldRestart()) {
            algorithm = algorithmFactory.randomInstance();
        }
        final double objectiveFunctionValue = algorithm.train();
        if(objectiveFunctionValue > globalOptimal) {
            globalOptimal = objectiveFunctionValue;
            globalOptimalInstance = algorithm.getOptimal();
        }
        return objectiveFunctionValue;
    }

    @Override
    public T getOptimizationProblem() {
        return algorithm.getOptimizationProblem();
    }

    public interface AlgorithmFactory<T extends OptimizationProblem> {

        OptimizationAlgorithm<T> randomInstance();


    }


}
