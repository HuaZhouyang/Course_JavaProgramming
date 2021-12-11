package Unit_9;

/**
 * 9.9
 */
public class RegularPolygon {
    private int n = 3; // 多边形的变数
    private double side = 1; // 边的长度
    private double x = 0, y = 0; // 坐标

    public RegularPolygon() {
    }

    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
    }

    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPerimeter() {
        return n * side;
    }

    public double getArea() {
        return n * side * side / 4 / Math.tan(Math.PI / n);
    }
}


class RegularPolygonTest {
    public static void main(String[] args) {
        // task 1
        RegularPolygon rp1 = new RegularPolygon();
        System.out.println(rp1.getPerimeter());
        System.out.println(rp1.getArea());
        // task 2
        RegularPolygon rp2 = new RegularPolygon(6, 4);
        System.out.println(rp2.getPerimeter());
        System.out.println(rp2.getArea());
        // task 3
        RegularPolygon rp3 = new RegularPolygon(10, 4, 5.6, 7.8);
        System.out.println(rp3.getPerimeter());
        System.out.println(rp3.getArea());
    }
}