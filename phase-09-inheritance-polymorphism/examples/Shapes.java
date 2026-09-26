// Phase 9 — inheritance + polymorphism with a tiny shape hierarchy.
// ONE loop calls area() on many different shapes and each answers differently.
// That "same call, different behavior" is POLYMORPHISM.
//
// Run:   java Shapes.java

public class Shapes {
    public static void main(String[] args) {

        // A Shape[] can hold ANY object that IS-A Shape (Circle, Rectangle, ...).
        // We're allowed to store a Circle in a Shape variable because a Circle IS-A Shape.
        Shape[] shapes = {
            new Circle(2.0),
            new Rectangle(3.0, 4.0),
            new Circle(1.0)
        };

        // The magic: we call the SAME method, shape.area(), on every shape,
        // but Java runs the RIGHT version for each object's real type.
        for (Shape shape : shapes) {
            System.out.printf("%-10s area = %.2f%n", shape.name(), shape.area());
        }
    }
}

// ABSTRACT class: a partial blueprint. You can't do `new Shape()` — it's incomplete.
// It promises every shape HAS an area(), but refuses to say how (that depends on the shape).
abstract class Shape {
    public abstract double area();     // ABSTRACT method: no body; subclasses MUST fill it in

    // A normal (concrete) method every shape inherits as-is.
    public String name() {
        return "a shape";
    }
}

// Circle IS-A Shape.  `extends` = "inherits from / is a kind of".
class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override                          // "I'm replacing Shape's version." Java checks I really am.
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String name() {
        return "Circle";
    }
}

// Rectangle IS-A Shape too — but its area() does something completely different.
class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String name() {
        return "Rectangle";
    }
}
