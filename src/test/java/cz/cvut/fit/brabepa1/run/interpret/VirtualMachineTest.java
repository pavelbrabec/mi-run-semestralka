/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.brabepa1.run.interpret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pavel
 */
public class VirtualMachineTest {

    @BeforeClass
    public static void copyJar() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("cp target/run-interpret.jar .");
        p.waitFor();
    }

    @Test
    public void testStackPush() throws IOException, InterruptedException {
        List<String> classFiles = new ArrayList<String>() {
            {
                add("TestPrimes");
            }
        };

        List<String> outputs = new ArrayList<String>() {
            {
                add("VirtualMachine-output:	17984");
            }
        };

        for (int i = 0; i < classFiles.size(); i++) {
            Process p = Runtime.getRuntime().exec("java -jar run-interpret.jar test_files/" + classFiles.get(i));
            p.waitFor();
            String output = readOutput(p);
            Assert.assertEquals(outputs.get(i), output);
        }

    }

    private String readOutput(Process p) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
