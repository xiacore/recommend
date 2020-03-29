package itcast.heima.domain;

public class New_User_Attr {

    private int sex;
    private int age;
    private String occupation;
    private int address;
    private int music_type;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getMusic_type() {
        return music_type;
    }

    public void setMusic_type(int music_type) {
        this.music_type = music_type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "New_User_Attr{" +
                "sex=" + sex +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", address=" + address +
                ", music_type=" + music_type +
                '}';
    }
}
