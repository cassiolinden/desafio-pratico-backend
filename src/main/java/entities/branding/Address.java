package entities.branding;

public class Address {
    private String line1;
    private String line2;
    private String postTown;
    private String county;
    private String postCode;

    // Constructors
    public Address() {}

    public Address(String line1, String line2, String postTown, String county, String postCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.postTown = postTown;
        this.county = county;
        this.postCode = postCode;
    }

    // Getters and Setters
    public String getLine1() { return line1; }
    public void setLine1(String line1) { this.line1 = line1; }

    public String getLine2() { return line2; }
    public void setLine2(String line2) { this.line2 = line2; }

    public String getPostTown() { return postTown; }
    public void setPostTown(String postTown) { this.postTown = postTown; }

    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }

    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", postTown='" + postTown + '\'' +
                ", county='" + county + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}