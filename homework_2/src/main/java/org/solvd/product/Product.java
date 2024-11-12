package org.solvd.product;


public class Product {
    private String name;
    private Integer  barcode;
    private String description;
    private ProductType type;
    private StorageMethod storageMethod;
    private Category category;

    public Product() {
    }

    public Product(String name, Integer barcode, String description, ProductType type, StorageMethod storageMethod, Category category) {
        this.name = name;
        this.barcode = barcode;
        this.description = description;
        this.type = type;
        this.storageMethod = storageMethod;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public StorageMethod getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(StorageMethod storageMethod) {
        this.storageMethod = storageMethod;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", barcode=" + barcode +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", storageMethod=" + storageMethod +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if(hashCode() != o.hashCode()) return false;
        Product product = (Product) o;
        if(this.name != product.name) return false;
        if(this.barcode != product.barcode) return false;
        if(this.description != product.description) return false;
        if(!this.type.equals(product.type))  return false;
        if(this.storageMethod != product.storageMethod) return false;
        if(this.category != product.category) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + barcode + description.hashCode() + type.hashCode() + storageMethod.hashCode()
                + category.hashCode();
    }
}
