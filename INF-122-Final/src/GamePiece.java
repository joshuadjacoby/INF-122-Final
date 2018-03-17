import java.awt.*;

public class GamePiece {
    private String name;
    private Color color;
    private int ownerNum; // 0 for no owner, 1 for player 1, 2 for player 2

    public GamePiece(String name, Color color, int ownerNum)
    {
        this.name = name;
        this.color = color;
        this.ownerNum = ownerNum;
    }

    public void setName(String name) { this.name = name; }

    public void setColor(Color color) { this.color = color; }

    public void setOwner(int ownerNum) { this.ownerNum = ownerNum; }

    public String getName() { return name; }

    public Color getColor() { return color; }

    public int getOwnerNum() { return ownerNum; }
}
