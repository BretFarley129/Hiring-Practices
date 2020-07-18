import java.lang.Integer;
import java.lang.String;

public class SimpleCharts{

    String[][] raceAndGender(String[][] arr){
        String[][] output = new String[11][3];
        System.out.println("Creating Form 2: Race and Gender");
        output[0][0] = "Lens";
        int x = 1;
        for (int i = 1; i < 8; i ++){
            output[i][0] = "Race";
            output[i][2] = arr[x][14]; 
            x += 2;
        }
        output[1][1] = "White";
        output[2][1] = "Asian";
        output[3][1] = "LatinX";
        output[4][1] = "Black";
        output[5][1] = "Multiracial";
        output[6][1] = "Pacific";
        output[7][1] = "Native";
        for (int i = 9; i < 11; i ++){
            output[i][0] = "Gender";
        }
        output[9][1] = "Men";
        output[9][2] = arr[1][15];
        output[10][1] = "Women";
        output[10][2] = arr[2][15];

        output = sanitize(output);
        return output;
    }
    String[][] highRankingPositions(String[][] arr){
        String[][] output = new String[15][3];
        System.out.println("Creating Form 3: Execs, Managers and Professionals");

        output[0][1] = "Execs and Managers";
        output[0][2] = "Professionals";

        for (int i = 1; i < 15; i++){
            // Demographic
            output[i][0] = arr[i][0];
            // Execs + Managers
            output[i][1] = String.valueOf(Integer.parseInt(arr[i][1]) + Integer.parseInt(arr[i][2]));
            // Professionals
            output[i][2] = arr[i][3];
        }

        output = sanitize(output);
        return output;
    }
    String[][] parliament(String[][] arr){
        String[][] output = new String[8][2];
        System.out.println("Creating Form 4: Parliament Chart Data");
        output[0][1] = "Execs and Professionals";
        output[1][0] = "White";
        output[2][0] = "Asian";
        output[3][0] = "LatinX";
        output[4][0] = "Black";
        output[5][0] = "Multiracial";
        output[6][0] = "Pacific";
        output[7][0] = "Native";
        for(int i = 1; i < output.length; i ++){
            int number = 0;
            int men = 1 + (i-1)*2;
            int women = 2 + (i-1)*2;
            for (int j = 1; j < 4; j ++){
                number += Integer.parseInt(arr[men][j]);
                number += Integer.parseInt(arr[women][j]);
            }
            output[i][1] = String.valueOf(number);
        }

        sanitize(output);
        return output;
    }
    String[][] y2yTrend(String[][] arr){
        String[][] output = new String[12][4];
        System.out.println("Creating Form 5: Year to Year Trend");
        output[0][2] = "Previous Year";
        output[0][3] = "Year of Report";
        for (int i = 1; i < 12; i ++){
            output[i][0] = "% change of workforce mix";
            output[i][1] = arr[i][0];
            output[i][2] = arr[i][21];
            output[i][3] = arr[i][18];
        }

        output = sanitize(output);
        return output;
    }

    String[][] sanitize(String[][] arr){
        for (int i = 0; i < arr.length; i ++){
            for (int j = 0; j < arr[i].length; j ++){
                if (arr[i][j] == null){
                    arr[i][j] = "";
                }
            }
        }
        return arr;
    }
    
}