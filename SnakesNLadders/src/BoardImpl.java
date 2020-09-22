import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardImpl implements Board{

    Tile[] board = new Tile[101];
    List<Player> players = new ArrayList<>();
    SecureRandom secureRandom = new SecureRandom();
    Set<Integer> occupiedPositions = new HashSet<>();

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Tile[] getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayers(Player player) {
        this.players.add(player);
    }

    @Override
    public void initialize() {
        System.out.println("Initializing board");

        int snakes = secureRandom.nextInt(15) + 1;
        int ladders = secureRandom.nextInt(15) + 1;

        setSnakes(snakes);
        setLadders(ladders);

        for(int i = 0; i < 101; i++){
            if(board[i] != null){
                continue;
            }
            Tile t  = new TileImpl(TileType.NORMAL);
            board[i] = t;
        }


    }

    @Override
    public void movePlayer(Player player, int places) {
        int currentPosition = player.getPosition();
        String checkPos = checkEndingPosition(currentPosition, places);
        if(checkPos.equals("winner")){
                winner(player);
        }
        if(checkPos.equals("outOfBoard")){
            System.out.println("That move cannot be made from your position! " +
                    "Roll a different number next time");
        }
        if(checkPos.equals("valid")){
                Tile t = getTile(currentPosition + places);
                if(t.getType().equals(TileType.NORMAL)){
                    player.setPosition(currentPosition + places);
                    System.out.println("Player " + player.getName() +
                            " moves to position "+ player.getPosition());
                }else {
                    player.setPosition(t.getDestination());
                    System.out.println(" Player " + player.getName() +
                            " encountered a "+ t.getType()+ "!!, moves to position "+ player.getPosition());
                }
        }
    }

    @Override
    public Tile getTile(int n) {
        return board[n];
    }

    public String checkEndingPosition(int currentPosition, int places){

        if((currentPosition + places) > 100){
            return "outOfBoard";
        }else if((currentPosition+ places) == 100){
            return "winner";
        }
        else{
            return "valid";
        }
    }

    public void winner(Player player){

        System.out.print("Player " + player.getName() + " has won the game !");
        System.exit(0);
    }

    public void setSnakes(int n){

        for(int i = 0; i < n; i ++){
            boolean flag = true;
            int start = 0;
            int dest = 0;

            while(flag) {
                 start = secureRandom.nextInt(98);
                if(!occupiedPositions.contains(start)){
                    occupiedPositions.add(start);
                    break;
                }
            }

            while(flag) {
                 dest = (int)(start * secureRandom.nextDouble());
                if(!occupiedPositions.contains(dest)){
                    occupiedPositions.add(dest);
                    break;
                }
            }

            Tile tile = new TileImpl(TileType.SNAKE, dest);
            board[start] = tile;

            System.out.println("Created snake " + "[" + start+ "," + dest + "]");
        }

    }

    public void setLadders(int n) {

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int start = 0;
            int dest = 0;

            while (flag) {
                start = secureRandom.nextInt(80);
                if (!occupiedPositions.contains(start)) {
                    occupiedPositions.add(start);
                    break;
                }
            }

            while (flag) {
                dest = (int) (start + secureRandom.nextDouble() * 10);
                if (!occupiedPositions.contains(dest)) {
                    occupiedPositions.add(dest);
                    break;
                }
            }

            Tile tile = new TileImpl(TileType.LADDER, dest);
            board[start] = tile;
            System.out.println("Created ladder " + "[" + start+ "," + dest + "]");
        }
    }
}
