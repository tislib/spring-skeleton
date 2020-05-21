package net.tislib.springskeleton.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import net.tislib.springskeleton.resource.base.BaseResource;
import org.springframework.stereotype.Component;

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
                return BaseResource.class.isAssignableFrom(beanDesc.getBeanClass()) ? new PartialResourceDeserializer(deserializer, beanDesc.getBeanClass()) : deserializer;
            }
        });
    }
}
