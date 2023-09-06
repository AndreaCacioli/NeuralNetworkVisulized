float input = 0;
PFont font;
Neuron n;

final float increment = 0.25;

final int h = 512;
final int w = 512;
final int neuronSize = 200;
final int buttonPlusX = 10;
final int buttonPlusY = (h / 2) - 100;
final int buttonMinusX = 10;
final int buttonMinusY = (h / 2) + 80;
final int buttonSize = 50;
final int buttonTextOffset = 25;
final color buttonFillColour = color(40, 60, 70);
char selectedButton = ' ';
NeuronVisualizer nv;

 void settings(){
  size(h,w);
 }

void setup(){
  n = new Neuron(new SigmoidFunction());
  font = createFont("arialmt.ttf", 16);
  textFont(font, 16);
  nv = new NeuronVisualizer(n, w / 2, h /2, neuronSize);
}

void draw(){
  background(30);
  fill(color(255,255,255));
  text( "Neuron activation value", w/2 - 80, 50);
  n.setInput(input);
  text(n.getActivationValue() + "", w/2 - 10, 100);

  //Neuron
  nv.draw();

  //Button +
  noFill();
  if(selectedButton == '+') fill(buttonFillColour);
  rect(buttonPlusX, buttonPlusY, buttonSize , buttonSize);
  fill(color(255,255,255));
  text( "+" , buttonPlusX + buttonTextOffset, buttonPlusY + buttonTextOffset);

  //Input value display
  text( "input: " + input, buttonPlusX , h / 2);

  //Button -
  noFill();
  if(selectedButton == '-') fill(buttonFillColour);
  rect(buttonMinusX, buttonMinusY, buttonSize , buttonSize);
  fill(color(255,255,255));
  text( "-" , buttonMinusX + buttonTextOffset, buttonMinusY + buttonTextOffset);

  updateInput();

}

void mousePressed(){
  if(selectedButton == '+')
  {
    input += increment;
  }
  else if(selectedButton == '-')
  {
    input -= increment;
  }
}

void updateInput(){
  if(mouseX > buttonPlusX && mouseX < buttonPlusX + buttonSize && mouseY > buttonPlusY && mouseY < buttonPlusY + buttonSize)
  {
    selectedButton = '+';
  }
  else if(mouseX > buttonMinusX && mouseX < buttonMinusX + buttonSize && mouseY > buttonMinusY && mouseY < buttonMinusY + buttonSize)
  {
    selectedButton = '-';
  }
  else selectedButton = ' ';
  circle(mouseX, mouseY, 20);
}
