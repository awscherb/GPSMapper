GPSMapper
=========

GPS mapping designed for creating geocraphically accurate maps of transportation networks. Allows drawing of lines of any color, showing points and labels, and highlighting paths. This program does not find paths, it simply draws paths based on user input.

Usage
-----
Create a new GPSMapper object using the `factory()` method.

    GPSMapper mbta = GPSMapper.factory();
  
You must then set the center point of the map. The center point, actually the top-left corner of the map, is the point we use to calculate distance to every other point we add. This must be a `MapPoint` object.

    mbta.setCenter(new MapPoint(42.46968,-71.271742));
    
You can use the `addPoint` method to add a single point, or the `addPoints` to add an entire line.

    // Add the MBTA Alewife station, setting it to be an endpoint
    mbta.addPoint(MapPoint(42.395428, -71.142483, "Alewife", GPSMapper.ENDPOINT));
    // Add the MBTA Orange Line 
    map.addPoints(orangeLine, Color.ORANGE)
    
The `addPoints` method takes an `ArrayList` of `ArrayList`s, as to take into account lines with multiple branches that should be drawn as the same color. (i.e. the B,C,D,E branches of the MBTA Green Line).

Adding a path is the same as adding a line. Paths will be drawn over their respective lines if paths are shown.

Controlling the Map
-------------------
When added to a GUI, the GPSMapper has several key bindings to control functionality:

Key|Function
---|--------
L|Toggle labels
P|Toggle paths
D|Toggle debug info
0-9|Change zoom level to levels 0-9
+,-|Zoom in, out
Arrows|Move in the direction of the arrow key presed

Current Limitations
-------------------
Because of how we draw labels, maps with a lot of labels will see a decrease in performance.
    
