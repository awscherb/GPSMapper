package com.awscherb.gps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Arrays;

import javax.swing.JComponent;

/**
 * Draws a map based on given GPS coordinates. Lines can be 
 * drawn between points. 
 * Designed for creating geographically accurate transit maps
 * @author Alex Scherb
 * @version 2/18/14
 */
public class GPSMapper extends JComponent {

    private static final long serialVersionUID = 1L;

    // We calculate the x, y coordinates for the input points based
    // on their distance from the center point. 
    // Center point actually refers to the top-left position on the map

    // Size, canvas info, graphics constants
    /** Scale of map */
    private static int scale = 1;
    /** Width of the canvas */ 
    private int width = 300;
    /** Height of the canvas */
    private int height = 630;
    /** Color to draw point circles */
    private final Color PT_COLOR = Color.WHITE;
    /** Size to draw points */
    private static final int PT_SIZE = 5;
    /** Offset to correctly draw points on line */
    private static final int PT_OFFSET = ((PT_SIZE / 2) * scale) - 1;
    /** Width of lines */
    private final int LINE_SIZE = 4;
    /** Color to draw paths */
    private final Color PATH_COLOR = Color.CYAN;
    /** Default rotation for text label */
    private final int ROTATION = -30;
    /** x,y coordinate string */
    private String xycoord = "";
    /** Intersection point on a path */
    public static int TRANSFER = 1;
    /** End point of a path */
    public static int ENDPOINT = 2;

    /** Center point */
    private MapPoint center;

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
    private GPSMapper(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(new Dimension(width, height));
        setVisible(true);
        points = new ArrayList<CartPT>();
        lines = new ArrayList<LineSegment>();
        pathPoints = new ArrayList<CartPT>();
        paths = new ArrayList<LineSegment>();
    }

    /** Public static factory method */
    public static GPSMapper factory(int width, int height) {
        return new GPSMapper(width * scale, height * scale);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Point manipulation

    /** Add the given point to the map */
    public void addPoint(MapPoint g) {
        double dx = 2000*(g.lon - center.lon);
        double dy = 1000*(center.lat - g.lat);

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
            CartPT pt = new CartPT(ax ,ay, g.label, g.type);
            pt.setRotation(ROTATION);
            this.points.add(pt);
        }
    }

    /** Add all the given points to the map */
    public void addPoints(ArrayList<ArrayList<MapPoint>> input, Color c) {
        for (ArrayList<MapPoint> l : input) {
            ArrayList<CartPT> temp = new ArrayList<CartPT>();

            for (MapPoint g : l) { // Convert GPS to Cartesian
                double dx = 2000*(g.lon - center.lon);
                double dy = 1000*(center.lat - g.lat);

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
                        pt1.x + PT_OFFSET, 
                        pt1.y + PT_OFFSET, 
                        pt2.x + PT_OFFSET, 
                        pt2.y + PT_OFFSET, c);
                this.lines.add(seg);
            }
            points.addAll(temp); // Add loop temporary list to actual list
        }
    }

    /** Move all coordinates */
    private void moveAll(int x, int y) {
        // Move points
        for (CartPT c : points) {
            c.x += (x / scale);
            c.y += (y / scale);
        }
        // Move path points
        for (CartPT c : pathPoints) {
            c.x += (x / scale);
            c.y += (y / scale);
        }
        // Move line segments
        for (LineSegment l : lines) {
            l.endX += (x / scale);
            l.endY += (y / scale);
            l.startX += (x / scale);
            l.startY += (y / scale);
        }
        // Move all path segments
        for (LineSegment ln : paths) {
            ln.endX += (x / scale);
            ln.endY += (y / scale);
            ln.startX += (x / scale);
            ln.startY += (y / scale);
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
            double dx = 2000*(m.lon - center.lon);
            double dy = 1000*(center.lat - m.lat);

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
                    pt1.x + PT_OFFSET, 
                    pt1.y + PT_OFFSET, 
                    pt2.x + PT_OFFSET, 
                    pt2.y + PT_OFFSET, PATH_COLOR);
            this.paths.add(seg);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Setters

    /** Set the center point */
    public void setCenter(MapPoint p) {
        this.center = p;
    }

    /** Set the scale */
    protected void setScale(int s) {
        scale = s;
    }

    ///////////////////////////////////////////////////////////////////////////

    /** Paint the points */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        if (debug) {
            g2.drawString("Zoom level: " + String.valueOf(scale - 1), 5, 15);
            g2.drawString(xycoord, 5, 30);
            g2.drawString("Show paths: " + showPaths, 5, 45);
        }

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        // Draw the line segments
        if (this.lines.size() > 0) {
            for (LineSegment l : this.lines) {
                g2.setColor(l.color);
                try {
                    g2.setStroke(new BasicStroke(LINE_SIZE * ((scale + 1) / 2), 
                            BasicStroke.CAP_ROUND, 
                            BasicStroke.JOIN_ROUND));
                } catch (IllegalArgumentException iae) { }
                g2.drawLine((int)l.startX * scale, (int)l.startY * scale, 
                        (int)l.endX * scale, (int)l.endY * scale);
            } 
        }

        // Draw paths, if showPaths is true
        if (showPaths) {
            g2.setColor(PATH_COLOR);
            if (this.paths.size() > 0) {
                for (LineSegment lp : this.paths) {
                    try {
                        g2.setStroke(new BasicStroke(
                                LINE_SIZE * ((scale + 1) / 2), 
                                BasicStroke.CAP_ROUND, 
                                BasicStroke.JOIN_ROUND));
                    } catch (IllegalArgumentException iae) { }
                    g2.drawLine((int)lp.startX * scale, (int)lp.startY * scale, 
                            (int)lp.endX * scale, (int)lp.endY * scale);
                } 
            }
        }

        // For creating rotated font
        AffineTransform af = new AffineTransform();
        Font ab = g2.getFont();
        Font fo = ab.deriveFont(Font.BOLD);

        // Draw the points
        for (CartPT p : this.points) {
            g2.setColor(PT_COLOR);
            int px = (int)p.x * scale;
            int py = (int)p.y * scale;
            int ps = (PT_SIZE * ((scale + 1)/2));
            g2.fillOval(px, py, ps, ps);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(1));
            g2.drawOval(px, py, ps, ps); 

            af.setToRotation(Math.toRadians(p.rotation));
            Font rotated = fo.deriveFont(af);

            // Labels
            if (labels) {
                float lx = (float)(((p.x)*scale )  + (PT_SIZE/2)*scale) + 5;
                float ly = (float)(p.y)*scale - 5 + (PT_SIZE / 2)*scale;
                if (scale > 3) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(p.label, lx-1, ly);
                    g2.drawString(p.label, lx+1, ly);
                    g2.drawString(p.label, lx, ly-1);
                    g2.drawString(p.label, lx, ly+1);
                    g2.setColor(Color.WHITE);
                    g2.drawString(p.label, lx, ly);
                }
                else if (p.type == ENDPOINT) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(p.label, lx-1, ly);
                    g2.drawString(p.label, lx+1, ly);
                    g2.drawString(p.label, lx, ly-1);
                    g2.drawString(p.label, lx, ly+1);
                    g2.setColor(Color.WHITE);
                    g2.drawString(p.label, lx, ly);
                }
            }
        }
        
        // Drawing path labels
        for (CartPT pp : this.pathPoints) {
            af.setToRotation(Math.toRadians(pp.rotation));
            Font rotated = fo.deriveFont(af);
            if ((!labels && showPaths) || (labels && showPaths)) {
                g2.setColor(Color.BLACK);
                float lx = (float)(((pp.x)*scale )  + (PT_SIZE/2)*scale) + 5;
                float ly = (float)(pp.y)*scale - 5 + (PT_SIZE / 2)*scale;
                if (scale > 3) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(pp.label, lx-1, ly);
                    g2.drawString(pp.label, lx+1, ly);
                    g2.drawString(pp.label, lx, ly-1);
                    g2.drawString(pp.label, lx, ly+1);
                    g2.setColor(Color.CYAN);
                    g2.drawString(pp.label, lx, ly);
                }
                else if (pp.type == ENDPOINT) {
                    g2.setFont(rotated);
                    g2.setColor(Color.BLACK);
                    g2.drawString(pp.label, lx-1, ly);
                    g2.drawString(pp.label, lx+1, ly);
                    g2.drawString(pp.label, lx, ly-1);
                    g2.drawString(pp.label, lx, ly+1);
                    g2.setColor(Color.CYAN);
                    g2.drawString(pp.label, lx, ly);
                }
            }    
            
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
            updateXYString(e);
            x = e.getX();
            y = e.getY();
        }

        /** Mouse has been dragged */
        public void mouseDragged(MouseEvent e) {

            int dx = e.getX() - x;
            int dy = e.getY() - y;

            // Move all coordinates by the change in x, y
            moveAll(dx, dy);

            // Update x, y fields to reflect the change in x,y
            x += dx;
            y += dy;
        }

        /** Mouse wheel has been moved */
        public void mouseWheelMoved(MouseWheelEvent e) {
            int i = (e.getUnitsToScroll()/3);
            scale+=i;
            repaint(); 
        }

        /** Update the String X, Y coordinates */
        private void updateXYString(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            String s = "X: " + x + ", Y: " + y;
            xycoord = s;
            repaint();
        }
    }

    /** Key listener */
    class KeyListen extends KeyAdapter {

        /** Handle key presses */
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            // Moving
            if (key == KeyEvent.VK_UP ) { moveAll(0,10*scale); }
            if (key == KeyEvent.VK_DOWN) { moveAll(0,-10*scale); }
            if (key == KeyEvent.VK_LEFT) { moveAll(10*scale,0); }
            if (key == KeyEvent.VK_RIGHT) { moveAll(-10*scale,0); }

            // Zooming
            // Zoom in/out
            if (key == KeyEvent.VK_EQUALS) { scale++; repaint(); }
            if (key == KeyEvent.VK_MINUS) { scale--; repaint(); }
            // Change between zoom levels
            if (key == KeyEvent.VK_0) { scale = 1; repaint(); }
            if (key == KeyEvent.VK_1) { scale = 2; repaint(); }
            if (key == KeyEvent.VK_2) { scale = 3; repaint(); }
            if (key == KeyEvent.VK_3) { scale = 4; repaint(); }
            if (key == KeyEvent.VK_4) { scale = 5; repaint(); }
            if (key == KeyEvent.VK_5) { scale = 6; repaint(); }
            if (key == KeyEvent.VK_6) { scale = 7; repaint(); }
            if (key == KeyEvent.VK_7) { scale = 8; repaint(); }
            if (key == KeyEvent.VK_8) { scale = 9; repaint(); }
            if (key == KeyEvent.VK_9) { scale = 10; repaint(); }

            // Various letter key functions
            // Toggle labels
            if (key == KeyEvent.VK_L) {
                labels = labels ? false : true;
                repaint();
            }
            // Toggle debug
            if (key == KeyEvent.VK_D) {
                debug = debug ? false : true;
                repaint();
            }
            // Toggle paths
            if (key == KeyEvent.VK_P) {
                showPaths = showPaths ? false : true;
                repaint();
            }
        }
    }

    /** Lazy system.out.println */
    public static void out(Object o) {
        System.out.println(o);
    }

}
