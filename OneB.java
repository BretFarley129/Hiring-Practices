import java.util.Arrays;

//import com.sun.javafx.css.CalculatedValue;

import java.lang.Integer;
import java.lang.Double;
import java.lang.String;
import java.lang.Math;
public class OneB {
    String[][] run(String[][] arr){
        System.out.println("Creating form 1B");

        String[][] newArr = plotArray(arr);
        newArr = calcRaceTotals(newArr);
        newArr = calcGenderTotals(newArr);
        newArr = calcColumns(newArr);
        newArr = addPercent(newArr);
        return newArr;
    }

    String[][] plotArray(String[][] arr){
        // I might need to change this later but I think this is correct.
        // Verification for the size of the 2D array is needed.
        // as the function is set up right now, it will handle arrays that have
        // been inverted, so that each row (save the first and the last) will comprise
        // of one demographic
        String[][] newArr = new String[16][26];
        
        for (int i = 0; i < newArr.length; i ++){
            for (int j = 0; j < newArr[i].length; j ++){
                if (newArr[i][j] == null){
                    newArr[i][j] = "";
                }
            }
            // System.out.println(Arrays.toString(newArr[i]));
        }
        for (int i  = 0; i < arr.length; i ++){
            for(int j = 0; j < arr[i].length; j ++){
                if (arr[i][j] != null){
                    newArr[i][j] = arr[i][j];
                }
            }
        }

        String[] temp = {"" , "Executives & Sr Mgrs", "First line & Middle Mgrs", "Professionals", "Technicians", "Sales Workers", "Administrative", "Craft workers", "Operatives", "Laborers & Workers", "Service Workers", "Totals", "Previous Year", "", "Race totals", "Gender Totals", "", "", "% by race & gender", "% by race", "% by gender" , "% total by race & gender (previous year)", "Year to year net job change", "% net job change from previous year", "% change y2y in workforce mix", "% of yr to yr net job change"};

        newArr[0] = temp;

        return newArr;
    }

    String[][] calcRaceTotals(String[][]arr){
        for (int i = 1; i < arr.length -1; i +=2){
            // arr[i][14] = arr[i][1] +arr[i][2] +arr[i][3] +arr[i][4] +arr[i][5] +arr[i][6] +arr[i][7] +arr[i][8] +arr[i][9] +arr[i][10];
            arr[i][14] = String.valueOf(Integer.parseInt(arr[i][11])  + Integer.parseInt(arr[i+1][11]));
        }
        return arr;
    }
    String[][] calcGenderTotals(String[][]arr){
        int menTot = 0;
        int womenTot = 0;
        for (int i = 1; i < 15; i++){
            System.out.println(arr[i][0] + " | " + arr[i][11]);
            // Men
            if (i % 2 == 1 ){
                menTot += Integer.parseInt(arr[i][11]);
            }
            else{
                womenTot += Integer.parseInt(arr[i][11]);
            }
        }
        arr[1][15] = String.valueOf(menTot);
        arr[2][15] = String.valueOf(womenTot);
        return arr;
    }
    String[][] calcColumns(String[][] arr){
        //This is a bit of a confusing name, as it is techinically dealing with rows pre-inversion
        double total = Integer.parseInt(arr[15][11]);
        double prevTotal = Integer.parseInt(arr[15][12]);
        Integer diff = 0;
        double tracker = 0.0;
        for(int i = 1; i < 15; i ++){
            int demTot = Integer.parseInt(arr[i][11]);
            int prevDemTot = Integer.parseInt(arr[i][12]);
            arr[i][18] = String.valueOf(Math.round(demTot*1000 /total) / 10.0 );
            arr[i][21] = String.valueOf(Math.round(prevDemTot * 1000 / prevTotal) / 10.0);
            arr[i][22] = String.valueOf(demTot - prevDemTot);
            diff = diff + demTot - prevDemTot;
            //this will require clarification. It would appear that the formula I was given does not work as intended.
            Double something = Math.round((demTot - prevDemTot) * 1000 / prevTotal)/10.0;
            tracker += something;
            arr[i][23] = String.valueOf(something);
            arr[i][24] = String.valueOf(Math.round(Double.parseDouble(arr[i][18]) - Double.parseDouble(arr[i][21])*10.0) / 10.0);


        }
        arr[15][22] = String.valueOf(total - prevTotal);
        arr[15][23] = String.valueOf(tracker);

        for(int i = 1; i < 15; i ++){
            int target = Integer.parseInt(arr[i][22]);
            arr[i][25] = String.valueOf(Math.round(target*1000 / diff) / 10.0);
        }

        for(int i = 1; i < 15; i +=2){
            arr[i][19] = String.valueOf(Double.parseDouble(arr[i][18]) + Double.parseDouble(arr[i+1][18]));            
        }
        arr[1][20] = String.valueOf(Math.round(Double.parseDouble(arr[1][15])* 1000 / total) /10.0);
        arr[2][20] = String.valueOf(Math.round(Double.parseDouble(arr[2][15])* 1000 / total) /10.0);

        return arr;
    }
    String[][] addPercent(String[][] arr){
        for (int i = 1; i < 15; i ++){
            for (int j = 19; j < 26; j ++){
                if (j == 22){
                    continue;
                }
                else if(arr[i][j] == null || arr[i][j] == ""){
                    continue;
                }
                else{
                    arr[i][j] = arr[i][j] + "%";
                }
            }
        }
        return arr;
    }
}