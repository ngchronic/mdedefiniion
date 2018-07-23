package gov.cdc.nccdphp.esurveillance.data;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEDefinition;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Created - 7/23/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */

@Component
public class DataLoader {
    public static final int RECORD_NUMBER = 0;
    public static final int CATEGORY = 1;
    public static final int ITEM_LABEL = 2;
    public static final int VARIABLE_NAME = 3;
    public static final int VARIABLE_LABEL = 4;
    public static final int ROUDNS = 5;
    public static final int FILE_POSITION = 6;
    public static final int TYPE = 7;
    public static final int ITEM_LENGTH = 8;
    public static final int FIELD_LENGTH = 9;
    public static final int LEADING_ZEROS = 10;
    public static final int STATIC_FIELD 11;
    public static final int FORMAT = 12;
    public static final int JUSTIFICATION = 13;



    public  void loadData(String filename, String mmgName, String mmgCode, int mmgVersion) {
//        Map<String, AnswerChoice> loadedOids = new HashMap<>();
//        Map<String, Question> loadedQuestions = new HashMap<>();

        File file = new File(getClass().getClassLoader().getResource(filename).getFile());
//        try (Scanner scanner = new Scanner(file)) {
//            scanner.nextLine(); //skip first line
//            //Create MMG ->
//            MDEDefinition mmg = new MDEDefinition( "Wonder Woman Definition", "MDE_WW", "9.0.3")
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//                String validateAs = values[DATA_TYPE]; //Default validate as the regular Datatype.
//                if (values.length > VALIDATE_AS && values[VALIDATE_AS] != null && values[VALIDATE_AS].trim().length() > 0 ) {
//                    validateAs = values[VALIDATE_AS]; //In case you want to overwrite how it's validated (INV155 and PID 11.4 on FDD MMG)
//                }
//                if (CODED_QUESTION_TYPES.contains(validateAs) ) { //Will not load Cities because it's validated as ST; and will load PID-11.4 because it's validatedAs CWE.
//                    savedAnswerchoice = getAnswerChoices(values[PHINVADSOID], loadedOids);
//                }
//                if (values.length > PHINVADSOID && values[UNITS_OF_MEASURE] != null && values[UNITS_OF_MEASURE].trim().length() > 0 ) {
//                    savedUnitsOfMeasure = getAnswerChoices(values[PHINVADSOID].trim(), loadedOids);
//                }
//                Question q;
//                if (loadedQuestions.containsKey(values[QUESTION_CODE].trim())) {
//                    q = loadedQuestions.get(values[QUESTION_CODE].trim());
//                } else {
//                    q = new Question(values[QUESTION_CODE].trim());
//                    q.setCodeSystem(values[CODING_SYSTEM].trim());
//                    q.setName(values[DESCRIPTION].replace("\"", "").trim());
//                    q.setExpectedDataType(values[DATA_TYPE].trim());
//                    q = questionService.createQuestion(q);
//                    loadedQuestions.put(q.getCode(), q);
//                }
//                MMGQuestion mmgQ = new MMGQuestion(values[SECTION].trim(), values[SEGMENT].trim(), q, savedAnswerchoice);
//                mmgQ.setUnitsOfMeasure(savedUnitsOfMeasure);
//                mmgQ.setCdcPriority(values[CDC_PRIORITY].trim());
//                mmgQ.setCardinality(values[CARDINALITY].trim());
//                mmgQ.setValidateAs(validateAs);
//                mmg.addQuestion(mmgQ);
////                log.info("Added one more question - " + mmgQ.getQuestion().getCode());
//
//            }
//            mmgService.createMMG(mmg);
//            log.info("###### Data loaded!!");
//
//        } catch (FileNotFoundException e) {
//            log.error("File not found: " + e.getMessage());
//        }
    }
}
