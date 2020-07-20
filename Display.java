import java.util.Arrays;
public class Display {
    void print2d(String[][] arr){
        for (String[] row : arr){
            System.out.println(Arrays.toString(row));
        }
    }
    void lineBreak(){
        System.out.println("\n----------------------------------------------\n");
    }
}