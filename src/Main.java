import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
    public static PApplet processing;
    private RayTracing trace;


    public static void main(String[] args) {
        PApplet.main("Main", args);
    }


    public void settings() {
        fullScreen();
        //size(500,500);
    }

    public void setup() {
        processing = this;
        background(0);

       this.trace = new RayTracing(100, 5);

    }

    public void draw() {
        background(0);
        this.trace.drawObjet();
        this.trace.updateRayTracing(mouseX, mouseY);
        this.trace.drawRayTracing();
        this.trace.drawCercleRayTracing();
    }
}