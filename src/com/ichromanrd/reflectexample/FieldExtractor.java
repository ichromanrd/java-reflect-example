package com.ichromanrd.reflectexample;

import java.lang.reflect.Field;

public class FieldExtractor {
    public String field;
    public Class clazz;

    public Object processDefault(Object o) throws NoSuchFieldException, IllegalAccessException {
        String[] subFields = field.split("\\.");
        if (subFields.length == 1) {
            return extractFieldValue(o);
        }

        return extractSubFieldValue(0, subFields, o);
    }

    private Object extractSubFieldValue(int currentIndex, String[] subFields, Object o)
            throws NoSuchFieldException, IllegalAccessException {
        int lastFieldIndex = subFields.length - 1;
        if (currentIndex == lastFieldIndex) {
            String lastField = subFields[lastFieldIndex];
            return extractFieldValue(o, lastField);
        }

        String currentField = subFields[currentIndex];
        Object currentValue = extractFieldValue(o, currentField);
        if (currentValue == null) {
            return null;
        }

        return extractSubFieldValue(currentIndex + 1, subFields, currentValue);
    }

    private Object extractFieldValue(Object o) throws NoSuchFieldException, IllegalAccessException {
        Field f = clazz.getDeclaredField(field);
        if (!f.isAccessible()) {
            f.setAccessible(true);
        }

        return f.get(o);
    }

    private Object extractFieldValue(Object o, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = o.getClass().getDeclaredField(fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        return field.get(o);
    }
}
