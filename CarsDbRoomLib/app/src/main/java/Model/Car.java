package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "car_id")
    private long id;
    @ColumnInfo(name = "car_name")
    private String name;
    @ColumnInfo(name = "car_price")
    private String price;

    @Ignore
    public Car() {
    }

    public Car(long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Ignore
    public Car(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
