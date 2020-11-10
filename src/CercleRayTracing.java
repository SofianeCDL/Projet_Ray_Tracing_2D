import processing.core.PApplet;
import processing.core.PVector;

public class CercleRayTracing extends PApplet {
    private PVector positionCentre;
    private float rayon;

    public CercleRayTracing(float x, float y, float rayon) {
        this.positionCentre = new PVector(x, y);
        this.rayon = rayon;
    }

    public PVector getPositionCentre() {
        return positionCentre;
    }

    public float getRayon() {
        return rayon;
    }
}
