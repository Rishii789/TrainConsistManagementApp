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
        testUC14();
        testUC15();
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
        if (!list.contains("Sleeper")) throw new RuntimeException();
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
        try {
            List<Bogie> list = new ArrayList<>();
            list.add(new Bogie("A", 50));
            list.add(new Bogie("B", 20));
            list.sort(Comparator.comparingInt(b -> b.capacity));

            if (list.get(0).capacity != 20) throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void testUC8() {
        try {
            List<Bogie> list = Arrays.asList(
                    new Bogie("A", 70),
                    new Bogie("B", 40)
            );

            List<Bogie> res = list.stream()
                    .filter(b -> b.capacity > 60)
                    .collect(Collectors.toList());

            if (res.size() != 1) throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void testUC9() {
        try {
            List<Bogie> list = Arrays.asList(
                    new Bogie("A", 10),
                    new Bogie("A", 20)
            );

            Map<String, List<Bogie>> g = list.stream()
                    .collect(Collectors.groupingBy(b -> b.name));

            if (g.get("A").size() != 2) throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void testUC10() {
        try {
            List<Bogie> list = Arrays.asList(
                    new Bogie("A", 10),
                    new Bogie("B", 20)
            );

            int sum = list.stream()
                    .map(b -> b.capacity)
                    .reduce(0, Integer::sum);

            if (sum != 30) throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void testUC11() {
        if (!Pattern.matches("TRN-\\d{4}", "TRN-1234")) throw new RuntimeException();
        if (Pattern.matches("TRN-\\d{4}", "TRN12")) throw new RuntimeException();

        if (!Pattern.matches("PET-[A-Z]{2}", "PET-AB")) throw new RuntimeException();
        if (Pattern.matches("PET-[A-Z]{2}", "PET-ab")) throw new RuntimeException();
    }

    static void testUC12() {
        List<GoodsBogie> goods = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Box", "Coal")
        );

        boolean safe = goods.stream()
                .allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        if (!safe) throw new RuntimeException();

        List<GoodsBogie> unsafe = Arrays.asList(
                new GoodsBogie("Cylindrical", "Coal")
        );

        boolean result = unsafe.stream()
                .allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        if (result) throw new RuntimeException();
    }

    static void testUC13() {
        try {
            List<Bogie> list = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                list.add(new Bogie("B", i));
            }

            long start1 = System.nanoTime();
            List<Bogie> loop = new ArrayList<>();
            for (Bogie b : list) {
                if (b.capacity > 60) loop.add(b);
            }
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            List<Bogie> stream = list.stream()
                    .filter(b -> b.capacity > 60)
                    .collect(Collectors.toList());
            long end2 = System.nanoTime();

            if (loop.size() != stream.size()) throw new RuntimeException();
            if ((end1 - start1) <= 0 || (end2 - start2) <= 0) throw new RuntimeException();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void testUC14() {
        try {
            Bogie b1 = new Bogie("Sleeper", 72);
            if (b1.capacity != 72) throw new RuntimeException();

            try {
                new Bogie("Invalid", -10);
                throw new RuntimeException();
            } catch (InvalidCapacityException e) {
                if (!e.getMessage().equals("Capacity must be greater than zero"))
                    throw new RuntimeException();
            }

            try {
                new Bogie("Zero", 0);
                throw new RuntimeException();
            } catch (InvalidCapacityException e) {
                if (!e.getMessage().equals("Capacity must be greater than zero"))
                    throw new RuntimeException();
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void testUC15() {

        GoodsBogie safe = new GoodsBogie("Cylindrical", "");
        safe.assignCargo("Petroleum");

        GoodsBogie unsafe = new GoodsBogie("Rectangular", "");
        unsafe.assignCargo("Petroleum");

        if (!safe.cargo.equals("Petroleum")) throw new RuntimeException();
        if (unsafe.cargo.equals("Petroleum")) throw new RuntimeException();

        GoodsBogie g = new GoodsBogie("Rectangular", "");
        g.assignCargo("Coal");

        if (!g.cargo.equals("Coal")) throw new RuntimeException();
    }
}