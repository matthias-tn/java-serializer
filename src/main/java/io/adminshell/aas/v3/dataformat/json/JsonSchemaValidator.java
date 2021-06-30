package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;
import io.adminshell.aas.v3.dataformat.SchemaValidator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for validating a serialized instance of AssetAdministrationShellEnvironment
 * against a json-schema.
 */
public class JsonSchemaValidator implements SchemaValidator {

    private static final String SCHEMA = "/aas.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonSchemaValidator() {}

    /**
     * validates against default schema
     *
     * @param serialized AssetAdministrationShellEnvironment,
     *                   serialized as json string
     * @return Set of messages to display validation results
     */
    @Override
    public Set<String> validateSchema(String serialized) {
        try {
            return validateSchema(serialized, loadDefaultSchema());
        } catch (IOException | URISyntaxException e) {
            return Set.of(e.getMessage());
        }
    }

    /**
     * validates against custom schema
     *
     * @param serialized AssetAdministrationShellEnvironment,
     *                   serialized as json string
     * @param serializedSchema Custom json-schema serialized as String
     *                         that must extend the default aas-schema
     * @return Set of messages to display validation results
     */
    public Set<String> validateSchema(String serialized, String serializedSchema) {
        try {
            JsonNode schemaRootNode = mapper.readTree(serializedSchema);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(schemaRootNode));
            JsonSchema schema = factory.getSchema(schemaRootNode);
            JsonNode node = mapper.readTree(serialized);
            Set<ValidationMessage> validationMessages = schema.validate(node);
            return generalizeValidationMessagesAsStringSet(validationMessages);
        } catch (JsonProcessingException e) {
            return Set.of(e.getMessage());
        }
    }

    private String loadDefaultSchema() throws IOException, URISyntaxException {
        return Files.readString(Paths.get(getClass().getResource(SCHEMA).toURI()));
    }

    private Set<String> generalizeValidationMessagesAsStringSet(Set<ValidationMessage> messages) {
        return messages.stream()
                .map(ValidationMessage::getMessage)
                .collect(Collectors.toSet());
    }
}
