package gov.cdc.nccdphp.esurveillance.mdeDefinition.repository;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Created - 7/23/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
public interface MDEDefinitionMongoRepo extends MongoRepository<MDEDefinition, String> {
     MDEDefinition findByCodeAndVersion(String code, String version);
}
