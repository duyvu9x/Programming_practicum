
/**
 * This class is the entry point into the application.
 * 
 * @author Duy Vu
 * @version Winter 2021
 */
public final class HelloMain {

    /**
     * Private constructor to avoid instantiation.
     */
    private HelloMain() {

    }

    /**
     * This is the entry point.
     * 
     * @param theArgs the command line arguments
     */
    public static void main(final String[] theArgs) {
        HelloWorld.sayGoodbye();

        final HelloWorld hello = new HelloWorld("Duy");
        hello.sayHello();

        new HelloMain();
    }

    /**
     * method to return average length.
     * 
     * @param theStrgs is array list of string
     * @return average length of strings
     */

    public static double averageLength(final String... theStrgs) {

        double count = 0;
        double n = 0;
        while (theStrgs.length > 0) {
            for (int i = 0; i < theStrgs.length; i++) {
                n = n + theStrgs[i].length();
                count++;
            }

            return n / count;

        }
        return 0;
    }

}
