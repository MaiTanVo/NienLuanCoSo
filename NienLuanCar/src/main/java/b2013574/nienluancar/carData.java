package b2013574.nienluancar;

import java.sql.Date;

public class carData {
    private Integer carId;
    private String brand;
    private String model;
    private Double price;
    private String status;
    private Date date;
    public carData(Integer carId, String brand, String model, Double price, String status, Date date){
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.status = status;
        this.date = date;
    }
    public Integer getCarId(){
        return carId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public Double getPrice(){
        return price;
    }
    public String getStatus(){
        return status;
    }
    public Date getDate(){
        return date;
    }
}
