package com.game2.kea.class2015.boris.myapplication;

/**
 * Created by oliwer on 16/04/2015.
 */

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Random;



public class Controller {

    private static Player player;
    private static ArrayList<Item> Items = new ArrayList<Item>();
    private static ArrayList<Item> PlayerItems = new ArrayList<Item>();
    public static boolean farming = false;
    private static Drawable img = null;
    // public static DefaultListModel<Object> model;
    // public static DefaultListModel<Object> Playermodel;
    private String plyrstr = "";
    private String plyrstraftr = "";
    private String mobhp = "";
    private String plyrgold = "";
    private String plyrlvl = "";
    private String mobname = "";
    private String mobarmor = "";
    private String mobmaxhp = "";

    protected MainActivity context;

    // Constructor
    public Controller(MainActivity context) {
        this.context = context;
    }

    // Damage after reduction calculator
    private static int dmgtaken(int armor, int hp, int str) {
        double s = str;
        double ar = armor;
        double dmg = s - (s * (ar / 100));
        return hp - (int) dmg;
    }

    //Farming
    //TODO: HP for player and to take dmg from mobs

    public void Farming() {

        String[] names = {"wolf", "tiger", "bear"};
        Random r = new Random();

        Mob monster;


        do {
            monster = new Mob(names[r.nextInt(3)], "monster",
                    r.nextInt(41) + 5, "melee",
                    r.nextInt(player.getLevel() + 2) + 1, 0.1,
                    player.getLevel() * 75);

            plyrgold = Integer.toString(player.getGold());
            plyrlvl = Integer.toString(player.getLevel());
            plyrstr = Integer.toString(player.getStr());
            mobname = monster.getName();
            mobarmor = Integer.toString(monster.getArmor());
            plyrstraftr = Integer.toString((monster.getHp() - dmgtaken(monster.getArmor(), monster.getHp(), player.getStr())));
            mobmaxhp = Integer.toString(monster.getHp());
            mobhp = mobmaxhp;
            update();

            do {

                int hp = monster.getHp();
                String a = Integer.toString(hp);
                mobhp = a;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                monster.setHp(dmgtaken(monster.getArmor(), monster.getHp(),
                        player.getStr()));

                hp = monster.getHp();
                if (hp < 0) {
                    hp = 0;
                    monster.setHp(0);
                }
                a = Integer.toString(hp);

                mobhp = a;

                update();

            } while (monster.getHp() > 0);

            player.setGold(player.getGold() + monster.getGold_drop());
            player.setExperience(player.getExperience() + monster.getExp_drop());
            plyrgold = Integer.toString(player.getGold());
            plyrlvl = Integer.toString(player.getLevel());

            update();

        } while (farming);

    }

    //Updates GUI-elements on the Farming-View
    public void update()
    {
        context.Kappa(plyrstr, mobname, mobarmor, plyrstraftr, player.getImg(), player.getNext_lvl_exp_req(), player.getExperience(), plyrgold, plyrlvl,mobmaxhp,mobhp);
    }

    //Create new Player
    public static void createPlayer(String name, String race, String classs, String origin, Drawable icon) {
        player = new Player(name, race, origin, classs, icon);
    }

    //Add new item to the List (Shop is using this list)
    private static void AddNewItem(Item item) {
        Items.add(item);
        //updateAllItemList();
    }

    // Add Item to the player (Shop - buy button)
    public static void AddItemtoPlayer(int index) {
        PlayerItems.add(Items.get(index));
        RefreshPlayerItems();
    }

    //Create new Item
    public static void generateItem(String name, String classs, String description, int str, int hp, int armor, int price)//,	BufferedImage img)
    {
        AddNewItem(new Item(name, classs, description, str, hp, armor, price));///, img));
    }

    //Drop Item from the Player's inventory
    public static void DropItem(int i) {
        PlayerItems.remove(i);
        RefreshPlayerItems();
    }

    //Checks if the Item is equipped by the player
    public static Boolean isEquipped(int i) {
        return PlayerItems.get(i).getIsEquipped();
    }

    //Get Item from the list
    public static Item getItem(int index) {
        return Items.get(index);
    }

    //Equipe / Unequipe item
    public static void Equipe_Unequipe(int i) {
        if (PlayerItems.get(i).getIsEquipped()) {
            player.Unequip_item(i);
            PlayerItems.get(i).setIsEquipped(false);

        } else {
            player.Equip_item(i);
            PlayerItems.get(i).setIsEquipped(true);
        }
        RefreshPlayerItems();
    }

    //Return a description for the Item
    public static String Descript(int i) {
        return new String("armor: " + Items.get(i).getArmor() + "\n" + "Str: "
                + Items.get(i).getStr() + "\n" + "sell price: "
                + Items.get(i).getSell_price() + "\n" + "description: "
                + Items.get(i).getDescription());
    }

    //Refreshes playerItems list
    private static void RefreshPlayerItems() {
        player.setMyItems(PlayerItems);
        PlayerItems = player.getMyItems();
       // updatePlayerItemList();
    }


    private static Random rand = new Random();


    /*
    Not yet? needed code

    public static void updateAllItemList() {
        model = new DefaultListModel<Object>();
        for (int i = 0; i < Items.size(); i++)
        {
            model.addElement(Items.get(i).getName() + " "+ Items.get(i).getBuy_price());
        }

    }

    public static void updatePlayerItemList() {
       /* Playermodel = new DefaultListModel<Object>();
        for (int i = 0; i < player.getMyItems().size(); i++)
        {
            Playermodel.addElement(player.getMyItems().get(i).getName() + " "
                    + player.getMyItems().get(i).getBuy_price());
        }

    }

    public static void Addhardcodedvalues() {
        Items = new ArrayList<Item>();

        BufferedImage imm;
        try
        {
            imm = ImageIO.read(new File("Images//sword_1.png"));
            generateItem("Sword of smthing", "Warrior",
                    "Your first sword", 5, 0, 1, rand.nextInt(550), imm);

            imm = ImageIO.read(new File("Images//armor_1.png"));
            generateItem("Cape of smthing", "Warrior",
                    "Your first cape", 0, 20, 5, rand.nextInt(550), imm);

            imm = ImageIO.read(new File("Images//boots_1.png"));
            generateItem("Boots of smthing", "Warrior",
                    "Your first Boots", 0, 0, 3, rand.nextInt(550), imm);

            imm = ImageIO.read(new File("Images//helmet_1.png"));
            generateItem("Helmet of smthing", "Warrior",
                    "Your first helmet", 0, 0, 2, rand.nextInt(550), imm);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

   /* public static Drawable itempic(int i)
    {
        return new Drawable(Items.get(i).getImg());
    }*/



/*
    public static void setStatValues() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                String upp = Integer.toString(player.getUpgrade_points());
                MainActivity.lblUpValue.setText(upp);
                String name = player.getName();
                MainActivity.lblNameValue.setText(name);
                MainActivity.lblStrValue.setText(Integer.toString(player.getStr()));
                MainActivity.lblArmorValue.setText(Integer.toString(player.getArmor()));
                MainActivity.lblHpValue.setText(Integer.toString(player.getHealth()));

                if(player.getUpgrade_points() == 0)
                {
                    MainActivity.btnArmorMinus.setEnabled(false);
                    MainActivity.btnHpMinus.setEnabled(false);
                    MainActivity.btnStrMinus.setEnabled(false);
                    MainActivity.btnArmorPlus.setEnabled(false);
                    MainActivity.btnHpPlus.setEnabled(false);
                    MainActivity.btnStrPlus.setEnabled(false);
                }
                else
                {
                    //	MainActivity.btnArmorMinus.setEnabled(true);
                    //	MainActivity.btnHpMinus.setEnabled(true);
                    //	MainActivity.btnStrMinus.setEnabled(true);
                    MainActivity.btnArmorPlus.setEnabled(true);
                    MainActivity.btnHpPlus.setEnabled(true);
                    MainActivity.btnStrPlus.setEnabled(true);
                }
            }});

    }

    public static void statChange(String stat, int i, String text) {
        switch (stat) {
            case "Hp":
                player.setHealth(player.getHealth() + i);
                break;
            case "Str":
                player.setStr(player.getStr() + i);
                break;
            case "Armor":
                player.setArmor(player.getArmor() + i);
                break;
        }

        switch (text) {
            case "plus":
                player.setUpgrade_points(player.getUpgrade_points() - 1);
                break;
            case "minus":
                player.setUpgrade_points(player.getUpgrade_points() + 1);
                break;

        }


    }*/


}
