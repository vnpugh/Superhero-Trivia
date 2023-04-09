
public class SuperHero {
    //three private instance variables for the SuperHero class
    private String superHeroName;
    private String realName;
    private String placeOfBirth;

    public SuperHero() {
    }

    //default constructor
    // getter
    public String getSuperHeroName() {
        return superHeroName;
    }

    // setter
    public void setSuperHeroName(String superHeroName) {
        this.superHeroName = superHeroName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    //this class overrides the toString() method of the Object class
    //returns the superHeroName of the object as a string
    //comes from the Object class
    @Override
    public String toString() {
        return superHeroName;
    }


}
