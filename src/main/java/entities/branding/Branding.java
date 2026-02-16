package entities.branding;

public class Branding {
    private String name;
    private Map map;
    private String directions;
    private String logoUrl;
    private String description;
    private Contact contact;
    private Address address;

    // Constructors
    public Branding() {}

    public Branding(String name, Map map, String directions, String logoUrl, 
                    String description, Contact contact, Address address) {
        this.name = name;
        this.map = map;
        this.directions = directions;
        this.logoUrl = logoUrl;
        this.description = description;
        this.contact = contact;
        this.address = address;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }

    public String getDirections() { return directions; }
    public void setDirections(String directions) { this.directions = directions; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Branding{" +
                "name='" + name + '\'' +
                ", map=" + map +
                ", logoUrl='" + logoUrl + '\'' +
                ", description='" + description + '\'' +
                ", directions='" + directions + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                '}';
    }
}