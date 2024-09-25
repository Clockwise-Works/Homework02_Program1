package com.example.Homework02_Program1;

public class CustomColorInfo
{
    private int red;
    private int green;
    private int blue;
    private String hex;

    //constructor
    public CustomColorInfo()
    {

    }

    public CustomColorInfo(int red, int green, int blue, String hex)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hex = hex;
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }

    public String getHex()
    {
        return hex;
    }
}
