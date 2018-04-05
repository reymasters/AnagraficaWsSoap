package it.au.service;

import it.au.bean.Persona;
import it.au.dao.PersonaDao;

public class PersonaService {
	
	public Persona getPersona(int id) {
		PersonaDao dao = new PersonaDao();
		return dao.getPersona(id);
	}
	
	public void addPersona(Persona p1) {
		Persona p = p1;
		PersonaDao dao = new PersonaDao();
		dao.addPersona(p);
	}
	
	public void addPersonaCustom(String nome, String cognome, String mail) {
		Persona p = new Persona(nome,cognome,mail);
		PersonaDao dao = new PersonaDao();
		dao.addPersona(p);
	}
	
}
