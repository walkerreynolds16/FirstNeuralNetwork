import java.util.ArrayList;

public class Neuron {

	private String id;
	private ArrayList<NeuronConnection> inputConnections;
	private ArrayList<NeuronConnection> outputConnections;
	private double value;

	public Neuron(){
		this.inputConnections = new ArrayList<>();
		this.outputConnections = new ArrayList<>();
		this.value = 0;
	}
	
	public Neuron(String id) {
		this.id = id;
		this.inputConnections = new ArrayList<>();
		this.outputConnections = new ArrayList<>();
		this.value = 0;
	}
	
	public Neuron(String id, double value) {
		this.id = id;
		this.inputConnections = new ArrayList<>();
		this.outputConnections = new ArrayList<>();
		this.value = value;
	}

	public Neuron(String id, double value, ArrayList<NeuronConnection> inputConnections,
		ArrayList<NeuronConnection> outputConnections) {
		this.inputConnections = inputConnections;
		this.outputConnections = outputConnections;
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<NeuronConnection> getInputConnections() {
		return inputConnections;
	}

	public void addInputConnection(NeuronConnection connection) {
		this.inputConnections.add(connection);
	}

	public ArrayList<NeuronConnection> getOutputConnections() {
		return outputConnections;
	}

	public void addOutputConnection(NeuronConnection connection) {
		this.outputConnections.add(connection);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public Neuron addInputWeights(){
		double res = 0;
		for(int i = 0; i < inputConnections.size(); i++){
			double value = inputConnections.get(i).getFromNueron().getValue();
			double weight = inputConnections.get(i).getWeight();
			res += (value * weight);
		}
		
		this.value = res;
		return this;
	}
	
	public Neuron ApplyActivationFunction(){
		value = 1 / (1 + Math.exp(-value));
		return this;
	}
	
	@Override
	public String toString(){
		String res = "";
		res += "Id = " + id + "\n";
		res += "Value = " + value + "\nInput Connections : \n";
		
		if(inputConnections.size() != 0){
			for(NeuronConnection c : inputConnections){
				res += c.toString() + "\n";
			}
			
		}else {
			res += "None\n";
		}
		
		res += "Output Connections : \n";
		
		if(outputConnections.size() != 0){
			for(NeuronConnection c : outputConnections){
				res += c.toString() + "\n";
			}
		}else {
			res += "None\n";
		}
		
				
				
		return res;
	}

}
