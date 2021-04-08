package OnlineTask1;
public class Shapes {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Shapes <shape> <size>");
            System.exit(1);
        }

        String shape = args[0];
        int size = Integer.valueOf(args[1]);

        if (!shape.equals("square") && !shape.equals("triangle") && !shape.equals("circle") && !shape.equals("rhombus")) {
            System.out.println("Invalid shape, must be either square, triangle, circle or rhombus.");
            System.exit(1);
        } else if (size <= 0) {
            System.out.println("Invalid size - it must be a positive integer.");
            System.exit(1);
        }

        if (shape.equals("square")) drawSquare(size);
        else if (shape.equals("triangle")) drawTriangle(size);
        else if (shape.equals("circle")) drawCircle(size);
        else drawRhombus(size);
    }

    public static void drawSquare(int size) {
        //draw top edge
        System.out.print(" ");
        for (int i = 0; i < size * 2; i++) {
            System.out.print("_");
        }
        System.out.println();

        //draw left, right and bottom edges
        for (int i = 0; i < size; i++) {
            //left edge
            System.out.print("|");
            for (int j = 0; j < size * 2; j++) {
                //bottom edge
                if (i == size - 1) System.out.print("_");
                else System.out.print(" ");
            }
            //right edge
            System.out.println("|");
        }
    }

    public static void drawTriangle(int size) {
        for (int i = 0; i <= size; i++) {
            //adjust to triangle left pos
            for (int s = 0; s <= size - i; s++) {
                System.out.print(" ");
            }

            //left edge
            System.out.print("/");

            for (int j = 0; j < i * 2; j++) {
                //bottom edge
                if (i == size) System.out.print("_");
                else System.out.print(" ");
            }
            System.out.println("\\");
        }
    }

    public static void drawCircle(int size) {
        int N = 2 * size;
        int x = 0, y = 0, r = size, R = size * size;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                x = r - i;
                y = r - j;
                int XYSum = x * x + y * y;
                if (Math.abs(XYSum - R) < size) {
                    System.out.print("XX");
                }
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void drawRhombus(int size) {
        int top = (int)Math.ceil(10 / 4.0 * (size + 1));
        int bottom = top - 1;

        for (int i = 0; i <= size + 1; i++) {
            //external empty space
            for (int j = 0; j <= size - i; j++) {
                System.out.print(" ");
            }

            //left edge
            if (i != 0) System.out.print("/");

            //top edge
            if (i == 0) {
                for (int j = 0; j < top; j++) {
                    System.out.print("_");
                }
            }
            //bottom edge
            else if (i == size + 1) {
                for (int j = 0; j < bottom; j++) {
                    System.out.print("_");
                }
            }
            //internal empty space
            else {
                for (int j = 0; j < bottom; j++) {
                    System.out.print(" ");
                }
            }
            //right edge
            if (i != 0) System.out.print("/");
            System.out.println();
        }
    }
}


