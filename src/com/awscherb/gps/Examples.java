package com.awscherb.gps;
import java.awt.Color;
import java.awt.Graphics2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;



public class Examples {

    /** Regular circle point */
    private int POINT = 0;
    /** Circle with black outline */
    private int TRANSFER = 1;
    /** Circle with outline  */
    private int ENDPOINT = 2;
    
    String input = "mbta.txt";

    MapPoint northeastern = new MapPoint(42.340401,-71.088806);

    // Red line stops
    MapPoint alewife = new MapPoint(42.395428, -71.142483, ENDPOINT);
    MapPoint davis = new MapPoint(42.39674,-71.121815);
    MapPoint porter = new MapPoint(42.3884,-71.119149);
    MapPoint harvard = new MapPoint(42.373362,-71.118956);
    MapPoint central = new MapPoint(42.365486,-71.103802);
    MapPoint mit = new MapPoint(42.36249079,-71.08617653);
    MapPoint mgh = new MapPoint(42.361166,-71.070628);
    MapPoint park = new MapPoint(42.35639457,-71.0624242, TRANSFER);
    MapPoint dtx = new MapPoint(42.355518,-71.060225, TRANSFER);
    MapPoint ss = new MapPoint(42.352271, -71.055242);
    MapPoint broadway = new MapPoint(42.342622,-71.056967);
    MapPoint andrew = new MapPoint(42.330154,-71.057655);
    MapPoint jfk = new MapPoint(42.320685,-71.052391);
    MapPoint northq = new MapPoint(42.275275,-71.029583);
    MapPoint wollaston = new MapPoint(42.2665139,-71.0203369);
    MapPoint quincycenter = new MapPoint(42.251809,-71.005409);
    MapPoint quincyadams = new MapPoint(42.233391,-71.007153);
    MapPoint braintree = new MapPoint(42.2078543, -71.0011385, "Braintree");
    MapPoint savinhill = new MapPoint(42.31129,-71.053331);
    MapPoint fieldscorner = new MapPoint(42.300093,-71.061667);
    MapPoint shawmut = new MapPoint(42.29312583,-71.06573796, POINT);
    MapPoint ashmont = new MapPoint(42.284652,-71.064489, ENDPOINT);

    // Blue line stops
    MapPoint bowdoin = new MapPoint(42.361365,-71.062037, ENDPOINT);
    MapPoint govtcenter = new MapPoint(42.359705,-71.059215, TRANSFER);
    MapPoint state = new MapPoint(42.358978,-71.057598, TRANSFER);
    MapPoint aquarium = new MapPoint(42.359784, -71.051652, "Aquarium");
    MapPoint maverick = new MapPoint(42.36911856,-71.03952958);
    MapPoint airport = new MapPoint(42.374262, -71.030395);
    MapPoint woodisland = new MapPoint(42.3796403,-71.02286539);
    MapPoint orientheights = new MapPoint(42.386867,-71.004736);
    MapPoint suffolkdowns = new MapPoint(42.39050067,-70.99712259);
    MapPoint beachmont = new MapPoint(42.39754234,-70.99231944);
    MapPoint reverebeach = new MapPoint(42.40784254,-70.9925332);
    MapPoint wonderland = new MapPoint(42.41342,-70.991648, ENDPOINT);

    ArrayList<MapPoint> blue = new ArrayList<MapPoint>
    (Arrays.asList(bowdoin, govtcenter, state, aquarium, maverick,
            airport, woodisland, orientheights, suffolkdowns,
            beachmont, reverebeach, wonderland));
    ArrayList<ArrayList<MapPoint>> blueline = 
            new ArrayList<ArrayList<MapPoint>>(Arrays.asList(
                    blue));


    // Red line main branch - Alewife - JFK/UMass
    ArrayList<MapPoint> redmain = new ArrayList<MapPoint>
    (Arrays.asList(alewife, davis, porter, harvard, central,
            mit, mgh, park, dtx, ss, broadway, andrew, jfk));
    // Red line Ashmont branch - JFK/UMass - Ashmont
    ArrayList<MapPoint> redashmont = new ArrayList<MapPoint>
    (Arrays.asList(jfk, savinhill, fieldscorner, shawmut, ashmont));
    // Red line Braintree branch - JFK/UMass - Braintree
    ArrayList<MapPoint> redbraintree = new ArrayList<MapPoint>
    (Arrays.asList(jfk, northq, wollaston, quincycenter, quincyadams,
            braintree));
    // Red line
    ArrayList<ArrayList<MapPoint>> redline = new 
            ArrayList<ArrayList<MapPoint>>(Arrays.asList(redmain,
                    redashmont, redbraintree));


    public static void main(String[] arg) {
//        new Examples();
        try {
            URL blueURL = new URL("http://developer.mbta.com/lib/rthr/blue.csv");
            MBTATrainLocater mb = new MBTATrainLocater(blueURL);
            mb.init();

            System.out.println(mb.trainPoints);
        } catch (Exception e) { }

    }

    Examples() {
        JFrame gui = new JFrame();
        gui.setSize(500, 394);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GPSMapper map = GPSMapper.factory();
        gui.add(map);
    }

}
