package one.blanke.firezemissiles.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre_000 on 20-04-2017.
 */

public class TargetGenerator {

    private static List<Target> targetList;

    public static void initialize() {
        targetList = new ArrayList<>();
        targetList.add(new Target(0,"United States of America", "USA",
                "Donald Trump","The Land of the Free and Home of the Brave",5000));
        targetList.add(new Target(1,"People's Democratic Republic of Korea", "North Korea",
                "Kim Jong-un", "True Korea, a Powerful and Prosperous Nation",2000));
        targetList.add(new Target(2,"People's Republic of China", "China",
                "Xi Jinping", "Dare to Think, Dare to Speak, Dare to Act",4500));
        targetList.add(new Target(3,"State of Japan", "Japan", "Emperor Akihito",
                "Teehee - don't touch me senpai <3",3500));
        targetList.add(new Target(4,"Republic of Korea","South Korea","Hwang Kyo-ahn",
                "South Korea, benefit broadly the human world",2500));
    }

    public static List<Target> getTargetList() {
        return targetList;
    }

    public static Target getTarget(int id) {
        for (Target target: targetList) {
            if (target.getID() == id) return target;
        }
        return null;
    }
}
