package com.awscherb.gps;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.awscherb.gps.GPSMapper.KeyListen;
import com.awscherb.gps.GPSMapper.MouseListen;

/** Map of the MBTA rapid transit system, using a GPSMapper */
public class MBTAMapper {

    // Constants
    /** Width of frame */
    private final int WIDTH = 600;
    /** Height of frame */
    private final int HEIGHT = 600;

    ///////////////////////////////////////////////////////////////////////////
    // UI Elements

    // Labels
    JLabel startLabel = new JLabel("Start point:");
    JLabel destinationLabel = new JLabel("Destination:");
    JLabel showPathLabel = new JLabel("Show path");
    JLabel showStationLabel = new JLabel("Show station names");

    // Combo boxes
    JComboBox startCombo = new JComboBox();
    JComboBox endCombo = new JComboBox();
    
    // Check boxes
    JCheckBox pathCheck = new JCheckBox();
    JCheckBox labelCheck = new JCheckBox();

    public static void main(String[] arg) {
        new MBTAMapper();
    }

    /** Fix rotations for drawing the map */
    private void fixRotations() {
        MBTAGPS.AQUARIUM.setRotation(1);
        MBTAGPS.MAVERICK.setRotation(1);
        MBTAGPS.AIRPORT.setRotation(1);
        MBTAGPS.WOOD_ISLAND.setRotation(1);
        MBTAGPS.ORIENT_HEIGHTS.setRotation(1);
        MBTAGPS.BOWDOIN.setRotation(-45);

        MBTAGPS.LECHMERE.setRotation(-45);
        MBTAGPS.BOYLSTON.setRotation(-10);
        MBTAGPS.PRUDENTIAL.setRotation(-60);
        MBTAGPS.NORTHEASTERN.setRotation(-55);
        MBTAGPS.BACK_OF_THE_HILL.setRotation(1);
        MBTAGPS.HEATH_STREET.setRotation(1);
        MBTAGPS.RIVERWAY.setRotation(1);
        MBTAGPS.FENWOOD_ROAD.setRotation(1);
        MBTAGPS.LONGWOOD_MEDICAL_AREA.setRotation(1);
        MBTAGPS.MUSEUM_OF_FINE_ARTS.setRotation(1);

        MBTAGPS.WARREN_STREET.setRotation(-75);
        MBTAGPS.ALLSTON_STREET.setRotation(-75);
        MBTAGPS.GRIGGS_STREET_LONG_AVENUE.setRotation(-75);
        MBTAGPS.WASHINGTON_STREET.setRotation(-15);
        MBTAGPS.SUTHERLAND_STREET.setRotation(-75);
        MBTAGPS.CHESTNUT_HILL_AVENUE.setRotation(-75);
        MBTAGPS.SOUTH_STREET.setRotation(-75);
        MBTAGPS.BOSTON_COLLEGE.setRotation(-45);
        MBTAGPS.CHISWICK_ROAD.setRotation(-75);

        MBTAGPS.CLEAVELAND_CIRCLE.setRotation(-30);
        MBTAGPS.ENGLEWOOD_AVENUE.setRotation(45);
        MBTAGPS.DEAN_ROAD.setRotation(-30);
        MBTAGPS.TAPPAN_STREET.setRotation(45);
        MBTAGPS.BRANDON_HALL.setRotation(30);
        MBTAGPS.FAIRBANKS.setRotation(-45);
        MBTAGPS.SUMMIT_AVENUE.setRotation(-45);
        MBTAGPS.ST_PAUL_STREET_C.setRotation(-45);
        MBTAGPS.KENT_STREET.setRotation(-45);
        MBTAGPS.LONGWOOD.setRotation(1);
        MBTAGPS.FENWAY.setRotation(1);
        MBTAGPS.ST_MARYS_STREET.setRotation(-15);

        MBTAGPS.BEACONSFIELD.setRotation(45);
        MBTAGPS.RESERVOIR.setRotation(45);
        MBTAGPS.NEWTON_HIGHLANDS.setRotation(1);
        MBTAGPS.CHESTNUT_HILL.setRotation(1);
        MBTAGPS.BROOKLINE_HILLS.setRotation(45);
        MBTAGPS.WASHINGTON_SQUARE.setRotation(35);
        MBTAGPS.COOLIDGE_CORNER.setRotation(30);

        MBTAGPS.CHARLES_MGH.setRotation(-37);
        MBTAGPS.SOUTH_STATION.setRotation(1);
        MBTAGPS.DOWNTOWN_CROSSING.setRotation(1);

        MBTAGPS.TUFTS_MEDICAL_CENTER.setRotation(35);
        MBTAGPS.CHINATOWN.setRotation(35);
        MBTAGPS.BROADWAY.setRotation(35);
        MBTAGPS.ROXBURY_CROSSING.setRotation(1);
        MBTAGPS.RUGGLES.setRotation(1);
        MBTAGPS.MASS_AVE.setRotation(1);
        MBTAGPS.BACK_BAY.setRotation(1);
        MBTAGPS.JACKSON_SQUARE.setRotation(1);
        MBTAGPS.STONY_BROOK.setRotation(1);
        MBTAGPS.GREEN_ST.setRotation(1);
        MBTAGPS.FOREST_HILLS.setRotation(1);

    }

    MBTAMapper() {

        ArrayList<MapPoint> path1 = new ArrayList<MapPoint>(
                Arrays.asList(MBTAGPS.MALDEN, MBTAGPS.WELLINGTON,
                        MBTAGPS.SULLIVAN_SQUARE, MBTAGPS.COMMUNITY_COLLEGE,
                        MBTAGPS.NORTH_STATION, MBTAGPS.HAYMARKET,
                        MBTAGPS.GOVERNMENT_CENTER, MBTAGPS.PARK, 
                        MBTAGPS.CHARLES_MGH, MBTAGPS.KENDALL_MIT));

        JFrame gui = new JFrame();
        gui.setSize(WIDTH, HEIGHT);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setResizable(true);
        GPSMapper map = GPSMapper.factory();
        map.setDoubleBuffered(true);
        MouseListen ml = map.new MouseListen();
        KeyListen kl = map.new KeyListen();

        fixRotations();

        map.setCenter(MBTAGPS.MBTA_CENTER);
        map.addPoints(MBTAGPS.ORANGE_LINE, Color.ORANGE);
        map.addPoints(MBTAGPS.RED_LINE, Color.RED);
        map.addPoints(MBTAGPS.BLUE_LINE, Color.BLUE);
        map.addPoints(MBTAGPS.GREEN_LINE, Color.GREEN);
        map.setBackground(Color.DARK_GRAY);

        map.addPath(path1);

        Container a = gui.getContentPane();
        GroupLayout grid = new GroupLayout(a);
//        a.setLayout(grid);
        grid.setAutoCreateContainerGaps(true);
        grid.setAutoCreateGaps(true);
//        grid.setHorizontalGroup(grid.createSequentialGroup()
//                .addGroup(grid.createParallelGroup(GroupLayout.Alignment.CENTER)
//                        .addComponent(startLabel)
//                        .addComponent(startCombo,50,100,200))
//                .addGroup(grid.createParallelGroup(GroupLayout.Alignment.CENTER)
//                        .addComponent(destinationLabel)
//                        .addComponent(map)));
//
//
//        grid.setVerticalGroup(grid.createSequentialGroup()
//                .addGroup(grid.createParallelGroup(GroupLayout.Alignment.CENTER)
//                            .addComponent(startLabel)
//                            .addComponent(destinationLabel))
//                        .addGroup(grid.createParallelGroup(GroupLayout.Alignment.CENTER)
//                                .addComponent(startCombo,50,100,200)
//                                .addComponent(map)));
//        

        //        gui.add(endLbl);
        //        gui.add(startLbl);
        //        gui.add(new JLabel("s"));
                gui.add(map);

        //        gui.pack();

        gui.addMouseListener(ml);
        gui.addMouseMotionListener(ml);
        gui.addMouseWheelListener(ml);
//        gui.addKeyListener(kl);

        gui.setBackground(Color.LIGHT_GRAY);

        map.addKeyListener(kl);
        map.requestFocusInWindow();
        
        gui.setVisible(true);


    }


}
