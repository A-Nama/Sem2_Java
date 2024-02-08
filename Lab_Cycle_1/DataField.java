import java.util.Date;

public class DataField {
    private static int objectCount = 0;
    private Date date;
    private int uniqueID;

    public DataField() {
        objectCount++;
        date = new Date();
        uniqueID = objectCount;
    }

    public void displayData() {
        System.out.println("Date: " + date);
        System.out.println("UniqueID: " + uniqueID);
    }

    public static void main(String[] args) {
       DataField obj1 = new DataField();
       obj1.displayData();

       DataField obj2 = new DataField();
       obj2.displayData();

    }
}