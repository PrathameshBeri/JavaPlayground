import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnakesAndLadders {

    public static void main(String args[]){

        Board board = new BoardImpl();
        List<Player> players = new ArrayList<>();
        System.out.println("How many players ?");

        Scanner scan = new Scanner(System.in);
        int noOfPlayers = scan.nextInt();

        for(int i = 0; i < noOfPlayers; i++){
            System.out.println("Enter name for player "+ i);
            String name = scan.next();
            Player player = new PlayerImpl(name);
            players.add(player);

        }

        board.initialize();
        int counter = 0;
        System.out.println("Lets Play!");
        String choice = "";
        SecureRandom random = new SecureRandom();
        do{
            if(counter >= noOfPlayers) counter = 0;
            Player currPlayer = players.get(counter);
            System.out.println(" Player " + currPlayer.getName() + " turn to play!");
            System.out.println(" R = Roll the dice, Q = quit");
            choice = scan.next();
            if(choice.equalsIgnoreCase("R")){
                int places = random.nextInt(6) + 1;
                board.movePlayer(currPlayer, places);
                counter++;

            }
        }while(!choice.equalsIgnoreCase("Q"));
    }
}
