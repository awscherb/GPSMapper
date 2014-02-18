package com.awscherb.gps;


import java.awt.Color;

/** Represents a line segment */
public class LineSegment {
    /** Starting X position */
    double startX;
    /** Starting Y position */
    double startY;
    /** Ending X position */
    double endX;
    /** Ending Y position */
    double endY;
    /** Color to draw the line on the map */
    Color color;
    
    /** Public constructor */
    public LineSegment(double x, double y, double x2, double y2, Color color) {
        this.startX = x;
        this.startY = y;
        this.endX = x2;
        this.endY = y2;
        this.color = color;
    }

}
