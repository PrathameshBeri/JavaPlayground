public class PlayerImpl implements Player{


    private int position = 0;
    private String name = null;

    public PlayerImpl(String name){
        this.name = name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPosition(int currentPosition) {
        this.position = currentPosition;
    }

    @Override
    public void setName(String playerName) {
        this.name = playerName;
    }
}
