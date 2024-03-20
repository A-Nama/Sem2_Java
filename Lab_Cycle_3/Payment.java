
interface PaymentMethod {
    void pay(double amount);
    void cancel();
}

class CreditCard implements PaymentMethod {
    private String CardNo;
    private String ExpDate;
    private String cvv;

    public CreditCard(String CardNo, String ExpDate, String cvv) {
        this.CardNo = CardNo;
        this.ExpDate = ExpDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + "Rs  via Credit Card: " + CardNo);
    }

    @Override
    public void cancel() {
        System.out.println("Payment via Credit Card cancelled.");
    }
}

class PayPal implements PaymentMethod {
    private String email;
    private String password;

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + "Rs via PayPal: " + email);
    }

    @Override
    public void cancel() {
        System.out.println("Payment via PayPal cancelled.");
    }
}

public class Payment {
    public static void main(String[] args) {

        PaymentMethod creditCard = new CreditCard("1234 5678 9012 3456", "12/24", "123");
        creditCard.pay(1000.0);
        creditCard.cancel();

        System.out.println();

        PaymentMethod payPal = new PayPal("example@example.com", "password");
        payPal.pay(1500.0);
        payPal.cancel();
    }
}

