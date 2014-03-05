package com.awscherb.gps;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;

import com.awscherb.util.LineReader;

public class MBTATrainLocater {

    ArrayList<MapPoint> trainPoints;
    
    LineReader a;
    
    private final Class<MBTAGPS> MGPS =
            MBTAGPS.class;
    
    
    public MBTATrainLocater(URL s) {
        a = LineReader.factory(s);
        trainPoints = new ArrayList<MapPoint>();
        a.next(); // First line is not train data
    }
    
    
    public void init() {
        while (a.hasNext()) {
            String[] ar = a.next().split(",");
            try {
                 String destination = ar[3];
                 Double lat = Double.valueOf(ar[9]);
                 Double lon = Double.valueOf(ar[10]);
                 MapPoint p = new MapPoint(lat, lon, destination);
                 trainPoints.add(p);
            } catch (Exception e) {
            }
        }
    }
    
    
}
