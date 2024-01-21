import java.util.ArrayList;
import java.util.stream.Collectors;



public class FileContent {

    private final ArrayList<Sensor> sensors;
    private final int noOfInvalidRecords;

    public FileContent(ArrayList<Sensor> sensors, int noOfInvalidRecords) {
        this.sensors = sensors;
        this.noOfInvalidRecords = noOfInvalidRecords;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors.stream().filter(s -> s.getLengthOfData() != 0).collect(Collectors.toCollection(ArrayList::new));
    }

    public int getNoOfInvalidRecords() {
        return noOfInvalidRecords;
    }

}
