package ca.conestogac.plu.dieball;

public class SnakePoints
{
    private int positionX, positionY;

    public SnakePoints(int positionX, int positionY)
    {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public void setPositionX(int positionX)
    {
        this.positionX = positionX;
    }

    public int getPositionY()
    {
        return this.positionY;
    }

    public void setPositionY(int positionY)
    {
        this.positionY = positionY;
    }
}
