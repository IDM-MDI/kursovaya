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
        System.out.println("1)Войти\n2)Регистрация\n0)Выход");
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
                    System.out.println("Пока");
                    exit();
                }
                default -> {
                    System.out.println("Постарайтесь снова");
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
