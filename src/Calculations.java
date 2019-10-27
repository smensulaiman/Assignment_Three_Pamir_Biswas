
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Calculations {

    private final int rowNumber;
    private final File file;
    Date[] dateArray;
    Date[] sortedDateByOpeningValues;
    Date[] sortedDateByClosingValues;
    float[][] dataArray;

    public Calculations(int rowNumber, File file) {
        this.rowNumber = rowNumber;
        this.file = file;
    }

    public void createDateArray() {
        dateArray = new Date[rowNumber];
        int index = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input = null;
            while ((input = bufferedReader.readLine()) != null && index != rowNumber) {
                StringTokenizer st = new StringTokenizer(input, ",");
                String strDate = st.nextToken();
                if (strDate.toLowerCase().contains("date")) {
                    continue;
                } else {
                    dateArray[index] = new SimpleDateFormat("MM/dd/yyyy").parse(strDate);
                    index++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDataArray() {
        dataArray = new float[rowNumber][6];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input;
            int row = 0;
            int col = 0;
            while ((input = bufferedReader.readLine()) != null && row < rowNumber) {
                StringTokenizer st = new StringTokenizer(input, ",");
                while (st.hasMoreTokens()) {
                    if (col == 0) {
                        st.nextToken();
                    }
                    dataArray[row][col] = Float.parseFloat(st.nextToken());
                    col++;
                }
                col = 0;
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDataArray() {
        System.out.println("\nOpen	\tHigh	\tLow	\tClose	\tAdj Close\tVolume");
        for (float rowIndex[] : dataArray) {
            for (float colIndex : rowIndex) {
                System.out.print(colIndex + "\t\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void questionAnswer() {

        float[] openingValues = new float[dataArray.length];
        float[] closingValues = new float[dataArray.length];

        for (int row = 0; row < dataArray.length; row++) {
            openingValues[row] = dataArray[row][0];
        }

        for (int row = 0; row < dataArray.length; row++) {
            closingValues[row] = dataArray[row][3];
        }

        sortedDateByOpeningValues = sortData(openingValues);
        sortedDateByClosingValues = sortData(closingValues);

        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sortedDateByOpeningValues[sortedDateByOpeningValues.length - 1]) + " date had the highest opening value");
        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sortedDateByClosingValues[sortedDateByClosingValues.length - 1]) + " date had the highest closing value");
        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sortedDateByOpeningValues[0]) + " date had the lowest opening value");
        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sortedDateByClosingValues[0]) + " date had the lowest closing value");
    
        System.out.println("\nTop ten dates with the highest closing value\n");
        
        for(int i = 0 ; i<10 ; i++){
            System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sortedDateByClosingValues[10-i]));
        }
        System.out.println("\nTop ten dates with the lowest closing value\n");
        
        for(int i = 0 ; i<10 ; i++){
            System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(sortedDateByClosingValues[i]));
        }
    }

    public Date[] sortData(float[] array) {
        Date[] date = new Date[dateArray.length];
        for (int i = 0; i < dateArray.length; i++) {
            date[i] = dateArray[i];
        }
        boolean sorted = false;
        float temp;
        Date tempDate;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    tempDate = date[i];
                    array[i] = array[i + 1];
                    date[i] = date[i + 1];
                    array[i + 1] = temp;
                    date[i + 1] = tempDate;
                    sorted = false;
                }
            }
        }
        return date;
    }

    public void createFile() {
        File file = new File("C:\\temp\\updateMSC.csv");
        Path filePath = Paths.get(file.getAbsolutePath());
        if (Files.exists(filePath)) {
            System.out.println(file.toString() + " already exists");
        } else {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String strData = "Date,Open,High,Low,Close,Adj Close,Volume,Difference \n";
        for (int index = 0; index < dateArray.length; index++) {
            strData += new SimpleDateFormat("MM/dd/yyyy").format(dateArray[index]);
            for (int row = 0; row < dataArray[index].length; row++) {
                strData += "," + String.valueOf(dataArray[index][row]);
            }
            strData += "," + (dataArray[index][0] - dataArray[index][3]);
            strData += "\n";
        }
        try {
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(filePath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write(strData, 0, strData.length());
            writer.close();
        } catch (Exception e) {
            System.out.println("Message: " + e);
        }
    }

}
