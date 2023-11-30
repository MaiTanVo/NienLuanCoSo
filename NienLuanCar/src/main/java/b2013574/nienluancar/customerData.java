package b2013574.nienluancar;

import java.sql.Date;

public class customerData {
    private Integer customerID;
    private Integer carId;
    private String brand;
    private String gender;
    private String name;
    private Integer phone;
    private String model;
    private Double price;
    private Date date;
    public customerData(Integer customerID, Integer carId, String brand,String gender, String name, Integer phone, String model, Double price, Date date){
        this.customerID = customerID;
        this.carId = carId;
        this.brand = brand;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.model = model;
        this.price = price;
        this.date = date;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Integer getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
