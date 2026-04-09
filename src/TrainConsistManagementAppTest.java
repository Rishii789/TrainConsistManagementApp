import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementAppTest {

    public static void main(String[] args) {
        testUC1();
        testUC2();
        testUC3();
        testUC4();
        testUC5();
        testUC6();
        testUC7();
        testUC8();
        testUC9();
        testUC10();
        System.out.println("All tests passed");
    }

    static void testUC1() {
        List<String> trainConsist = new ArrayList<>();
        if (!trainConsist.isEmpty()) throw new RuntimeException();
    }

    static void testUC2() {
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        passengerBogies.remove("AC Chair");

        if (passengerBogies.size() != 2) throw new RuntimeException();
        if (!passengerBogies.contains("Sleeper")) throw new RuntimeException();
        if (passengerBogies.contains("AC Chair")) throw new RuntimeException();
    }

    static void testUC3() {
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG104");
        bogieIds.add("BG103");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        bogieIds.add("BG101");

        if (bogieIds.size() != 4) throw new RuntimeException();
    }

    static void testUC4() {
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

    static void testUC5() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Engine");
        set.add("Sleeper");
        set.add("Cargo");
        set.add("Guard");
        set.add("Sleeper");

        List<String> expected = Arrays.asList("Engine", "Sleeper", "Cargo", "Guard");
        if (!new ArrayList<>(set).equals(expected)) throw new RuntimeException();
    }

    static void testUC6() {
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

    static void testUC7() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 56));
        list.add(new Bogie("First Class", 24));
        list.add(new Bogie("General", 90));

        list.sort(Comparator.comparingInt(b -> b.capacity));

        int[] expected = {24, 56, 72, 90};
        for (int i = 0; i < expected.length; i++) {
            if (list.get(i).capacity != expected[i]) throw new RuntimeException();
        }
    }

    static void testUC8() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("Sleeper", 72));
        list.add(new Bogie("AC Chair", 56));
        list.add(new Bogie("First Class", 24));
        list.add(new Bogie("General", 90));

        List<Bogie> filtered = list.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        if (filtered.size() != 2) throw new RuntimeException();

        Set<String> names = new HashSet<>();
        for (Bogie b : filtered) {
            names.add(b.name);
        }

        if (!names.contains("Sleeper") || !names.contains("General")) throw new RuntimeException();
    }

    static void testUC9() {
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

    static void testUC10() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        if (total != 152) throw new RuntimeException();

        List<Bogie> empty = new ArrayList<>();
        int totalEmpty = empty.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        if (totalEmpty != 0) throw new RuntimeException();
    }
}