//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import lombok.SneakyThrows;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;

public final class ClassUtils {
    static final String FAILED_TO_LOOKUP_PROPERTY = "Failed to lookup %s for property [%s]";
    static final String MISSING_GETTER_FOR_PROPERTY = "Missing getter for property [%s] in class [%s]";
    static final String MISSING_SETTER_FOR_PROPERTY = "Missing setter for property [%s] in class [%s]";

    public static Collection<PropertyDescriptor> extractPropertyDescriptors(Class<?> clazz) {
        Map<String, PropertyDescriptor> propertyDescriptorMap = new HashMap();
        extractPropertyDescriptorsInternally(propertyDescriptorMap, clazz);
        return propertyDescriptorMap.values();
    }

    public static Object getFieldValue(Object object, String field) {
        PropertyDescriptor descriptor = (PropertyDescriptor)Validate.notNull(BeanUtils.getPropertyDescriptor(object.getClass(), field), "Failed to lookup %s for property [%s]", new Object[]{object.getClass(), field});
        Method reader = (Method)Validate.notNull(descriptor.getReadMethod(), "Missing getter for property [%s] in class [%s]", new Object[]{field, object.getClass()});
        return getFieldValue(object, reader);
    }

    @SneakyThrows
    public static Object getFieldValue(Object object, Method reader) {
        try {
            reader.setAccessible(true);
            return reader.invoke(object);
        } catch (Throwable var3) {
            throw var3;
        }
    }

    @SneakyThrows
    public static void setFieldValue(Object object, String field, Object value) {
        try {
            PropertyDescriptor descriptor = (PropertyDescriptor)Validate.notNull(BeanUtils.getPropertyDescriptor(object.getClass(), field), "Failed to lookup %s for property [%s]", new Object[]{object.getClass(), field});
            Method writer = (Method)Validate.notNull(descriptor.getWriteMethod(), "Missing setter for property [%s] in class [%s]", new Object[]{field, object.getClass()});
            writer.setAccessible(true);
            writer.invoke(object, value);
        } catch (Throwable var5) {
            throw var5;
        }
    }

    private static void extractPropertyDescriptorsInternally(Map<String, PropertyDescriptor> propertyDescriptorMap, Class<?> clazz) {
        if (clazz != Object.class && clazz != null) {
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
            PropertyDescriptor[] var3 = propertyDescriptors;
            int var4 = propertyDescriptors.length;

            int var5;
            for(var5 = 0; var5 < var4; ++var5) {
                PropertyDescriptor propertyDescriptor = var3[var5];
                if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getReadMethod().getDeclaringClass() != Object.class && !propertyDescriptorMap.containsKey(propertyDescriptor.getName())) {
                    propertyDescriptorMap.put(propertyDescriptor.getName(), propertyDescriptor);
                }
            }

            extractPropertyDescriptorsInternally(propertyDescriptorMap, clazz.getSuperclass());
            Class[] var7 = clazz.getInterfaces();
            var4 = var7.length;

            for(var5 = 0; var5 < var4; ++var5) {
                Class<?> interfaceClass = var7[var5];
                extractPropertyDescriptorsInternally(propertyDescriptorMap, interfaceClass);
            }

        }
    }

    @Generated
    private ClassUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
