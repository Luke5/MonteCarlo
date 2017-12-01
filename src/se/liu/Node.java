package se.liu;

import java.util.ArrayList;

class Node {
    private double xmin, xmax, ymin, ymax, zmin, zmax, r0;
    private Node n1, n2, n3, n4, n5, n6, n7, n8;
    private ArrayList<Photon> photons;

    Node(double r0, double xmin, double xmax, double ymin, double ymax, double zmin, double zmax, ArrayList<Photon> photons) {
        this.r0=r0;
        int n0 = 500;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.zmin = zmin;
        this.zmax = zmax;
        if (photons.size() > n0 && Math.min((xmax - xmin), Math.min((ymax - ymin), (zmax - zmin))) > 9 * r0) {
            double xmed = xmin + (xmax - xmin) / 2;
            double ymed = ymin + (ymax - ymin) / 2;
            double zmed = zmin + (zmax - zmin) / 2;
            ArrayList<Photon> p1 = new ArrayList<>();
            ArrayList<Photon> p2 = new ArrayList<>();
            ArrayList<Photon> p3 = new ArrayList<>();
            ArrayList<Photon> p4 = new ArrayList<>();
            ArrayList<Photon> p5 = new ArrayList<>();
            ArrayList<Photon> p6 = new ArrayList<>();
            ArrayList<Photon> p7 = new ArrayList<>();
            ArrayList<Photon> p8 = new ArrayList<>();
            for (Photon photon : photons) {
                if (photon.getPosition().getX() < xmed) {
                    if (photon.getPosition().getY() < ymed) {
                        if (photon.getPosition().getZ() < zmed) {
                            p1.add(photon);
                        } else {
                            p2.add(photon);
                        }
                    } else {
                        if (photon.getPosition().getZ() < zmed) {
                            p3.add(photon);
                        } else {
                            p4.add(photon);
                        }
                    }
                } else {
                    if (photon.getPosition().getY() < ymed) {
                        if (photon.getPosition().getZ() < zmed) {
                            p5.add(photon);
                        } else {
                            p6.add(photon);
                        }
                    } else {
                        if (photon.getPosition().getZ() < zmed) {
                            p7.add(photon);
                        } else {
                            p8.add(photon);
                        }
                    }
                }
            }
            n1 = new Node(r0, xmin, xmed, ymin, ymed, zmin, zmed, p1);
            n2 = new Node(r0, xmin, xmed, ymin, ymed, zmed, zmax, p2);
            n3 = new Node(r0, xmin, xmed, ymed, ymax, zmin, zmed, p3);
            n4 = new Node(r0, xmin, xmed, ymed, ymax, zmed, zmax, p4);
            n5 = new Node(r0, xmed, xmax, ymin, ymed, zmin, zmed, p5);
            n6 = new Node(r0, xmed, xmax, ymin, ymed, zmed, zmax, p6);
            n7 = new Node(r0, xmed, xmax, ymed, ymax, zmin, zmed, p7);
            n8 = new Node(r0, xmed, xmax, ymed, ymax, zmed, zmax, p8);
        } else {
            this.photons = photons;
        }
    }

    ArrayList<Photon> getPhotons(Vector vector) {
        return this.getPhotons(vector.getX(), vector.getY(), vector.getZ());
    }

    public boolean isLeaf(){
        return (photons != null);
    }

    private ArrayList<Photon> getAllPhotons(){
        if (this.isLeaf()) {
            return photons;
        } else {
            ArrayList<Photon> all = n1.getAllPhotons();
            all.addAll(n2.getAllPhotons());
            all.addAll(n3.getAllPhotons());
            all.addAll(n4.getAllPhotons());
            all.addAll(n5.getAllPhotons());
            all.addAll(n6.getAllPhotons());
            all.addAll(n7.getAllPhotons());
            all.addAll(n8.getAllPhotons());
            return all;
        }
        
    }
    
    ArrayList<Photon> getPhotons(double x, double y, double z) {
        if (this.isLeaf()) {
            return photons;
        }
        else {
            double xmed = xmin + (xmax - xmin) / 2;
            double ymed = ymin + (ymax - ymin) / 2;
            double zmed = zmin + (zmax - zmin) / 2;
            if (x < xmed && Math.abs(xmin-x)>=r0 && Math.abs(xmed-x)>=r0 ) {
                if (y < ymed && Math.abs(ymin-y)>=r0 && Math.abs(ymed-y)>=r0 ) {
                    if (z < zmed && Math.abs(zmin-z)>=r0 && Math.abs(zmed-z)>=r0 ) {
                        return n1.getPhotons(x, y, z);
                    }
                    if (z > zmed && Math.abs(zmax-z)>=r0 && Math.abs(zmed-z)>=r0 ) {
                        return n2.getPhotons(x, y, z);
                    }
                }
                if (y > ymed && Math.abs(ymax-y)>=r0 && Math.abs(ymed-y)>=r0 ) {
                    if (z < zmed && Math.abs(zmin-z)>=r0 && Math.abs(zmed-z)>=r0 ){
                        return n3.getPhotons(x, y, z);
                    }
                    if (z > zmed && Math.abs(zmax-z)>=r0 && Math.abs(zmed-z)>=r0 ) {
                        return n4.getPhotons(x, y, z);
                    }
                }
            }
            if (x > xmed && Math.abs(xmax-x)>=r0 && Math.abs(xmed-x)>=r0 ) {
                if (y < ymed && Math.abs(ymin-y)>=r0 && Math.abs(ymed-y)>=r0 ) {
                    if (z < zmed && Math.abs(zmin-z)>=r0 && Math.abs(zmed-z)>=r0 ) {
                        return n5.getPhotons(x, y, z);
                    }
                    if (z > zmed && Math.abs(zmax-z)>=r0 && Math.abs(zmed-z)>=r0 ) {
                        return n6.getPhotons(x, y, z);
                    }
                }
                if (y > ymed && Math.abs(ymax-y)>=r0 && Math.abs(ymed-y)>=r0 ) {
                    if (z < zmed && Math.abs(zmin-z)>=r0 && Math.abs(zmed-z)>=r0 ){
                        return n7.getPhotons(x, y, z);
                    }
                    if (z > zmed && Math.abs(zmax-z)>=r0 && Math.abs(zmed-z)>=r0 ) {
                        return n8.getPhotons(x, y, z);
                    }
                    else{
                        return new ArrayList<>();
                    }
                }
                else{
                    return new ArrayList<>();
                }
            }
            else{
                return new ArrayList<>();
            }
        }
    }
}
