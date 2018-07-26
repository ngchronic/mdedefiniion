package gov.cdc.nccdphp.esurveillance.mdeDefinition.service;

import gov.cdc.nccdphp.esurveillance.exceptions.InvalidDataException;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @Created - 7/25/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MDETransformerTest {

    public static final String WW_MDE = "WW_MDE";
    public static final String VERSION = "9.0.3";

    @Autowired
    MDETransformer transformer;
    @Test
    public void parseContent() throws InvalidDataException {
        parse();
    }

    @Test
    public void generateContent() throws InvalidDataException {
        MDEFile file = this.parse();
        String newFile = transformer.generateContent(WW_MDE, VERSION, file);
        System.out.println("New File generated...");
        System.out.println("newFile = \n\n" + newFile);
    }

    private MDEFile parse() throws InvalidDataException {
        InputStream is =  getClass().getClassLoader().getResourceAsStream("testFile.mde");
        String content = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        MDEFile file = transformer.parseContent("WW_MDE", "9.0.3", content);
        System.out.println("file = " + file);
        return file;
    }
}