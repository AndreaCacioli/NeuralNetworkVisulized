class Neuron {
    EvaluationFunction activationFunction;
    double bias = 0;
    double internalState;

    public Neuron(EvaluationFunction f) {
        this.activationFunction = f;
    }

    public void setActivationFunction(EvaluationFunction f) {
        this.activationFunction = f;
    }

    public void setBias(double b) {
        bias = b;
    }

    public double getBias() {
        return bias;
    }

    public double getActivationValue() {
        return activationFunction.evaluate(internalState + bias);
    }

    public void setInput(double internalState) {
        this.internalState = internalState;
    }

    public double getInput() {
        return internalState;
    }

}
