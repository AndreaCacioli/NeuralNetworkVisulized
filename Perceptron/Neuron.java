class Neuron {
    EvaluationFunction activationFunction;
    double internalState;
    Neuron[] inputs;
    Neuron[] outputs;

    public Neuron(EvaluationFunction f) {
        this.activationFunction = f;
    }

    public double getActivationValue(){
        return activationFunction.evaluate(internalState);
    }
    public void setInput(double internalState){
        this.internalState = internalState;
    }
}
