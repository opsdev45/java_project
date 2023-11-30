package org.example;

public class Person {
    private String _name;
    private String _id;
    private Date _dateOfBirth;
    private final String DEFULTNAME="someone";
    private final String DEFULTID="000000000";

    public Person(String name,String id,Date dateOfBirth){
        if (name.isEmpty())
            _name=DEFULTNAME;
        else
            _name=name;
        if (id.length() != 9)
            _id=DEFULTID;
        else
            _id=id;
        _dateOfBirth = new Date(dateOfBirth);
    }
    public Person(Person other){
        _name=other._name;
        _id=other._id;
        _dateOfBirth=other._dateOfBirth;
    }
    public  String getName(){return _name;}
    public  String getId(){return _id;}
    public  Date getDateOfBirth(){return _dateOfBirth;}
    public void setName (String name){
        if (name.isEmpty())
            _name=DEFULTNAME;
        else
            _name=name;

    }
    public void setId (String id){
        if (id.length() !=9 )
            _id=DEFULTID;
        else
            _id=id;
    }
    public void setDateOfBirth (Date d){
        _dateOfBirth = new Date(d);
    }
    public String toString(){
        return "Name: " + _name + "\nID: " + _id + "\nDate of birth: " + _dateOfBirth;
    }
    public boolean equals(Person other){
        return (this._name.equals(other._name) && this._id.equals(other._id) && _dateOfBirth.equals(other._dateOfBirth));
    }
    public int compareTo(Person other){
        if (this._dateOfBirth.after(other._dateOfBirth))
            return -1;
        else if (this._dateOfBirth.before(other._dateOfBirth))
            return 1;
        else
            return 0;
    }
}

class PersonTest {
    public static void main(String[] args) {
        Date birthDate = new Date(30, 2, 1990);
        Date birthDate1 = new Date(21, 2, 1990);
        Person person1 = new Person("John Doe", "123456789", birthDate);
        Person person2 = new Person(person1);
        person2.setName("ofir");
        person2.setId("123456789");
        person2.setDateOfBirth(birthDate1);
        System.out.println(person1.equals(person2));
        System.out.println(person1.equals(person1));
        System.out.println(person1.compareTo(person2));
        System.out.println(person2.compareTo(person1));
        System.out.println(person2.compareTo(person2));
        System.out.println("Person 1: \n" + person1.toString());
        System.out.println("\nPerson 2 (copy of Person 1): \n" + person2.toString());
    }
}
