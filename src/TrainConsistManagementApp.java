import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

class InvalidCapacityException extends Exception {
    InvalidCapacityException(String message) {
        super(message);
    }
}

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }
}

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> trainConsist = new ArrayList<>();

        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        passengerBogies.remove("AC Chair");
        boolean hasSleeper = passengerBogies.contains("Sleeper");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG104");
        bogieIds.add("BG103");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        bogieIds.add("BG101");

        LinkedList<String> orderedConsist = new LinkedList<>();
        orderedConsist.add("Engine");
        orderedConsist.add("Sleeper");
        orderedConsist.add("AC");
        orderedConsist.add("Cargo");
        orderedConsist.add("Guard");
        orderedConsist.add(2, "Pantry Car");
        orderedConsist.removeFirst();
        orderedConsist.removeLast();

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        Map<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 56);
        bogieCapacity.put("First Class", 24);
        bogieCapacity.put("General", 90);

        List<Bogie> bogies = new ArrayList<>();
        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 56));
            bogies.add(new Bogie("First Class", 24));
            bogies.add(new Bogie("General", 90));
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Enter Train ID:");
        String trainId = sc.nextLine();

        System.out.println("Enter Cargo Code:");
        String cargoCode = sc.nextLine();

        boolean validTrain = Pattern.matches("TRN-\\d{4}", trainId);
        boolean validCargo = Pattern.matches("PET-[A-Z]{2}", cargoCode);

        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Box", "Coal"));
        goods.add(new GoodsBogie("Open", "Grain"));

        boolean isSafe = goods.stream()
                .allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        List<Bogie> largeDataset = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            try {
                largeDataset.add(new Bogie("B" + i, (i % 100) + 1));
            } catch (InvalidCapacityException e) {
            }
        }

        long startLoop = System.nanoTime();
        List<Bogie> loopFiltered = new ArrayList<>();
        for (Bogie b : largeDataset) {
            if (b.capacity > 60) {
                loopFiltered.add(b);
            }
        }
        long endLoop = System.nanoTime();

        long startStream = System.nanoTime();
        List<Bogie> streamFiltered = largeDataset.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();

        System.out.println("\nFinal Train Report\n");

        System.out.println("Initial Train Consist Size: " + trainConsist.size());
        System.out.println("Passenger Bogies: " + passengerBogies);
        System.out.println("Contains Sleeper: " + hasSleeper);
        System.out.println("Unique Bogie IDs: " + bogieIds);
        System.out.println("Ordered Consist: " + orderedConsist);
        System.out.println("Formation: " + formation);

        System.out.println("\nCapacity Map:");
        for (Map.Entry<String, Integer> e : bogieCapacity.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        System.out.println("\nSorted Bogies:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        System.out.println("\nFiltered Bogies (>60):");
        for (Bogie b : filtered) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        System.out.println("\nGrouped Bogies:");
        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            List<String> caps = entry.getValue().stream()
                    .map(b -> String.valueOf(b.capacity))
                    .collect(Collectors.toList());
            System.out.println(caps);
        }

        System.out.println("\nTotal Seating Capacity: " + totalSeats);

        System.out.println("\nTrain ID Valid: " + validTrain);
        System.out.println("Cargo Code Valid: " + validCargo);
        System.out.println("Safety Compliance: " + isSafe);

        System.out.println("\nLoop Filtering Time: " + (endLoop - startLoop));
        System.out.println("Stream Filtering Time: " + (endStream - startStream));
    }
}