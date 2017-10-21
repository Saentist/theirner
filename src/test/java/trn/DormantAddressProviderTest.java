package trn;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * @author aillusions
 */
public class DormantAddressProviderTest extends TestCase {

    public void testDupl() {
        DormantAddressProvider provider = new DormantAddressProvider();

        Set<String> duplicates = new HashSet<>();
        Set<String> uniques = new HashSet<>();

        if (provider.getDormantAddresses().size() != new HashSet<>(provider.getDormantAddresses()).size()) {

            for (String t : provider.getDormantAddresses()) {
                if (!uniques.add(t)) {
                    duplicates.add(t);
                }
            }

            System.out.println(duplicates);
        }


        assertEquals(provider.getDormantAddresses().size(), new HashSet<>(provider.getDormantAddresses()).size());
    }
}
