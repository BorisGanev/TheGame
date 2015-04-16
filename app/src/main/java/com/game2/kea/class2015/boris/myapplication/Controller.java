package com.game2.kea.class2015.boris.myapplication;

/**
 * Created by oliwer on 16/04/2015.
 */

import java.util.ArrayList;
import java.util.Random;



public class Controller
{

    private static Player player;
    private static ArrayList<Item> Items = new ArrayList<Item>();
    private static ArrayList<Item> PlayerItems = new ArrayList<Item>();
    public static boolean farming = false;
   // private static BufferedImage img = null;
   // public static DefaultListModel<Object> model;
   // public static DefaultListModel<Object> Playermodel;
    private static String plyrstr = "";
    private static String plyrstraftr = "";
    private static String mobhp = "";
    private static String plyrgold = "";
    private static String plyrlvl = "";
    private static String mobname = "";
    private static String mobarmor = "";

    private static int dmgtaken(int armor, int hp, int str)
    {
        double s = str;
        double ar = armor;
        double dmg = s - (s * (ar / 100));
        return hp - (int) dmg;
    }

    public static void Farming()
    {

        String[] names = { "wolf", "tiger", "bear" };
        Random r = new Random();

        Mob monster;

        do
        {
            monster = new Mob(names[r.nextInt(3)], "monster",
                    r.nextInt(41) + 5, "melee",
                    r.nextInt(player.getLevel() + 2) + 1, 0.1,
                    player.getLevel() * 75);

            plyrgold = "Player Gold: " + Integer.toString(player.getGold());
            plyrlvl = "Player level: " + Integer.toString(player.getLevel());
            plyrstr = "Player str: " + Integer.toString(player.getStr());
            mobname = monster.getName();
            mobarmor = "Mob armor: " + monster.getArmor();
            plyrstraftr = "Str after armor reduc. : "
                    + (monster.getHp() - dmgtaken(monster.getArmor(),
                    monster.getHp(), player.getStr()));
            updateProgress();
            do
            {

                int hp = monster.getHp();
                String a = Integer.toString(hp);
                mobhp = "Mob hp: " + a;
                updateProgress();
                try
                {
                    Thread.sleep(500);
                } catch (InterruptedException e)
                {

                    e.printStackTrace();
                }

                monster.setHp(dmgtaken(monster.getArmor(), monster.getHp(),
                        player.getStr()));

                hp = monster.getHp();
                if (hp < 0)
                {
                    hp = 0;
                    monster.setHp(0);
                }
                a = Integer.toString(hp);

                mobhp = "Mob hp: " + a;
                updateProgress();

            } while (monster.getHp() > 0);

            player.setGold(player.getGold() + monster.getGold_drop());
            player.setExperience(player.getExperience() + monster.getExp_drop());
            plyrgold = "Player Gold: " + Integer.toString(player.getGold());
            plyrlvl = "Player level: " + Integer.toString(player.getLevel());
            updateProgress();
        } while (farming);

    }

 /* public static ImageIcon getimage()
    {
        return player.getImg();
    }*/

    private static void updateProgress()
    {
       /* SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                // Here, we can safely update the GUI
                // because we'll be called from the
                // event dispatch thread
                MainActivity.Label_Playerstr.setText(plyrstr);
                MainActivity.lblMob.setText("Mob name : " + mobname);
                MainActivity.lblMobArmor.setText(mobarmor);
                MainActivity.lblPlayerStrAfter.setText(plyrstraftr);

                img = null;
                try
                {
                    switch (mobname)
                    {
                        case "wolf":
                            img = ImageIO.read(new File("Images//wolf.png"));
                            break;
                        case "tiger":
                            img = ImageIO.read(new File("Images//tiger.png"));
                            break;
                        case "bear":
                            img = ImageIO.read(new File("Images//bear.png"));
                            break;
                    }

                } catch (IOException e)
                {
                }
                ImageIcon icon = new ImageIcon(img);
                MainActivity.Label_mobpic.setIcon(icon);
                MainActivity.progressBar.setMinimum(0);
                MainActivity.progressBar.setMaximum(player
                        .getNext_lvl_exp_req());
                MainActivity.progressBar.setValue(player.getExperience());

                MainActivity.progressBar.setString(player.getExperience() + "/"
                        + player.getNext_lvl_exp_req());

                MainActivity.lblMobHp.setText(mobhp);

                MainActivity.lblPlayerGold.setText(plyrgold);
                MainActivity.lblPlayerExp.setText("Player Experience: ");
                MainActivity.lblPlayerLevel.setText(plyrlvl);

                setStatValues();

            }
        });*/
    }

    public static void createPlayer(String name,String race,String classs,String origin)//ImageIcon icon)
    {
        player = new Player(name, race, origin, classs);//,icon);
    }

    private static Random rand = new Random();

    public static void updateAllItemList()
    {
      /*  model = new DefaultListModel<Object>();
        for (int i = 0; i < Items.size(); i++)
        {
            model.addElement(Items.get(i).getName() + " "+ Items.get(i).getBuy_price());
        }
       */
    }

    public static void updatePlayerItemList()
    {
       /* Playermodel = new DefaultListModel<Object>();
        for (int i = 0; i < player.getMyItems().size(); i++)
        {
            Playermodel.addElement(player.getMyItems().get(i).getName() + " "
                    + player.getMyItems().get(i).getBuy_price());
        }
*/
    }

    public static void Addhardcodedvalues()
    {
        /*Items = new ArrayList<Item>();

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
        }*/
    }

  /*  public static ImageIcon itempic(int i)
    {
        return new ImageIcon(Items.get(i).getImg());
    }*/

    public static String Descript(int i)
    {
        return new String("armor: " + Items.get(i).getArmor() + "\n" + "Str: "
                + Items.get(i).getStr() + "\n" + "sell price: "
                + Items.get(i).getSell_price() + "\n" + "description: "
                + Items.get(i).getDescription());
    }

    private static void AddNewItem(Item item)
    {
        Items.add(item);
        updateAllItemList();
    }

    public static void AddItemtoPlayer(int index)
    {
        PlayerItems.add(Items.get(index));
        RefreshPlayerItems();
    }

    private static void RefreshPlayerItems()
    {
        player.setMyItems(PlayerItems);
        PlayerItems = player.getMyItems();
        updatePlayerItemList();
    }

    public static void generateItem(String name, String classs,	String description, int str, int hp, int armor, int price)//,	BufferedImage img)
    {
        AddNewItem(new Item(name, classs, description, str, hp, armor, price));///, img));
    }

    public static void DropItem(int i)
    {
        PlayerItems.remove(i);
        RefreshPlayerItems();
    }

    public static Boolean isEquipped(int i)
    {
        return PlayerItems.get(i).getIsEquipped();
    }

    public static Item getItem(int index)
    {
        return Items.get(index);
    }

    public static void Equipe_Unequipe(int i)
    {
        if(PlayerItems.get(i).getIsEquipped())
        {
            player.Unequip_item(i);
            PlayerItems.get(i).setIsEquipped(false);

        }
        else
        {
            player.Equip_item(i);
            PlayerItems.get(i).setIsEquipped(true);
        }
        RefreshPlayerItems();
    }


    public static void setStatValues()
    {
     /*   SwingUtilities.invokeLater(new Runnable() {
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
            */
    }


    public static void statChange(String stat,int i,String text)
    {
        switch(stat)
        {
            case"Hp": player.setHealth(player.getHealth() + i);break;
            case"Str": player.setStr(player.getStr() + i); break;
            case"Armor": player.setArmor(player.getArmor() + i);break;
        }

        switch(text)
        {
            case "plus": player.setUpgrade_points(player.getUpgrade_points()-1);break;
            case "minus": player.setUpgrade_points(player.getUpgrade_points()+1);break;

        }


    }

}
