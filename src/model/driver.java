package model;

public class driver {
    private String License_number;
    private String name;
    private String sex;
    private String birth_year;
    private String dirving_type;


    public void setLicense_number(String License_number){
        this.License_number = License_number;
    }

    public String getLicense_number(){
        return License_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getDirving_type() {
        return dirving_type;
    }

    public void setDirving_type(String dirving_type) {
        this.dirving_type = dirving_type;
    }
}
