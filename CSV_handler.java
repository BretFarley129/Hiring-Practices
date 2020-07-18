import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.lang.StringBuilder;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

public class CSV_handler {
    void statement(){
        System.out.println("Class CSV_reader has been activated");
    }

    void read_file(){
        try{
            // "grid" will represent the original csv file as it currently is
            ArrayList<String[]> grid = new ArrayList<String[]>();
            
            File f = new File("starting_data.csv");
            File filepath = f.getAbsoluteFile();
            // System.out.println(filepath);
            FileReader fr = new FileReader(filepath);
            BufferedReader csvReader = new BufferedReader(fr);
            // System.out.println(csvReader.readLine());
            
            //While loop to convert csv into a 2D array
            Boolean cont = true;
            while ( cont ) {
                String row = csvReader.readLine();
                // System.out.println(row);
                if (row == null){
                    cont = false;
                    for (int i = 0; i < grid.size(); i ++){
                        String[] cur = grid.get(i);
                        System.out.println(Arrays.toString(cur));
                    }
                    System.out.println("!!!!!\n\n!!!!!!");
                    break;
                }
                String[] data = row.split(",");
                // System.out.println(Arrays.toString(data));
                grid.add(data);
            }

            // "inverse" will represent the csv but inversed. This will allow us to easily access all
            // data for one demographic

            // Fetch horizontal length
            int width = grid.get(0).length;
            System.out.println(width);

            // Fetch vertical length
            int height = grid.size();
            System.out.println(height);

            String[][] inverse = new String[width][height];

            //Testing Purposes
            for (int i = 0; i < inverse.length; i++){
                for (int j = 0; j < inverse[i].length; j ++){
                    inverse[i][j] = "X";
                }
                System.out.println(Arrays.toString(inverse[i]));
            }

            // ArrayList<Arraylist<String>> inverse = new ArrayList<Arraylist<String>>();


            // Create inverse 2D array
            for (int i = 0; i < grid.size(); i ++){
                for (int j = 0; j < grid.get(i).length; j ++){
                    String cur = grid.get(i)[j];
                    System.out.println(i + " | " + j + " | " + cur);
                    inverse[j][i] = cur;
                }
            }

            for (int i = 0; i < inverse.length; i ++){
                System.out.println(Arrays.toString(inverse[i]));
            }
            
            BufferedWriter br = new BufferedWriter(new FileWriter("inverse.csv"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inverse.length; i ++){

                for (String element : inverse[i]) {
                    sb.append(element);
                    sb.append(",");
                }
                sb.append("\n");
            }
                
            br.write(sb.toString());
            br.close();
            
            //Close opened files
            fr.close();
            csvReader.close();
        }
        catch(Exception e){
            System.out.println("The file could not be found");
        }
    }
    
    ArrayList<String[]> csv_to_array(String csv_file){
        //initialize method output
        ArrayList<String[]> grid = new ArrayList<String[]>();

        try{
            // Prepare file handling
            File f = new File(csv_file);
            File filepath = f.getAbsoluteFile();
            FileReader fr = new FileReader(filepath);
            BufferedReader csvReader = new BufferedReader(fr);
            

            //While loop to convert csv into a 2D array
            Boolean cont = true;
            while ( cont ) {
                String row = csvReader.readLine();

                // EXIT CONDITION
                if (row == null){
                    cont = false;
                    break;
                }

                //Manipulate data format
                String[] data = row.split(",");
                grid.add(data);
            }

            //Sanitize and return
            csvReader.close();
            fr.close();

        }
        catch(Exception e){
            System.out.println("The File could not be found");
        }
        return grid;
    }

    String[][] invert(ArrayList<String[]> arr){

        // Fetch original length and width
        int width = arr.get(0).length;
        int height = arr.size();

        // Init output
        String[][] inverse = new String[width][height];

        for (int i = 0; i < arr.size(); i ++){
            for (int j = 0; j < arr.get(i).length; j ++){
                String cur = arr.get(i)[j];
                inverse[j][i] = cur;
            }
        }


        return inverse;
        
    }

    String[][] invert(String[][] arr){

        // Fetch original length and width
        int width = arr[0].length;
        int height = arr.length;

        // Init output
        String[][] inverse = new String[width][height];

        for (int i = 0; i < arr.length; i ++){
            for (int j = 0; j < arr[i].length; j ++){
                String cur = arr[i][j];
                inverse[j][i] = cur;
            }
        }


        return inverse;
        
    }

    String[][] sortByAlph(String[][] arr){
        // This method will only work with the inverted original array
        // its purpose is to group demographics by race as opposed to 
        // whatever orginization the government forms use

        for (int i = 1; i < arr.length - 1; i ++){
            for (int j = i + 1; j < arr.length - 1; j++){
                if (arr[i][0].compareTo(arr[j][0]) > 0){
                    String[] temp  = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    String[][] sortByRace(String[][] arr){
        System.out.println("In sortbyRace");
        // This method will only work with the inverted original array
        // its purpose is to group demographics by race as opposed to 
        // whatever orginization the government forms use
        
        String[] temp = arr[1];
        arr[1] = arr[3];
        arr[3] = arr[6];
        arr[6] = arr[2];
        arr[2] = arr[9];
        arr[9] = arr[8];
        arr[8] = arr[10];
        arr[10] = arr[14];
        arr[14] = arr[13];
        arr[13] = arr[7];
        arr[7] = arr[4];
        arr[4] = arr[12];
        arr[12] = arr[11];
        arr[11] = arr[5];
        arr[5] = temp;
        System.out.println("Terminating sortbyRace");
        return arr;
    }

    void writeCsv(String[][] arr, String destination){
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter(destination));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i ++){
    
                for (String element : arr[i]) {
                    sb.append(element);
                    sb.append(",");
                }
                sb.append("\n");
            }
                
            br.write(sb.toString());
            br.close();

        }
        catch(Exception e){
            System.out.println("The File could not be written");
        }
        
    }

    void display_AL(ArrayList<String[]> aList){
        System.out.println(aList.size());
        for (int i = 0; i < aList.size(); i ++){
            String[] cur = aList.get(i);
            System.out.println(Arrays.toString(cur));
        }
    }
    void display_2d(String[][] arr){
        for (String[] row : arr){
            System.out.println(Arrays.toString(row));
        }
    }

}