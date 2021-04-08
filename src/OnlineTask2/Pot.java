package OnlineTask2;

import java.util.*;


public class Pot {
    private Flower flowers[];
    private int numberOfFlowers;
    private String shape;

    /**
     * Create a new flower pot with the designated shape and initial capacity.
     * @param shape
     * @param capacity
     */
    public Pot(String shape, int capacity) {
        this.shape = shape;
        this.flowers = new Flower[capacity];
        numberOfFlowers = 0;
    }

    /**
     * Insert a flower into this pot. If the pot is full, return false.
     * If null passed as insert, return false (cannot add null)
     * @param f - The flower to be inserted
     * @return Whether the flower was successfully inserted
     */
    public boolean insert(Flower f) {
        if (numberOfFlowers == size() || f == null) return false;
        flowers[numberOfFlowers] = f;
        numberOfFlowers++;
        return true;
    }

    /**
     * Search this pot for a flower that matches the given parameter exactly (case insensitive) in terms of colour,
     * species, age (within the nearest whole number) and height (within 0.001).
     * If the given flower is null, return whether this pot has empty space.
     * @param f The flower to check.
     * @return whether the flower was found in this pot.
     */
    public boolean contains(Flower f) {
        if (f == null) return count() < size();
        for (Flower flower: flowers) {
            if (flower == null) break;
            if (flower.equals(f)) return true;
        }

        return false;
    }

    /**
     * Search this pot for a flower that matches the given species (case insensitive).
     * Need to handle the situation where the input value is null
     * @param f - Species of the flower
     * @return whether the flower was found in this pot.
     */
    public boolean containsType(String f) {
        for (Flower flower: flowers) {
            if (flower == null) break;
            if (f == null && flower.getSpecies() == null) return true;
            if (flower.getSpecies() != null && flower.getSpecies().equalsIgnoreCase(f)) return true;
        }
        return false;
    }

    /**
     * Search this pot for a flower that matches the given colour (case insensitive).
     * Need to handle the situation where the input value is null
     * @param f - Colour of the flower
     * @return whether the flower was found in this pot.
     */
    public boolean containsColour(String f) {
        for (Flower flower: flowers) {
            if (flower == null) break;
            if (f == null && flower.getColour() == null) return true;
            if (flower.getColour() != null && flower.getColour().equalsIgnoreCase(f)) return true;
        }
        return false;
    }

    /**
     * @return This pot's maximum capacity.
     */
    public int size() {
        return flowers.length;
    }

    /**
     * @return How many flowers are currently in this pot.
     */
    public int count() {
        return numberOfFlowers;
    }

    /**
     * @return shape of the pot
     */
    public String getShape() {
        return shape;
    }

    /**
     * Search this pot for a flower that matches the given species (case insensitive).
     * Need to handle the situation where the input value is null
     * @param t - Species of the flower
     * @return Number of flowers that match.
     */
    public int countType(String t) {
        int cnt = 0;
        for (Flower flower: flowers) {
            if (flower == null) break;
            if (t == null && flower.getSpecies() == null) cnt++;
            else if (flower.getSpecies() != null && flower.getSpecies().equalsIgnoreCase(t)) cnt++;
        }
        return cnt;
    }

    /**
     * Search this pot for flowers that match the given colour (case insensitive).
     * Need to handle the situation where the input value is null
     * @param t - Colour of the flower
     * @return Number of flowers that match.
     */
    public int countColour(String t) {
        int cnt = 0;
        for (Flower flower: flowers) {
            if (flower == null) break;
            if (t == null && flower.getColour() == null) cnt++;
            else if (flower.getColour() != null && flower.getColour().equalsIgnoreCase(t)) cnt++;
        }
        return cnt;
    }

    /**
     * View the arrangement of the flower pot in terms of its colours.
     * Should not include flowers with null colour in the result
     * @return A string denoting how many of each colour type there are
     * in this pot. Ordered in alphabetical order by colour.
     * For example:
     * 5x blue
     * 1x green
     * 3x red
     * 1x violet
     * 2x yellow
     */
    public String view() {
        //Map<String, Integer> flowerMap = new HashMap<>();
        Map<String, Integer> flowerMap = new HashMap<>();
        for (Flower f: flowers) {
            if (f == null) break;
            if (f.getColour() == null) continue;
            String lcColor = f.getColour().toLowerCase();
            if (flowerMap.containsKey(lcColor)) flowerMap.put(lcColor, flowerMap.get(lcColor) + 1);
            else flowerMap.put(lcColor, 1);
        }

        List<String> colorList = new ArrayList<>(flowerMap.keySet());
        Collections.sort(colorList, (s1, s2) -> s1.compareTo(s2));

        String view = "";
        for (String color: colorList) {
            view += flowerMap.get(color) +  "x " + color + "\n";
        }

        return view;
    }

    /**
     * TreeMap Implementation
     */
//    public String view() {
//        Map<String, Integer> flowerMap = new TreeMap<>((s1, s2) -> s1.compareTo(s2));
//        for (Flower f: flowers) {
//            if (f == null) break;
//            if (f.getColour() == null) continue;
//            String lcColor = f.getColour().toLowerCase();
//            if (flowerMap.containsKey(lcColor)) flowerMap.put(lcColor, flowerMap.get(lcColor) + 1);
//            else flowerMap.put(lcColor, 1);
//        }
//
//        String view = "";
//        for (String color: flowerMap.keySet()) {
//            view += flowerMap.get(color) +  "x " + color + "\n";
//        }
//
//        return view;
//    }

    /**
     * All the flowers inside this pot grow.
     */
    public void water() {
        for (Flower f: flowers) {
            if (f == null) break;
            f.growInside(this);
        }
    }

    /**
     * Remove all dead flowers from this pot.
     * @return How many dead flowers were removed.
     */
    public int rearrange() {
        Flower[] temp = new Flower[size()];
        int tempIdx = 0, deadCnt = 0;

        for (Flower f: flowers) {
            if (f == null) break;
            if (f.isAlive()) {
                temp[tempIdx] = f;
                tempIdx++;
            } else {
                deadCnt++;
            }
        }
        flowers = temp;
        numberOfFlowers = tempIdx;

        return deadCnt;
    }

    /**
     * Calculate the average age of all the live flowers in this pot.
     * If the pot is empty, return -1.
     * @return Average age of live flowers.
     */
    public double averageAge() {
        if (numberOfFlowers == 0) return -1;
        double cnt = 0, ageSum = 0;

        for (Flower f: flowers) {
            if (f == null) break;
            if (f.isAlive()) {
                ageSum += f.getAge();
                cnt++;
            }
        }
        if (cnt == 0) return -1;
        return ageSum / cnt;
    }

    /**
     * Calculate the average height of all the live flowers in this pot.
     * If the pot is empty, return -1.
     * @return Average height of live flowers.
     */
    public double averageHeight() {
        if (numberOfFlowers == 0) return -1;
        double cnt = 0, heightSum = 0;

        for (Flower f: flowers) {
            if (f == null) break;
            if (f.isAlive()) {
                heightSum += f.getHeight();
                cnt++;
            }
        }
        return heightSum / cnt;
    }

    /**
     * Return a new pot with only the flowers that match the given species criterion (case insensitive).
     * Need to handle the situation where the input value is null
     * Need to consider object deep copy
     * @param t - species criterion
     * @return New pot with the flowers. Its shape and size are the same as the original.
     */
    public Pot filterType(String t) {
        Flower[] temp = new Flower[size()];
        int tempIdx = 0, deadCnt = 0;
        for (Flower flower: flowers) {
            if (flower == null) break;
            if (t == null && flower.getSpecies() == null) {
                temp[tempIdx] = flower.clone();
                tempIdx++;
            } else if (flower.getSpecies() != null && flower.getSpecies().equalsIgnoreCase(t)) {
                temp[tempIdx] = flower.clone();
                tempIdx++;
            }
        }
        Pot newPot = new Pot(this.shape, this.size());
        newPot.numberOfFlowers = tempIdx;
        newPot.flowers = temp;
        return newPot;
    }

    /**
     * Return a new pot with only the flowers that match the given colour criterion (case insensitive).
     * Need to handle the situation where the input value is null
     * Need to consider object deep copy
     * @param t - colour criterion
     * @return New pot with the flowers. Its shape and size are the same as the original.
     */
    public Pot filterColour(String t) {
        Flower[] temp = new Flower[size()];
        int tempIdx = 0, deadCnt = 0;
        for (Flower flower: flowers) {
            if (flower == null) break;
            if ((t == null && flower.getColour() == null) || (flower.getColour() != null && flower.getColour().equalsIgnoreCase(t))) {
                temp[tempIdx] = flower.clone();
                tempIdx++;
            }
        }
        Pot newPot = new Pot(this.shape, this.size());
        newPot.numberOfFlowers = tempIdx;
        newPot.flowers = temp;
        return newPot;
    }

    /**
     * Replace all the flowers matching f1 with f2. Based on having the same colour,
     * species, age (within the nearest whole number) and height (within 0.001).
     * If f1 is null, fill all empty spaces in the pot with f2.
     * If f2 is null, remove any instances of f1 from the pot.
     * Need to consider object deep copy
     * @param f1 - Flower to be replaced
     * @param f2 - Flower to insert
     * @return How many flowers were replaced
     */
    public int replace(Flower f1, Flower f2) {
        int replaceCnt = 0;

        if (f1 == null && f2 == null) { //if both null
            return replaceCnt;
        } else if (f1 == null) { // If f1 is null, fill all empty spaces in the pot with f2.
            for (; numberOfFlowers < size(); numberOfFlowers++) {
                flowers[numberOfFlowers] = f2.clone();
                replaceCnt++;
            }
        } else if (f2 == null) { // If f2 is null, remove any instances of f1 from the pot.
            Flower[] temp = new Flower[size()];
            int tempIdx = 0;
            for (Flower flower: flowers) {
                if (flower == null) break;
                if (!flower.equals(f1)) {
                    temp[tempIdx] = flower;
                    tempIdx++;
                } else if (flower.equals(f1)) {
                    replaceCnt++;
                }
            }
            numberOfFlowers = tempIdx;
            flowers = temp;
        } else { // normal replacement when f1 and f2 are both not null
            for (int i = 0; i < numberOfFlowers; i++) {
                if (flowers[i].equals(f1)) {
                    flowers[i] = f2.clone();
                    replaceCnt++;
                }
            }
        }
        return replaceCnt;
    }

    /**
     * Replace all the flowers matching the given species criterion with f.
     * If f is null, remove the matching flowers from the pot.
     * Need to handle the situation when the colour type is null
     * Need to consider object deep copy
     * @param type - Species criterion
     * @param f - Flower to insert
     * @return How many flowers were replaced
     */
    public int replaceType(String type, Flower f) {
        int replaceCnt = 0;
        if (f == null) { //If f is null, remove the matching flowers from the pot.
            Flower[] temp = new Flower[size()];
            int tempIdx = 0;
            for (Flower flower: flowers) {
                if (flower == null) break;
                if (type == null && flower.getSpecies() != null || type != null && flower.getSpecies() == null || !flower.getSpecies().equalsIgnoreCase(type)) {
                    temp[tempIdx] = flower;
                    tempIdx++;
                } else {
                    replaceCnt++;
                }
            }
            numberOfFlowers = tempIdx;
            flowers = temp;
        } else { // f is not null, normal replacement
            for (int i = 0; i < numberOfFlowers; i++) {
                if (type == null && flowers[i].getSpecies() == null || type != null && type.equalsIgnoreCase(flowers[i].getSpecies())) {
                    flowers[i] = f.clone();
                    replaceCnt++;
                }
            }
        }
        return replaceCnt;
    }

    /**
     * Replace all the flowers matching the given colour criterion with f.
     * If f is null, remove the matching flowers from the pot.
     * Need to handle the situation when the colour type is null
     * Need to consider object deep copy
     * @param colour - Colour criterion
     * @param f - Flower to insert
     * @return How many flowers were replaced
     */
    public int replaceColour(String colour, Flower f) {
        int replaceCnt = 0;
        if (f == null) { //If f is null, remove the matching flowers from the pot.
            Flower[] temp = new Flower[size()];
            int tempIdx = 0;
            for (Flower flower: flowers) {
                if (flower == null) break;
                if (colour == null && flower.getColour() != null || colour != null && flower.getColour() == null || !flower.getColour().equalsIgnoreCase(colour)) {
                    temp[tempIdx] = flower;
                    tempIdx++;
                } else {
                    replaceCnt++;
                }
            }
            numberOfFlowers = tempIdx;
            flowers = temp;
        } else { // f is not null, normal replacement
            for (int i = 0; i < numberOfFlowers; i++) {
                if (colour == null && flowers[i].getColour() == null || colour != null && colour.equalsIgnoreCase(flowers[i].getColour())) {
                    flowers[i] = f.clone();
                    replaceCnt++;
                }
            }
        }
        return replaceCnt;
    }

    /**
     * Combine the flowers from each pot into one containing only unique colours. Flowers from the
     * first pot (p1) are prioritised over those in p2. The resulting pot should have no spare capacity,
     * and it's shape the same as the first pot.
     * Should not include flowers with the null colour in the result
     * If either p1 or p2 are null, return the one which isn't null. If they both are, return null.
     * @param p1 - First pot to use in the combination
     * @param p2 - Second pot to use in the combination
     * @return The combined pot. It's shape is that of p1.
     */
    public static Pot combine(Pot p1, Pot p2) {
        if (p1 == null && p2 == null) return null;
        if (p1 == null) return p2;
        if (p2 == null) return p1;

        List<Flower> flowerList = new ArrayList<>();
        Set<String> colourSet = new HashSet<>();

        for (Flower flower: p1.flowers) {
            if (flower == null) break;
            if (flower.getColour() != null && !colourSet.contains(flower.getColour())) {
                colourSet.add(flower.getColour());
                flowerList.add(flower.clone());
            }
        }

        for (Flower flower: p2.flowers) {
            if (flower == null) break;
            if (flower.getColour() != null && !colourSet.contains(flower.getColour())) {
                colourSet.add(flower.getColour());
                flowerList.add(flower.clone());
            }
        }

        Pot combinedPot = new Pot(p1.shape, flowerList.size());
        combinedPot.numberOfFlowers = flowerList.size();
        combinedPot.flowers = flowerList.toArray(new Flower[flowerList.size()]);

        return combinedPot;
    }
}
