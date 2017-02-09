package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.math;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author pavel
 */
public class IaddTest {

    public IaddTest() {
    }

    @Test
    public void testExecute() {
        for (int i = 0; i < 100; i++) {
            
            StackFrame mockedSF = new StackFrame() {

                Integer first = new Random().nextInt(10000) - 5000;
                Integer second = new Random().nextInt(10000) - 5000;

                public boolean operandPushed = false;
                public boolean pcIncremented = false;

                int state = 0;

                @Override
                public Object popOperand() {
                    if (state == 0) {
                        state++;
                        return first;
                    } else {
                        return second;
                    }
                }

                @Override
                public void pushOperand(Object obj) {
                    Integer result = (Integer) obj;
                    Integer expected = first + second;
                    Assert.assertEquals(expected, result);
                    operandPushed = true;
                }

                @Override
                public void incrementPc() {
                    pcIncremented = true;
                }

                @Override
                public void discard() {
                    Assert.assertTrue(pcIncremented);
                    Assert.assertTrue(pcIncremented);
                }
                
            };

            Iadd add = new Iadd();
            add.execute(mockedSF);
            mockedSF.discard();
        }
    }

}
