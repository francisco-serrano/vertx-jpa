package application.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Car {

    public Car() {
    }

    public Car(String brand, String model, String version, int engineSize, int horsePower) {
//        this.carPublicId = carPublicId;
        this.carPublicId = UUID.randomUUID().toString();
        this.brand = brand;
        this.model = model;
        this.version = version;
        this.engineSize = engineSize;
        this.horsePower = horsePower;
    }

//    @Id
//    @GeneratedValue()
//    private int id;

//    @Column(nullable = false)
    @Id
    @Column(columnDefinition = "varchar(32)")
//    @Type(type = "org.hibernate.type.UUIDBinaryType")
    @GeneratedValue()
    private String carPublicId;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private int engineSize;

    @Column(nullable = false)
    private int horsePower;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getCarPublicId() {
        return carPublicId;
    }

    public void setCarPublicId(String carPublicId) {
        this.carPublicId = carPublicId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
