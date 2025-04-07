class CustomHashMap {
    private static final int SIZE = 1000;
    private Node[] buckets;
    public CustomHashMap() {
        buckets = new Node[SIZE];
    }
    private static class Node {
        int key, value;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private int getIndex(int key) {
        return Integer.hashCode(key) % SIZE;
    }
    public void put(int key, int value) {
        int index = getIndex(key);
        Node head = buckets[index];
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = head;
        buckets[index] = newNode;
    }
    public int get(int key) {
        int index = getIndex(key);
        Node current = buckets[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return -1;
    }
    public void remove(int key) {
        int index = getIndex(key);
        Node current = buckets[index];
        Node prev = null;
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(1001, 30);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        map.remove(1);
        System.out.println(map.get(1));
    }
}