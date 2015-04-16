package com.game2.kea.class2015.boris.myapplication;

/**
 * Created by oliwer on 16/04/2015.
 */

import java.util.ArrayList;

/**
 * Created by oliwer on 23/03/2015.
 */
public class Player
{

    private String name;
    private String race;
    private String origin;
    private int gold;
    private String classs;
    private int next_lvl_exp_req;
    private int last_lvl_exp_req;
   // private ImageIcon img;

    //stats

    private int level;
    private int upgrade_points;
    private int experience;


    private int str;
    private int armor;
    private int health;

    private ArrayList<Item> MyItems = new ArrayList<Item>();

    public Player(String _name, String _race, String _origin, String _classs)//,ImageIcon icon)
    {
        this.name = _name;
        this.race = _race;
        this.origin = _origin;
        this.gold = 100;
        this.classs = _classs;
        this.level = 1;
        this.upgrade_points = 0;
        this.health = 100;
        this.str = 10;
        this.armor = 5;
        this.experience = 0;
        this.next_lvl_exp_req = 100;
        //this.img = icon;
        this.upgrade_points = 0;
    }

    public String getName()
    {
        return name;
    }

    public String getRace()
    {
        return race;
    }

    public String getOrigin()
    {
        return origin;
    }

    public String getClasss()
    {
        return classs;
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getUpgrade_points()
    {
        return upgrade_points;
    }

    public void setUpgrade_points(int upgrade_points)
    {
        this.upgrade_points = upgrade_points;
    }

    public int getExperience()
    {
        return experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;

        if (this.experience >= this.next_lvl_exp_req)
        {
            this.experience = this.experience - this.next_lvl_exp_req;
            this.level++;
            this.upgrade_points += 2;

            this.last_lvl_exp_req = this.next_lvl_exp_req;
            this.next_lvl_exp_req = this.last_lvl_exp_req
                    + this.last_lvl_exp_req / 2;

            this.str += 1;
        }
    }

    public int getStr()
    {
        return str;
    }

    public void setStr(int _str)
    {
        this.str = _str;
    }

    public void setArmor(int _armor)
    {
        this.armor = _armor;
    }


    public int getNext_lvl_exp_req()
    {
        return next_lvl_exp_req;
    }


  /*  public ImageIcon getImg()
    {
        return img;
    }

    public void setImg(ImageIcon img)
    {
        this.img = img;
    }*/

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getArmor()
    {
        return armor;
    }

    public ArrayList<Item> getMyItems()
    {
        return MyItems;
    }

    public void setMyItems(ArrayList<Item> myItems)
    {
        this.MyItems = myItems;
    }
    public void Equip_item(int i)
    {
        if(!this.MyItems.get(i).getIsEquipped())
        {
            this.armor = this.armor + this.MyItems.get(i).getArmor();
            this.str = this.str + this.MyItems.get(i).getStr();
            this.health = this.health + this.MyItems.get(i).getHp();
        }
    }

    public void Unequip_item(int i)
    {
        if(this.MyItems.get(i).getIsEquipped())
        {
            this.armor = this.armor - this.MyItems.get(i).getArmor();
            this.str = this.str - this.MyItems.get(i).getStr();
            this.health = this.health - this.MyItems.get(i).getHp();
        }
    }

}
