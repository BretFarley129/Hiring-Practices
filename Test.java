import java.util.ArrayList;
import java.io.File;

public class Test {

    public static void main (String[] args) {
        System.out.println("Test method running");
        System.out.println("Displaying command line arguements");

        // for (int i = 0; i < args.length; i ++){
        //     System.out.println("Command line argument " + i +": "+ args[i]);
        // }
        boolean cmd_display = false;
        String directory = "";
        String original;
        // First arg will be for input file
        if (args.length > 0){
            original = args[0];
            System.out.println("Reading from file: " + original);
            //Second arg will be for output folder
            if (args.length > 1){
                directory = args[1];
                System.out.println("Outputting to directory: " + directory);
                File oD = new File(directory);
                boolean newFolder = oD.mkdir();
                if (newFolder){
                    System.out.println("Directory created successfully");
                }
                else{
                    System.out.println("The specified directory could not be created.\n - Perhaps it already exists?");
                }
                directory = directory + "/";
                //Existance of Third arg will display csvs in terminal
                if (args.length > 2){
                    cmd_display = true;
                    System.out.println("CMD output activated");
                }
            }
        }
        
        
        CSV_handler csv_handle = new CSV_handler();
        
        // Legacy code
        // csv_handle.read_file();
        
        ArrayList<String[]> og_arr = csv_handle.csv_to_array("starting_data.csv");

        if (cmd_display){
            csv_handle.display_AL(og_arr);
        }

        lineBreak();

        String[][] inverse_arr = csv_handle.invert(og_arr);

        if (cmd_display){
            csv_handle.display_2d(inverse_arr);
        }

        csv_handle.writeCsv(inverse_arr, directory + "inverse.csv");

        lineBreak();

        String[][] sorted = csv_handle.sortByRace(inverse_arr);

        if (cmd_display){
            csv_handle.display_2d(sorted);
        }

        sorted = csv_handle.invert(sorted);
        // System.out.println("\n\n\n HERE \n\n\n");
        csv_handle.writeCsv(sorted, directory + "1_sorted_by_race.csv");

        OneB oneB = new OneB();
        String[][] invExp = oneB.run(inverse_arr);
        // csv_handle.display_2d(inverse_arr);
        lineBreak();

        if (cmd_display){
            csv_handle.display_2d(invExp);
        }

        String[][] expanded = csv_handle.invert(invExp);
        csv_handle.writeCsv(expanded, directory + "1B_percentage_breakdown.csv");

        // We will now use the inverted Expanded CSV to create the rest of the forms
        SimpleCharts sc = new SimpleCharts();
        String[][] cur = sc.raceAndGender(invExp);
        csv_handle.writeCsv(cur, directory + "2_gender_and_race.csv");

        cur = sc.highRankingPositions(invExp);
        csv_handle.writeCsv(cur, directory + "3_execs_and_professionals.csv");

        cur = sc.parliament(invExp);
        csv_handle.writeCsv(cur, directory + "4_parliament.csv");

        cur = sc.y2yTrend(invExp);
        csv_handle.writeCsv(cur, directory + "5_year_to_year.csv");
        

    }

    static void lineBreak(){
        System.out.println("\n-----------------------------------------------------------------------------\n");
    }
}