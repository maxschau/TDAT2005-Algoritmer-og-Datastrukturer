import java.util.HashMap;
import java.util.Map;

public class Locations {

    Map<Integer, String> locations = new HashMap<>();
    public Locations() {

    }

    void addLocation(int node, String name) {
        locations.put(node, name);
    }

    int getNode(String name) {
        for (Map.Entry<Integer, String> entry : locations.entrySet()) {
            if (name.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }

    String getName(int node) {
        return locations.get(node);
    }


}
