package com.bosch.si.as;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ZipInZipProducerApplication implements CommandLineRunner {


    final static String queueName = "spring-boot";

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
    AmqpTemplate template = context.getBean(AmqpTemplate.class);
    //Receiver receiver = context.getBean(Receiver.class);


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ZipInZipProducerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);

        System.out.println("Sending message the old way...");
        Sender.send();

        System.out.println("Sending message the Spring way ...");
        template.convertAndSend(queueName, "Hello from RabbitMQ!");
        //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
