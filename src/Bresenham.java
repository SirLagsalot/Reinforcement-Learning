
public class Bresenham {

    /**
     * Bresenham-based supercover algorithm.
     *
     * Checks the board from the top left corner using y for rows and x for
     * columns. If a # character is found as one of the intersecting spaces it
     * is returned to indicate a collision. If all spaces are either . , s, or
     * f, the intended destination position is returned based on initial
     * position and the velocity vector. Up to the simulator to check intended
     * destination vs actual destination returned by this method.
     *
     * @param x pos x
     * @param y pos y
     * @param vx velocity x
     * @param vy velocity y
     * @param board char board
     * @return position of either the intended destination or the first
     * collision
     */
    public static Position checkCollision(int x, int y, int vx, int vy, char[][] board) {
        int x1 = x;
        int y1 = y;
        int x2 = x + vx;
        int y2 = y + vy;
        int count = 1 + vx + vy;
        int xInc = (x2 > x) ? 1 : -1;
        int yInc = (y2 > y) ? 1 : -1;
        int error = vx - vy;
        vx *= 2;
        vy *= 2;

        for (; count > 0; --count) {

            if (board[y][x] == '#') {
                return new Position(x, y);
            }
            if (board[y][x] == 'F') {
                return new Position(x, y);
            }

            if (error > 0) {
                x += xInc;
                error -= vy;
            } else {
                y += yInc;
                error += vx;
            }
        }
        return new Position(x2, y2);
    }
}
