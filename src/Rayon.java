import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Rayon extends PApplet {
    private PVector positionOrigine;
    private PVector direction;

    private ArrayList<CercleRayTracing> listeCercle;


    public Rayon(float x, float y, float direction) {
        this.positionOrigine = new PVector(x, y);
        this.direction = PVector.fromAngle(direction);

        this.listeCercle = new ArrayList<CercleRayTracing>();
    }

    public void drawRayon() {
        Main.processing.strokeWeight(1);
        Main.processing.stroke(255);
        Main.processing.line(this.positionOrigine.x, this.positionOrigine.y, this.positionOrigine.x + this.direction.x * 10, this.positionOrigine.y + this.direction.y * 10);
    }

    public void updateRayon(float x, float y) {
        this.positionOrigine = new PVector(x, y);
    }

    public PVector contactLigne(Ligne ligne) {
        float x1 = ligne.getPositionA().x;
        float y1 = ligne.getPositionA().y;
        float x2 = ligne.getPositionB().x;
        float y2 = ligne.getPositionB().y;

        float x3 = this.positionOrigine.x;
        float y3 = this.positionOrigine.y;
        float x4 = this.positionOrigine.x + this.direction.x;
        float y4 = this.positionOrigine.y + this.direction.y;

        float denominateur = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominateur == 0) {
            return null;
        }

        float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominateur;
        float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denominateur;

        if (t > 0 && t < 1 && u > 0) {
            PVector pointContact = new PVector(x1 + t * (x2 - x1), y1 + t * (y2 - y1));
            return pointContact;
        }

        return null;

    }

    public PVector contactCercle(ArrayList<Cercle> cercles, Cercle cercle) {
        PVector positionPointRay = this.positionOrigine;
        int index = this.rechercheCercleProche(cercles, positionPointRay);
        Cercle c = null;
        if (index != -1) {
            c = cercles.get(index);

        }

            while (Math.round(PVector.dist(positionPointRay, cercle.getPositionCentre()) * 100) / 100 > cercle.getRayon() && index != -1) {
                positionPointRay = new PVector(positionPointRay.x + this.direction.x * ((PVector.dist(positionPointRay, c.getPositionCentre())) - c.getRayon()), positionPointRay.y + this.direction.y * ((PVector.dist(positionPointRay, c.getPositionCentre())) - c.getRayon()));
                //Main.processing.stroke(255,0,0);
                //Main.processing.fill(255,0,0);
                //Main.processing.circle(positionPointRay.x, positionPointRay.y, 5);
                index = this.rechercheCercleProche(cercles, positionPointRay);
                if (index != -1) {
                    //Main.processing.noFill();
                    //Main.processing.stroke(255);
                    //Main.processing.circle(positionPointRay.x, positionPointRay.y, (PVector.dist(positionPointRay, c.getPositionCentre()) * 2 ) - c.getRayon() * 2);
                    System.out.println(Math.round(PVector.dist(positionPointRay, cercle.getPositionCentre()) * 100) / 100 + " | " + cercle.getRayon());
                } else {
                    System.out.println("termine");
                }
            }

        if (this.conditionArret(positionPointRay)) {
            positionPointRay = null;
        }

        return positionPointRay;
        }

    public boolean conditionArret(PVector pt) {
        return pt.x < 0 && pt.x > Main.processing.width && pt.y < 0 && pt.y > Main.processing.height;
    }

    public int rechercheCercleProche(ArrayList<Cercle> cercles, PVector positionPointRay) {
        //PVector positionPointRay = this.positionOrigine;
        float distance = Main.processing.width;
        int index = -1;
        for (int iteratorCercle = 0 ; iteratorCercle < cercles.size() ; ++iteratorCercle) {
            Cercle c = cercles.get(iteratorCercle);
            float distanceMinimum = PVector.dist(positionPointRay, c.getPositionCentre());

            if (distance >= distanceMinimum) {
                //System.out.println("distance : " + distance + " | Distance Minimum : " + distanceMinimum);
                distance = distanceMinimum;
                index = iteratorCercle;
            }
        }
        return index;
    }

    public PVector getPositionOrigine() {
        return positionOrigine;
    }
}
