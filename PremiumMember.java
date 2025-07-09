public class PremiumMember extends GymMember {
    private final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    private String removalReason; // New field

    public PremiumMember(int id, String name, String location, String phone,
                        String email, String gender, String DOB,
                        String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
        this.removalReason = ""; // Initialize
    }

    // Accessor methods
    public String getPersonalTrainer() { return personalTrainer; }
    public boolean isFullPayment() { return isFullPayment; }
    public double getPaidAmount() { return paidAmount; }
    public double getDiscountAmount() { return discountAmount; }
    public String getRemovalReason() { return removalReason; } // New getter

    @Override
    public void markAttendance() {
        attendance++;
        loyaltyPoints += 10;
    }

    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment already completed";
        }
        if ((paidAmount + amount) > premiumCharge) {
            return "Payment exceeds total charge";
        }

        paidAmount += amount;
        if (paidAmount == premiumCharge) {
            isFullPayment = true;
        }

        return "Payment accepted. Remaining: Rs." + (premiumCharge - paidAmount);
    }

    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10;
            System.out.println("Discount calculated: Rs." + discountAmount);
        } else {
            System.out.println("Complete payment for discount");
        }
    }

    // Updated method to accept and store removal reason
    public void revertPremiumMember(String removalReason) {
        super.resetMember();
        personalTrainer = "";
        isFullPayment = false;
        paidAmount = 0;
        discountAmount = 0;
        this.removalReason = removalReason;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Member Type: Premium");
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Amount Paid: Rs." + paidAmount);
        System.out.println("Payment Status: " + (isFullPayment ? "Full" : "Partial"));
        System.out.println("Amount Due: Rs." + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Available Discount: Rs." + discountAmount);
        }
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}
