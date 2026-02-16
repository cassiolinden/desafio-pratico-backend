package entities.message;

public class Message {
    private int messageid;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String description;

    // Constructors
    public Message() {}

    public Message(String name, String email, String phone, String subject, String description) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.description = description;
    }

    // Getters and Setters
    public int getMessageid() { return messageid; }
    public void setMessageid(int messageid) { this.messageid = messageid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}