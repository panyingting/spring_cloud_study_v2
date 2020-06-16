import org.junit.Test;

public class TestClass {

    @Test
    public void testMethod(){
        System.out.println(0x90);

        System.out.println( (byte)(-0x10));
        System.out.println( (byte)(0x2f));
        System.out.println( (byte)(144));
        System.out.println( (byte)(144+2));
    }
}
