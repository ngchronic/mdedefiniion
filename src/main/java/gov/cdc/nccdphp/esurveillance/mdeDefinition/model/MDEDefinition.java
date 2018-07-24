package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import gov.cdc.nccdphp.esurveillance.model.AuditFields;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created - 7/20/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
@Document(collection="mde_definitions")
@NoArgsConstructor
public class MDEDefinition extends AuditFields {
    @Id //Mongo internal ID
    private String id;


    private String name;
    private String code;
    private String version;
    private Map<String, MDEFieldDefinition> fields;

    public MDEDefinition(String name, String code, String version) {
        super();
        this.name = name;
        this.code = code;
        this.version = version;
    }

    public void addField(MDEFieldDefinition field) {
        if (fields == null) {
            fields = new HashMap<>();
        }
        fields.put(field.getItemNumber(), field);
    }
}
