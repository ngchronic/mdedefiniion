package gov.cdc.nccdphp.esurveillance.model;

import lombok.Data;

/**
 * @Created - 7/20/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 *
 * Answer Choice can be either a code and Label or a numeric value within range: [rangeMin-rangeMax]
 *
 */
@Data
public class AnswerChoice {
    private String label;
    private int code;
    private int rangeMin;
    private int rangeMax;
}
