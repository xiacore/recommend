package itcast.heima.domain;

public class User_Attr {

    private int sex;
    private int age;
    private String occupation;

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
        return "User_Attr{" +
                "sex=" + sex +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
