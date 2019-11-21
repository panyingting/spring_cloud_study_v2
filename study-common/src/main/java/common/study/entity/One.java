package common.study.entity;

import common.study.annotation.ExtAnnotation;

import java.lang.annotation.Annotation;

/**
 * Created by panyingting on 2018/10/8.
 */
@ExtAnnotation
public class One {

    public static void main(String[] args) {
        Annotation[] annotations = One.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
            Annotation[] annotations2 = annotation.annotationType().getDeclaredAnnotations();
            System.out.println(annotations2[3].annotationType());
        }
    }
}
