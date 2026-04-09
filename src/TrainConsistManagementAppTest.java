import java.util.*;
import java.util.regex.*;
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
        testUC11();
        testUC12();
        testUC13();
        System.out.println("All tests passed");
    }

    static void testUC1() {
        List<String> list = new ArrayList<>();
        if (!list.isEmpty()) throw new RuntimeException();
    }

    static void testUC2() {
        List<String> list = new ArrayList<>();
        list.add("Sleeper");
        list.add("AC Chair");
        list.add("First Class");
        list.remove("AC Chair");

        if (list.size() != 2) throw new RuntimeException();
    }

    static void testUC3() {
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("A");
        if (set.size() != 1) throw new RuntimeException();
    }

    static void testUC4() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Engine");
        list.add("Sleeper");
        list.add(1, "Pantry");
        list.removeFirst();
        if (!list.contains("Pantry")) throw new RuntimeException();
    }

    static void testUC5() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("A");
        set.add("A");
        if (set.size() != 1) throw new RuntimeException();
    }

    static void testUC6() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        if (!map.get("A").equals(10)) throw new RuntimeException();
    }

    static void testUC7() {
        List<Bogie> list = new ArrayList<>();
        list.add(new Bogie("A", 50));
        list.add(new Bogie("B", 20));
        list.sort(Comparator.comparingInt(b -> b.capacity));
        if (list.get(0).capacity != 20) throw new RuntimeException();
    }

    static void testUC8() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 70),
                new Bogie("B", 40)
        );
        List<Bogie> res = list.stream().filter(b -> b.capacity > 60).collect(Collectors.toList());
        if (res.size() != 1) throw new RuntimeException();
    }

    static void testUC9() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 10),
                new Bogie("A", 20)
        );
        Map<String, List<Bogie>> g = list.stream().collect(Collectors.groupingBy(b -> b.name));
        if (g.get("A").size() != 2) throw new RuntimeException();
    }

    static void testUC10() {
        List<Bogie> list = Arrays.asList(
                new Bogie("A", 10),
                new Bogie("B", 20)
        );
        int sum = list.stream().map(b -> b.capacity).reduce(0, Integer::sum);
        if (sum != 30) throw new RuntimeException();
    }

    static void testUC11() {
        Pattern train = Pattern.compile("TRN-\\d{4}");
        Pattern cargo = Pattern.compile("PET-[A-Z]{2}");

        if (!train.matcher("TRN-1234").matches()) throw new RuntimeException();
        if (train.matcher("TRN12").matches()) throw new RuntimeException();

        if (!cargo.matcher("PET-AB").matches()) throw new RuntimeException();
        if (cargo.matcher("PET-ab").matches()) throw new RuntimeException();
    }

    static void testUC12() {
        List<GoodsBogie> goods = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Box", "Coal")
        );

        boolean safe = goods.stream()
                .allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        if (!safe) throw new RuntimeException();
    }

    static void testUC13() {
        List<Bogie> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Bogie("B", i));
        }

        long start1 = System.nanoTime();
        List<Bogie> loop = new ArrayList<>();
        for (Bogie b : list) {
            if (b.capacity > 60) loop.add(b);
        }
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<Bogie> stream = list.stream().filter(b -> b.capacity > 60).collect(Collectors.toList());
        long end2 = System.nanoTime();

        if (loop.size() != stream.size()) throw new RuntimeException();
        if ((end1 - start1) <= 0 || (end2 - start2) <= 0) throw new RuntimeException();
    }
}