package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created - 7/25/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
public class Row {
    private int rowNumber;
    private List<Field> fields;

    public Row() {

    }

    public Row(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void addField(Field field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
    }
}
