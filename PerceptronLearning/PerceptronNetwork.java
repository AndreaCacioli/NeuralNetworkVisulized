import java.util.Random;

public class PerceptronNetwork extends NeuralNetwork {
    Neuron theNeuron;

    public PerceptronNetwork(int inputsNumber) {
        super(new int[] { inputsNumber, 1 }, new StepFunction());
        for(Neuron n : layers[0]){
            n.setActivationFunction(new IdentityFunction());
        }
        theNeuron = layers[1][0];
        theNeuron.setBias(1);
    }

    public void setExample(double [] example) throws Exception{
        if (example.length - 1 != getInputsNumber()) {
            throw new Exception("The dataset is not compatible in size with the network");
        }
        for (int i = 0; i < example.length - 1; i++) {
            this.layers[0][i].setInput(example[i]);
        }
    }
    public double getBias() {
        return theNeuron.getBias();
    }

    public Neuron[] getInputNeurons() {
        return layers[0];
    }

    public double getWeightFrom(Neuron n) {
        return getWeight(n, theNeuron);
    }

    private int getInputsNumber() {
        return layers[0].length;
    }

    public boolean learnOneStep(double[][] dataset, double learningRate) throws Exception {
        if (dataset[0].length - 1 != getInputsNumber()) {
            throw new Exception("The dataset is not compatible in size with the network");
        }
        if (satisfies(dataset)) {
            return true;
        }
        Random r = new Random();
        double[] unsatisfiedExample = null;
        while (satisfies(unsatisfiedExample)) {
            int randomIndex = r.nextInt(dataset.length);
            unsatisfiedExample = dataset[randomIndex];
        }
        adjustWeights(unsatisfiedExample, learningRate);
        return false;
    }

    private void adjustWeights(double[] unsatisfiedExample, double learningRate) {
        double wrongResult = getOutputs()[0];
        double[] modification = new double[unsatisfiedExample.length - 1];
        double target = unsatisfiedExample[unsatisfiedExample.length - 1];
        // Updating the bias
        double bias = theNeuron.getBias();
        if (wrongResult > target) {
            theNeuron.setBias(bias - learningRate);
            System.out.println("Bias decreasing\t new bias: " + theNeuron.getBias());
        } else {
            theNeuron.setBias(bias + learningRate);
            System.out.println("Bias increasing\t new bias: " + theNeuron.getBias());
        }

        // Updating the weights
        for (int i = 0; i < modification.length; i++) {
            modification[i] = unsatisfiedExample[i] * learningRate;
            if (wrongResult > target) {
                // target is lower, we gotta lower the weights
                modification[i] *= -1;
            }
            // I add the modification array to the weights array
            Neuron n = layers[0][i];
            setWeight(n, theNeuron, getWeight(n, theNeuron) + modification[i]);
        }
    }

    private boolean satisfies(double[][] dataset) {
        for (double[] example : dataset) {
            if (!satisfies(example))
                return false;
        }
        return true;
    }

    private boolean satisfies(double[] example) {
        if (example == null)
            return true;
        double target = example[example.length - 1];
        try{
            setExample(example);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        propagate();
        return Math.abs(this.getOutputs()[0] - target) < 0.001;
    }
}
