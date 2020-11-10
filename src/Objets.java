import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Objets extends PApplet {

    private ArrayList<Ligne> listeLigne;
    private int nombreLigne;

    public Objets() {
        this.listeLigne = new ArrayList<Ligne>();
        this.nombreLigne = 1;

        this.initLignes();
    }

    public Objets(int bord) {
        this.listeLigne = new ArrayList<Ligne>();
        this.nombreLigne = 4;

        this.objetBord();
    }

    public void initLignes() {
        PVector pointA = new PVector(random(Main.processing.width), random(Main.processing.height));
        for (int iteratorLigne = 0 ; iteratorLigne < this.nombreLigne ; ++iteratorLigne) {
            float angle = random(2 * PI);
            float distance = random(200);
            PVector pointB = new PVector(pointA.x + cos(angle) * distance, pointA.y + sin(angle) * distance);
            this.listeLigne.add(new Ligne(pointA.x, pointA.y, pointB.x, pointB.y));
            pointA = pointB;

        }

        /*PVector centre = new PVector(random(Main.processing.width), random(Main.processing.height));
        float angle = 2 * PI / this.nombreLigne;
        float rayon = random(100);
        PVector pointA = new PVector(centre.x + cos(0) * rayon, centre.y + sin(0 * rayon));

        for (int i = 1 ; i <= this.nombreLigne ; ++i) {
            PVector pointB = new PVector(centre.x + cos(angle * i) * rayon, centre.y + sin(angle * i) * rayon);
            this.listeLigne.add(new Ligne(pointA.x, pointA.y, pointB.x, pointB.y));
            pointA = pointB;
        }*/
    }

    public void objetBord() {
        this.listeLigne.add(new Ligne(0, 0, Main.processing.width, 0));
        this.listeLigne.add(new Ligne(0, 0, 0, Main.processing.height));
        this.listeLigne.add(new Ligne(0, Main.processing.height, Main.processing.width, Main.processing.height));
        this.listeLigne.add(new Ligne(Main.processing.width, 0, Main.processing.width, Main.processing.height));
    }

    public void drawObjets() {
        for (int i = 0 ; i < this.listeLigne.size() ; ++i) {
            Ligne l = this.listeLigne.get(i);

            l.drawLine();
        }
    }

    public ArrayList<Ligne> getListeLigne() {
        return listeLigne;
    }
}
