import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class RayTracing extends PApplet {
    private int nombreRayons;
    private int nombreObjet;

    private ArrayList<Rayon> listeRayons;

    private ArrayList<Objets> listeLignes;
    private ArrayList<Cercle> listeCercles;

    public RayTracing(int nombreRayons, int nombreObjet) {
        this.nombreRayons = nombreRayons;
        this.nombreObjet = nombreObjet;

        this.listeRayons = new ArrayList<Rayon>();
        this.listeLignes = new ArrayList<Objets>();
        this.listeCercles = new ArrayList<Cercle>();
        //this.listeLignes = new Ligne(random(Main.processing.width), random(Main.processing.height), random(Main.processing.width), random(Main.processing.height));

        this.initRayTracing();
    }

    public void initRayTracing() {
        float angle = 2 * PI / this.nombreRayons;
        for (int iteratorRayon = 0 ; iteratorRayon < this.nombreRayons ; ++iteratorRayon) {
            this.listeRayons.add(new Rayon(Main.processing.width / 2, Main.processing.height / 2, angle * iteratorRayon));
        }

        this.listeLignes.add(new Objets(1));
        for (int iteratorObjet = 0 ; iteratorObjet < this.nombreObjet ; ++iteratorObjet) {

            this.listeLignes.add(new Objets());
        }

        for (int iteratorCercle = 0 ; iteratorCercle < 1 ; ++iteratorCercle) {
            this.listeCercles.add(new Cercle(random(Main.processing.width), random(Main.processing.height), random(100)));
        }


    }

    public void drawObjet() {
        for (int iteratorObjet = 1 ; iteratorObjet < this.listeLignes.size() ; ++iteratorObjet) {
            Objets l = this.listeLignes.get(iteratorObjet);

            l.drawObjets();
        }

        /*for (int i = 0 ; i < this.listeCercles.size() ; ++i) {
            Cercle c = this.listeCercles.get(i);

            c.drawCercle();
        }*/
    }

    public void updateRayTracing(float x, float y) {
        for (int iteratorRayon = 0 ; iteratorRayon < this.nombreRayons ; ++iteratorRayon) {
            Rayon r = this.listeRayons.get(iteratorRayon);

            r.updateRayon(x, y);
        }
    }

    public void drawRayTracing() {
        //Main.processing.beginShape();
        for (int iteratorRayon = 0 ; iteratorRayon < this.nombreRayons ; ++iteratorRayon) {
            Rayon r = this.listeRayons.get(iteratorRayon);

            PVector pointMinimum = null;
            float distancePoints = Main.processing.width;

            for (int iteratorObjet = 0 ; iteratorObjet < this.listeLignes.size() ; ++iteratorObjet) {
                Objets l = this.listeLignes.get(iteratorObjet);
                for (int i = 0; i < l.getListeLigne().size(); ++i) {
                    Ligne ln = l.getListeLigne().get(i);
                    PVector pointContact = r.contactLigne(ln);
                    if (pointContact != null) {
                        float distanceMinimum = PVector.dist(r.getPositionOrigine(), pointContact);

                        if (distanceMinimum < distancePoints) {
                            distancePoints = distanceMinimum;
                            pointMinimum = pointContact;
                        }
                    }
                }
            }
            if (pointMinimum != null) {
                //Main.processing.vertex(pointMinimum.x, pointMinimum.y);
                Main.processing.line(r.getPositionOrigine().x, r.getPositionOrigine().y, pointMinimum.x, pointMinimum.y);
            }
        }
        //Main.processing.endShape();
    }
    
    public void drawCercleRayTracing() {
        for (int iteratorRayon = 0 ; iteratorRayon < this.nombreRayons ; ++iteratorRayon) {
            Rayon r = this.listeRayons.get(iteratorRayon);
            PVector point = null;

            for (int iteratorCercle = 0 ; iteratorCercle < this.listeCercles.size() ; ++iteratorCercle) {
                Cercle c = this.listeCercles.get(iteratorCercle);

                point = r.contactCercle(this.listeCercles, c);
            }

            if (point != null) {
                Main.processing.line(r.getPositionOrigine().x, r.getPositionOrigine().y, point.x, point.y);
            }
        }
        
    }
}
