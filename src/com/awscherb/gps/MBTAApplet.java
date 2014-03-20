package com.awscherb.gps;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JApplet;

import com.awscherb.gps.GPSMapper.KeyListen;
import com.awscherb.gps.GPSMapper.MouseListen;

public class MBTAApplet extends JApplet {
    
    public MBTAApplet() {
        ArrayList<MapPoint> path1 = new ArrayList<MapPoint>(
                Arrays.asList(MBTAGPS.MALDEN, MBTAGPS.WELLINGTON,
                        MBTAGPS.SULLIVAN_SQUARE, MBTAGPS.COMMUNITY_COLLEGE,
                        MBTAGPS.NORTH_STATION, MBTAGPS.HAYMARKET,
                        MBTAGPS.GOVERNMENT_CENTER, MBTAGPS.PARK, 
                        MBTAGPS.CHARLES_MGH, MBTAGPS.KENDALL_MIT));
        
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
        
        Container a = getContentPane();
        a.add(map);
        map.addMouseListener(ml);
        map.addMouseMotionListener(ml);
        map.addMouseWheelListener(ml);
        map.addKeyListener(kl);
        
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

}
