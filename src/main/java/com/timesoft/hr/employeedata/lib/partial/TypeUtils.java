//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.timesoft.hr.employeedata.lib.partial;

import lombok.Generated;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public final class TypeUtils {
    public static Class<?> extractElementClass(Method readMethod) {
        throw new UnsupportedOperationException();
//        Class<?> clazz = Collection.class.isAssignableFrom(readMethod.getReturnType()) ? GenericCollectionTypeResolver.getCollectionReturnType(readMethod) : readMethod.getReturnType();
//        return clazz == null ? Object.class : clazz;
    }

    public static Class<?> extractElementClass(Field field) {
        throw new UnsupportedOperationException();
//        Class<?> clazz = Collection.class.isAssignableFrom(field.getType()) ? GenericCollectionTypeResolver.getCollectionFieldType(field) : field.getType();
//        return clazz == null ? Object.class : clazz;
    }

    @Generated
    private TypeUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
