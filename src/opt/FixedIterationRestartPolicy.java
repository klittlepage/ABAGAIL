package opt;

/**
 * @author Kelly Littlepage (indigoShift LLC)
 */
public class FixedIterationRestartPolicy implements RestartPolicy {

    private final int requiredIterations;

    private int iterations;

    public FixedIterationRestartPolicy(int requiredIterations) {
        this.requiredIterations = requiredIterations;
    }

    @Override
    public boolean shouldRestart() {
        if(requiredIterations == iterations) {
            iterations = 0;
            return true;
        }
        ++iterations;
        return false;
    }


}
