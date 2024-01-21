import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Logger {
    private ArrayList<String> logs = new ArrayList<>();

    public Logger() {
    }

    public void log(String info) {
        logs.add(info);
    }

    public void clear() {
        logs.clear();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String log : logs) {
            output.append(log).append("\n");
        }
        return output.toString();
    }

    public void dumpToLogFile(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
