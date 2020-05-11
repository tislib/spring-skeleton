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
public class RedundantFieldsException extends RuntimeException {
    public static final String FIELD_REDUNDANT_FIELD_NAMES = "redundantFieldNames";
    public static final String FIELD_TARGET_CLASS = "targetClass";
    private static final long serialVersionUID = 1571804987728L;
    private Class<?> targetClass;
    private Collection<String> redundantFieldNames;

    public RedundantFieldsException(Class<?> targetClass, Collection<String> redundantFieldNames) {
        Validate.notNull(targetClass);
        Validate.isTrue(redundantFieldNames != null && redundantFieldNames.size() > 0);
        this.targetClass = targetClass;
        this.redundantFieldNames = redundantFieldNames;
    }

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Collection<String> getRedundantFieldNames() {
        return this.redundantFieldNames;
    }

    public String getMessage() {
        String fields = String.join(", ", this.redundantFieldNames);
        return String.format("Fields [%s] are not allowed for resource [%s].", fields, this.targetClass.getSimpleName());
    }
}
