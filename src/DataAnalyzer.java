
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataAnalyzer {

    public static void main(String[] args) {
        String[][] values;
        ReadFiles readFiles = new ReadFiles();
        File file = null;
        Path filePath = Paths.get("C:\\temp\\newMSC.csv");
        
        FileChecker fileChecker=()->{  
            if(readFiles.checkFile(filePath)){
                System.out.println("File exists");
                return true;
            }else{
                System.out.println("File not exists");
                return false;
            } 
        }; 
        
        if (fileChecker.isFileFound()) { 
            file = filePath.toFile();
            Calculations calculations = new Calculations(20, file);
            calculations.createDateArray();
            calculations.createDataArray();
            calculations.printDataArray();
            calculations.questionAnswer();
            calculations.createFile();
        }
    }
}
