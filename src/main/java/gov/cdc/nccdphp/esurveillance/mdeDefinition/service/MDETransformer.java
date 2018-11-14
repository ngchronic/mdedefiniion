package gov.cdc.nccdphp.esurveillance.mdeDefinition.service;

import gov.cdc.nccdphp.esurveillance.exceptions.InvalidDataException;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.*;
import gov.cdc.nccdphp.esurveillance.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Created - 7/25/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Service
public class MDETransformer {
    public static final String JUSTIFY_RIGHT = "Right";
    public static final char PAD_ZERO = '0';
    public static final char PAD_SPACE = ' ';
    @Autowired
    private MDEDefinitionService definitionService;

    private Map<String, MDEDefinition> definitions = new HashMap<>();

    public MDEFile parseContent(String defCode, String version, String content) throws InvalidDataException {
        MDEDefinition def = this.getDefinition(defCode, version);
        MDEFile file = new MDEFile();
        int rowNumber = 1;
        try (Scanner scanner = new Scanner(content)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().length() > 0) {
                    Row row = new Row(rowNumber++);
                    row.setFields(
                            def.getFields().entrySet().stream().map(e -> populateField(line, e.getValue())).collect(Collectors.toList())
                    );
                    file.addRow(row);
                }
            }
        }
        return file;
    }

    private Field populateField(String line, MDEFieldDefinition fieldDef) {
        Field field = new Field(fieldDef.getFieldNumber(), fieldDef.getItemNumber());
        if (line.length() >= fieldDef.getPosition()) {
            String values = line.substring(fieldDef.getPosition() - 1, Math.min(fieldDef.getPosition() - 1 + fieldDef.getItemLength() * fieldDef.getRounds(), line.length()));
            //This line divides the values equaly by item length.
            field.setValues(values.split("(?<=\\G.{" + fieldDef.getItemLength() + "})"));
        }
        return field;
    }

    public String generateContent(String defCode, String version, MDEFile file) throws InvalidDataException {
        MDEDefinition def = this.getDefinition(defCode, version);
        StringBuilder content = new StringBuilder();
        for (Row row : file.getRows()) {
            StringBuilder line = new StringBuilder();
            row.getFields().sort(Comparator.comparingInt(Field::getFieldNumber));
            for (Field field: row.getFields()) { // Fields should be sorted!
                line.append(getFieldValue(field, def.getFields().get(field.getFieldItem())));
            }
            content.append(line + "\n");
        }
        return content.toString();
    }

    private String getFieldValue(Field field, MDEFieldDefinition fieldDef) {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < fieldDef.getRounds(); i++) {//Have to append all values for all Rounds:
            String aValue = "";
            if (field.getValues() != null && field.getValues().length > i) {
                aValue = field.getValues()[i];
            }
            String paddedValue = StringUtils.padGrow(aValue, fieldDef.isLeadingZeroes()? PAD_ZERO : PAD_SPACE, fieldDef.getItemLength(), fieldDef.getJustification().equals(JUSTIFY_RIGHT)?StringUtils.PAD_LEFT:StringUtils.PAD_RIGHT);
            //Truncate value in case is too big (keep file safe! loose data!)
            value.append(paddedValue, 0, Math.min(paddedValue.length(), fieldDef.getItemLength()));

        }
        return value.toString();
    }

    private MDEDefinition getDefinition(String code, String version) throws InvalidDataException {
        String key = code + "-" + version;
        MDEDefinition def =  definitions.get(key);
        if (def == null) {
            def = definitionService.getMDE(code, version);
            if (def == null) {
                throw new InvalidDataException("No MDE Definition found for " + code + " and version " + version);
            }
            definitions.put(key, def);
        }
        return def;
    }
}


