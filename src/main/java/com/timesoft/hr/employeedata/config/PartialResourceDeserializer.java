package com.timesoft.hr.employeedata.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.timesoft.hr.employeedata.resource.base.BaseResource;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PartialResourceDeserializer extends StdDeserializer<BaseResource> implements ResolvableDeserializer {
    static final String UNABLE_TO_READ_FIELD = "Unable to read field %s from object %s";
    private static final long serialVersionUID = 1L;
    private final JsonDeserializer<?> defaultDeserializer;

    PartialResourceDeserializer(JsonDeserializer<?> defaultDeserializer, Class<?> valueType) {
        super(valueType);
        this.defaultDeserializer = defaultDeserializer;
    }

    public BaseResource deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();
        JsonParser newParser = new TreeTraversingParser(node, parser.getCodec());

        newParser.nextToken();

        BaseResource resource = (BaseResource) defaultDeserializer.deserialize(newParser, context);

        Set<String> fields = new HashSet<>();

        node.fields().forEachRemaining(item -> {
            fields.add(item.getKey());
        });

        resource.setFields(fields);

        return resource;
    }

    public void resolve(DeserializationContext context) throws JsonMappingException {
        ((ResolvableDeserializer) this.defaultDeserializer).resolve(context);
    }
}