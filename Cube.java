import java.util.ArrayList;

public class Cube {

    private final String unicode = String.valueOf('\u25A0');
    private final int cubeWidth = 9;
    private final int number;
    private final String[] oldMatrix;

    private final ArrayList<String> matrix = new ArrayList<>();

    public Cube(int number, String... matrix) {
        this.number = number;
        this.oldMatrix = matrix;

        makeCube();
    }

    public void makeCube() {
        blackLine();
        addFirstAndLast();

        for (String line : oldMatrix) {
            String line1 = line.replace("0", " ")
                    .replace("1", unicode);

            addFirstAndLastWithText(line1);
            addFirstAndLast();
        }

        blackLine();
    }

    public String getSpecificLine(int index) {
        return matrix.get(index);
    }

    private void addFirstAndLast() {
        String string = unicode + " ".repeat(cubeWidth - 2) + unicode;

        matrix.add(string);
    }

    private void addFirstAndLastWithText(String text) {
        String string = unicode + " " + text + " " + unicode;

        matrix.add(string);
    }

    private void blackLine() {
        matrix.add(unicode.repeat(cubeWidth));
    }

    public ArrayList<String> getMatrix() {
        return matrix;
    }

    public int getNumber() {
        return number;
    }
}
