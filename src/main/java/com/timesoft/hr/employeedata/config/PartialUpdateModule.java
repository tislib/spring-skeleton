package com.timesoft.hr.employeedata.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.timesoft.hr.employeedata.lib.partial.PartialResource;
import com.timesoft.hr.employeedata.resource.base.PartialUpdate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartialUpdateModule extends Module {


    @Override
    public String getModuleName() {
        return this.getClass().getName();
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addBeanDeserializerModifier(new BeanDeserializerModifier() {
            public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                return PartialUpdate.class.isAssignableFrom(beanDesc.getBeanClass()) ? new PartialResourceDeserializer(deserializer, beanDesc.getBeanClass()) : deserializer;
            }
        });
    }
}
