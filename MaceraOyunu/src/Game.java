import java.util.Scanner;
public class Game {
    private Scanner input = new Scanner(System.in);
    public void start() {
        System.out.println("Macera oynuna hoşgeldiniz!");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " macera adasına hoşgeldin!");
        System.out.print("Lütfen bir karakter seçiniz: ");
        player.selectChar();
        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println("Bölgeler");
            System.out.println("1- Güvenli ev");
            System.out.println("2- Eşya Dükkanı");
            System.out.println("3- Mağara --> Ödülün Yemek, karşına zombi çıkabilir");
            System.out.println("4- Orman --> Ödülün Odun, karşına vampir çıkabilir ");
            System.out.println("5- Nehir --> Ödülün  Su, karşına ayı çıkabilir");
            System.out.println("6- Maden --> Ödülün  eşya kazanma ihtimali, karşına yılan çıkabilir");
            System.out.println("0 - Çıkış yap ===> Oynu sonlandır");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!player.isFood()) {
                        location = new Cave(player);
                    } else {
                        System.out.println("----------------------------");
                        System.out.println("Bu bölgedeki ödülü daha önce kazandınız lütfen yeni bir bölge seçiniz.");
                        System.out.println("----------------------------");
                        continue;
                    }
                    break;
                case 4:
                    if (!player.isFirewood()) {
                        location = new Forest(player);
                    } else {
                        System.out.println("----------------------------");
                        System.out.println("Bu bölgedeki ödülü daha önce kazandınız lütfen yeni bir bölge seçiniz.");
                        System.out.println("----------------------------");
                        continue;
                    }
                    break;
                case 5:
                    if (!player.isWater()) {
                        location = new River(player);
                    } else {
                        System.out.println("----------------------------");
                        System.out.println("Bu bölgedeki ödülü daha önce kazandınız lütfen yeni bir bölge seçiniz.");
                        System.out.println("----------------------------");
                        continue;
                    }
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz");
            }

            if (location==null) {
                System.out.println("Oyun bitti!");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("Game Over!");
                break;
            }
        }

    }
}
