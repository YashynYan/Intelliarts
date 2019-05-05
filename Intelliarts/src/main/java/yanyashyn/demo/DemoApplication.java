package yanyashyn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.websocket.Session;

@SpringBootApplication
public class DemoApplication {

    @PostConstruct
    public void init() {

    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();


}
