public class SigmoidFunction implements EvaluationFunction{
    public double evaluate(double x){
        return 1 / (1 + Math.exp(-x));
    }
}
