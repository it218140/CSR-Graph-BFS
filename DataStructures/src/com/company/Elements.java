package com.company;

public class Elements implements Comparable<Elements> {
    private int source;
    private int target;

    public Elements(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public int compareTo(Elements myElement) {
        if (this.getSource() > myElement.getSource()) {
            return 1;
        } else if (this.getSource() == myElement.getSource()) {
            return 0;
        } else {
            return -1;
        }
    }
}