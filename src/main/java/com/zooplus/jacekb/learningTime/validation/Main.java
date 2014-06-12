package com.zooplus.jacekb.learningTime.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: jacek_bilski
 * Date: 12.06.14
 * Time: 11:45
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-validation.xml");
        Entity2Service service = applicationContext.getBean(Entity2Service.class);
//        System.out.println("Service class: " + service.getClass().getCanonicalName());
//        System.out.println("Annotations:");
//        for (Annotation annotation : service.getClass().getAnnotations()) {
//            System.out.println("Annotation: " + annotation.toString());
//        }
//        System.out.println("Interfaces:");
//        for (Class i : service.getClass().getInterfaces()) {
//            System.out.println("Interface: " + i.toString());
//            System.out.println("Interface's annotations:");
//            for (Annotation annotation : i.getAnnotations()) {
//                System.out.println("Annotation: " + annotation.toString());
//            }
//        }
        service.processEntity2(null);
        System.out.println("null processed");
        Entity2 entity2 = new Entity2();
        service.processEntity2(entity2);
        System.out.println("Entity2 processed");
    }
}
