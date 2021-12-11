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
import java.util.List;
import java.util.Scanner;

public class BuyMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private User user;
    private GadgetMenu menu;
    private final String NUMBER = "\\d+";
    private List<Gadget> gadgetList;
    private List<Cart> cartEntities;

    public BuyMenu(User user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.gadgetList = new ArrayList<>();
        this.cartEntities = new ArrayList<>();
        this.menu  = new GadgetMenu(this.user);
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        menu.showGadgets();
        System.out.println("Choice which shoes u should buy");
        System.out.println("0)Back");
    }

    @Override
    public Menu nextMenu(User user)
    {
        while(true)
        {
            getAllInfo();
            showMenu();
            input = scanner.nextLine();
            switch(input)
            {
                case "0" -> {
                    return new UserMenu(this.user);
                }
                default -> {
                    if(input.matches(NUMBER) && menu.isGadgetExist(Integer.parseInt(input)))
                    {
                        addOrder();
                        return new UserMenu(this.user);
                    }
                    else
                    {
                        System.out.println("Try again");
                        continue;
                    }
                }
            }
        }
    }

    private void addOrder()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        Orders order = new Orders();
        Cart cart = findCart(user.getId(),cartEntities);
        order.setCartId(cart.getId());
        order.setGadgetId(Integer.parseInt(input));
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUser()
    {
        return this.user;
    }

    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        gadgetList = menu.getGadgetList();
        cartEntities = session.createQuery("from Cart ",Cart.class).getResultList();

        session.getTransaction().commit();
        session.close();
    }
    private Cart findCart(Integer id, List<Cart> carts)
    {
        boolean result = false;
        for (Cart i : carts)
        {
            if(i.getUserId().equals(id))
            {
                return i;
            }
        }
        return null;
    }
}
