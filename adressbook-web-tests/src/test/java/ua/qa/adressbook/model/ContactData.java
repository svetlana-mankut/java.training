package ua.qa.adressbook.model;

public class ContactData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String adress;
    private final String homephone;
    private final String mobile;
    private final String email;
    private final String group;

    public ContactData
            (String firstname, String lastname, String adress, String homephone, String mobile, String email, String group) {
        this.id = Integer.MAX_VALUE;

        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.homephone = homephone;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactData
            (int id, String firstname, String lastname, String adress, String homephone, String mobile, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.homephone = homephone;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }


    public int getId() { return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAdress() {
        return adress;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() { return group; }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public void setId(int id){
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }


}
