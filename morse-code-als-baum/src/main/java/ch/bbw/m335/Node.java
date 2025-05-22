package ch.bbw.m335;

public class Node {
    char value;
    Node dot; // Left child
    Node dash; // Right child

    public Node(char value) {
        this.value = value;
        this.dot = null;
        this.dash = null;
    }
}
