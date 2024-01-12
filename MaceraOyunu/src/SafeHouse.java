public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        if (this.getPlayer().isFood() && this.getPlayer().isFirewood() && this.getPlayer().isWater()) {
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
            System.out.println("Tebrikler oynu kazandınız ! ");
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
            System.exit(0);
        }
        System.out.println("Güvenli evdesiniz / Canınız yenilendi");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        return true;
    }
}
