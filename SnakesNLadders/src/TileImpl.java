public class TileImpl implements Tile {

    private TileType type;
    private int position;
    private int destination;

    public TileImpl(TileType type) {
        this.type = type;
    }

    public TileImpl(TileType type, int dest) {
        this.type = type;
        this.destination = dest;
    }

    public int getPosition() {
        return position;
    }

    public int getDestination() {
        return destination;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    @Override
    public TileType getType() {
        return type;
    }

}
