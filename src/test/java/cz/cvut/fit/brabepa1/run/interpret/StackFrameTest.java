package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import java.io.DataInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pajcak
 */
public class StackFrameTest {
    
    private static VirtualMachine dummyVM;
    private static ClassFile dummyCF;
    private static Method dummyMethod;
    
    public StackFrameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dummyCF = ClassFileReader.lookupAndResolve("TestDyn");
        dummyVM = new VirtualMachine(dummyCF);
        dummyMethod = dummyCF.getMethod("<init>", "()V");
        fail("TODO");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInvoker method, of class StackFrame.
     */
    @Test
    public void testGetInvoker() {
        System.out.println("getInvoker");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassFile method, of class StackFrame.
     */
    @Test
    public void testGetClassFile() {
        System.out.println("getClassFile");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMethod method, of class StackFrame.
     */
    @Test
    public void testGetMethod() {
        System.out.println("getMethod");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of peekOperand method, of class StackFrame.
     */
    @Test
    public void testPeekOperand() {
        System.out.println("peekOperand");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of popOperand method, of class StackFrame.
     */
    @Test
    public void testPopOperand() {
        System.out.println("popOperand");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushOperand method, of class StackFrame.
     */
    @Test
    public void testPushOperand() {
        System.out.println("pushOperand");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementPc method, of class StackFrame.
     */
    @Test
    public void testIncrementPc() {
        System.out.println("incrementPc");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOffsetToPc method, of class StackFrame.
     */
    @Test
    public void testAddOffsetToPc() {
        System.out.println("addOffsetToPc");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValue method, of class StackFrame.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class StackFrame.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVM method, of class StackFrame.
     */
    @Test
    public void testGetVM() {
        System.out.println("getVM");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of discard method, of class StackFrame.
     */
    @Test
    public void testDiscard() {
        System.out.println("discard");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class StackFrame.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
