package dfw.template.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestVelocityTemplate {


    @Test
    public void common() throws Exception {

        // 存放
        VelocityContext context = new VelocityContext();
        context.put("title", "Velocity tutorials");
        context.put("welcome", "Welcome to Velocity tutorials!");

        Map<String, String> tutorial = new HashMap<>();
        tutorial.put("url", "http://tutorials.waycoolsearch.com/java/");
        tutorial.put("name", "java tutorial");
        context.put("tutorial", tutorial);

        List<String> list = Arrays.asList("JAXB", "JAXP", "Flex", "Velocity","Annotation", "Hibernate");
        context.put("list", list);

        String testVmOutput = applyTemplate("test.vm", context);
        String testIncludeVmOutput = applyTemplate("testInclude.vm", context);
        String listVmOutput = applyTemplate("list.vm", context);

        System.out.println("testVmOutput = " + testVmOutput);
        System.out.println("testIncludeVmOutput = " + testIncludeVmOutput);
        System.out.println("listVmOutput = " + listVmOutput);


        Assert.assertTrue(testVmOutput.contains(listVmOutput));
        Assert.assertFalse(testIncludeVmOutput.contains(listVmOutput));


    }


    public static String applyTemplate(String templateFile,
                                       VelocityContext context) throws Exception {
        try {
            Velocity.init();

            Template template = Velocity.getTemplate(templateFile);

            Writer writer = new StringWriter();
            template.merge(context, writer);
            return writer.toString();

        } catch (Exception e) {
            throw e;
        }
    }
}
