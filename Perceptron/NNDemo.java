public class NNDemo {
    
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(new int[]{3,5,5,2}, new SigmoidFunction());
        nn.setInput(new double[]{.5, .2, 5});
        nn.propagate();
        System.out.println(nn);
        nn.reset();
        System.out.println(nn);
    }
}
