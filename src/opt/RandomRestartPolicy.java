package opt;

import opt.RestartPolicy;

import java.util.Random;

/**
 * @author Kelly Littlepage (indigoShift LLC)
 */
public class RandomRestartPolicy implements RestartPolicy {

    private final double restartProbability;
    private final Random random;

    public RandomRestartPolicy(double restartProbability) {
        this(restartProbability, new Random());
    }

    public RandomRestartPolicy(double restartProbability, Random random) {
        if(restartProbability <= 0 || restartProbability >= 1) {
            throw new IllegalArgumentException("restartProbability must " +
                    "be in the interval (0, 1).");
        }
        this.restartProbability = restartProbability;
        this.random = random;
    }

    @Override
    public boolean shouldRestart() {
        if(random.nextDouble() < restartProbability) {
            return true;
        }
        return false;
    }


}
