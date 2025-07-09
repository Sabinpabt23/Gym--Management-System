public class RegularMember extends GymMember {
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    public RegularMember(int id, String name, String location, String phone,
                        String email, String gender, String DOB, 
                        String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "Basic";
        this.price = 6500;

    }

    // Accessor methods
    public boolean isEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }

    @Override
public void markAttendance() {
    // Increment the attendance count by 1 each time this method is called
    attendance++;
    // Add 5 loyalty points to the member's account for each attendance
    loyaltyPoints += 5;
    // Check if the attendance exceeds or meets the defined attendance limit
    if (attendance >= attendanceLimit) {
        // Mark the member as eligible for a plan upgrade
        isEligibleForUpgrade = true;
    }
}

public double getPlanPrice(String plan) {
    // Return the price of the membership plan based on the input string
    switch(plan) {
        case "Basic": 
            return 6500; // Price for the basic plan
        case "Standard": 
            return 12500; // Price for the standard plan
        case "Deluxe": 
            return 18500; // Price for the deluxe plan
        default: 
            return -1; // Return -1 if the plan name does not match any case
    }
}

public String upgradePlan(String newPlan) {
    // Check if the new plan is the same as the current plan
    if (newPlan.equals(this.plan)) {
        // Inform the user that the member is already on the specified plan
        return "Already on " + plan + " plan";
    }
    
    // Check if the member is eligible for an upgrade
    if (!isEligibleForUpgrade) {
        // Return the number of additional visits needed for eligibility
        return "Not eligible for upgrade (Need " + (attendanceLimit - attendance) + " more visits)";
    }

    // Get the price of the new plan
    double newPrice = getPlanPrice(newPlan);
    // Check if the plan name is invalid (price returned as -1)
    if (newPrice == -1) {
        // Return a message indicating invalid plan selection
        return "Invalid plan selection";
    }

    // Update the member's plan to the new plan
    plan = newPlan;
    // Update the price to the price of the new plan
    price = newPrice;
    // Return a confirmation message of the plan upgrade with its price
    return "Upgraded to " + newPlan + " plan (Rs." + price + ")";
}

public void revertRegularMember(String removalReason) {
    // Reset all member details using the parent class's resetMember method
    super.resetMember();
    // Mark the member as not eligible for an upgrade
    isEligibleForUpgrade = false;
    // Revert the plan back to the basic plan
    plan = "Basic";
    // Set the price of the basic plan
    price = 6500;
    // Store the reason for the member's removal
    this.removalReason = removalReason;
}


    @Override
    public void display() {
        super.display();
        System.out.println("Member Type: Regular");
        System.out.println("Current Plan: " + plan);
        System.out.println("Monthly Fee: Rs." + price);
        System.out.println("Referral Source: " + referralSource);
        if(!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}
