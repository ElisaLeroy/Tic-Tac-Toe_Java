package game;
/**
 * UserInteraction
 * This class contains scanner to interact with user
 */
import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInteraction {
    public UserInteraction() {
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
