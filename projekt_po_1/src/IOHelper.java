import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class IOHelper {

    static String delimiter1 = " ";
    static String delimiter2 = " id:";

    public static FileContent readFile(String filePath, Logger logger) throws IOException {
        Sensor dummySensor = new Sensor("<N/A>");
        ArrayList<Sensor> sensors = new ArrayList<>();
        sensors.add(dummySensor);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int noOfInvalidRecords = 0;
        String line;
        String uuid;
        while ((line = reader.readLine()) != null) {
            try {
                if (line.contains("id:")) {
                    line = line.replaceAll(delimiter2, delimiter1);
                    String[] parts = line.split(delimiter1, 3);
                    uuid = parts[1].trim();
                    double value = Double.parseDouble(parts[0].trim());
                    ReadoutWithUuid readoutWithUuid = new ReadoutWithUuid(value, uuid);
                    if (parts.length > 2) {
                        addReadOutToSensor(sensors, parts[2], readoutWithUuid);
                    } else {
                        dummySensor.addReadout(readoutWithUuid);
                    }
                } else {
                    ReadOut rdata = new ReadOut(Double.parseDouble(line));
                    dummySensor.addReadout(rdata);
                }
            } catch (NumberFormatException ex) {
                logger.log("Faulty record in [" + filePath + "]: " + line);
                noOfInvalidRecords++;
            }
        }
        reader.close();
        return new FileContent(sensors, noOfInvalidRecords);
    }

    public static void addReadOutToSensor(ArrayList<Sensor> sensorList, String sensorName, ReadOut readout) {
        for (Sensor sensor : sensorList) {
            if (sensor.getName().equals(sensorName)) {
                sensor.addReadout(readout);
                return;
            }
        }

        Sensor newSensor = new Sensor(sensorName);
        newSensor.addReadout(readout);
        sensorList.add(newSensor);
    }

    public static String getOutputInfo(FileContent fContent, String title) {
        String output = "";
        output += "Żurawicki Michał, 285298\n\n";
        output += "Numbers of sensors: " + fContent.getSensors().size() + "\n";
        output += title + "\n";
        for (Sensor sensor : fContent.getSensors()) {
            Optional<Double> mean = sensor.getMean();
            Optional<ReadOut> max = sensor.getMax();
            Optional<ReadOut> min = sensor.getMin();
            Optional<MedianWrapper> median = sensor.getMedian();
            output += "********************************\n";
            output += "Sensor name: " + sensor.getName();
            output += "\n********************************\n";
            output += "Length of the series: " + String.format("%d", sensor.getLengthOfData()) + "\n";
            if (max.isPresent()) output += "Max. value: " + max.get() + "\n";
            if (min.isPresent()) output += "Min. value: " + min.get() + "\n";
            if (mean.isPresent()) output += "Mean value: " + String.format("%.3f", mean.get()) + "\n";
            if (median.isPresent()) output += "Median: " + median.get() + "\n";
            if (max.isPresent() && min.isPresent()) {
                output += "Number of central elements: " + String.format("%d", sensor.noOfCentralElements(mean.get(), (max.get().getValue() - min.get().getValue()) / 100)) + "\n";
            }
        }
        output += "--------------------------------\n";
        output += "Number of invalid records: " + String.format("%d", fContent.getNoOfInvalidRecords()) + "\n";
        return output;
    }

}
