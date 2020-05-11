//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Stream;

import lombok.Generated;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PartialResourceUtils {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(PartialResourceUtils.class);
    private static final String NOT_AN_INSTANCE = "[%s] is not an instance of [%s]";
    private static final String NOT_SAME_CLASS = "The inputs are not instances of the same class ([%s] and [%s])";

    public static void validate(PartialResource partialResource, Class<?> partialInterface) {
        Validate.notNull(partialResource);
        Validate.notNull(partialInterface);
        Validate.isTrue(partialInterface.isInterface(), "%s is not an interface", new Object[]{partialInterface.getName()});
        Validate.isTrue(partialInterface.isInstance(partialResource), "[%s] is not an instance of [%s]", new Object[]{partialResource.getClass().getName(), partialInterface.getName()});
        Collection<PropertyDescriptor> sourceDescriptors = ClassUtils.extractPropertyDescriptors(partialInterface);
        validateNoRedundantFields(partialResource, sourceDescriptors);
        validateRequiredFields(partialResource, sourceDescriptors);
        validateNested(partialResource, partialInterface);
    }

    public static void copy(PartialResource source, PartialResource target) {
        Validate.notNull(source);
        Validate.notNull(target);
        Validate.isTrue(source.getClass().equals(target.getClass()), "The inputs are not instances of the same class ([%s] and [%s])", new Object[]{source.getClass().getName(), target.getClass().getName()});
        Map<String, Object> properties = source.getPartialProperties();
        target.setPartialProperties(properties);
        Set<Entry<String, Object>> entries = properties.entrySet();
        Iterator var4 = entries.iterator();

        while (true) {
            while (var4.hasNext()) {
                Entry<String, Object> entry = (Entry) var4.next();
                Object sourceValue = entry.getValue();
                Object targetValue = ClassUtils.getFieldValue(target, (String) entry.getKey());
                if (sourceValue instanceof PartialResource && targetValue instanceof PartialResource) {
                    copy((PartialResource) sourceValue, (PartialResource) targetValue);
                } else if (sourceValue instanceof Collection && targetValue instanceof Collection) {
                    copyCollection((Collection) sourceValue, (Collection) targetValue);
                } else {
                    ClassUtils.setFieldValue(target, (String) entry.getKey(), sourceValue);
                }
            }

            return;
        }
    }

    private static void validateNested(PartialResource partialResource, Class<?> partialInterface) {
        Method[] methods = partialInterface.getMethods();
        Method[] var3 = methods;
        int var4 = methods.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Method method = var3[var5];
            Class<?> fieldClass = TypeUtils.extractElementClass(method);
            if (BasePartial.class.isAssignableFrom(fieldClass)) {
                Object value = ClassUtils.getFieldValue(partialResource, method);
                if (value instanceof PartialResource) {
                    validate((PartialResource) value, fieldClass);
                } else if (value instanceof Collection) {
                    Iterator var9 = ((Collection) value).iterator();

                    while (var9.hasNext()) {
                        Object element = var9.next();
                        if (element instanceof PartialResource) {
                            validate((PartialResource) element, fieldClass);
                        }
                    }
                }
            }
        }

    }

    private static void copyCollection(Collection source, Collection target) {
        Collection backupCollection = new ArrayList(target);
        target.clear();
        Iterator var3 = source.iterator();

        while (true) {
            while (var3.hasNext()) {
                Object sourceElement = var3.next();
                Stream var10000 = backupCollection.stream();
                sourceElement.getClass();
                Object targetElement = var10000.filter(sourceElement::equals).findFirst().orElse((Object) null);
                if (sourceElement instanceof PartialResource && targetElement instanceof PartialResource) {
                    target.add(targetElement);
                    copy((PartialResource) sourceElement, (PartialResource) targetElement);
                } else {
                    target.add(sourceElement);
                }
            }

            return;
        }
    }

    private static void validateNoRedundantFields(PartialResource partialResource, Collection<PropertyDescriptor> sourceDescriptors) {
        Set<String> updatedFields = new HashSet(partialResource.getPartialProperties().keySet());
        sourceDescriptors.stream().map(FeatureDescriptor::getName).forEach(updatedFields::remove);
        if (!updatedFields.isEmpty()) {
            throw new RedundantFieldsException(partialResource.getClass(), updatedFields);
        }
    }

    private static void validateRequiredFields(PartialResource partialResource, Collection<PropertyDescriptor> sourceDescriptors) {
        List<String> invalidFields = new ArrayList();
        Iterator var3 = sourceDescriptors.iterator();

        while (var3.hasNext()) {
            PropertyDescriptor sourceDescriptor = (PropertyDescriptor) var3.next();
            if (sourceDescriptor.getReadMethod().getAnnotation(Required.class) != null) {
                Object value = partialResource.getPartialProperties().get(sourceDescriptor.getName());
                if (isRequiredValueInvalid(value)) {
                    invalidFields.add(sourceDescriptor.getName());
                }
            }
        }

        if (!invalidFields.isEmpty()) {
            throw new RequiredFieldsException(partialResource.getClass(), invalidFields);
        }
    }

    private static boolean isRequiredValueInvalid(Object value) {
        if (value == null) {
            return true;
        } else {
            if (value instanceof Collection) {
                Collection collection = (Collection) value;
                if (collection.isEmpty()) {
                    return true;
                }

                Iterator var2 = collection.iterator();

                while (var2.hasNext()) {
                    Object element = var2.next();
                    if (isRequiredValueInvalid(element)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Generated
    private PartialResourceUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
