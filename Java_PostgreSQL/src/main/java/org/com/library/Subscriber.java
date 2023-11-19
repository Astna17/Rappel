package org.com.library;

public class Subscriber extends User {
    private String reference;
    public Subscriber(int idSubscriber, String userName, String password, String reference) {
        super(idSubscriber, userName, password);
        this.reference = reference;
    }

    public Subscriber() {
        
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
