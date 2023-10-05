public class LearningTest {
  public static void main(String[] args) {
    
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

    double[][] usedDataset = andDataset;

    PerceptronNetwork p = new PerceptronNetwork(2);
    boolean finito = false;
    while(!finito){
        try{
            finito = p.learnOneStep(usedDataset, .25);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    System.out.println("finito");
    for(double[] example : usedDataset){
        try{
            p.setExample(example);
            p.propagate();
            var outs = p.getOutputs();
            System.out.println(outs[0]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
  }  
}
