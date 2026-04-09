import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
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
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        List<Bogie> bogiesForGrouping = new ArrayList<>();
        bogiesForGrouping.add(new Bogie("Sleeper", 72));
        bogiesForGrouping.add(new Bogie("AC Chair", 56));
        bogiesForGrouping.add(new Bogie("First Class", 24));
        bogiesForGrouping.add(new Bogie("Sleeper", 70));
        bogiesForGrouping.add(new Bogie("AC Chair", 60));

        Map<String, List<Bogie>> grouped = bogiesForGrouping.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Enter Train ID:");
        String trainId = sc.nextLine();

        System.out.println("Enter Cargo Code:");
        String cargoCode = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        boolean validTrain = trainPattern.matcher(trainId).matches();
        boolean validCargo = cargoPattern.matcher(cargoCode).matches();

        System.out.println("\nFinal Train Report:\n");

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
    }
}