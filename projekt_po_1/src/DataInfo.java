
import java.io.IOException;


public class DataInfo {

    public static void main(String[] args) throws IOException {
        Logger logger = new Logger();

    //    processOneFile("src/data/data_a1.txt", "src/error_logs/error_log_a", "Assignment 1.1 + 1.7", logger);
    //    processOneFile("src/data/data_b1.txt", "src/error_logs/error_log_b", "Assignment 1.2 + 1.7", logger);
    //    processOneFile("src/data/data_c1.txt", "src/error_logs/error_log_c", "Assignment 1.3 + 1.7", logger);
    //    processOneFile("src/data/data_d1.txt", "src/error_logs/error_log_d", "Assignment 1.7", logger);
    //    processOneFile("src/data/data_e1.txt", "src/error_logs/error_log_e1", "Assignment 1.8 - data_e1.txt", logger);
    //    processOneFile("src/data/data_e2.txt", "src/error_logs/error_log_e2", "Assignment 1.8 - data_e2.txt", logger);
        processOneFile("src/data/data_e3.txt", "src/error_logs/error_log_e3", "Assignment 1.8 - data_e3.txt", logger);

    }

    static void processOneFile(String filePath, String logFilename, String title, Logger logger) throws IOException {
        logger.clear();
        FileContent fContent = IOHelper.readFile(filePath, logger);
        System.out.println(IOHelper.getOutputInfo(fContent, title));
        logger.dumpToLogFile(logFilename);
    }

}
