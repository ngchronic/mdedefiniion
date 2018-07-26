package gov.cdc.nccdphp.esurveillance.mdeDefinition.controller;

import gov.cdc.nccdphp.esurveillance.exceptions.InvalidDataException;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEDefinition;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.model.MDEFile;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.service.MDEDefinitionService;
import gov.cdc.nccdphp.esurveillance.mdeDefinition.service.MDETransformer;
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
    private MDEDefinitionService service;

    @Autowired
    private MDETransformer transformer;

    @GetMapping("/{mdeCode}")
    public MDEDefinition getMDE(@PathVariable String mdeCode, @RequestParam Optional<String> version) {
        log.info("AUDIT - retrieving definition for " + mdeCode);

        if (version.isPresent())
            return service.getMDE(mdeCode, version.get());
        else
            return service.getMDE(mdeCode, "LATEST");
    }

    @PostMapping("parse")
    public MDEFile parseContent(@RequestParam String defCode, @RequestParam String version,  @RequestBody String content) throws InvalidDataException {
        return transformer.parseContent(defCode, version, content);
    }

    @PostMapping("generate")
    public String generateFile(@RequestParam String defCode, @RequestParam String version,@RequestBody MDEFile file) throws InvalidDataException {
        return transformer.generateContent(defCode, version, file);
    }
}
