public class StepFunction implements EvaluationFunction{
    public double evaluate(double input){
        if(input > 0) return 1;
        else return -1;
    }
}
