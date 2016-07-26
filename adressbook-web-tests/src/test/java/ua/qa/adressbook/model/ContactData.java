package ua.qa.adressbook.model;

public class ContactData {
    private final String ussername;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String adress;
    private final String homephone;
    private final String mobile;
    private final String year;
    private final String group ;

    public ContactData
            (String ussername, String middlename, String lastname, String nickname, String title, String company, String adress, String homephone, String mobile, String year, String group) {
        this.ussername = ussername;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.adress = adress;
        this.homephone = homephone;
        this.mobile = mobile;
        this.year = year;
        this.group = group;
    }



    public String getUssername() {
        return ussername;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
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

    public String getYear() {
        return year;
    }

    public String getGroup() { return group; }
}
