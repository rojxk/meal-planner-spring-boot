package com.mealplanner.dto;

import java.math.BigDecimal;

public class IngredientDTO {
    private String name;
    private BigDecimal quantity;
    private Integer measureId;

    public IngredientDTO(){

    }

    public IngredientDTO(String name, BigDecimal quantity, Integer measureId) {
        this.name = name;
        this.quantity = quantity;
        this.measureId = measureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Integer measureId) {
        this.measureId = measureId;
    }
}