package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import lombok.Data;

/**
 * @Created - 7/25/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
public class Field implements Comparable {
    private int fieldNumber;
    private String fieldItem;
    private String[] values;

    public Field(int fieldNumber, String fieldItem) {
        this.fieldNumber = fieldNumber;
        this.fieldItem = fieldItem;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
