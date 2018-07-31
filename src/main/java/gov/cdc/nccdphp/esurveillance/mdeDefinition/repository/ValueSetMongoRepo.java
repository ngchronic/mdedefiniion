package gov.cdc.nccdphp.esurveillance.mdeDefinition.repository;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.ValueSet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Created - 7/27/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
public interface ValueSetMongoRepo extends MongoRepository<ValueSet, String> {
}
