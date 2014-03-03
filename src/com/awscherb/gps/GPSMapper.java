package com.awscherb.gps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Draws a map based on given GPS coordinates. Lines can be 
 * drawn between points. 
 * Designed for creating geographically accurate transit maps
 * @author Alex Scherb
 * @version 2/18/14
 */
public class GPSMapper extends JPanel {

    private static final long serialVersionUID = 1L;

    // We calculate the x, y coordinates for the input points based
    // on their distance from the center point. 
    // Center point actually refers to the top-left position on the map

    // Size, canvas info, graphics constants
    /** Zoom level of map */
    private int zoomLevel = 1;
    /** Constant to scale by each time with scale */
    private final int SCALE_CONSTANT = 2;
    /** Constant to adjust coordinate by when arrow key is pressed */
    private final int KEY_CONSTANT = 10;
    /** Color to draw point circles */
    private final Color PT_COLOR = Color.WHITE;
    /** Color to draw paths */
    private final Color PATH_COLOR = Color.CYAN;
    /** Color to draw point labels */
    private final Color LABEL_COLOR = Color.WHITE;
    /** Size to draw points */
    private final int PT_SIZE = 5;
    /** Width of lines */
    private final int LINE_SIZE = 6;
    /** Constant to scale x points on map */
    private int XSCALE = 1000;
    /** Constant to scale y points by on map */
    private int YSCALE = 500;
    /** Default rotation for text label */
    private final int ROTATION = -30;
    /** Intersection point on a path */
    public int TRANSFER = 1;
    /** End point of a path */
    public int ENDPOINT = 2;

    // Point information
    /** Center point */
    private MapPoint center;
    /** Distance movey from center-x */
    private int dXc = 0;
    /** Distance moved from center-y */
    private int dYc = 0;

    // Storing points, lines,
    /** ArrayList of all the points */
    private ArrayList<CartPT> points;
    /** ArrayList of line segments */
    private ArrayList<LineSegment> lines;
    /** ArrayList of points on paths */
    private ArrayList<CartPT> pathPoints;
    /** ArratList of line segments for paths */
    private ArrayList<LineSegment> paths;

    // Switches
    /** Toggle labels */
    private boolean labels = true;
    /** Toggle debug info */
    private boolean debug = false;
    /** Toggle for showing paths */
    private boolean showPaths = true;

    ///////////////////////////////////////////////////////////////////////////
    // Constructor, factory method

    /** Private constructor */
    private GPSMapper() {
        setVisible(true);
        setFocusable(true);
        points = new ArrayList<CartPT>();
        lines = new ArrayList<LineSegment>();
        pathPoints = new ArrayList<CartPT>();
        paths = new ArrayList<LineSegment>();
    }

    /** Public static factory method */
    public static GPSMapper factory() {
        return new GPSMapper();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Point manipulation

    /** Add the given point to the map */
    public void addPoint(MapPoint g) {
        double dx = XSCALE*(g.lon - center.lon);
        double dy = YSCALE*(center.lat - g.lat);

        double ax = (dx * WIDTH);
        double ay = (dy * HEIGHT);

        // Point has a specific rotation, apply its rotation
        if (g.rotation != 0) {
            CartPT pt = new CartPT(ax, ay, g.label, g.type);
            pt.setRotation(g.rotation);
            this.points.add(pt);
        }
        // Point has no specific rotation, apply constant
        else {
            CartPT pt = new CartPT(ax, ay, g.label, g.type);
            pt.setRotation(ROTATION);
            this.points.add(pt);
        }
    }

    /** Add all the given points to the map */
    public void addPoints(ArrayList<ArrayList<MapPoint>> input, Color c) {
        for (ArrayList<MapPoint> l : input) {
            ArrayList<CartPT> temp = new ArrayList<CartPT>();

            for (MapPoint g : l) { // Convert GPS to Cartesian
                double dx = XSCALE*(g.lon - center.lon);
                double dy = YSCALE*(center.lat - g.lat);

                double ax = (dx * WIDTH);
                double ay = (dy * HEIGHT);

                // Point has specific rotation for label
                if (g.rotation != 0) {
                    CartPT pt = new CartPT(ax, ay, g.label, g.type);
                    pt.setRotation(g.rotation);
                    temp.add(pt);
                }
                // Point has no specific rotation, apply constant
                else {
                    CartPT pt = new CartPT(ax ,ay, g.label, g.type);
                    pt.setRotation(ROTATION);
                    temp.add(pt);
                }
            }

            // Calculate line segments
            for (int i = 0; (i + 1) < temp.size(); i = i + 1) {
                CartPT pt1 = temp.get(i);
                CartPT pt2 = temp.get(i + 1);

                LineSegment seg = new LineSegment(
                        pt1.x, pt1.y, pt2.x, pt2.y, c);
                this.lines.add(seg);
            }
            points.addAll(temp); // Add loop temporary list to actual list
        }
    }

    /** Move all coordinates */
    private void moveAll(int x, int y) {
        // Adjust center
        dXc += x;
        dYc += y;

        // Move points
        for (CartPT c : points) {
            c.x += (x);
            c.y += (y);

        }
        // Move path points
        for (CartPT c : pathPoints) {
            c.x += (x);
            c.y += (y);
        }
        // Move line segments
        for (LineSegment l : lines) {
            l.endX += (x);
            l.endY += (y);
            l.startX += (x);
            l.startY += (y);
        }
        // Move all path segments
        for (LineSegment ln : paths) {
            ln.endX += (x);
            ln.endY += (y);
            ln.startX += (x);
            ln.startY += (y);
        }

        repaint();
    }

    /** Zoom by the given amount (usually SCALE_CONSTANT) */
    public void zoom(int s) {
        if (s > 0) { // s is positive, multiply all points
            dXc = dXc*s;
            dYc = dYc*s;
            for (CartPT c : points) {
                c.x = (c.x*s);
                c.y = (c.y*s);

            }
            for (CartPT c : pathPoints) {
                c.x = (c.x*s);
                c.y = (c.y*s);
            }
            for (LineSegment l : lines) {
                l.endX = (l.endX*s);
                l.endY = (l.endY*s);
                l.startX = (l.startX*s);
                l.startY = (l.startY*s);
            }
            for (LineSegment ln : paths) {
                ln.endX = ln.endX*s;
                ln.endY = ln.endY*s;
                ln.startX = ln.startX*s;
                ln.startY = ln.startY*s;
            }
        } else { // s is negative, divide by absolute value of s
            int m = Math.abs(s);
            dXc = dXc/m;
            dYc = dYc/m;
            for (CartPT c : points) {
                c.x = c.x/m;
                c.y = c.y/m;

            }
            for (CartPT c : pathPoints) {
                c.x = c.x/m;
                c.y = c.y/m;
            }
            for (LineSegment l : lines) {
                l.endX = l.endX/m;
                l.endY = l.endY/m;
                l.startX = l.startX/m;
                l.startY = l.startY/m;
            }
            for (LineSegment ln : paths) {
                ln.endX = ln.endX/m;
                ln.endY = ln.endY/m;
                ln.startX = ln.startX/m;
                ln.startY = ln.startY/m;
            }
        }
        repaint();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Path stuff

    /** 
     * Add a path to be painted
     * @param p the <code>ArrayList</code> of <code>MapPoint</code>s
     */
    public void addPath(ArrayList<MapPoint> p) {
        ArrayList<CartPT> temp = new ArrayList<CartPT>();

        for (MapPoint m : p) { // Convert GPS to Cartesian
            // Distance from center point
            double dx = XSCALE*(m.lon - center.lon);
            double dy = YSCALE*(center.lat - m.lat);
            // Multiply distances by width, height of canvas
            double ax = (dx * WIDTH);
            double ay = (dy * HEIGHT);

            // Point has specific rotation for label
            if (m.rotation != 0) {
                CartPT pt = new CartPT(ax, ay, m.label, m.type);
                pt.setRotation(m.rotation);
                temp.add(pt);
            }
            // Point has no specific rotation, apply constant
            else {
                CartPT pt = new CartPT(ax , ay, m.label, m.type);
                pt.setRotation(ROTATION);
                temp.add(pt);
            }
        }

        // Set the first and last points on path to be endpoint type point
        int size = temp.size() - 1;
        CartPT last = temp.get(size);
        CartPT first = temp.get(0);
        last.type = ENDPOINT;
        first.type = ENDPOINT;
        temp.set(size, last);
        temp.set(0, first);
        pathPoints.addAll(temp);

        // Calculate line segments
        for (int i = 0; (i + 1) < temp.size(); i = i + 1) {
            CartPT pt1 = temp.get(i);
            CartPT pt2 = temp.get(i + 1);

            LineSegment seg = new LineSegment(
                    pt1.x, pt1.y, pt2.x, pt2.y, PATH_COLOR);
            this.paths.add(seg);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setters

    /** Set the center point */
    public void setCenter(MapPoint p) {
        this.center = p;
    }

    ///////////////////////////////////////////////////////////////////////////

    /** Paint method */
    public void paint(Graphics g) {
        long startTime = System.nanoTime();
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the line segments
        if (this.lines.size() > 0) {
            for (LineSegment l : this.lines) {
                g2.setColor(l.color);
                g2.setStroke(new BasicStroke(LINE_SIZE, 
                        BasicStroke.CAP_ROUND, 
                        BasicStroke.JOIN_ROUND));
                g2.drawLine((int)(l.startX), 
                        (int)(l.startY), 
                        (int)(l.endX), 
                        (int)(l.endY));
            } 
        }

        // Draw paths, if showPaths is true
        if (showPaths) {
            g2.setColor(PATH_COLOR);
            if (this.paths.size() > 0) {
                for (LineSegment lp : this.paths) {
                    g2.setStroke(new BasicStroke(
                            LINE_SIZE, 
                            BasicStroke.CAP_ROUND, 
                            BasicStroke.JOIN_ROUND));
                    g2.drawLine((int)(lp.startX), 
                            (int)(lp.startY), 
                            (int)(lp.endX), 
                            (int)(lp.endY));
                } 
            }
        }

        // For creating rotated font
        AffineTransform af = new AffineTransform();
        Font fo = new Font("Sans_Serif", Font.BOLD, 12);

        // Draw the points
        for (CartPT p : this.points) {
            g2.setColor(PT_COLOR);
            int px = (int)((p.x) - (PT_SIZE / 2));
            int py = (int)((p.y) - (PT_SIZE / 2));
            g2.fillOval(px, py, PT_SIZE, PT_SIZE);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(1));
            g2.drawOval(px, py, PT_SIZE, PT_SIZE); 

            // Rotate the font according to point rotation
            af.setToRotation(Math.toRadians(p.rotation));
            Font rotated = fo.deriveFont(af);
            af.setToRotation(Math.toRadians(ROTATION));
            Font def = fo.deriveFont(af);

            // Labels
            if (labels) {
                float lx = px + 5;
                float ly = py - 5;
                if (zoomLevel > 4) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(p.label, lx-1, ly);
                    g2.drawString(p.label, lx+1, ly);
                    g2.drawString(p.label, lx, ly-1);
                    g2.drawString(p.label, lx, ly+1);
                    g2.setColor(LABEL_COLOR);
                    g2.drawString(p.label, lx, ly);
                }
                else if (p.type == ENDPOINT) {
                    g2.setFont(def);
                    g2.setColor(Color.BLACK);
                    g2.drawString(p.label, lx-1, ly);
                    g2.drawString(p.label, lx+1, ly);
                    g2.drawString(p.label, lx, ly-1);
                    g2.drawString(p.label, lx, ly+1);
                    g2.setColor(LABEL_COLOR);
                    g2.drawString(p.label, lx, ly);
                }
            }
        }

        // Drawing path labels
        for (CartPT pp : this.pathPoints) {

            af.setToRotation(Math.toRadians(pp.rotation));
            Font rotated = fo.deriveFont(af);
            if ((!labels && showPaths) || (labels && showPaths)) {
                g2.setColor(PATH_COLOR);
                int px = (int)((pp.x) - (PT_SIZE / 2));
                int py = (int)((pp.y) - (PT_SIZE / 2));
                g2.fillOval(px, py, PT_SIZE, PT_SIZE);
                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(1));
                g2.drawOval(px, py, PT_SIZE, PT_SIZE); 
                g2.setColor(Color.BLACK);
                float lx = px  + 5;
                float ly = py - 5 ;
                if (zoomLevel > 3) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(pp.label, lx-1, ly);
                    g2.drawString(pp.label, lx+1, ly);
                    g2.drawString(pp.label, lx, ly-1);
                    g2.drawString(pp.label, lx, ly+1);
                    g2.setColor(PATH_COLOR);
                    g2.drawString(pp.label, lx, ly);
                }
                else if (pp.type == ENDPOINT) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(pp.label, lx-1, ly);
                    g2.drawString(pp.label, lx+1, ly);
                    g2.drawString(pp.label, lx, ly-1);
                    g2.drawString(pp.label, lx, ly+1);
                    g2.setColor(PATH_COLOR);
                    g2.drawString(pp.label, lx, ly);
                }
            }    
        }

        long endTime = System.nanoTime();
        g2.setFont(fo);
        g2.setColor(LABEL_COLOR);
        if (debug) {
            g2.drawString("Zoom level: " + String.valueOf(zoomLevel - 1), 5, 15);
            g2.drawString("Show paths: " + showPaths, 5, 45);
            g2.drawString("Show labels: " + labels, 5, 60);
            g2.drawString(String.valueOf((endTime - startTime)/1000000), 5, 75);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Key, Mouse listeners

    /** Mouse listener */
    class MouseListen extends MouseAdapter {
        /** X coordinate of a click */
        private int x;
        /** Y coordinate of a click */
        private int y;

        /** Mouse has been pressed */
        public void mousePressed(MouseEvent e) {
            requestFocusInWindow(); // Request focus in the window
            x = e.getX();
            y = e.getY();
        }

        /** Mouse has been dragged */
        public void mouseDragged(MouseEvent e) {

            int dx = e.getX() - x;
            int dy = e.getY() - y;

            moveAll(dx, dy);

            // Update x, y fields to reflect the change in x,y
            x += dx;
            y += dy;
        }

        /** Mouse wheel has been moved */
        public void mouseWheelMoved(MouseWheelEvent e) {
            int i = (e.getUnitsToScroll()/3);
            if (i > 0) {
                zoom(SCALE_CONSTANT);
                zoomLevel++;
            } else {
                zoom(-SCALE_CONSTANT);
                zoomLevel--;
            }
            repaint(); 
        }

    }

    /** Key listener */
    class KeyListen extends KeyAdapter {

        /** Handle key presses */
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            // Moving
            if (key == KeyEvent.VK_UP ) { moveAll(0,KEY_CONSTANT); }
            if (key == KeyEvent.VK_DOWN) { moveAll(0,-KEY_CONSTANT); }
            if (key == KeyEvent.VK_LEFT) { moveAll(KEY_CONSTANT,0); }
            if (key == KeyEvent.VK_RIGHT) { moveAll(-KEY_CONSTANT,0); }
            
            // Centering
            // Go to center
            if (key == KeyEvent.VK_C) { moveAll(-dXc, -dYc); }
            // Set center
            if (key == KeyEvent.VK_S) { 
                dXc = 0;
                dYc = 0; }

            // Zooming
            // Zoom in/out
            if (key == KeyEvent.VK_EQUALS) { 
                zoom(SCALE_CONSTANT); 
                zoomLevel++;
            }
            if (key == KeyEvent.VK_MINUS) { 
                zoom(-SCALE_CONSTANT);
                zoomLevel--;
            }
            // Adjust zoom levels 1-10
            if (key == KeyEvent.VK_0) { zoomLevel = 1; }
            if (key == KeyEvent.VK_1) { zoomLevel = 2; }
            if (key == KeyEvent.VK_2) { zoomLevel = 3; }
            if (key == KeyEvent.VK_3) { zoomLevel = 4; }
            if (key == KeyEvent.VK_4) { zoomLevel = 5; }
            if (key == KeyEvent.VK_5) { zoomLevel = 6; }
            if (key == KeyEvent.VK_6) { zoomLevel = 7; }
            if (key == KeyEvent.VK_7) { zoomLevel = 8; }
            if (key == KeyEvent.VK_8) { zoomLevel = 9; }
            if (key == KeyEvent.VK_9) { zoomLevel = 10; }

            // Various letter key functions
            // Toggle labels
            if (key == KeyEvent.VK_L) {
                labels = labels ? false : true;
            }
            // Toggle debug
            if (key == KeyEvent.VK_D) {
                debug = debug ? false : true;
            }
            // Toggle paths
            if (key == KeyEvent.VK_P) {
                showPaths = showPaths ? false : true;
            }
            repaint();
        }
    }

    /** Lazy system.out.println */
    public static void out(Object o) {
        System.out.println(o);
    }

}
