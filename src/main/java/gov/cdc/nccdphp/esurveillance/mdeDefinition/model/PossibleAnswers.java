package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import gov.cdc.nccdphp.esurveillance.model.AnswerChoice;
import lombok.Data;

import java.util.List;

/**
 * @Created - 7/20/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
public class PossibleAnswers {
    private String name;
    private String description;
    private List<AnswerChoice> choices;

}
