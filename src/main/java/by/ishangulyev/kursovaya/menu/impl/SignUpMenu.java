package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.Cart;
import by.ishangulyev.kursovaya.entity.User;
import by.ishangulyev.kursovaya.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.sql.Date;
import java.util.Scanner;

public class SignUpMenu extends Menu
{
    private Scanner scanner;
    private User user;
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    public SignUpMenu(User user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showMenu()
    {
    }

    @Override
    public Menu nextMenu(User user)
    {
        System.out.println("Регистрация");
        System.out.println("Почта:");
        user.setEmail(scanner.nextLine());
        System.out.println("Пароль:");
        user.setPassword(scanner.nextLine());
        System.out.println("Имя пользователя:");
        user.setUsername(scanner.nextLine());
        saveUser();
        saveCart();
        this.user = new User();
        return new RegistrationMenu(this.user);
    }

    private void saveCart()
    {
        Session session = factory.openSession();
        session.beginTransaction();

        Cart cart = new Cart();
        cart.setUserId(this.user.getId());

        session.save(cart);

        session.getTransaction().commit();
        session.close();

    }

    private void saveUser()
    {
        Session session = factory.openSession();
        session.beginTransaction();

        user.setRoleId(1);

        long millis=System.currentTimeMillis();
        Date date = new Date(millis);
        user.setCreateTime(date);
        session.save(this.user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUser()
    {
        return user;
    }
}
