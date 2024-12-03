// initialize the bomb disc with an owner.
public class BombDisc implements Disc {
    private Player owner;

    public BombDisc(Player owner) {
        this.owner = owner;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    @Override
    public String getType() {
        return "\uD83D\uDCA3";
    }
}