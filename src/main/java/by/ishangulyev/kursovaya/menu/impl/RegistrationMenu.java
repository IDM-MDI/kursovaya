package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.User;
import by.ishangulyev.kursovaya.menu.Menu;

import java.util.Scanner;

public class RegistrationMenu extends Menu
{
    private Scanner scanner;
    private User user;
    private  String input;

    public RegistrationMenu(User user)
    {
        this.user = user;
        scanner = new Scanner(System.in);
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Sign in\n2)Sign up\n0)Exit");
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
                    return new SignInMenu(this.user);
                }
                case "2" -> {
                    return new SignUpMenu(this.user);
                }
                case "0" -> {
                    System.out.println("Bye");
                    exit();
                }
                default -> {
                    System.out.println("Try again");
                    continue;
                }
            }
            return null;
        }
    }

    @Override
    public void exit()
    {
        super.exit();
    }
    @Override
    public User getUser()
    {
        return user;
    }

}
