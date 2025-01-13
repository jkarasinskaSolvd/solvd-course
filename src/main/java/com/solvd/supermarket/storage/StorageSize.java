package com.solvd.supermarket.storage;

public enum StorageSize {
    SMALL(3),
    MEDIUM(6),
    LARGE(9);

    private final Integer capacity;

    StorageSize(Integer capacity) {
        this.capacity = capacity;

    }

    public Integer getCapacity() {
        return capacity;
    }

    public Double electricityRate(){
        return switch (this){
            case SMALL -> 1.0;
            case MEDIUM -> 4.0;
            case LARGE -> 9.0;
        };
    }

    static {
        System.out.println("Initializing Storage Size");
    }
}
