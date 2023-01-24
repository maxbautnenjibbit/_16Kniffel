import java.util.*;

public class Kniffel {

    public static void main(String[] args) {
        new Kniffel().startGame();
    }

    private final ArrayList<Cube> cubes = new ArrayList<>();

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        cubes.add(new Cube(1, "00000", "00100", "00000"));
        cubes.add(new Cube(2, "00001", "00000", "10000"));
        cubes.add(new Cube(3, "00001", "00100", "10000"));
        cubes.add(new Cube(4, "10001", "00000", "10001"));
        cubes.add(new Cube(5, "10001", "00100", "10001"));
        cubes.add(new Cube(6, "10001", "10001", "10001"));

        Random random = new Random();

        int[] frequency = new int[5];
        int[] randoms = new int[5];

        for (int i = 0; i < randoms.length; i++) {
            randoms[i] = random.nextInt(5) + 1;
        }

        System.out.println();
        System.out.println("Erster Wurf:");
        List<Cube> cubes1 = Arrays.stream(randoms)
                .mapToObj(value -> cubes.get(value - 1))
                .toList();
        printCubes(cubes1);
        System.out.println("Mit welchen Würfeln soll nochmal gewürfelt werden?");
        System.out.println("(z.B. 1 0 0 1 0 für Würfel 1 und 4)");
        System.out.println();

        List<Cube> newCubes = getNewCubes(cubes1, scanner.nextLine());

        System.out.println();
        System.out.println("Zweiter Wurf:");
        printCubes(newCubes);
        System.out.println("Mit welchen Würfeln soll nochmal gewürfelt werden?");
        System.out.println("(z.B. 1 0 0 1 0 für Würfel 1 und 4)");
        System.out.println();

        List<Cube> newCubes1 = getNewCubes(newCubes, scanner.nextLine());

        System.out.println("Dritter Wurf:");
        printCubes(newCubes1);
        System.out.println();

        for (Cube cube : newCubes1) {
            frequency[cube.getNumber() - 1] += 1;
        }

        System.out.println("Häufigkeiten:");
        for (int i = 0; i < frequency.length; i++) {
            System.out.println((i + 1) + ": " + frequency[i] + "mal");
        }

        scanner.close();
    }

    private List<Cube> getNewCubes(List<Cube> old, String input) {
        Random random = new Random();

        ArrayList<Cube> newCubes = new ArrayList<>(old);

        String[] inputs = input.split(" ");

        for (int i = 0; i < inputs.length; i++) {
            int j = Integer.parseInt(inputs[i]);

            if (j == 1)
                newCubes.set(i, cubes.get(random.nextInt(5)));
        }

        return newCubes;
    }

    private void printCubes(List<Cube> cubes) {
        for (int i = 0; i < cubes.get(0).getMatrix().size(); i++) {
            for (Cube cube : cubes) {
                String specificLine = cube.getSpecificLine(i);

                System.out.print(specificLine + "\t");
            }

            System.out.println();
        }
    }

}
