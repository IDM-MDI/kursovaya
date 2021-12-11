package by.ishangulyev.kursovaya.menu;

import by.ishangulyev.kursovaya.entity.User;

public abstract class Menu
{
    public void showMenu(){};
    public Menu nextMenu(User user)
    {
        return null;
    }
    public User getUser(){return null;}

    public void exit()
    {
        System.exit(0);
    }
}
