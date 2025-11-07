package meeting;

public class Check {

    public static int width = 13;
    public static int height = 13;


    public static void main(String[] args) {


        int midWidth = (height + 1) / 2;

        int midHeight = (width + 1) / 2;

        for (int h = 0; h <= height; h++) {
            for (int w = 0; w <= width; w++) {
                if (w == 0 || w == midWidth || w == width ||h==0||h==midHeight||h==height) {
                    System.out.print("*");
                    continue;
                }


                if (height-h==width-w){
                    System.out.print("*");
                    continue;
                }
                if (height-h==w){
                    System.out.print("*");
                    continue;
                }
                System.out.print(" ");
            }
            System.out.println();

        }
    }

}
