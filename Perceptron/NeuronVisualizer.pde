class NeuronVisualizer {
    public Neuron n;
    public int x, y, size;
    public NeuronVisualizer(Neuron neuron, int x, int y, int size){
        n = neuron;
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(){
        color neuronColor = color((int)((1 - n.getActivationValue()) * 255),(int) (n.getActivationValue() * 255), 50);
        fill(neuronColor);
        circle(x, y, size);
        noFill();
    }
}
