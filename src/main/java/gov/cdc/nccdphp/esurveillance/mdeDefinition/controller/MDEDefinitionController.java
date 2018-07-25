package gov.cdc.nccdphp.esurveillance.mdeDefinition.controller;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEDefinition;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.repository.MDEDefinitionMongoRepo;
import gov.cdc.nccdphp.esurveillance.rest.ApiVersion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Created - 7/23/18
 * @Author Marcelo Caldas mcq1@cdc.gov
 */
@RestController
@RequestMapping("/mde/")
@ApiVersion({1})
public class MDEDefinitionController {
    Log log = LogFactory.getLog(MDEDefinitionController.class);

    @Autowired
    private MDEDefinitionMongoRepo repo;

    @GetMapping("/{mdeCode}")
    public MDEDefinition getMDE(@PathVariable String mdeCode, @RequestParam Optional<String> version) {
        log.info("AUDIT - retrieving definition for " + mdeCode);

        if (version.isPresent())
            return repo.findByCodeAndVersion(mdeCode, version.get());
        else
            return repo.findByCodeAndVersion(mdeCode, "9.0.0");
    }
}
