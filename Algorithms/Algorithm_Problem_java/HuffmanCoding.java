import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }
}

class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return node1.frequency - node2.frequency;
    }
}

public class HuffmanCoding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();

        Map<Character, Integer> frequencyMap = buildFrequencyMap(input);
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        Map<Character, String> codeTable = buildCodeTable(root);

        System.out.println("Frequency Table:");
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nCode Table:");
        for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static Map<Character, Integer> buildFrequencyMap(String input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new HuffmanComparator());

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue(), null, null);
            queue.add(node);
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
            queue.add(parent);
        }

        return queue.poll();
    }

    private static Map<Character, String> buildCodeTable(HuffmanNode root) {
        Map<Character, String> codeTable = new HashMap<>();
        buildCodeTableRecursive(root, "", codeTable);
        return codeTable;
    }

    private static void buildCodeTableRecursive(HuffmanNode node, String code, Map<Character, String> codeTable) {
        if (node.isLeaf()) {
            codeTable.put(node.character, code);
            return;
        }

        buildCodeTableRecursive(node.left, code + "0", codeTable);
        buildCodeTableRecursive(node.right, code + "1", codeTable);
    }
}
