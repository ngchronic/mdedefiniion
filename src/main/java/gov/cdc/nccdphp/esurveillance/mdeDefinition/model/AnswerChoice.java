package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Created - 7/20/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 *
 * Answer Choice can be either a code and Label or a numeric value within range: [rangeMin-rangeMax]
 *
 */
@Data
@AllArgsConstructor
public class AnswerChoice {
    private String code;
    private String label;
}
