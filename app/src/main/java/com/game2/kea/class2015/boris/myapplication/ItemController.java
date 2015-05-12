package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by oliwer on 09/05/2015.
 */
public class ItemController {

    public ArrayList<Item> Items = new ArrayList<Item>();
    public ArrayList<Item> PlayerItems = new ArrayList<Item>();
    public boolean success = false; //for checking if the purchase was successful or not
    private static Random rand = new Random();
    // METHODES FOR THE ITEMS --------------------------------------------------------------

    //Add new item to the List (Shop is using this list)
    private  void AddNewItem(Item item)
    {
        Items.add(item);
    }

    //Create new Item
    public  void generateItem(String name, String classs, String description, int str, int hp, int armor, int price,int img,int lvl)
    {
        AddNewItem(new Item(name, classs, description, str, hp, armor, price, img,lvl));
    }

    //Equip / Unequip item
    public  Player Equip_Unequip(Player player,int selectedItemId,MainActivity context)
    {
        if(player.getLevel()>=PlayerItems.get(selectedItemId).getLevel())
        {
            if (PlayerItems.get(selectedItemId).getIsEquipped()) {
                player.Unequip_item(selectedItemId);
                PlayerItems.get(selectedItemId).setIsEquipped(false);

            } else {
                player.Equip_item(selectedItemId);
                PlayerItems.get(selectedItemId).setIsEquipped(true);
            }
            RefreshPlayerItems(player);
        }
        else
        {
            Toast.makeText(context, "You can't use this Item.", Toast.LENGTH_SHORT).show();
        }

        return player;
    }

    //Returns the description of a specific shop Item
    public  String Descript_Item(int selectedItemId)
    {
        return new String("armor: " + Items.get(selectedItemId).getArmor() + "\n" + "Str: "
                + Items.get(selectedItemId).getStr() + "\n" + "Price: "
                + Items.get(selectedItemId).getBuy_price() + "\n" + "description: "
                + Items.get(selectedItemId).getDescription()+ "\n" + "Name: "
                + Items.get(selectedItemId).getName()+ "\n" + "Lvl req:: "
                + Items.get(selectedItemId).getLevel());
    }

    //Returns the description of a specific Player Item
    public  String Descript_PlayerItem(int selectedItemId)
    {
        return new String("armor: " + PlayerItems.get(selectedItemId).getArmor() + "\n" + "Str: "
                + PlayerItems.get(selectedItemId).getStr() + "\n" + "Sell price: "
                + PlayerItems.get(selectedItemId).getSell_price() + "\n" + "description: "
                + PlayerItems.get(selectedItemId).getDescription()+ "\n" + "Equipped: "
                + PlayerItems.get(selectedItemId).getIsEquipped()+ "\n" + "Lvl req: "
                + PlayerItems.get(selectedItemId).getLevel());
    }

    //Refreshes playerItems list
    public  Player RefreshPlayerItems(Player player)
    {
        player.setMyItems(PlayerItems);
        PlayerItems = player.getMyItems();

        return player;
    }

    // Add Item to the player (Shop - buy button)
    public  Player AddItemtoPlayer(Player player,int selectedItemId)
    {
        Items.get(selectedItemId).setOwned(true);
        PlayerItems.add(Items.get(selectedItemId));
        Items.remove(selectedItemId);
        return RefreshPlayerItems(player);
    }

    //Drop Item from the Player's inventory
    public  Player DropItem(Player player, int selectedItemId)
    {
        Items.get(selectedItemId).setOwned(false);
        Items.add(PlayerItems.get(selectedItemId));
        PlayerItems.remove(selectedItemId);
        return RefreshPlayerItems(player);
    }

    //Controls the selling of an Item.
    public Player sell(Player player, int selectedItemId)
    {
        player.setGold(player.getGold() + PlayerItems.get(selectedItemId).getSell_price());
        return DropItem(player,selectedItemId);
    }

    //Controls the purchase of an Item.
    public Player buy(Player player, int selectedItemId,MainActivity context)
    {
        if (player.getGold() - Items.get(selectedItemId).getBuy_price() > 0)
        {
            player.setGold(player.getGold() - Items.get(selectedItemId).getBuy_price());
            player = AddItemtoPlayer(player,selectedItemId);
            success = true;
        }
        else
        {
            success = false;
            Toast.makeText(context,"You don't have enough gold to buy this Item.",Toast.LENGTH_SHORT).show();
        }

        return player;
    }

    //Generates a specific number of Item(s).
    public void generateXitems(int x,MainActivity context)
    {
        int[] img = {R.drawable.standard_sword_64x64,R.drawable.armor_1,R.drawable.golden_sword};

        for(int i = 0;i<x;i++)
        {
            generateItem("Cape of smthing", "Warrior",
                    "Your first cape", rand.nextInt(55), 20, 5, rand.nextInt(550), img[rand.nextInt(3)],rand.nextInt(5));
        }
        SaveXml(context);
        ReadXml(context);
    }

    public void SaveXml(MainActivity context)
    {
        ArrayList<Item> allItems = Items;

        for(int i = 0;i< PlayerItems.size();i++)
        {
            allItems.add(PlayerItems.get(i));
        }

        String filename = "Saved_Items.xml";

        FileOutputStream fos;

        try {

            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(allItems);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadXml(MainActivity context)
    {
        String filename = "Saved_Items.xml";
        FileInputStream fos;

        try {

            fos = context.openFileInput(filename);
            ObjectInputStream oos = new ObjectInputStream(fos);


            Items = (ArrayList<Item>) oos.readObject();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        setPlayerItems();

    }

    private void setPlayerItems()
    {
        PlayerItems = new ArrayList<Item>();

        for(int i = Items.size()-1; i>-1 ;i--)
        {
            if (Items.get(i).getOwned())
            {
                PlayerItems.add(Items.get(i));
                Items.remove(i);
            }
        }
    }

}
