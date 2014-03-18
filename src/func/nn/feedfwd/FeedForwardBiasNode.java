package func.nn.feedfwd;

/**
 * 
 * @author Jesse Rosalia <https://github.com/theJenix>
 * Last edited: 2013-03-05
 */
public class FeedForwardBiasNode extends FeedForwardNode {

    public FeedForwardBiasNode(double activation) {
        super(null);
        super.setActivation(activation);
    }

    /**
     */
    @Override
    public void feedforward() { }
}