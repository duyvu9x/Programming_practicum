
/**
 * Object from this class are able to say hello.
 * 
 * @author Duy Vu
 * @version Winter 2021
 *
 */
public class HelloWorld {
    /** Holds the name odd the user. */
    private final String myName;

    /**
     * Constructs a HelloWorld object.
     * 
     * @param theName the name of the HelloWord use
     */
    public HelloWorld(final String theName) {
        myName = theName;
    }

    /**
     * Say hello to the user.
     */

    public void sayHello() {
        System.out.println("Hello" + myName);
    }

    /**
     * Say goodbye to the class.
     */
    public static void sayGoodbye() {
        System.out.println("Bye");
    }

}
