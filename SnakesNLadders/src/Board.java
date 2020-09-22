public interface Board {

    public void initialize();
    public void movePlayer(Player player, int places);
    public Tile getTile(int n);

}
