class MyHashMap {

    private static final int CAPACITY = 769;
    private final Node[] table;

    public MyHashMap() {
        table = new Node[CAPACITY];
    }

    public void put(int key, int value) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        table[index] = new Node(key, value, table[index]);
    }

    public int get(int key) {
        Node current = table[hash(key)];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }

            current = current.next;
        }

        return -1;
    }

    public void remove(int key) {
        int index = hash(key);
        Node current = table[index];
        Node previous = null;

        while (current != null) {
            if (current.key == key) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return;
            }

            previous = current;
            current = current.next;
        }
    }

    private int hash(int key) {
        return key % CAPACITY;
    }

    private static final class Node {
        private final int key;
        private int value;
        private Node next;

        private Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
