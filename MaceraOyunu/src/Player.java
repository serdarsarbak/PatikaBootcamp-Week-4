import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    private boolean food;
    private boolean firewood;
    private boolean water;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar () {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println();
        System.out.println("----------------------");
        for (GameChar gameChar: charList){
            System.out.println("ID : " + gameChar.getId() +" " + gameChar.getName() + " \t Hasar: " + gameChar.getDamage() + "\t Sağlık: "+gameChar.getHealth() + "\t Para: " + gameChar.getMoney());
        }

        System.out.println("-------------------");
        System.out.print ("Lütfen bir karakter giriniz: ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer((new Knight()));
                break;
            default:
                initPlayer(new Samurai());
        }

        System.out.println("Karakter : " + this.getCharName() + ", Hasar : " + this.getDamage() + ", Sağlık: " + this.getHealth() + ", Para : " + this.getMoney());
    }


    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public void printInfo() {

        System.out.println("Silah : " + this.getInventory().getWeapon().getName() +
                ", Zırh : " + this.getInventory().getArmor().getName() +
                ", Bloklama : " + this.getInventory().getArmor().getBlock() +
                ", Hasarınız : " + this.getTotalDamage() + ", Sağlık : " + this.getHealth() +
                ", Para : " + this.getMoney() + " Ödüller:" + (isFood()? " -Yemek- " : "") + (isFirewood()? " -Odun- " : "" ) + (isWater()? " -Su- ": "" ));
    }

    public Scanner getInput() {
        return input;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public int getDamage() {
        return damage ;
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getHealth() {
        return health;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public void setHealth(int health) {
        if (health<0) {
            health=0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Weapon getWeapon() {
        return this.getInventory().getWeapon();
    }
}
