
public class NeuralNetwork {

	private int inputLayerSize, outputLayerSize, hiddenLayers, hiddenLayerSize;
	
	private double[] inputs;
	private double[][] trainedOutputs;
	
	private Neuron[] inputNeurons;
	private Neuron[] outputNeurons;
	private Neuron[][] hiddenNeurons;

	public NeuralNetwork(int inputLayerSize, int outputLayerSize, int hiddenLayers, int hiddenLayerSize, double[] inputs, double[][] trainedOutputs) {
		this.inputLayerSize = inputLayerSize;
		this.outputLayerSize = outputLayerSize;
		this.hiddenLayers = hiddenLayers;
		this.hiddenLayerSize = hiddenLayerSize;
		this.inputs = inputs;
		this.trainedOutputs = trainedOutputs;
		
		startNetwork();
		
	}
	
	public void startNetwork(){
		createNetwork();
		populateInputs();
		forward();
	}
	
	public void populateInputs(){
		for(int i = 0; i < inputLayerSize; i++){
			inputNeurons[i].setValue(inputs[i]);
		}
	}

	private void createNetwork() {
		inputNeurons = new Neuron[inputLayerSize];
		outputNeurons = new Neuron[outputLayerSize];
		hiddenNeurons = new Neuron[hiddenLayers][hiddenLayerSize];

		// create objects
		for (int i = 0; i < inputLayerSize; i++) {
			inputNeurons[i] = new Neuron("0x" + i);
		}
		for (int i = 0; i < outputLayerSize; i++) {
			outputNeurons[i] = new Neuron((hiddenLayers + 1) + "x" + i);
		}

		for (int i = 0; i < hiddenLayers; i++) {
			for (int k = 0; k < hiddenLayerSize; k++) {
				hiddenNeurons[i][k] = new Neuron((i + 1) + "x" + k);
			}
		}

		// make connections

		//make input layer connect with first hidden layer
		for (int i = 0; i < inputLayerSize; i++) {
			for (int k = 0; k < hiddenLayerSize; k++) {
				double weight = Math.random();
				inputNeurons[i].addOutputConnection(new NeuronConnection(weight, hiddenNeurons[0][k], inputNeurons[i]));
				hiddenNeurons[0][k].addInputConnection(new NeuronConnection(weight, hiddenNeurons[0][k], inputNeurons[i]));
			}
		}
		
		//attempt to find neuron in another connection list
		for(int i = 0; i < hiddenLayers - 1; i++){
			for(int k = 0; k < hiddenLayerSize; k++){
				if(i + 1 < hiddenLayers){
					for(int j = 0; j < hiddenLayerSize; j++){
						double weight = Math.random();
						hiddenNeurons[i][k].addOutputConnection(new NeuronConnection(weight, hiddenNeurons[i+1][j], hiddenNeurons[i][k]));
						hiddenNeurons[i+1][j].addInputConnection(new NeuronConnection(weight, hiddenNeurons[i][k], hiddenNeurons[i+1][j]));
					}
					
				}
			}
		}
		
		//make last hidden layer connect to output layer
		for (int i = 0; i < outputLayerSize; i++) {
			for (int k = 0; k < hiddenLayerSize; k++) {
				double weight = Math.random();
				outputNeurons[i].addInputConnection(new NeuronConnection(weight, outputNeurons[i], hiddenNeurons[hiddenLayers - 1][k]));
				hiddenNeurons[hiddenLayers - 1][k].addOutputConnection(new NeuronConnection(weight, outputNeurons[i], hiddenNeurons[hiddenLayers - 1][k]));
			}
		}

	}

	public void forward() {
		
		for(int i = 0; i < hiddenLayers; i++){
			for(int k = 0; k < hiddenLayerSize; k++){
				hiddenNeurons[i][k].addInputWeights().ApplyActivationFunction();
			}
		}
		
		for(int i = 0; i < outputLayerSize; i++){
			outputNeurons[i].addInputWeights().ApplyActivationFunction();
		}
		
		
	}

	public Neuron[] getInputNeurons() {
		return inputNeurons;
	}

	public Neuron[] getOutputNeuron() {
		return outputNeurons;
	}

	public Neuron[][] getHiddenNeurons() {
		return hiddenNeurons;
	}
}
