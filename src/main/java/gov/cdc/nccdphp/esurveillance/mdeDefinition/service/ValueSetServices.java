package gov.cdc.nccdphp.esurveillance.mdeDefinition.service;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.ValueSet;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.repository.ValueSetMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Created - 8/9/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@Service
public class ValueSetServices {
    @Autowired
    private ValueSetMongoRepo repo;

    public Map<String, ValueSet> getValueSets(String mdeDef) {
        List<ValueSet> list = repo.findAll();

        Map<String, ValueSet> map =
                list.stream().collect(Collectors.toMap(item -> item.getName(), item -> item));

        return map;

    }
}
