package net.tislib.springskeleton.util;

import net.tislib.springskeleton.exception.DisallowedUpdateException;
import net.tislib.springskeleton.resource.base.AllowAllFields;
import net.tislib.springskeleton.resource.base.PartialUpdate;
import net.tislib.springskeleton.resource.base.Resource;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import javax.validation.*;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class PartialUpdateUtil {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T extends Resource> void update(T existingResource, T updatedResource) {
        Set<String> ignoredProperties = new HashSet<>();
        ignoredProperties.add("id");

        ReflectionUtils.doWithFields(updatedResource.getClass(), field -> {
            if (!updatedResource.getFields().contains(field.getName())) {
                ignoredProperties.add(field.getName());
            }
        });

        BeanUtils.copyProperties(updatedResource, existingResource, ignoredProperties.toArray(new String[0]));
    }

    public static <UT extends PartialUpdate, T extends Resource> void validate(T existingResource, T updatedResource, Class<UT> updateClass) {
        Set<ConstraintViolation<T>> errors = validator.validate(existingResource);

        if (errors.size() > 0) {
            throw new ConstraintViolationException(errors);
        }

        // if resource class is annotated with AllowAllFields annotation we bypass field check
        if (updateClass.getAnnotation(AllowAllFields.class) == null) {
            Set<String> allowedFieldsForUpdate = new HashSet<>();
            ReflectionUtils.doWithMethods(updateClass, method -> {
                String methodName = method.getName();
                if (methodName.startsWith("get") || methodName.startsWith("is")) {
                    String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    allowedFieldsForUpdate.add(fieldName);
                }
            });

            HashSet<String> disallowedUpdatedFields = new HashSet<>(updatedResource.getFields());
            disallowedUpdatedFields.removeAll(allowedFieldsForUpdate);
            if (disallowedUpdatedFields.size() > 0) {
                String message = "Updating fields not allowed: " + String.join(",", disallowedUpdatedFields);
                throw new DisallowedUpdateException(disallowedUpdatedFields, message);
            }
        }

    }
}
