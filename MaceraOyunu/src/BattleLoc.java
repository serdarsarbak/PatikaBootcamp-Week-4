import java.util.Random;

public class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public void setAward(String award) {
        this.award = award;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız :" + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç: ");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz !");
            if (this.getObstacle().getName().equals("Zombie")) {
                this.getPlayer().setFood(true);
            } else if (this.getObstacle().getName().equals("Ayı")) {
                this.getPlayer().setWater(true);
            } else if (this.getObstacle().getName().equals("Vampir")) {
                this.getPlayer().setFirewood(true);

            }

                return true;
        }
        if (this.getPlayer().getHealth()<=0){
            System.out.println("Öldünüz");
            return false;
        }

        return true;
    }

    public boolean combat (int obsNumber ) {
        for (int i=1; i<=obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());

            if (this.getObstacle().getName().equals("Yılan")) {
                this.getObstacle().setDamage(snakeDamage ());
            }

            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0) {
                System.out.print("<V>ur veya <K>aç: ");
                String selectCombat = input.nextLine().toUpperCase();

                if (selectCombat.equals("V")) {

                    if (firstStarter()==0) {
                        System.out.println("-----------------");
                        System.out.println("Siz vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth()- this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth()>0) {
                            System.out.println("-----------------");
                            System.out.println("Canavar size vurdu ! ");
                            int obstacleDamage = this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage <0 ) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else {

                        System.out.println("-----------------");
                        System.out.println("Canavar size vurdu ! ");
                        int obstacleDamage = this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage <0 ) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();

                        if (this.getPlayer().getHealth()>0) {
                            System.out.println("-----------------");
                            System.out.println("Siz vurdunuz !");
                            this.getObstacle().setHealth(this.obstacle.getHealth()- this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }

                } else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz !");

                if (this.getObstacle().getName().equals("Yılan")) {
                    snakeAward ();

                } else {
                    System.out.println(this.getObstacle().getAward() + " para kazandınız ! ");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getObstacle().getAward());
                    System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
                }

            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit () {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı: " + this.getObstacle().getHealth());

    }

    public void playerStats () {
        System.out.println("Oyuncu değerleri: ");
        System.out.println("-------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());

    }

    public void obstacleStats (int i) {
        System.out.println();
        System.out.println(i + ". " + this.getObstacle().getName() + " değerleri: ");
        System.out.println(this.getObstacle().getName() + " değerleri");
        System.out.println("--------------------");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " +this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public int firstStarter () {
        Random r = new Random();
        return r.nextInt(2);
    }

    public int snakeDamage () {
        Random rand = new Random();
        int max=6,min=3;
        return rand.nextInt(max - min + 1) + min;
    }

    public void snakeAward () {
        Random rand = new Random();
        int max=200,min=1;
        int randomNum = rand.nextInt(max - min + 1) + min;
        if (randomNum<=6) {
            System.out.println("Tüfek kazandınız!");
            Weapon selectedWeapon = Weapon.getWeaponObjByID(3);
            this.getPlayer().getInventory().setWeapon(selectedWeapon);

        } else if (randomNum<=15) {
            System.out.println("Kılıç kazandınız");
            Weapon selectedWeapon = Weapon.getWeaponObjByID(2);
            this.getPlayer().getInventory().setWeapon(selectedWeapon);

        } else if (randomNum<=30) {
            System.out.println("Tabanca kazandınız !");
            Weapon selectedWeapon = Weapon.getWeaponObjByID(1);
            this.getPlayer().getInventory().setWeapon(selectedWeapon);

        } else if (randomNum<=36) {
            System.out.println("Ağır zırh kazandınız !");
            Armor selectedArmor = Armor.getArmorObjByID(3);
            this.getPlayer().getInventory().setArmor(selectedArmor);

        } else if (randomNum<=45) {
            System.out.println("Orta zırh kazandınız !");
            Armor selectedArmor = Armor.getArmorObjByID(2);
            this.getPlayer().getInventory().setArmor(selectedArmor);

        } else if (randomNum<=60) {
            System.out.println("Hafif zırh kazandınız !");
            Armor selectedArmor = Armor.getArmorObjByID(1);
            this.getPlayer().getInventory().setArmor(selectedArmor);

        } else if (randomNum<=70) {
            System.out.println("10 para kazandınız !");
            this.getPlayer().setMoney(this.getPlayer().getMoney()+10);

        } else if (randomNum<=85) {
            System.out.println("5 para kazandınız !");
            this.getPlayer().setMoney(this.getPlayer().getMoney()+5);

        } else if (randomNum<=110) {
            System.out.println("1 para kazandınız !");
            this.getPlayer().setMoney(this.getPlayer().getMoney()+1);

        } else {
            System.out.println("Ödül kazanamadınız :/");
        }
    }
}
