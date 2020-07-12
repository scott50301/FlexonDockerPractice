import java.math.BigDecimal;

public class Customers {
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal phone;
    private String email;

    public Customers(int id, String firstName, String lastName, BigDecimal phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public int getId(){
        return id;
    }

    public void setFirstNema(String firstName){
        this.firstName = firstName;
    }

    public String getFirstNema(){
        return firstName;
    }

    public void setLastNema(String lastName){
        this.lastName = lastName;
    }

    public String getLastNema(){
        return lastName;
    }

    public void setPhone(BigDecimal phone){
        this.phone = phone;
    }

    public BigDecimal getPhone(){
        return phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}
