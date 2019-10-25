import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.*;
import java.util.StringTokenizer;

public class ReadFiles {

    public boolean checkFile(Path filePath) {

        try {
            if (Files.exists(filePath)) {
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("IO Exception");
            return false;
        }
        return true;
    }

    public int numberOfRow(File file) {

        int rowCount = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input;
            while ((bufferedReader.readLine()) != null) {
                rowCount++;
            }

            System.out.println("Total Number Of Row Is : " + rowCount);
        } catch (Exception e) {

        }
        return rowCount;
    }

    public int numberOfCol(File file) {
        
        int colCount = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                colCount = input.split(",").length;
                return colCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colCount;
    }

    public String[][] getArray(File file, String[][] values) {
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input;
            int row = 0;
            int col = 0;
            while ((input = bufferedReader.readLine()) != null && row < 20) {
                StringTokenizer st = new StringTokenizer(input, ",");
                while (st.hasMoreTokens()) {
                    values[row][col] = st.nextToken();
                    col++;
                }
                col = 0;
                row++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return values;
    }

    public void printArray(String[][] values) {
        
        int row;
        int col;
        boolean gotValue = true;
        
        for (row = 0; row < values.length; row++) {
            for (col = 0; col < values[row].length; col++) {
                if (values[row][col] != null) {

                    if (row == 0) {
                        System.out.print(values[row][col].trim());
                        System.out.print("\t\t");
                    } else {
                        System.out.print(values[row][col]);
                        System.out.print("\t  ");
                    }

                } else {
                    gotValue = false;
                    break;
                }
            }
            if (gotValue) {
                System.out.println("");
            } else {
                break;
            }
        }
    }
}
