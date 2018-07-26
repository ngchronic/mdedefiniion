package gov.cdc.nccdphp.esurveillance.mdeDefinition.service;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEDefinition;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEFile;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.repository.MDEDefinitionMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MDEDefinitionService {
    @Autowired
    private MDEDefinitionMongoRepo repo;

    public MDEDefinition save(MDEDefinition mdeDef) {
        return repo.save(mdeDef);
    }

    public MDEDefinition getMDE(String code, String version) {
        return repo.findByCodeAndVersion(code, version);
    }



}
