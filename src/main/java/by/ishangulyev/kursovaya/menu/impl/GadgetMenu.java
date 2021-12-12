package by.ishangulyev.kursovaya.menu.impl;

import by.ishangulyev.kursovaya.entity.*;
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

public class GadgetMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private User user;
    private List<Gadget> gadgets;
    private List<Cpu> cpus;
    private List<Memory> memories;
    private List<Memorytype> memorytypes;
    private List<Battery> batteries;
    private List<Video> videos;
    private List<Videotype> videotypes;
    private List<Category> categories;
    private List<Audio> audios;
    private List<Audiotype> audiotypes;

    private final String NUMBER = "\\d+";

    public GadgetMenu(User user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Показать все гаджеты");
        System.out.println("2)Показать определенный гаджет");
        if(isAdmin())
        {
            System.out.println("3)Добавить гаджет" +
                            "\n4)Обновить гаджет" +
                            "\n5)Удалить гаджет" +
                            "\n6)Добавить характеристики");
        }
        System.out.println("9)Вернуться" +
                "\n0)Выход");
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
                case "1" -> {
                    if(isListsEmpty())
                    {
                        System.out.println("Gadgets empty");
                        continue;
                    }
                    showGadgets();
                    continue;
                }
                case "2" -> {
                    showGadget();
                    continue;
                }
                case "3" -> {
                    if(isAdmin())
                    {
                        addGadget();
                    }
                    continue;
                }
                case "4" -> {
                    if(isAdmin())
                    {
                        if(gadgets.isEmpty())
                        {
                            continue;
                        }
                        updateGadget();
                    }
                    continue;
                }
                case "5" -> {
                    if(isAdmin())
                    {
                        if(gadgets.isEmpty())
                        {
                            continue;
                        }
                        deleteGadget();
                    }
                    continue;
                }
                case "6" -> {
                    if(isAdmin())
                    {
                        return new MaterialMenu(this.user);
                    }
                    continue;
                }
                case "9" -> {
                    return new UserMenu(this.user);
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

    public void showGadgets()
    {
        System.out.println("ID\tName\tCPU\tMemory\tBattery\tVideo\tAudio\tCategory\tPrice");
        Session session = factory.openSession();

        session.beginTransaction();
        List l = session.createQuery("SELECT g.id,g.name,c.name,m.name,b.name,v.name,a.name,c.name,g.price " +
                "FROM Gadget g " +
                "LEFT JOIN Cpu c on g.cpuId = c.id " +
                "LEFT JOIN Memory m on g.memoryId = m.id " +
                "LEFT JOIN Battery b on g.batteryId = b.id " +
                "LEFT JOIN Video v on g.videoId = v.id " +
                "LEFT JOIN Audio a on g.audioId = a.id " +
                "LEFT JOIN Category cat on g.categoryId = cat.id").getResultList();

        String formats = "%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s";
        String formats1 = "%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s";
        System.out.println(String.format(formats,"GadgetID","GadgetName","Manufacture","Color","Season","MaterialInside","MaterialOutside","MaterialUpper","MaterialSole"));
        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object rows[] = (Object[])iterator.next();
            System.out.println(String.format(formats1,rows[0],rows[1],rows[2],rows[3],rows[4],rows[5],rows[6],rows[7],rows[8]));
        }
        session.getTransaction().commit();
        session.close();
    }
    private void showGadget()
    {
        showGadgets();
        System.out.println("Enter ID: ");
        input = scanner.nextLine();
        if(input.matches(NUMBER) && isGadgetExist(Integer.parseInt(input)))
        {
            Gadget gadget = findGadget(Integer.parseInt(input));
            Category category = findCategory(gadget.getCategoryId());
            Audio audio = findAudio(gadget.getAudioId());
            Audiotype audiotype = findAudioType(audio.getAudiotypeId());
            Video video = findVideo(gadget.getVideoId());
            Videotype videotype = findVideoType(video.getVideotypeId());
            Memory memory = findMem(gadget.getMemoryId());
            Memorytype memorytype = findMemType(memory.getMemorytypeId());
            Cpu cpu = findCpu(gadget.getCpuId());
            Battery battery = findBattery(gadget.getBatteryId());

            System.out.println("ID: " + gadget.getId() +
                                "Name: " + gadget.getName() +
                                "Category: " + category.getName() +
                                "Price: " + gadget.getPrice() +
                                "CPU Name: " + cpu.getName() +
                                "CPU Core: " + cpu.getCore() +
                                "CPU Frequency: " + cpu.getFrequence() +
                                "CPU Bit: " + cpu.getBit() +
                                "Memory Name: " + memory.getName() +
                                "Memory Size: " + memory.getSize() +
                                "MemoryType: " + memorytype.getName() +
                                "Battery Name: " + battery.getName() +
                                "Battery MaH: " + battery.getMah() +
                                "Video Name: " + video.getName() +
                                "Video Resolution: " + video.getResolution() +
                                "Video Ratio: " + video.getRatio() +
                                "Video Brightness: " + video.getBrightness() +
                                "VideoType: " + videotype.getName() +
                                "Audio Name: " + audio.getName() +
                                "Audio Frequency: " + audio.getFrequency() +
                                "AudioType: " + audiotype.getName());

        }
        else
        {
            return;
        }


    }

    private void addGadget()
    {
        Session session = factory.openSession();
        Gadget gadget = createGadget();
        if(gadget == null)
        {
            return;
        }
        else
        {
            session.beginTransaction();
            session.save(gadget);
            session.getTransaction().commit();
            session.close();
        }
    }
    private void updateGadget()
    {
        showGadgets();
        input = scanner.nextLine();
        if(input.matches(NUMBER) && isGadgetExist(Integer.parseInt(input)))
        {
            Integer id = Integer.parseInt(input);
            Gadget entity = createGadget();
            entity.setId(id);

            registry = new StandardServiceRegistryBuilder().configure().build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            System.out.println("Error while input number");
            return;
        }
    }
    private void deleteGadget()
    {
        showGadgets();
        input = scanner.nextLine();
        if(input.matches(NUMBER) && isGadgetExist(Integer.parseInt(input)))
        {
            Integer id = Integer.parseInt(input);
            Gadget entity = new Gadget();
            entity.setId(id);


            registry = new StandardServiceRegistryBuilder().configure().build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    private Gadget createGadget()
    {
        Gadget entity = new Gadget();
        System.out.println("Enter gadget name: ");
        input = scanner.nextLine();
        entity.setName(input);

        input = choiceCpu();
        if(input.matches(NUMBER) && isCpuExist(Integer.parseInt(input)))
        {
            entity.setCpuId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        input = choiceMemory();
        if(input.matches(NUMBER) && isMemoryExist(Integer.parseInt(input)))
        {
            entity.setMemoryId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        input = choiceBattery();
        if(input.matches(NUMBER) && isBatteryExist(Integer.parseInt(input)))
        {
            entity.setBatteryId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        input = choiceVideo();
        if(input.matches(NUMBER) && isVideoExist(Integer.parseInt(input)))
        {
            entity.setVideoId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        input = choiceAudio();
        if(input.matches(NUMBER) && isAudioExist(Integer.parseInt(input)))
        {
            entity.setAudioId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        input = choiceCategory();
        if(input.matches(NUMBER) && isCategoryExist(Integer.parseInt(input)))
        {
            entity.setCategoryId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        System.out.println("Enter the gadget price:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            entity.setPrice(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }
        return entity;
    }

    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        gadgets = session.createQuery("from Gadget",Gadget.class).getResultList();
        cpus = session.createQuery("from Cpu ",Cpu.class).getResultList();
        batteries = session.createQuery("from Battery ",Battery.class).getResultList();
        memories = session.createQuery("from Memory ",Memory.class).getResultList();
        memorytypes = session.createQuery("from Memorytype ",Memorytype.class).getResultList();
        categories = session.createQuery("from Category ",Category.class).getResultList();
        audios = session.createQuery("from Audio ",Audio.class).getResultList();
        audiotypes = session.createQuery("from Audiotype ",Audiotype.class).getResultList();
        videos = session.createQuery("from Video ",Video.class).getResultList();
        videotypes = session.createQuery("from Videotype ",Videotype.class).getResultList();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUser() {return user;}

    public List<Gadget> getGadgetList()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        gadgets = session.createQuery("from Gadget",Gadget.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return gadgets;
    }

    private String choiceCategory()
    {
        System.out.println("Category:");
        System.out.println("ID\tName");
        for(Category i : categories)
        {
            System.out.println(i.getId() + "\t" + i.getName());
        }
        System.out.println("Enter ID:");
        return scanner.nextLine();
    }

    private String choiceCpu()
    {
        System.out.println("CPU:");
        System.out.println("ID\tName\tCore\tFrequence\tBit");
        for(Cpu i : cpus)
        {
            System.out.println(i.getId() + "\t" + i.getName() + "\t" + i.getCore()
                                         +  "\t" + i.getFrequence() + "\t" + i.getBit());
        }
        System.out.println("Enter ID:");
        return scanner.nextLine();
    }
    private String choiceAudio()
    {
        System.out.println("Audio:");
        System.out.println("ID\tName\tFrequence\tAudioType");
        for(Audio i : audios)
        {
            System.out.println(i.getId() + "\t" + i.getName() + "\t" + i.getFrequency());
            for (Audiotype j: audiotypes)
            {
                if(i.getAudiotypeId().equals(j.getId()))
                {
                    System.out.print("\t" + j.getName());
                }
            }
        }
        System.out.println("Enter ID:");
        return scanner.nextLine();
    }
    private String choiceVideo()
    {
        System.out.println("Video:");
        System.out.println("ID\tName\tResolution\tRatio\tBrightness\tVideoType");
        for(Video i : videos)
        {
            System.out.println(i.getId() + "\t" + i.getName() +
                    "\t" + i.getResolution() + "\t" + i.getRatio() + "\t" + i.getBrightness());
            for (Videotype j: videotypes)
            {
                if(i.getVideotypeId().equals(j.getId()))
                {
                    System.out.print("\t" + j.getName());
                }
            }
        }
        System.out.println("Enter ID:");
        return scanner.nextLine();
    }
    private String choiceBattery()
    {
        System.out.println("Battery:");

        System.out.println("ID\tName\tMaH");

        for(Battery i : batteries)
        {
            System.out.println(i.getId() + "\t" + i.getName() + "\t" + i.getMah());
        }

        System.out.println("Enter ID:");
        return scanner.nextLine();
    }
    private String choiceMemory()
    {
        System.out.println("Memory:");
        System.out.println("ID\tName\tSize\tMemoryType");
        for(Memory i : memories)
        {
            System.out.println(i.getId() + "\t" + i.getName() + "\t" + i.getSize());
            for (Memorytype j: memorytypes)
            {
                if(i.getMemorytypeId().equals(j.getId()))
                {
                    System.out.print("\t" + j.getName());
                }
            }
        }
        System.out.println("Enter ID:");
        return scanner.nextLine();
    }

    public boolean isGadgetExist(Integer id)
    {
        boolean result = false;
        for (Gadget i: gadgets)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isCategoryExist(Integer id)
    {
        boolean result = false;
        for (Category i: categories)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isAudioExist(Integer id)
    {
        boolean result = false;
        for (Audio i: audios)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isVideoExist(Integer id)
    {
        boolean result = false;
        for (Video i: videos)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isBatteryExist(Integer id)
    {
        boolean result = false;
        for (Battery i: batteries)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isMemoryExist(Integer id)
    {
        boolean result = false;
        for (Memory i: memories)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isCpuExist(Integer id)
    {
        boolean result = false;
        for (Cpu i: cpus)
        {
            if(i.getId().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isListsEmpty()
    {
        return cpus.isEmpty() && memories.isEmpty() && batteries.isEmpty()
                && videos.isEmpty() && audios.isEmpty() && categories.isEmpty();
    }
    private boolean isAdmin()
    {
        return this.user.getRoleId().equals(2);
    }

    private Gadget findGadget(Integer id)
    {
        Gadget result = null;
        for(Gadget i: gadgets)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Category findCategory(Integer id)
    {
        Category result = null;
        for(Category i: categories)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Cpu findCpu(Integer id)
    {
        Cpu result = null;
        for(Cpu i: cpus)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Memory findMem(Integer id)
    {
        Memory result = null;
        for(Memory i: memories)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Memorytype findMemType(Integer id)
    {
        Memorytype result = null;
        for(Memorytype i: memorytypes)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Video findVideo(Integer id)
    {
        Video result = null;
        for(Video i: videos)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Videotype findVideoType(Integer id)
    {
        Videotype result = null;
        for(Videotype i: videotypes)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Audio findAudio(Integer id)
    {
        Audio result = null;
        for(Audio i: audios)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Audiotype findAudioType(Integer id)
    {
        Audiotype result = null;
        for(Audiotype i: audiotypes)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
    private Battery findBattery(Integer id)
    {
        Battery result = null;
        for(Battery i: batteries)
        {
            if(i.getId().equals(id))
            {
                result = i;
            }
        }
        return result;
    }
}
