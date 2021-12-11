package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.User;
import by.ishangulyev.kursovaya.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignInMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private String login,password;
    private User user;
    private List<User> list;
    private SessionFactory factory;
    private StandardServiceRegistry registry;

    public SignInMenu(User user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.user = user;
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
    }

    @Override
    public void showMenu() {}

    @Override
    public Menu nextMenu(User user)
    {
        System.out.println("Sign in");
        System.out.println("Email:");
        login = scanner.nextLine();
        System.out.println("Password:");
        password = scanner.nextLine();
        getUserFrom();

        if(isUser())
        {
            return new UserMenu(this.user);
        }
        else
        {
            return new RegistrationMenu(this.user);
        }
    }

    private void getUserFrom()
    {
        Session session = factory.openSession();
        session.beginTransaction();

        Query<User> one = session.createQuery("from User",User.class);
        list = one.getResultList();

        session.getTransaction().commit();
        session.close();
    }
    private boolean isUser()
    {
        for(User i:list)
        {
            if(login.equals(i.getEmail()))
            {
                if(password.equals(i.getPassword()))
                {
                    this.user = i;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User getUser()
    {
        return user;
    }

}
