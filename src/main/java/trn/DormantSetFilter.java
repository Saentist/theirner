package trn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author aillusions
 */
public class DormantSetFilter {

    private Set<String> filter = new HashSet<>();

    public DormantSetFilter(List<String> listOfLines) {
        filter.addAll(listOfLines);
    }

    public boolean has(String addr) {
        return filter.contains(addr);
    }
}
