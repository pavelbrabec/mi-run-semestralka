package cz.cvut.fit.brabepa1.run.interpret.instructions;

import cz.cvut.fit.brabepa1.run.interpret.instructions.impl.AConstNull;
import cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ALoad;
import cz.cvut.fit.brabepa1.run.interpret.instructions.impl.Nop;
import java.util.List;
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
public class JavaInstructionFactoryTest {

    private static JavaInstructionFactory jif;

    public JavaInstructionFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jif = JavaInstructionFactory.getInstance();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        JavaInstructionFactory result = JavaInstructionFactory.getInstance();
        assertNotNull(result);
    }

    @Test
    public void testRegisterInstruction() {
        System.out.println("registerInstruction");
        Instruction instruction = new AConstNull();
        int instrOpCode = 0x01;
        jif.registerInstruction(instrOpCode, instruction);
        Instruction receivedInstr = jif.getInstruction(instrOpCode);
        assertNotNull(receivedInstr);
        assertEquals(receivedInstr, instruction);
    }

    @Test
    public void testCreateInstructions() {
        jif.registerInstruction(0x01, new AConstNull());
        jif.registerInstruction(0x19, new ALoad());
        jif.registerInstruction(0x0, new Nop());

        System.out.println("createInstructions");
        byte[] instructionsCode = new byte[]{
            0x01, // AConstNull
            0x19, // ALoad
            0x1, // ALoad index
            0x0, // Nop
        };
        List<Instruction> instrs = jif.createInstructions(instructionsCode);
        assertTrue(instrs.get(0) instanceof AConstNull);
        assertTrue(instrs.get(1) instanceof ALoad);
        assertEquals(((ALoad) instrs.get(1)).bytes(), 2);
        assertTrue(instrs.get(2) instanceof Nop);
    }
}
