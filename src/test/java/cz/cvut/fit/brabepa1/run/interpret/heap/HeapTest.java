package cz.cvut.fit.brabepa1.run.interpret.heap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pajcak
 */
public class HeapTest {

    private static Heap heap;

    public HeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        heap = Heap.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetInstance() {
        System.out.println("testGetInstance");
        Heap result = Heap.getInstance();
        assertNotNull(result);
    }

    @Test
    public void testInitHeapSize() {
        System.out.println("testInitHeapSize");
        int initialHeapSize = 8192;
        assertEquals(initialHeapSize, heap.getHeapSize());
    }
    
    @Test
    public void testAllocObject() {
        System.out.println("testAllocObject");
    }

    @Test
    public void testAllocArray() {
        System.out.println("testAllocArray");
        int elemType = 10; //int
        int elemCount = 5;
        ArrayRef arrRef = heap.allocArray(elemType, elemCount);
        assertTrue(heap.arrayRefs.contains(arrRef));
        assertEquals(arrRef.getCount(), elemCount);
        assertEquals(arrRef.getaType(), elemType);
        assertEquals(arrRef.getReferenceCnt(), 1);
        assertEquals(arrRef.getReferenceCnt(), 1);
        assertEquals(heap.getHeapPtr(), elemCount * 4 /*(size of integer)*/);
    }
    
    @Test
    public void testStoreBytes() {
        System.out.println("testStoreBytes");
        byte[] byteArr = new byte [] {1, 1, 1, 2, 2, 1, 1, 1};
        int memoryOffset = 0;
        heap.storeBytes(byteArr, memoryOffset);
        byte [] storedBytes = heap.getBytes(memoryOffset, byteArr.length);
        assertNotNull(storedBytes);
        Assert.assertArrayEquals(storedBytes, byteArr);
    }

}
