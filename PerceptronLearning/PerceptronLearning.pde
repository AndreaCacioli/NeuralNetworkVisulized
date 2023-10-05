double [][] orDataset = new double[][]{
    {1, 1, 1},
    {1, 0, 1},
    {0, 1, 1}, 
    {0, 0, -1}
};

double [][] andDataset = new double[][]{
    {1, 1, 1},
    {1, 0, -1},
    {0, 1, -1}, 
    {0, 0, -1}
};

double [][] bigDataset = new double[2000][];


double[][] usedDataset = bigDataset;

PerceptronNetwork p = new PerceptronNetwork(2);

int pixelUnitMultiplier = 150; //Each unit is ninety pixels

void setup(){
    for(int i = 0 ; i < 1000; i++){
        float val1 = random(-1, 0);
        float val2 = random(-1, 0);
        bigDataset[i] = new double[]{val1, val2, -1};
    }
    for(int i = 1000 ; i < 2000; i++){
        float val1 = random(0, 1);
        float val2 = random(0,1);
        bigDataset[i] = new double[]{val1, val2, 1};
    }
    size(700, 700);
    print(p);
}

EvaluationFunction findFunction(PerceptronNetwork p){
    Neuron[] inputs = p.getInputNeurons();
    double w1 = p.getWeightFrom(inputs[0]);
    double w2 = p.getWeightFrom(inputs[1]);
    double bias = p.getBias();
    EvaluationFunction ret = new EvaluationFunction(){
        public double evaluate(double x){
            return -w1/w2 * x - (bias/w2); 
        }
    };
    println(w1);
    println(w2);
    println(bias);
    println();
    return ret;
}

boolean done = false;

void draw(){
    background(255);
    drawAxis();
    drawDataset(usedDataset, 20);
    try{
        done = p.learnOneStep(usedDataset, 0.25);
    }
    catch (Exception e) {
        e.printStackTrace();
        exit();
    }
    EvaluationFunction f = findFunction(p);
    drawFunction(f);
    color c = done ? color(0, 255 ,0) : color(255, 0 , 0);
    fill(c);
    circle(width / 2, 600, 50);
    if(done){
        Neuron[] inputs = p.getInputNeurons();
        double w1 = p.getWeightFrom(inputs[0]);
        double w2 = p.getWeightFrom(inputs[1]);
        double bias = p.getBias();
    }
    delay(500);
}

private void drawAxis(){
    line(0, height / 2, width, height / 2);
    line(width / 2,  0, width / 2 , height);
}

private double[] processing2Math(int x, int y){
    int centerX = width / 2;
    int centerY = height / 2;
    double[] ret = new double[2];
    ret[0] = (double)(x - centerX) / pixelUnitMultiplier;
    ret[1] = (double)(centerY - y) / pixelUnitMultiplier;
    return ret;
}

private float[] math2Processing(double x, double y){
    int centerX = width / 2;
    int centerY = height / 2;
    float[] ret = new float[2];
    ret[0] = (float)(centerX + pixelUnitMultiplier*x);
    ret[1] = (float)(centerY - pixelUnitMultiplier*y);
    return ret;
}

private void drawFunction(EvaluationFunction f)
{
    fill(0, 0 ,0);
    for(int x = 0 ; x < width; x++)
    {
        var mathCoordx = processing2Math(x, 123)[0];
        var mathCoordy = f.evaluate(mathCoordx);
        var processingCoords = math2Processing(mathCoordx, mathCoordy);
        circle(processingCoords[0], processingCoords[1], 2);
    }
}

private void drawDataset(double[][] dataset, double dotSize){
    for(double[] example : dataset){
        if(example[example.length - 1] == -1){
            fill(255, 0, 0);
        }
        else{
            fill(0, 255, 0);
        }
        float[] coords = math2Processing(example[0], example[1]);
        circle(coords[0], coords[1], (float)dotSize);
    }
}