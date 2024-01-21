public class ReadOut implements Comparable<ReadOut>{
    private final double value;
    
    public ReadOut(double value){
        this.value = value;
    }
    
    public double getValue(){
        return value;
    }
    
    @Override
    public int compareTo(ReadOut readout) {
        return Double.compare(this.value, readout.getValue());
    }
    
    @Override
    public String toString() {
        return String.format("%.3f", value);
    }
}
