package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import lombok.Data;

/**
 * @Created - 7/20/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
public class MDEFieldDefinition {
    private int fieldNumber;
    private String category;
    private String itemNumber;
    private String name;
    private String label;
    private int rounds;
    private int position;
    private String type;
    private int itemLength;
    private boolean leadingZeroes;
    private String format;
    private boolean staticField;
    //TODO: Future Version:
    //private List<PossibleAnswers> possibleAnswers;
}
