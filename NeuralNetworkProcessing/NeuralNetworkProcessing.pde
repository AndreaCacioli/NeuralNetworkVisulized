NeuralNetwork nn;
final int [] shape = new int[]{3, 4, 4, 5, 2};
int NEURON_SIZE;
final ArrayList<NeuronVisualizer> neurons = new ArrayList();

void settings(){
    size(900, 900);
}

void setup() {
    NEURON_SIZE = Math.min(height * width / 100_000 * 20, 80);
    nn = new NeuralNetwork(shape, new SigmoidFunction());
    final int X_OFFSET = 40;
    int x = 0 + NEURON_SIZE/2 + X_OFFSET;
    int xIncrement = width / shape.length;
    for(int i = 0; i < shape.length; i++)
    {
        int yIncrement = height / (shape[i] + 1);
        int y = yIncrement + NEURON_SIZE / 2;
        for (int j = 0 ; j < shape[i]; j++)
        {
            NeuronVisualizer nv = new NeuronVisualizer(nn.getNeuron(i, j), x, y, NEURON_SIZE);
            neurons.add(nv);
            y += yIncrement;
        }
        x += xIncrement;
    }
}

void draw() {
    background(30);
    for(NeuronVisualizer n : neurons){
        n.draw();
    }
}
