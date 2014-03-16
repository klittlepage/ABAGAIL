package func.svm;

import shared.DataSet;
import shared.Instance;

/**
 * A support vector machine implementation
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class SingleClassSupportVectorMachine {
    
    /**
     * The support vectors
     */
    private final DataSet supportVectors;

    /**
     * The weights for the support vectors
     */
    private final double[] a;
    
    /**
     * The kernel function
     */
    private final Kernel kernel;
    
    /**
     * The threshold (which is subtracted)
     */
    private final double b;
    
    /**
     * Create a new support vector machine
     * @param supportVectors the support vectors
     * @param a the weights for the support vectors
     * @param kernel the kernel function
     * @param b the threshold
     */
    public SingleClassSupportVectorMachine(DataSet supportVectors,
            double[] a, Kernel kernel, double b) {
        this.supportVectors = supportVectors;
        this.a = a;
        this.kernel = kernel;
        this.b = b;
        kernel.clear();
        kernel.setExamples(supportVectors);
    }
    

    /**
     * Evaluate the support vector machine for the given data
     * @param data the data to evaluate for
     * @return the value
     */
    public Instance value(Instance d) {
        return new Instance(margin(d) >= 0);
    }

    /**
     * Evaluate the support vector machine for the given data
     * @param data the data to evaluate for
     * @return the value
     */
    public double margin(Instance data) {
        double result = 0;
        for (int i = 0; i < supportVectors.size(); i++) {
            result += a[i] * kernel.value(i, data);
        }
        result -= b;
        return result;
    }
    
    /**
     * Get the support vectors for the machine
     * @return the support vectors
     */
    public DataSet getSupportVectors() {
        return supportVectors;
    }
    
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
       String ret = "b = " + b + "\n";
       ret += "kernel = " + kernel + "\n";
       for (int i = 0; i < supportVectors.size(); i++) {
            ret += a[i] + " || " + supportVectors.get(i) + "\n";
       }
       return ret;
    }
}
