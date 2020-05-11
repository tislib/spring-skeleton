//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

class PartialResourceDeserializerModifier extends BeanDeserializerModifier {
    PartialResourceDeserializerModifier() {
    }

    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        return PartialResource.class.isAssignableFrom(beanDesc.getBeanClass()) ? new PartialResourceDeserializer(deserializer, beanDesc.getBeanClass()) : deserializer;
    }
}
