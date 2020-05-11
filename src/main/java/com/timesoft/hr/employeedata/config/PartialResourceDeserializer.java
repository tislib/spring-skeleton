package com.timesoft.hr.employeedata.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.timesoft.hr.employeedata.resource.base.PartialUpdate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class PartialResourceDeserializer extends StdDeserializer<PartialUpdate> implements ResolvableDeserializer {
    private static final long serialVersionUID = 1L;
    static final String UNABLE_TO_READ_FIELD = "Unable to read field %s from object %s";
    private final JsonDeserializer<?> defaultDeserializer;

    PartialResourceDeserializer(JsonDeserializer<?> defaultDeserializer, Class<?> valueType) {
        super(valueType);
        this.defaultDeserializer = defaultDeserializer;
    }

    public PartialUpdate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = (JsonNode)parser.readValueAsTree();

        //Object resource = defaultDeserializer.deserialize(new )

        return (PartialUpdate) null;
    }

    public void resolve(DeserializationContext context) throws JsonMappingException {
        ((ResolvableDeserializer)this.defaultDeserializer).resolve(context);
    }
}