package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.*;
import by.ishangulyev.kursovaya.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;

public class MaterialMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private static final String NUMBER = "\\d+";
    private User user;
    private Scanner scanner;
    private String input;

    public MaterialMenu(User user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Добавить Процессор\n" +
                            "2)Добавить Память\n" +
                            "3)Добавить Батарею\n" +
                            "4)Добавить Экран\n" +
                            "5)Добавить Звук\n" +
                            "9)Вернуться");
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
                    addCpu();
                    continue;
                }
                case "2" -> {
                    addMem();
                    continue;
                }
                case "3" -> {
                    addBat();
                    continue;
                }
                case "4" -> {
                    addVid();
                    continue;
                }
                case "5" -> {
                    addAud();
                    continue;
                }
                case "9" -> {
                    return new UserMenu(this.user);
                }
                default -> {
                    System.out.println("Постарайтесь снова");
                    continue;
                }
            }
        }
    }

    private Audio createAud()
    {
        Audio result = new Audio();

        System.out.println("Enter audio name:");
        input = scanner.nextLine();
        result.setName(input);

        System.out.println("Enter audio frequency:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            result.setFrequency(Integer.parseInt(input));
        }
        else
        {
            result = null;
        }
        return result;
    }
    private Video createVid()
    {
        Video result = new Video();

        System.out.println("Enter video name:");
        input = scanner.nextLine();
        result.setName(input);

        System.out.println("Enter video resolution:");
        input = scanner.nextLine();
        result.setResolution(input);

        System.out.println("Enter video ratio:");
        input = scanner.nextLine();
        result.setRatio(input);

        System.out.println("Enter video brightness:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            result.setBrightness(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Указанное вами строка не является цифрой");
            result = null;
        }

        return result;
    }
    private Battery createBat()
    {
        Battery result = new Battery();

        System.out.println("Enter battery name:");
        input = scanner.nextLine();
        result.setName(input);

        System.out.println("Enter battery mah:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            result.setMah(Integer.parseInt(input));
        }
        else
        {
            result = null;
        }

        return result;
    }
    private Memory createMem()
    {
        Memory result = new Memory();

        System.out.println("Enter memory name:");
        input = scanner.nextLine();
        result.setName(input);

        System.out.println("Enter memory size:");
        input = scanner.nextLine();
        result.setSize(input);

        return result;
    }
    private Cpu createCpu()
    {
        Cpu result = new Cpu();

        System.out.println("Enter cpu name:");
        input = scanner.nextLine();
        result.setName(input);

        System.out.println("Enter core name:");
        input = scanner.nextLine();
        result.setCore(input);

        System.out.println("Enter frequency:");
        input = scanner.nextLine();
        result.setFrequence(input);

        System.out.println("Enter bit:");
        input = scanner.nextLine();
        result.setBit(input);

        return result;
    }


    private void addCpu()
    {
        Cpu cpu = createCpu();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(cpu);
        session.getTransaction().commit();
        session.close();

    }
    private void addMem()
    {
        Memory mem = createMem();
        showMemoryType();
        //TODO:
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(mem);
        session.getTransaction().commit();
        session.close();
    }
    private void addBat()
    {
        Battery bat = createBat();

        Session session = factory.openSession();
        session.beginTransaction();
        session.save(bat);
        session.getTransaction().commit();
        session.close();
    }
    private void addVid()
    {
        Video video = createVid();
        if(video != null)
        {
            showVideoType();
            //TODO:


            Session session = factory.openSession();
            session.beginTransaction();
            session.save(video);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            return;
        }
    }
    private void addAud()
    {
        Audio audio = createAud();
        if(audio != null)
        {
            showAudioType();
            //TODO:


            Session session = factory.openSession();
            session.beginTransaction();
            session.save(audio);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            return;
        }
    }


    private void showAudioType()            //TODO:
    {
    }
    private void showVideoType()             //TODO:
    {
    }
    private void showMemoryType()           //TODO:
    {
    }

    @Override
    public User getUser()
    {
        return this.user;
    }
}
