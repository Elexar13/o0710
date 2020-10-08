package entities;

import java.util.Objects;

public class Vendor {
    private int id;
    private String name;
    private int countryId;

    public Vendor(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public Vendor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return id == vendor.id &&
                countryId == vendor.countryId &&
                Objects.equals(name, vendor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryId);
    }
}
