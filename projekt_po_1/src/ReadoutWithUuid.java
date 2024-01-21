public class ReadoutWithUuid extends ReadOut{
    private final String uuid;
    
    public ReadoutWithUuid(double value, String uuid){
        super(value);
        this.uuid = uuid;
    }
    
    @Override
    public String toString() {
    return String.format("%.3f", getValue())+" ["+uuid+"]";
    }

    
}
