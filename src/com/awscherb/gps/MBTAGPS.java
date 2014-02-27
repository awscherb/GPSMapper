package com.awscherb.gps;
import java.util.ArrayList;
import java.util.Arrays;


public class MBTAGPS {
    
    public static int TRANSFER = 1;
    public static int ENDPOINT = 2;
    
    public static final MapPoint MBTA_CENTER = new MapPoint(42.46968,-71.271742);
    
    ///////////////////////////////////////////////////////////////////////////
    // Red line
    
    public static final MapPoint ALEWIFE = new MapPoint(42.395428, -71.142483, "Alewife", ENDPOINT);
    public static final MapPoint DAVIS = new MapPoint(42.39674,-71.121815,"Davis");
    public static final MapPoint PORTER = new MapPoint(42.3884,-71.119149,"Porter");
    public static final MapPoint HARVARD = new MapPoint(42.373362,-71.118956,"Harvard");
    public static final MapPoint CENTRAL = new MapPoint(42.365486,-71.103802,"Central");
    public static final MapPoint KENDALL_MIT = new MapPoint(42.36249079,-71.08617653,"Kendall/MIT");
    public static final MapPoint CHARLES_MGH = new MapPoint(42.361166,-71.070628,"Charles/MGH");
    public static final MapPoint PARK = new MapPoint(42.35639457,-71.0624242,"Park");
    public static final MapPoint DOWNTOWN_CROSSING = new MapPoint(42.355518,-71.060225,"Downtown Crossing", TRANSFER);
    public static final MapPoint SOUTH_STATION = new MapPoint(42.352271, -71.055242,"South Station");
    public static final MapPoint BROADWAY = new MapPoint(42.342622,-71.056967,"Broadway");
    public static final MapPoint ANDREW = new MapPoint(42.330154,-71.057655,"Andrew");
    public static final MapPoint JFK_UMASS = new MapPoint(42.320685,-71.052391,"JFK/UMass");
    public static final MapPoint NORTH_QUINCY = new MapPoint(42.275275,-71.029583,"North Qunicy");
    public static final MapPoint WOLLASTON = new MapPoint(42.2665139,-71.0203369,"Wollaston");
    public static final MapPoint QUINCY_CENTER = new MapPoint(42.251809,-71.005409,"Quincy Center");
    public static final MapPoint QUINCY_ADAMS = new MapPoint(42.233391,-71.007153,"Quincy Adams");
    public static final MapPoint BRAINTREE = new MapPoint(42.2078543, -71.0011385,"Braintree", ENDPOINT);
    public static final MapPoint SAVIN_HILL = new MapPoint(42.31129,-71.053331,"Savin Hill");
    public static final MapPoint FIELDS_CORNER = new MapPoint(42.300093,-71.061667,"Fields Corner");
    public static final MapPoint SHAWMUT = new MapPoint(42.29312583,-71.06573796,"Shawmut");
    public static final MapPoint ASHMONT = new MapPoint(42.284652,-71.064489,"Ashmont",ENDPOINT);
    public static final ArrayList<MapPoint> RED_MAIN = new ArrayList<MapPoint>(Arrays.
            asList(ALEWIFE, DAVIS, PORTER, HARVARD, CENTRAL, KENDALL_MIT,
                    CHARLES_MGH, PARK, DOWNTOWN_CROSSING, SOUTH_STATION, BROADWAY,
                    ANDREW, JFK_UMASS));
    public static final ArrayList<MapPoint> RED_ASHMONT = new ArrayList<MapPoint>(Arrays.
            asList(JFK_UMASS, SAVIN_HILL, FIELDS_CORNER, SHAWMUT, ASHMONT));
    public static final ArrayList<MapPoint> RED_BRAINTREE = new ArrayList<MapPoint>
    (Arrays.asList(JFK_UMASS, NORTH_QUINCY, WOLLASTON, QUINCY_CENTER, QUINCY_ADAMS,
            BRAINTREE));
    public static final ArrayList<ArrayList<MapPoint>> RED_LINE = 
            new ArrayList<ArrayList<MapPoint>>(Arrays.asList(
                    RED_MAIN, RED_ASHMONT, RED_BRAINTREE));
    
    ///////////////////////////////////////////////////////////////////////////
    // Orange line
    
    public static final MapPoint OAK_GROVE = new MapPoint(42.43668, -71.071097, "Oak Grove",ENDPOINT);
    public static final MapPoint MALDEN = new MapPoint(42.426632, -71.07411, "Malden");
    public static final MapPoint WELLINGTON = new MapPoint(42.40237,-71.077082,"Wellington");
    public static final MapPoint SULLIVAN_SQUARE = new MapPoint(42.383975,-71.076994,"Sullivan Square");
    public static final MapPoint COMMUNITY_COLLEGE = new MapPoint(42.373622,-71.069533,"Community College");
    public static final MapPoint NORTH_STATION = new MapPoint(42.365577,-71.06129,"North Station");
    public static final MapPoint HAYMARKET = new MapPoint(42.363021,-71.05829,"Haymarket");
    public static final MapPoint STATE = new MapPoint(42.358978,-71.057598,"State");
    // Downtown Crossing already exists in Red Line definitions
    public static final MapPoint CHINATOWN  = new MapPoint(42.352547,-71.062752,"Chinatown");
    public static final MapPoint TUFTS_MEDICAL_CENTER = new MapPoint(42.349662,-71.063917,"Tufts Medical");
    public static final MapPoint BACK_BAY = new MapPoint(42.34735,-71.075727,"Back Bay");
    public static final MapPoint MASS_AVE = new MapPoint(42.341512,-71.083423,"Mass Ave");
    public static final MapPoint RUGGLES = new MapPoint(42.336377,-71.088961,"Ruggles");
    public static final MapPoint ROXBURY_CROSSING = new MapPoint(42.331397,-71.095451,"Roxbury Crossing");
    public static final MapPoint JACKSON_SQUARE = new MapPoint(42.323132,-71.099592,"Jackson Square");
    public static final MapPoint STONY_BROOK = new MapPoint(42.317062,-71.104248,"Stony Brook");
    public static final MapPoint GREEN_ST = new MapPoint(42.310525,-71.107414,"Green St");
    public static final MapPoint FOREST_HILLS = new MapPoint(42.300523,-71.113686,"Forest Hills",ENDPOINT);
    public static final ArrayList<MapPoint> ORANGE_MAIN = new ArrayList<MapPoint>(Arrays.asList(
            OAK_GROVE, MALDEN, WELLINGTON, SULLIVAN_SQUARE, COMMUNITY_COLLEGE, NORTH_STATION,
            HAYMARKET, STATE, DOWNTOWN_CROSSING, CHINATOWN, TUFTS_MEDICAL_CENTER, BACK_BAY,
            MASS_AVE, RUGGLES, ROXBURY_CROSSING, JACKSON_SQUARE, STONY_BROOK, GREEN_ST,
            FOREST_HILLS));
    public static final ArrayList<ArrayList<MapPoint>> ORANGE_LINE = new
            ArrayList<ArrayList<MapPoint>>(Arrays.asList(ORANGE_MAIN));
    
    ///////////////////////////////////////////////////////////////////////////
    // Blue line
    
    public static final MapPoint BOWDOIN = new MapPoint(42.361365,-71.062037,"Bowdoin",ENDPOINT);
    public static final MapPoint GOVERNMENT_CENTER = new MapPoint(42.359705,-71.059215,"Gov't Center");
    // State exists in Orange line
    public static final MapPoint AQUARIUM = new MapPoint(42.359784, -71.051652,"Aquarium");
    public static final MapPoint MAVERICK = new MapPoint(42.36911856,-71.03952958,"Maverick");
    public static final MapPoint AIRPORT = new MapPoint(42.374262, -71.030395,"Airport");
    public static final MapPoint WOOD_ISLAND = new MapPoint(42.3796403,-71.02286539,"Wood Island");
    public static final MapPoint ORIENT_HEIGHTS = new MapPoint(42.386867,-71.004736,"Orient Heights");
    public static final MapPoint SUFFOLK_DOWNS = new MapPoint(42.39050067,-70.99712259,"Suffolk Downs");
    public static final MapPoint BEACHMONT = new MapPoint(42.39754234,-70.99231944,"Beachmont");
    public static final MapPoint REVERE_BEACH = new MapPoint(42.40784254,-70.9925332,"Revere Beach");
    public static final MapPoint WONDERLAND = new MapPoint(42.41342,-70.991648,"Wonderland", ENDPOINT);

        
    public static final ArrayList<MapPoint> BLUE_MAIN = new ArrayList<MapPoint>
    (Arrays.asList(BOWDOIN, GOVERNMENT_CENTER, STATE, AQUARIUM, MAVERICK,
            AIRPORT, WOOD_ISLAND, ORIENT_HEIGHTS, SUFFOLK_DOWNS,
            BEACHMONT, REVERE_BEACH, WONDERLAND));
    public static final ArrayList<ArrayList<MapPoint>> BLUE_LINE = 
            new ArrayList<ArrayList<MapPoint>>(Arrays.asList(
                    BLUE_MAIN));
    
    ///////////////////////////////////////////////////////////////////////////
    // Green line
    
    // Main branch
    public static final MapPoint LECHMERE = new MapPoint(42.370772,-71.076536,"Lechmere",ENDPOINT);
    public static final MapPoint SCIENCE_PARK = new MapPoint(42.366664,-71.067666,"Science Park");
    // North Station
    // Haymarket
    // Government Center
    // Park
    public static final MapPoint BOYLSTON = new MapPoint(42.35302,-71.06459,"Boylston");
    public static final MapPoint ARLINGTON = new MapPoint(42.351902,-71.070893,"Arlington");
    public static final MapPoint COPLEY_SQUARE = new MapPoint(42.349974,-71.077447,"Copley Square");
    public static final MapPoint HYNES_CONVENTION_CENTER = new MapPoint(42.347888,-71.087903,"Hynes");
    public static final MapPoint KENMORE = new MapPoint(42.348949,-71.095169,"Kenmore");
    
    // B Branch
    // Kenmore
    public static final MapPoint BLANDFORD_STREET = new MapPoint(42.349293,-71.100258,"Blandford St");
    public static final MapPoint BU_EAST = new MapPoint(42.349735,-71.103889,"BU East");
    public static final MapPoint BU_CENTRAL = new MapPoint(42.350082,-71.106865,"BU Central");
    public static final MapPoint BU_WEST = new MapPoint(42.350941,-71.113876,"BU West");
    public static final MapPoint ST_PAUL_STREET = new MapPoint(42.3512,-71.116104,"St Paul St");
    public static final MapPoint PLEASANT_STREET = new MapPoint(42.351521,-71.118889,"Pleasant St");
    public static final MapPoint BABCOCK_STREET = new MapPoint(42.35182,-71.12165,"Babcock St");
    public static final MapPoint PACKARDS_CORNER = new MapPoint(42.351967,-71.125031,"Packards Corner");
    public static final MapPoint HARVARD_AVENUE = new MapPoint(42.350243,-71.131355,"Harvard Ave");
    public static final MapPoint GRIGGS_STREET_LONG_AVENUE = new MapPoint(42.348545,-71.134949,"Griggs St");
    public static final MapPoint ALLSTON_STREET = new MapPoint(42.348701,-71.137955,"Allston St");
    public static final MapPoint WARREN_STREET = new MapPoint(42.348343,-71.140457,"Warren St");
    public static final MapPoint WASHINGTON_STREET = new MapPoint(42.343864,-71.142853,"Washington St");
    public static final MapPoint SUTHERLAND_STREET = new MapPoint(42.341614,-71.146202,"Sutherland St");
    public static final MapPoint CHISWICK_ROAD = new MapPoint(42.340805,-71.150711,"Chiswick Rd");
    public static final MapPoint CHESTNUT_HILL_AVENUE = new MapPoint(42.338169,-71.15316,"Chestnut Hill Ave");
    public static final MapPoint SOUTH_STREET = new MapPoint(42.3396,-71.157661,"South St");
    public static final MapPoint BOSTON_COLLEGE = new MapPoint(42.340081,-71.166769,"Boston College",ENDPOINT);

    // C Branch
    // Kenmore
    public static final MapPoint ST_MARYS_STREET = new MapPoint(42.345974,-71.107353,"St Marys St");
    public static final MapPoint HAWES_STREET = new MapPoint(42.344906,-71.111145,"Hawes St");
    public static final MapPoint KENT_STREET = new MapPoint(42.344074,-71.114197,"Kent St");
    public static final MapPoint ST_PAUL_STREET_C = new MapPoint(42.343327,-71.116997,"St Paul St");
    public static final MapPoint COOLIDGE_CORNER = new MapPoint(42.342213,-71.121201,"Coolidge Cnr");
    public static final MapPoint SUMMIT_AVENUE = new MapPoint(42.34111,-71.12561,"Summit Ave");
    public static final MapPoint BRANDON_HALL = new MapPoint(42.340023,-71.129082,"Brandon Hall");
    public static final MapPoint FAIRBANKS = new MapPoint(42.339725,-71.131073,"Fairbanks");
    public static final MapPoint WASHINGTON_SQUARE = new MapPoint(42.339394,-71.13533,"Washington Sq");
    public static final MapPoint TAPPAN_STREET = new MapPoint(42.338459,-71.138702,"Tappan St");
    public static final MapPoint DEAN_ROAD = new MapPoint(42.337807,-71.141853,"Dean Rd");
    public static final MapPoint ENGLEWOOD_AVENUE = new MapPoint(42.336971,-71.14566,"Englewood Ave");
    public static final MapPoint CLEAVELAND_CIRCLE = new MapPoint(42.336142,-71.149326,"Cleaveland Cir",ENDPOINT);
    
    // D Branch
    // Kenmore
    public static final MapPoint FENWAY = new MapPoint(42.345394,-71.104187,"Fenway");
    public static final MapPoint LONGWOOD = new MapPoint(42.341145,-71.110451,"Longwood");
    public static final MapPoint BROOKLINE_VILLAGE = new MapPoint(42.332774,-71.116296,"Brookline Village");
    public static final MapPoint BROOKLINE_HILLS = new MapPoint(42.331333,-71.126999,"Brookline Hills");
    public static final MapPoint BEACONSFIELD = new MapPoint(42.335846,-71.140823,"Beaconsfield");
    public static final MapPoint RESERVOIR = new MapPoint(42.335027,-71.148952,"Reservoir");
    public static final MapPoint CHESTNUT_HILL = new MapPoint(42.326653,-71.165314,"Chestnut Hill");
    public static final MapPoint NEWTON_CENTRE = new MapPoint(42.329391,-71.192429,"Newton Centre");
    public static final MapPoint NEWTON_HIGHLANDS = new MapPoint(42.321735,-71.206116,"Newton Highlands");
    public static final MapPoint ELIOT = new MapPoint(42.319023,-71.216713,"Eliot");
    public static final MapPoint WABAN = new MapPoint(42.325943,-71.230728,"Waban");
    public static final MapPoint WOODLAND = new MapPoint(42.333374,-71.244301,"Woodland");
    public static final MapPoint RIVERSIDE = new MapPoint(42.337059,-71.251742,"Riverside",ENDPOINT);
    
    // E Branch
    // Kenmore
    public static final MapPoint PRUDENTIAL = new MapPoint(42.34557,-71.081696,"Prudential");
    public static final MapPoint SYMPHONY = new MapPoint(42.342687,-71.085056,"Symphony");
    public static final MapPoint NORTHEASTERN = new MapPoint(42.340401,-71.088806,"Northeastern");
    public static final MapPoint MUSEUM_OF_FINE_ARTS = new MapPoint(42.337711,-71.095512,"MFA");
    public static final MapPoint LONGWOOD_MEDICAL_AREA = new MapPoint(42.33596,-71.100052,"LMA");
    public static final MapPoint BRIGHAM_CIRCLE = new MapPoint(42.334229,-71.104609,"Brigham Circle");
    public static final MapPoint FENWOOD_ROAD = new MapPoint(42.333706,-71.105728,"Fenwood Rd");
    public static final MapPoint MISSION_PARK = new MapPoint(42.333195,-71.109756,"Mission Pk");
    public static final MapPoint RIVERWAY = new MapPoint(42.331684,-71.111931,"Riverway");
    public static final MapPoint BACK_OF_THE_HILL = new MapPoint(42.330139,-71.111313,"Back of the Hill");
    public static final MapPoint HEATH_STREET = new MapPoint(42.328681,-71.110559,"Heath St",ENDPOINT);

    public static final ArrayList<MapPoint> GREEN_MAIN = new ArrayList<MapPoint>
    (Arrays.asList(LECHMERE, SCIENCE_PARK, NORTH_STATION, HAYMARKET, GOVERNMENT_CENTER,
            PARK, BOYLSTON, ARLINGTON, COPLEY_SQUARE, HYNES_CONVENTION_CENTER, KENMORE));
    
    public static final ArrayList<MapPoint> GREEN_B = new ArrayList<MapPoint>
    (Arrays.asList(KENMORE, BLANDFORD_STREET, BU_EAST, BU_CENTRAL, BU_WEST, ST_PAUL_STREET,
            PLEASANT_STREET, BABCOCK_STREET, PACKARDS_CORNER, HARVARD_AVENUE,
            GRIGGS_STREET_LONG_AVENUE, ALLSTON_STREET, WARREN_STREET, WASHINGTON_STREET,
            SUTHERLAND_STREET, CHISWICK_ROAD, CHESTNUT_HILL_AVENUE, SOUTH_STREET, BOSTON_COLLEGE));

    public static final ArrayList<MapPoint> GREEN_C = new ArrayList<MapPoint>
    (Arrays.asList(KENMORE, ST_MARYS_STREET, HAWES_STREET, KENT_STREET, ST_PAUL_STREET_C, COOLIDGE_CORNER,
            SUMMIT_AVENUE, BRANDON_HALL, FAIRBANKS, WASHINGTON_SQUARE, TAPPAN_STREET, DEAN_ROAD,
            ENGLEWOOD_AVENUE, CLEAVELAND_CIRCLE));
    
    public static final ArrayList<MapPoint> GREEN_D = new ArrayList<MapPoint>
    (Arrays.asList(KENMORE, FENWAY, LONGWOOD, BROOKLINE_VILLAGE, BROOKLINE_HILLS, BEACONSFIELD,
            RESERVOIR, CHESTNUT_HILL, NEWTON_CENTRE, NEWTON_HIGHLANDS, ELIOT, WABAN, WOODLAND,
            RIVERSIDE));
    
    public static final ArrayList<MapPoint> GREEN_E = new ArrayList<MapPoint>
    (Arrays.asList(COPLEY_SQUARE, PRUDENTIAL, SYMPHONY, NORTHEASTERN, MUSEUM_OF_FINE_ARTS,
            LONGWOOD_MEDICAL_AREA, BRIGHAM_CIRCLE, FENWOOD_ROAD, MISSION_PARK,
            RIVERWAY, BACK_OF_THE_HILL, HEATH_STREET));
    
    public static final ArrayList<ArrayList<MapPoint>> GREEN_LINE = 
            new ArrayList<ArrayList<MapPoint>>
    (Arrays.asList(GREEN_MAIN, GREEN_B, GREEN_C, GREEN_D, GREEN_E));
    

    

}