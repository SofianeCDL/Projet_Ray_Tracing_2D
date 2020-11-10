import processing.core.PVector;

public class Cercle {
    private PVector positionCentre;
    private float rayon;

    public Cercle(float x, float y, float rayon) {
        this.positionCentre = new PVector(x, y);
        this.rayon = rayon;
    }

    public void drawCercle() {
        Main.processing.strokeWeight(2);
        Main.processing.stroke(255);
        Main.processing.noFill();
        Main.processing.circle(this.positionCentre.x, this.positionCentre.y, this.rayon * 2);
    }

    public PVector getPositionCentre() {
        return positionCentre;
    }

    public float getRayon() {
        return rayon;
    }
}
