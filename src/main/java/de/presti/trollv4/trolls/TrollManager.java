package de.presti.trollv4.trolls;

import de.presti.trollv4.trolls.impl.*;

import java.util.ArrayList;

public class TrollManager {

    private ArrayList<Troll> trolls = new ArrayList<>();

    public TrollManager() {
        loadTrolls();
    }

    public void loadTrolls() {
        addTroll(new Explode());
        addTroll(new Fakehack());
        addTroll(new Demoscreen());
        addTroll(new Hackuser());
        addTroll(new Strike());
        addTroll(new Rocket());
        addTroll(new Mlg());
        addTroll(new Spam());
        addTroll(new Control());
    }

    public void addTroll(Troll troll) {
        if(!getTrolls().contains(troll)) {
            getTrolls().add(troll);
        }
    }

    public Troll getTrollByName(String name) {
        return getTrolls().stream().filter(troll -> troll.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Troll getTrollByDisplayName(String displayName) {
        return getTrolls().stream().filter(troll -> troll.getDisplayName().equalsIgnoreCase(displayName)).findFirst().orElse(null);
    }

    public ArrayList<Troll> getTrolls() {
        return trolls;
    }

}
