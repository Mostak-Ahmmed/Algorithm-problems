

import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left, right;

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanTree {

    public static Node buildHuffmanTree(char[] characters, int[] frequencies) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // Step 1: Create nodes for each character and frequency
        for (int i = 0; i < characters.length; i++) {
            Node node = new Node(characters[i], frequencies[i]);
            queue.add(node);
        }

        // Step 2: Build the Huffman tree
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();

            Node parent = new Node('*', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            queue.add(parent);
        }

        // Step 3: Return the root of the Huffman tree
        return queue.poll();
    }

    public static void printHuffmanCodes(Node root, String code) {
        if (root.isLeaf()) {
            System.out.println(root.character + " : " + code);
            return;
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] frequencies = {5, 25, 7, 15, 4, 12};

        Node root = buildHuffmanTree(characters, frequencies);

        System.out.println("Huffman Codes:");
        printHuffmanCodes(root, "");
    }
}
