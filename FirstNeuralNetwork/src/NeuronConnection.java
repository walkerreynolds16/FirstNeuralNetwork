
public class NeuronConnection {

	private String id;
	private double weight;
	private Neuron fromNueron;
	private Neuron toNeuron;
	
	public NeuronConnection(double weight, Neuron to, Neuron from){
		this.weight = weight;
		this.fromNueron = from;
		this.toNeuron = to;
		this.id = from.getId() + to.getId();
	}
	
	
	
	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}



	public String getId() {
		return id;
	}



	public Neuron getFromNueron() {
		return fromNueron;
	}



	public Neuron getToNeuron() {
		return toNeuron;
	}



	@Override
	public String toString(){
		return "[Weight = " + weight + ", From = " + fromNueron.getId() + ", To = " + toNeuron.getId() + " ]";
	}
	
	
	
	
}
