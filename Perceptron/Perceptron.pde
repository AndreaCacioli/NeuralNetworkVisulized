int startingValue = 0;
PFont font;
Neuron n;

void setup(){
  size(512, 512);
  n = new Neuron(new SigmoidFunction());
  font = createFont("arialmt.ttf", 16);
  textFont(font, 16);
}

void draw(){
  background(30);
  text("n.getActivationValue()", 10, 100);
}
