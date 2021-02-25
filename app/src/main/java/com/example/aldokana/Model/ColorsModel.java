package com.example.aldokana.Model;

public class ColorsModel {
    int id;
    String product_serial_number;
    String name;
    String color_code;
    boolean selected;

    public ColorsModel(int id, String product_serial_number, String name, String color_code, boolean selected) {
        this.id = id;
        this.product_serial_number = product_serial_number;
        this.name = name;
        this.color_code = color_code;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_serial_number() {
        return product_serial_number;
    }

    public void setProduct_serial_number(String product_serial_number) {
        this.product_serial_number = product_serial_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
