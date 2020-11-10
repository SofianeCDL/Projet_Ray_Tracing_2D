import processing.core.PApplet;
import processing.core.PVector;

public class Ligne extends PApplet {
    private PVector positionA;
    private PVector positionB;

    public Ligne(float x1, float y1, float x2, float y2) {
        this.positionA = new PVector(x1, y1);
        this.positionB = new PVector(x2, y2);
    }

    public void drawLine() {
        Main.processing.strokeWeight(2);
        Main.processing.stroke(255);
        Main.processing.line(this.positionA.x, this.positionA.y, this.positionB.x, this.positionB.y);
    }

    public PVector getPositionA() {
        return positionA;
    }

    public PVector getPositionB() {
        return positionB;
    }
}
