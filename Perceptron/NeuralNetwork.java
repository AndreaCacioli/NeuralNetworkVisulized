import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

class NeuralNetwork{
    Neuron[][] layers;
    Dictionary<Neuron, Dictionary<Neuron, Double>> weights;
    Dictionary<Neuron, Dictionary<Neuron, Double>> biases;

    /**
     * 
     * @param sizes the number of neuron for each layer
     * @param function the activation function of each neuron
     */
    public NeuralNetwork(int [] sizes, EvaluationFunction function){
        Random r = new Random();
        weights = new Hashtable<>();
        biases = new Hashtable<>();
        layers = new Neuron[sizes.length][];
        int l = 0;
        for(int size : sizes){
            layers[l] = new Neuron[size];
            for(int i = 0 ; i < size; i++){
                layers[l][i] = new Neuron(function);
            }
            l++;
        }
        for(int i = 1 ; i < layers.length; i++){
            Neuron[] layer = layers[i];
            Neuron[] prevLayer = layers[i-1];
            for(Neuron n : layer){
                weights.put(n, new Hashtable<>());
                biases.put(n, new Hashtable<>());
                for(Neuron np : prevLayer){
                    weights.get(n).put(np, r.nextDouble(-3,3));
                    biases.get(n).put(np, r.nextDouble(-3,3));
                }
            }
        }
    }

    /***
     * 
     * @param a the neuron from which the connection starts
     * @param b the neuron a is connected to;
     * @return the weight of that connection
     */
    public double getWeight(Neuron a, Neuron b){
        return weights.get(b).get(a);
    }
    /***
     * 
     * @param a the neuron from which the connection starts
     * @param b the neuron a is connected to;
     * @return the bias of that connection
     */
    public double getBias(Neuron a, Neuron b){
        return biases.get(b).get(a);
    }

    /**
     * 
     * @param inputs a set of values that we assign to the initial neurons
     * Example: inputs = [0.1, 0.5, 0, 0.9, 1, 0, 3]
     * Attention: the length of inputs should be the same as the first layer of the Neural Network
     */
    public void setInput(double[] inputs){
        if(inputs.length != layers[0].length){
            System.err.println("The input size is not equal to the first layer's one");
            return;
        }
        int i = 0;
        for(double input : inputs){
            layers[0][i].setInput(input);
            i++;
        }
    }

    public void propagate(){
        for (int i = 1; i < layers.length; i++){
            Neuron[] layer = layers[i];
            Neuron[] prevLayer = layers[i - 1];
            for (Neuron n : layer){
                Double sum = 0.0;
                for(Neuron nn : prevLayer){
                    Double value = getWeight(nn, n) * nn.getActivationValue() + getBias(nn, n);
                    sum += value;
                }
                n.setInput(sum/prevLayer.length);
            }
        }
    }

    public double[] getOutputs(){
        Neuron[] lastLayer = layers[layers.length - 1];
        double[] ret = new double[lastLayer.length];
        for(int i = 0; i < lastLayer.length; i++)
        {
            ret[i] = lastLayer[i].getActivationValue();
        }
        return ret;
    }

    public void reset(){
        for(Neuron[] layer : layers){
            for(Neuron n : layer){
                n.setInput(0);
            }
        }
    }

    public String toString(){
        String s = "";
        final int stringSize = 6;
        for(Neuron[] layer : layers){
            for (Neuron n : layer){
                
                String in = ((Double)n.getInput()).toString();
                String inShorter = in.substring(0, Math.min(in.length(), stringSize));
                String out = ((Double)n.getActivationValue()).toString();
                String outShorter = out.substring(0, Math.min(in.length(), stringSize));
                s += "|" + inShorter + "->" + outShorter + "\t";
            }
            s += "\n";
        }
        return s;
    }

}