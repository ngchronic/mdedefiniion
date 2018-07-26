package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created - 7/25/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
public class MDEFile {

    private String fileName;
    private List<Row> rows;

    public void addRow(Row row) {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }
        this.rows.add(row);
    }
}
