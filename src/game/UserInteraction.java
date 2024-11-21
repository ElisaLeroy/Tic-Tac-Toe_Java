package game;
/**
 * UserInteraction
 * This class contains scanners to interact with user
 */
import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInteraction {
    View view;
    public UserInteraction() {
        view = new View();
    }

    /**
     * scannerInt
     * @return int that represent the user's choice
     */
    public int scannerInt() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }







}
