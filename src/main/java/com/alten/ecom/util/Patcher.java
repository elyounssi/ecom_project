package com.alten.ecom.util;

import com.alten.ecom.dto.ProductDTO;

import java.lang.reflect.Field;

public class Patcher {

    public static void productPatcher(ProductDTO existing, ProductDTO incomplete) throws IllegalAccessException {

        Class<?> productClass = ProductDTO.class;
        Field[] productFields = productClass.getDeclaredFields();

        for(Field field: productFields) {
            field.setAccessible(true);

            Object value = field.get(incomplete);
            if(value != null){
                field.set(existing, value);
            }

            field.setAccessible(false);
        }

    }

}
