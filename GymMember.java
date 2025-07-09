public abstract class GymMember {
    // Fields to store member's personal and membership information
    protected int id; 
    protected String name; 
    protected String location; 
    protected String phone; 
    protected String email; 
    protected String gender; 
    protected String DOB; 
    protected String membershipStartDate; 
    protected int attendance; 
    protected double loyaltyPoints; 
    protected boolean activeStatus; // Membership status: active (true) or inactive (false)

    // Constructor to initialize a new gym member with their details
    public GymMember(int id, String name, String location, String phone, 
                     String email, String gender, String DOB, String membershipStartDate) {
        this.id = id; 
        this.name = name; 
        this.location = location; 
        this.phone = phone; 
        this.email = email; 
        this.gender = gender; 
        this.DOB = DOB; 
        this.membershipStartDate = membershipStartDate; 
        this.attendance = 0; // Set initial attendance to 0
        this.loyaltyPoints = 0; // Set initial loyalty points to 0
        this.activeStatus = false; // Set initial membership status as inactive
    }

    // Accessor (getter) methods to retrieve member details
    public int getId() { return id; } 
    public String getName() { return name; } 
    public String getLocation() { return location; } 
    public String getPhone() { return phone; } 
    public String getEmail() { return email; } 
    public String getGender() { return gender; } 
    public String getDOB() { return DOB; } 
    public String getMembershipStartDate() { return membershipStartDate; } 
    public int getAttendance() { return attendance; } 
    public double getLoyaltyPoints() { return loyaltyPoints; } 
    public boolean isActive() { return activeStatus; } // Check if the membership is active

    // Abstract method to mark attendance - to be implemented by subclasses
    public abstract void markAttendance();

    // Activate the membership by setting activeStatus to true
    public void activateMembership() {
        this.activeStatus = true; // Mark membership as active
    }

    // Deactivate the membership by setting activeStatus to false
    public void deactivateMembership() {
        if (this.activeStatus) { // Check if membership is currently active
            this.activeStatus = false; // Mark membership as inactive
        }
    }

    // Reset all membership details such as status, attendance, and loyalty points
    public void resetMember() {
        this.activeStatus = false; // Reset membership status to inactive
        this.attendance = 0; // Reset attendance count to 0
        this.loyaltyPoints = 0; // Reset loyalty points to 0
    }
 

    // Display the member's details in a structured format
    public void display() {
        System.out.println("ID: " + id); 
        System.out.println("Name: " + name); 
        System.out.println("Location: " + location); 
        System.out.println("Phone: " + phone); 
        System.out.println("Email: " + email); 
        System.out.println("Gender: " + gender); 
        System.out.println("Date of Birth: " + DOB); 
        System.out.println("Membership Start: " + membershipStartDate); 
        System.out.println("Attendance: " + attendance); 
        System.out.println("Loyalty Points: " + loyaltyPoints); 
        System.out.println("Status: " + (activeStatus ? "Active" : "Inactive")); 
    }
}
