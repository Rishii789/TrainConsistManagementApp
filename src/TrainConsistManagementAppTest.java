import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementAppTest {

    public static void main(String[] args) {
        testUC1Initialization();
        testUC2Operations();
        testUC3HashSetUniqueness();
        testUC4LinkedListOperations();
        testUC5LinkedHashSetOrderAndUniqueness();
        testUC6HashMapMapping();
        testUC7SortingComparator();
        testUC8FilteringStreams();
        testUC9GroupingStreams();
        System.out.println("All tests passed");
    }

    static void testUC1Initialization() {
        List<String> trainConsist = new ArrayList<>();
        if (trainConsist.size() != 0) throw new RuntimeException();
    }

    static void testUC2Operations() {
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        passengerBogies.remove("AC Chair");

        if (passengerBogies.size() != 2) throw new RuntimeException();
        if (!passengerBogies.contains("Sleeper")) throw new RuntimeException();
        if (passengerBogies.contains("AC Chair")) throw new RuntimeException();
    }

    static void testUC3HashSetUniqueness() {
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG104");
        bogieIds.add("BG103");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        bogieIds.add("BG101");

        if (bogieIds.size() != 4) throw new RuntimeException();
    }

    static void testUC4LinkedListOperations() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Engine");
        list.add("Sleeper");
        list.add("AC");
        list.add("Cargo");
        list.add("Guard");

        list.add(2, "Pantry Car");
        list.removeFirst();
        list.removeLast();

        List<String> expected = Arrays.asList("Sleeper", "Pantry Car", "AC", "Cargo");
        if (!list.equals(expected)) throw new RuntimeException();
    }

    static void testUC5LinkedHashSetOrderAndUniqueness() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Engine");
        set.add("Sleeper");
        set.add("Cargo");
        set.add("Guard");
        set.add("Sleeper");

        List<String> expected = Arrays.asList("Engine", "Sleeper", "Cargo", "Guard");
        if (!new ArrayList<>(set).equals(expected)) throw new RuntimeException();
    }

    static void testUC6HashMapMapping() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Sleeper", 72);
        map.put("AC Chair", 56);
        map.put("First Class", 24);
        map.put("General", 90);

        if (!map.get("Sleeper").equals(72)) throw new RuntimeException();
        if (!map.get("AC Chair").equals(56)) throw new RuntimeException();
        if (!map.get("First Class").equals(24)) throw new RuntimeException();
        if (!map.get("General").equals(90)) throw new RuntimeException();
    }

    static void testUC7SortingComparator() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 56));
        list.add(new Bogie("First Class", 24));
        list.add(new Bogie("General", 90));

        list.sort(Comparator.comparingInt(b -> b.capacity));

        if (list.get(0).capacity != 24) throw new RuntimeException();
        if (list.get(1).capacity != 56) throw new RuntimeException();
        if (list.get(2).capacity != 72) throw new RuntimeException();
        if (list.get(3).capacity != 90) throw new RuntimeException();
    }

    static void testUC8FilteringStreams() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 56));
        list.add(new Bogie("First Class", 24));
        list.add(new Bogie("General", 90));

        List<Bogie> filtered = list.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        if (filtered.size() != 2) throw new RuntimeException();
    }

    static void testUC9GroupingStreams() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 56));
        list.add(new Bogie("First Class", 24));
        list.add(new Bogie("Sleeper", 70));
        list.add(new Bogie("AC Chair", 60));

        Map<String, List<Bogie>> grouped = list.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        if (grouped.size() != 3) throw new RuntimeException();
        if (grouped.get("Sleeper").size() != 2) throw new RuntimeException();
        if (grouped.get("AC Chair").size() != 2) throw new RuntimeException();
        if (grouped.get("First Class").size() != 1) throw new RuntimeException();
    }
}