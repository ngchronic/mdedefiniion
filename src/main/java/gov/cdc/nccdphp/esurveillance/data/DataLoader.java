package gov.cdc.nccdphp.esurveillance.data;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEDefinition;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEFieldDefinition;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.ValueSet;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.repository.ValueSetMongoRepo;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.service.MDEDefinitionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @Created - 7/23/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */

@Component
public class DataLoader {

    private static final int VS_NAME = 0;
    @Autowired
    MDEDefinitionService service;
    @Autowired
    ValueSetMongoRepo valueSetRepo;

    private Log log = LogFactory.getLog(DataLoader.class);

    private static final int RECORD_NUMBER = 0;
    private static final int CATEGORY = 1;
    private static final int ITEM_LABEL = 2;
    private static final int VARIABLE_NAME = 3;
    private static final int VARIABLE_LABEL = 4;
    private static final int ROUNDS = 5;
    private static final int FILE_POSITION = 6;
    private static final int TYPE = 7;
    private static final int ITEM_LENGTH = 8;
    private static final int FIELD_LENGTH = 9;
    private static final int LEADING_ZEROS = 10;
    private static final int STATIC_FIELD = 11;
    private static final int FORMAT = 12;
    private static final int JUSTIFICATION = 13;
    private static final int RANGE_MIN = 14;
    private static final int RANGE_MAX = 15;
    private static final int VALUE_SET = 16;



    public void loadFieldDefinitions(String filename, String mmgName, String mmgCode, String mmgVersion) {
        InputStream file = getClass().getClassLoader().getResourceAsStream(filename);
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); //skip first line
            //Create MMG ->
            MDEDefinition mmg = new MDEDefinition( mmgName, mmgCode, mmgVersion);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                MDEFieldDefinition field = new MDEFieldDefinition();
                    field.setFieldNumber(new Integer(values[RECORD_NUMBER]));
                    field.setCategory(values[CATEGORY]);
                    field.setItemNumber(values[ITEM_LABEL]);
                    field.setName(values[VARIABLE_NAME]);
                    field.setLabel(values[VARIABLE_LABEL].replaceAll("\"", ""));
                    field.setRounds(new Integer(values[ROUNDS]));
                    field.setPosition(new Integer(values[FILE_POSITION]));
                    field.setType(values[TYPE]);
                    field.setItemLength(new Integer(values[ITEM_LENGTH]));
                    field.setLeadingZeroes("Y".equals(values[LEADING_ZEROS]));
                    field.setStaticField("Y".equals(values[STATIC_FIELD]));
                    field.setFormat(values[FORMAT]);
                    field.setJustification(values[JUSTIFICATION]);
                    field.setRangeMin(getDoubleValue(values[RANGE_MIN]));
                    field.setRangeMax(getDoubleValue(values[RANGE_MAX]));
                    field.setPossibleAnswers(values[VALUE_SET]);

                mmg.addField(field);
            }
            service.save(mmg);
            log.info("###### Data loaded!!");
        }
    }

    public void loadDataSets(String filename) {
        InputStream file = getClass().getClassLoader().getResourceAsStream(filename);
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); //skip first line
            //Create MMG ->
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                ValueSet valueSet = new ValueSet(values[VS_NAME]);
                for (int i = 1; i < values.length; i++) { //Add all possible value Sets:
                    String[] vs_code = values[i].split("\\^");
                    valueSet.addChoice(vs_code[0], vs_code[1]);
                }
                valueSetRepo.save(valueSet);
            }
        }
    }

    private Double getDoubleValue(String value) {
        if (value == null || value.trim().length() == 0) {
            return 0.0;
        } else {
            return new Double(value);
        }
    }
}
