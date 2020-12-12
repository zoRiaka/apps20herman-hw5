package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }
    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testAvrarage() {
        double expResult = 1.0;
        double result = StreamApp.streamAvar(intStream);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testMin() {
        int expResult = -1;
        int result = StreamApp.streamMin(intStream);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testMax() {
        int expResult = 3;
        int result = StreamApp.streamMax(intStream);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testSum() {
        int expResult = 5;
        int result = StreamApp.streamSum(intStream);
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testCount() {
        long expResult = 5;
        long result = StreamApp.streamCount(intStream);
        assertEquals(expResult, result, 0.0001);
    }
}
