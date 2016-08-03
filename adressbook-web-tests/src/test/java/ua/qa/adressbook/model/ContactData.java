package ua.qa.adressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Transient
    private String adress;
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Column(name = "work")
    @Type(type = "text")
    private String workphone;
    @Transient
    private String allPhones;
    @Transient
    private String email1;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allEmails;

    @Transient
    private String group;
    @Transient
    private String allDetails;
    @Column(name = "photo")
    @Transient
    private String photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public File getPhoto() {
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }

    public ContactData withAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }


    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }


    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }


    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }


    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public int getId() {

        return id;
    }

    public String getGroup() {

        return group;
    }

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

    public String getWorkphone() {
        return workphone;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllDetails() {

        return allDetails;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


}
