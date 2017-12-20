import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
		double[] inputs = {3,5};
		double[][] trainedOutputs = {{75},{82},{93}};
		NeuralNetwork nn = new NeuralNetwork(2, 1, 1, 3, inputs);
		Neuron[] iN = nn.getInputNeurons();
		Neuron[] oN = nn.getOutputNeuron();
		Neuron[][] hN = nn.getHiddenNeurons();
		
		
		
		System.out.println(Arrays.toString(iN));
		
		String res = "";
		for(int i = 0; i < hN.length; i++){
			res += Arrays.toString(hN[i]) + ", ";
			
		}
		System.out.println(res.substring(0, res.length() - 2));
		
		System.out.println(Arrays.toString(oN));
		
	}

}
