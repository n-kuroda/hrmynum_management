package cresco.co.jp.mynumber.dao;

import java.util.List;

import cresco.co.jp.mynumber.model.Person;

public interface PersonDAO {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
}
