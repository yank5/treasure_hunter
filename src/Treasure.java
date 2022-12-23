public class Treasure {

    private String treasure;
    private boolean god = false;
    private boolean devil = false;
    private boolean goblet = false;


    public Treasure() {
    }


    public String getTreasure() {
        return generateTreasure();
    }
    public String hasAll(){
        if(god&&goblet&&devil){
            return "\nYou win! You have found all items!";
        }
        else{
            return "";
        }
    }

    public String returnTreasure(){
        if(god&&devil&&goblet)
            return "Devil King's Dagger, Goblet of Stolen Souls, God's Holy Shield";
        else if(god&&goblet)
            return "God's Holy Shield, Goblet of Stolen Souls";
        else if(devil&&goblet)
            return "Devil King's Dagger, Goblet of Stolen Souls";
        else if(god&&devil)
            return "God's Holy Shield, Devil King's Dagger";
        else if(god)
            return "God's Holy Shield";
        else if(goblet)
            return "Goblet of Stolen Souls";
        else if(devil)
            return "Devil King's Dagger";
        else
            return "something broke";
    }

    private String generateTreasure() {
        double temp = Math.random();
        if (temp < 0.25) {
            if(!god) {
                god = true;
                return "You found God's Holy Shield!";
            } else {
                return "You have already found God's Holy Shield";
            }
        } else if (temp < 0.5) {
            if(!devil) {
                devil = true;
                return "You found Devil King's Dagger!";
            } else{
                return "You have already found Devil King's Dagger";
            }
        } else if (temp < 0.75) {
            if(!goblet) {
                goblet = true;
                return "You found the Goblet of Stolen Souls!";
            } else{
                return "You have already found the Goblet of Stolen Souls";
            }
        } else {
            return "You found Rare manure";
        }
    }
}