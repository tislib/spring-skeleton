//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import java.util.Collection;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredFieldsException extends RuntimeException {
    public static final String FIELD_REQUIRED_FIELD_NAMES = "requiredFieldNames";
    public static final String FIELD_TARGET_CLASS = "targetClass";
    private static final long serialVersionUID = 1571815950005L;
    private Class<?> targetClass;
    private Collection<String> requiredFieldNames;

    public RequiredFieldsException(Class<?> targetClass, Collection<String> requiredFieldNames) {
        Validate.notNull(targetClass);
        Validate.isTrue(requiredFieldNames != null && requiredFieldNames.size() > 0);
        this.targetClass = targetClass;
        this.requiredFieldNames = requiredFieldNames;
    }

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Collection<String> getRequiredFieldNames() {
        return this.requiredFieldNames;
    }

    public String getMessage() {
        String fields = String.join(", ", this.requiredFieldNames);
        return String.format("Fields [%s] are required for resource [%s].", fields, this.targetClass.getSimpleName());
    }
}
