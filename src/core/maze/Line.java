package core.maze;


class Line {
    final int x1;
    final int y1;
    final int x2;
    final int y2;
    final boolean pill;

    public Line(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, true);
    }

    public Line(int x1, int y1, int x2, int y2, boolean pill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.pill = pill;
    }

    public boolean in(int px, int py) {
        return between(x1, px, x2) && between(y1, py, y2);
    }

    private static boolean between(int a, int b, int c) {

        return (a - b) * (b - c) >= 0;
    }
}