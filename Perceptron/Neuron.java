class Neuron {
    EvaluationFunction activationFunction;
    double internalState;

    public Neuron(EvaluationFunction f) {
        this.activationFunction = f;
    }

    public double getActivationValue(){
        return activationFunction.evaluate(internalState);
    }

    public void setInput(double internalState){
        this.internalState = internalState;
    }

    public double getInput(){
        return internalState;
    }


}
