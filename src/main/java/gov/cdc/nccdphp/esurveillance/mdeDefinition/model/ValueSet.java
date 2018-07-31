package gov.cdc.nccdphp.esurveillance.mdeDefinition.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created - 7/20/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Data
@Document(collection="ValueSet")
public class ValueSet {
    @Indexed(name="valueset_name", unique = true)
    private String name;
    private String description;
    private List<AnswerChoice> choices;

    public ValueSet(String name) {
        this.name = name;
    }

    public void addChoice(AnswerChoice choice) {
        if (this.choices == null) {
            this.choices = new ArrayList<>();
        }
        this.choices.add(choice);
    }

    public void addChoice(String code, String label) {
        this.addChoice(new AnswerChoice(code, label));
    }
}
