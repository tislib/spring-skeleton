//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

class PartialResourceDeserializer extends StdDeserializer<PartialResource> implements ResolvableDeserializer {
    private static final long serialVersionUID = 1L;
    static final String UNABLE_TO_READ_FIELD = "Unable to read field %s from object %s";
    private final JsonDeserializer<?> defaultDeserializer;

    PartialResourceDeserializer(JsonDeserializer<?> defaultDeserializer, Class<?> valueType) {
        super(valueType);
        this.defaultDeserializer = defaultDeserializer;
    }

    public PartialResource deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = (JsonNode)parser.readValueAsTree();
        JsonParser newParser = new TreeTraversingParser(node, parser.getCodec());
        Throwable var5 = null;

        try {
            newParser.nextToken();
            List<String> fieldNames = new ArrayList();
            node.fieldNames().forEachRemaining(fieldNames::add);
            PartialResource resource = (PartialResource)this.defaultDeserializer.deserialize(newParser, context);
            Map<String, Object> properties = new HashMap();
            Iterator var9 = fieldNames.iterator();

            while(var9.hasNext()) {
                String field = (String)var9.next();

                try {
                    properties.put(field, FieldUtils.readField(resource, field, true));
                } catch (IllegalAccessException var21) {
                    throw new IOException(String.format("Unable to read field %s from object %s", field, resource.getClass().getName()));
                } catch (IllegalArgumentException var22) {
                    properties.put(field, node.findValue(field).asText());
                }
            }

            List<Field> fieldsWithAlias = FieldUtils.getFieldsListWithAnnotation(resource.getClass(), JsonProperty.class);
            fieldsWithAlias.forEach((fieldx) -> {
                String jsonPropertyName = ((JsonProperty)fieldx.getAnnotation(JsonProperty.class)).value();
                if (StringUtils.isNotEmpty(jsonPropertyName) && properties.containsKey(jsonPropertyName)) {
                    Object value = properties.get(jsonPropertyName);
                    properties.remove(jsonPropertyName);
                    properties.put(fieldx.getName(), value);
                }

            });
            resource.setPartialProperties(properties);
            PartialResource var25 = resource;
            return var25;
        } catch (Throwable var23) {
            var5 = var23;
            throw var23;
        } finally {
            if (newParser != null) {
                if (var5 != null) {
                    try {
                        newParser.close();
                    } catch (Throwable var20) {
                        var5.addSuppressed(var20);
                    }
                } else {
                    newParser.close();
                }
            }

        }
    }

    public void resolve(DeserializationContext context) throws JsonMappingException {
        ((ResolvableDeserializer)this.defaultDeserializer).resolve(context);
    }
}
