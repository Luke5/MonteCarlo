package se.liu;

import java.util.ArrayList;

public class Node {
    private double xmin,xmax,ymin,ymax,zmin,zmax;
    private Node n1,n2,n3,n4,n5,n6,n7,n8;
    private int n0=500;
    private ArrayList<Photon> photons;

    public Node(double r0, double xmin, double xmax, double ymin, double ymax, double zmin, double zmax, ArrayList<Photon> photons) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.zmin = zmin;
        this.zmax = zmax;
        if(photons.size()>n0 && Math.min((xmax-xmin),Math.min((ymax-ymin),(zmax-zmin)))>8*r0){
            double xmed=xmin+(xmax-xmin);
            double ymed=ymin+(ymax-ymin);
            double zmed=zmin+(zmax-zmin);
            ArrayList<Photon> p1 = new ArrayList<>();
            ArrayList<Photon> p2 = new ArrayList<>();
            ArrayList<Photon> p3 = new ArrayList<>();
            ArrayList<Photon> p4 = new ArrayList<>();
            ArrayList<Photon> p5 = new ArrayList<>();
            ArrayList<Photon> p6 = new ArrayList<>();
            ArrayList<Photon> p7 = new ArrayList<>();
            ArrayList<Photon> p8 = new ArrayList<>();
            for (Photon photon:photons) {
                if(photon.getPosition().getX()<xmed){
                    if(photon.getPosition().getY()<ymed){
                        if(photon.getPosition().getZ()<zmed){ p1.add(photon); }
                        else{ p2.add(photon); }
                    }
                    else{
                        if(photon.getPosition().getZ()<zmed){ p3.add(photon); }
                        else{ p4.add(photon); }
                    }
                }
                else{
                    if(photon.getPosition().getY()<ymed){
                        if(photon.getPosition().getZ()<zmed){ p5.add(photon); }
                        else{ p6.add(photon); }
                    }
                    else{
                        if(photon.getPosition().getZ()<zmed){ p7.add(photon); }
                        else{ p8.add(photon); }
                    }
                }
            }
            n1= new Node(r0,xmin,xmed,ymin,ymed,zmin,zmed,p1);
            n2= new Node(r0,xmin,xmed,ymin,ymed,zmed,zmax,p2);
            n3= new Node(r0,xmin,xmed,ymed,ymax,zmin,zmed,p3);
            n4= new Node(r0,xmin,xmed,ymed,ymax,zmed,zmax,p4);
            n5= new Node(r0,xmed,xmax,ymin,ymed,zmin,zmed,p5);
            n6= new Node(r0,xmed,xmax,ymin,ymed,zmed,zmax,p6);
            n7= new Node(r0,xmed,xmax,ymed,ymax,zmin,zmed,p7);
            n8= new Node(r0,xmed,xmax,ymed,ymax,zmed,zmax,p8);
        }
        else{
            this.photons = photons;
        }
    }

    public ArrayList<Photon> getPhotons(double x, double y, double z) {
        if(photons!=null){
            return photons;
        }
        else{
            double xmed=xmin+(xmax-xmin);
            double ymed=ymin+(ymax-ymin);
            double zmed=zmin+(zmax-zmin);
            if(x<xmed){
                if(y<ymed){
                    if(z<zmed){ return n1.getPhotons(x,y,z); }
                    else{ return n2.getPhotons(x,y,z); }
                }
                else{
                    if(z<zmed){ return n3.getPhotons(x,y,z); }
                    else{ return n4.getPhotons(x,y,z); }
                }
            }
            else{
                if(y<ymed){
                    if(z<zmed){ return n5.getPhotons(x,y,z); }
                    else{ return n6.getPhotons(x,y,z); }
                }
                else{
                    if(z<zmed){ return n7.getPhotons(x,y,z); }
                    else{ return n8.getPhotons(x,y,z); }
                }
            }            
        }
    }
}
