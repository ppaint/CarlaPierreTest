package dao;

import model.Adherent;

public interface DaoAdherent extends DaoGeneric<Adherent, Integer> {
	
	public Adherent findByAdherent(Adherent key);
	
}