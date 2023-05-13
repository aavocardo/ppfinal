package Project.Logic;

import java.util.Vector;
import static Project.Logic.Footballer.generateID;

public class Team {
    Vector<Footballer> team = new Vector<>();
    private final int id = generateID();

    public void addFootballer(Footballer f) {
        team.add(f);
    }

    public void removeFootballer(Footballer f) {
        team.remove(f);
    }

    public boolean isPresent(Footballer f) {
        return team.contains(f);
    }
}

