
package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    
    private Matcher original;

    public Not(Matcher original) {
        this.original = original;
    }
    

    @Override
    public boolean matches(Player p) {
        return !original.matches(p);
    }
    
}
