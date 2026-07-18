class MyHashMap {

    private static final int[] CAPACITIES = {
        769, 1543, 3079, 6151, 12289, 24593,
        49157, 98317, 196613, 393241, 786433
    };

    private static final byte EMPTY = 0;
    private static final byte ACTIVE = 1;
    private static final byte DELETED = 2;

    private static final int LOAD_NUMERATOR = 7;
    private static final int LOAD_DENOMINATOR = 10;

    private int capacityIndex;
    private int size;

    private int[] keys;
    private int[] values;
    private byte[] states;

    public MyHashMap() {
        capacityIndex = 0;
        initializeTable(CAPACITIES[capacityIndex]);
    }

    public void put(int key, int value) {
        int index = hash(key);
        int firstDeleted = -1;

        for (int probes = 0; probes < keys.length; probes++) {
            if (states[index] == EMPTY) {
                break;
            }

            if (states[index] == ACTIVE && keys[index] == key) {
                values[index] = value;
                return;
            }

            if (states[index] == DELETED && firstDeleted == -1) {
                firstDeleted = index;
            }

            index = nextIndex(index);
        }

        if (shouldGrow()) {
            rehash();
            insertNew(key, value);
            return;
        }

        int insertionIndex =
            firstDeleted != -1 ? firstDeleted : index;

        keys[insertionIndex] = key;
        values[insertionIndex] = value;
        states[insertionIndex] = ACTIVE;
        size++;
    }

    public int get(int key) {
        int index = findIndex(key);

        return index == -1 ? -1 : values[index];
    }

    public void remove(int key) {
        int index = findIndex(key);

        if (index == -1) {
            return;
        }

        states[index] = DELETED;
        size--;
    }

    private int findIndex(int key) {
        int index = hash(key);

        for (int probes = 0; probes < keys.length; probes++) {
            if (states[index] == EMPTY) {
                return -1;
            }

            if (states[index] == ACTIVE && keys[index] == key) {
                return index;
            }

            index = nextIndex(index);
        }

        return -1;
    }

    private void rehash() {
        int[] oldKeys = keys;
        int[] oldValues = values;
        byte[] oldStates = states;

        capacityIndex++;
        initializeTable(CAPACITIES[capacityIndex]);

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldStates[i] == ACTIVE) {
                insertNew(oldKeys[i], oldValues[i]);
            }
        }
    }

    private void insertNew(int key, int value) {
        int index = hash(key);

        while (states[index] == ACTIVE) {
            index = nextIndex(index);
        }

        keys[index] = key;
        values[index] = value;
        states[index] = ACTIVE;
        size++;
    }

    private boolean shouldGrow() {
        if (capacityIndex + 1 >= CAPACITIES.length) {
            return false;
        }

        return (size + 1) * LOAD_DENOMINATOR
            > keys.length * LOAD_NUMERATOR;
    }

    private int hash(int key) {
        return key % keys.length;
    }

    private int nextIndex(int index) {
        index++;
        return index == keys.length ? 0 : index;
    }

    private void initializeTable(int capacity) {
        keys = new int[capacity];
        values = new int[capacity];
        states = new byte[capacity];
        size = 0;
    }
}
