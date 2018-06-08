import java.lang.*;
//*****************************************************************
// Name: Casey Nord
// Class: CS162 Spring 2018
// Class Time: Mon/Wed/Fri 10:00am
// Date: June 12, 2018
// Final Project
// Driver Name: Main.java
// Program Description: Animated sorting program
//*****************************************************************

import javafx.event.ActionEvent;

public interface SortScreenInterface
{
    public void drawBars(int[] array);
    public void highlightSwap(int[] array, int index, int color);
    public void drawArray(int[] array);
    public void drawHighlightedArray(int[] array, int firstSwap, int secondSwap);
    public void keyFrameSwap(int[] array, int swapOne, int swapTwo);
}