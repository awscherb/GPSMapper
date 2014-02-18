package com.awscherb.gps;

public class CartPT {
    double x;
    double y;
    String label;
    int type;
    int rotation = 0;
    
    public CartPT(double x, double y, String label, int type) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.type = type;
    }
    
    public CartPT(double x, double y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.type = 0;
    }
    
    
    public CartPT(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.label = "Test";
        this.type = type;
    }
    
    public CartPT(double x, double y) {
        this.x = x;
        this.y = y;
        this.label = "Test";
        this.type = 0;
    }
    
    public void setRotation(int r) {
        this.rotation = r;
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")"; 
    }
}
