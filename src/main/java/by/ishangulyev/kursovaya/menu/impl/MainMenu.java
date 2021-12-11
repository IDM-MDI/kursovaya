package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.User;
import by.ishangulyev.kursovaya.menu.Menu;

import java.util.Scanner;

public class MainMenu
{
    private Menu menu;
    private User user;

    public MainMenu()
    {
        user = new User();
    }

    public void start()
    {
        menu = new RegistrationMenu(user);
        while(true)
        {
            menu = menu.nextMenu(user);
            this.user = menu.getUser();
        }
    }


}
