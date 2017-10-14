package se.liu.test;

import junit.framework.TestCase;
import se.liu.Vector;

public class VectorTest extends TestCase {
    public void testAdd1() throws Exception {
        Vector vec1 = new Vector(-1,4,7);
        Vector vec2 = new Vector(6,-8,0);
        Vector sum = vec1.add(vec2);
        assertEquals("X Value",5.0,sum.getX());
        assertEquals("Y Value",-4.0,sum.getY());
        assertEquals("Z Value",7.0,sum.getZ());
    }
    public void testAdd2() throws Exception {
        Vector vec1 = new Vector(-8.78,(-1.0/3.0),5.6);
        Vector vec2 = new Vector(788,-50,0);
        Vector sum = vec1.add(vec2);
        assertEquals("X Value",779.22,sum.getX(),1e-4);
        assertEquals("Y Value",-(151.0/3.0),sum.getY(),1e-4);
        assertEquals("Z Value",5.6,sum.getZ(),1e-4);
    }

    public void testInvert() throws Exception {
        Vector vec1 = new Vector(-8.78,(-1.0/3.0),5.6);
        Vector v = vec1.invert();
        assertEquals("X Value",8.78,v.getX(),1e-4);
        assertEquals("Y Value",(1.0/3.0),v.getY(),1e-4);
        assertEquals("Z Value",-5.6,v.getZ(),1e-4);
    }

    public void testSub1() throws Exception {
        Vector vec1 = new Vector(-1,4,7);
        Vector vec2 = new Vector(6,-8,0);
        Vector sub = vec1.sub(vec2);
        assertEquals("X Value",-7.0,sub.getX());
        assertEquals("Y Value",12.0,sub.getY());
        assertEquals("Z Value",7.0,sub.getZ());
    }
    public void testSub2() throws Exception {
        Vector vec1 = new Vector(-8.78,(-1.0/3.0),5.6);
        Vector vec2 = new Vector(788,-50,0);
        Vector sub = vec1.sub(vec2);
        assertEquals("X Value",-796.78,sub.getX(),1e-4);
        assertEquals("Y Value",(149.0/3.0),sub.getY(),1e-4);
        assertEquals("Z Value",5.6,sub.getZ(),1e-4);
    }

    public void testScalarMult() throws Exception {
        Vector vec1 = new Vector(-8.78,(-1.0/3.0),5.6);
        Vector prod = vec1.scalarMult(3.0/8.0);
        assertEquals("X Value",-3.2925,prod.getX(),1e-4);
        assertEquals("Y Value",-(1.0/8.0),prod.getY(),1e-4);
        assertEquals("Z Value",2.1,prod.getZ(),1e-4);
    }

    public void testDotProduct1() throws Exception {
        Vector vec1 = new Vector(-1,4,7);
        Vector vec2 = new Vector(6,-8,0);
        double dotP = vec1.dotProduct(vec2);
        assertEquals("Dot Product",-38.0,dotP);
    }

    public void testDotProduct2() throws Exception {
        Vector vec1 = new Vector(-8.78,-(1.0/5.0),5.6);
        Vector vec2 = new Vector(788,-50,0);
        double dotP = vec1.dotProduct(vec2);
        assertEquals("Dot Product",-6908.64,dotP,1e-4);
    }

    public void testCrossProduct1() throws Exception {
        Vector vec1 = new Vector(-1,4,7);
        Vector vec2 = new Vector(6,-8,0);
        Vector prod = vec1.crossProduct(vec2);
        assertEquals("X Value",56.0,prod.getX());
        assertEquals("Y Value",42.0,prod.getY());
        assertEquals("Z Value",-16.0,prod.getZ());
    }
    public void testCrossProduct2() throws Exception {
        Vector vec1 = new Vector(-8.78,-(1.0/5.0),5.6);
        Vector vec2 = new Vector(788,-50,0);
        Vector prod = vec1.crossProduct(vec2);
        assertEquals("X Value",280.0,prod.getX(),1e-4);
        assertEquals("Y Value",4412.8,prod.getY(),1e-4);
        assertEquals("Z Value",596.6,prod.getZ(),1e-4);
    }

    public void testLength1() throws Exception {
        Vector vec = new Vector(6,-8,0);
        double norm = vec.length();
        assertEquals("Length",10.0,norm);
    }

    public void testLength2() throws Exception {
        Vector vec = new Vector(-8.78,-1/5,5.6);
        double norm = vec.length();
        assertEquals("Length",10.415776495298,norm,1e-2);
    }

    public void testUnitVector() throws Exception {
        Vector vec = new Vector(6,-8,0);
        Vector unit = vec.unitVector();
        assertEquals("X Value",(3.0/5.0),unit.getX(),1e-4);
        assertEquals("Y Value",-(4.0/5.0),unit.getY(),1e-4);
        assertEquals("Z Value",0.0,unit.getZ(),1e-4);

    }

}