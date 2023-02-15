package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("Fedor", "Shestakov", "fed@mail.ru");
      userService.add(user1);
      carService.addCar(new Car("kia", 100, user1));

      User user2 = new User("Ivan", "Ivanov", "ivan@mail.ru");
      userService.add(user2);
      carService.addCar(new Car("bmw", 120, user2));

      User user3 = new User("Oleg", "Sokolov", "sokol@mail.ru");
      userService.add(user3);
      carService.addCar(new Car("Hyndai", 90, user3));

      User user4 = new User("Alex", "Petrov", "alex42@gmail.ru");
      userService.add(user4);
      carService.addCar(new Car("Haval", 20, user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(carService.getUserByCar("Haval", 20));

      context.close();
   }
}
