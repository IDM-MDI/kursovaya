package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.Cart;
import by.ishangulyev.kursovaya.entity.Gadget;
import by.ishangulyev.kursovaya.entity.Orders;
import by.ishangulyev.kursovaya.entity.User;
import by.ishangulyev.kursovaya.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private User user;
    private List<Gadget> GadgetList;
    private List<Orders> orderEntities;
    private List<Cart> cartEntities;
    private GadgetMenu menu;

    public UserMenu(User user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.menu  = new GadgetMenu(this.user);
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("\n1)Gadgets");
        if(isAdmin())
            System.out.println("2)Users");
        System.out.println("7)Show your cart");
        System.out.println("8)Show your account info");
        System.out.println("9)Back" +
                "\n0)Exit");
    }

    @Override
    public Menu nextMenu(User user)
    {
        while(true)
        {
            showMenu();
            input = scanner.nextLine();
            switch(input)
            {
                case "1" -> {
                    return new GadgetMenu(this.user);
                }
                case "2" -> {
                    if(isAdmin())
                        showAccounts();
                    continue;
                }
                case "7" -> {
                    showCart();
                    continue;
                }
                case "8" -> {
                    showAccInfo();
                    continue;
                }
                case "9" -> {
                    return new RegistrationMenu(new User());
                }
                case "0" -> {
                    System.out.println("See you next time");
                    exit();
                }
                default -> {
                    System.out.println("Try again");
                    showMenu();
                }
            }
            return null;
        }
    }

    private void showCart()
    {
        showAccInfo();
        getAllInfo();
        System.out.println("Gadgets: { ");
        List<Gadget> list = findGadgets(findOrderID(findCartID()));
        for (Gadget i: list)
        {
            System.out.print(i.getName() + "\t");
        }
        System.out.print(" }");
    }

    @Override
    public User getUser()
    {
        return user;
    }

    private boolean isAdmin()
    {
        return this.user.getRoleId().equals(2);
    }

    private void showAccInfo()
    {
        System.out.println("Email: " + this.user.getEmail()
                + "\nPassword: " + this.user.getPassword()
                + "\nName: " + this.user.getUsername()
                + "\nRegistration time: " + this.user.getCreateTime()
                + "\nRole: User");
    }
    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();
        cartEntities = session.createQuery("from Cart",Cart.class).getResultList();
        orderEntities = session.createQuery("from Orders ",Orders.class).getResultList();
        GadgetList = menu.getGadgetList();
        session.getTransaction().commit();
        session.close();
    }
    private List<Integer> findCartID()
    {
        List<Integer> result = new ArrayList<>();

        for(Cart i : cartEntities)
        {
            if(i.getUserId().equals(this.user.getId()))
            {
                result.add(i.getId());
            }
        }

        return result;
    }
    private List<Integer> findOrderID(List<Integer> integers)
    {
        List<Integer> result = new ArrayList<>();

        for (Orders orderEntity : orderEntities)
        {
            for (int j = 0; j < integers.size(); j++)
            {
                if (orderEntity.getCartId().equals(integers.get(j)))
                {
                    result.add(orderEntity.getGadgetId());
                }
            }
        }

        return result;
    }
    private List<Gadget> findGadgets(List<Integer> integers)
    {
        List<Gadget> result = new ArrayList<>();

        for (int i = 0; i < GadgetList.size(); i++)
        {
            for (int j = 0; j < integers.size(); j++)
            {
                if(GadgetList.get(i).getId().equals(integers.get(j)))
                {
                    result.add(GadgetList.get(i));
                }
            }
        }

        return result;
    }
    private void showAccounts()
    {
        Session session = factory.openSession();

        session.beginTransaction();
        List l = session.createQuery("SELECT u.id,u.username,u.email,u.password,r.name,u.createTime " +
                "FROM User u " +
                "LEFT JOIN 'Role' r on u.roleId = r.id ").getResultList();

        String formats = "%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s";
        String formats1 = "%s\t%s\t%s\t%s\t%s\t%s";
        System.out.println(String.format(formats,"ID","Name","Email","Password","Role","CreatedTime"));
        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object rows[] = (Object[])iterator.next();
            System.out.println(String.format(formats1,rows[0],rows[1],rows[2],rows[3],rows[4],rows[5]));
        }
        session.getTransaction().commit();
        session.close();
    }
}
