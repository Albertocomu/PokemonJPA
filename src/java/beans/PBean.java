/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pokemon;
import entities.Trainer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author DAM
 */
@Stateless
public class PBean {
    
    @PersistenceUnit
    EntityManagerFactory emf;
    
    public List<Pokemon> selectAllPokemons(){
        return emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
    }
    
    public List<Pokemon> selectAllPokemonsOfTrainer(String name){
        Trainer t = obtainTrainer(name);
        return emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", t).getResultList();
    }
    
    public List<Pokemon> selectAllPokemonsInOrder(){
        return emf.createEntityManager().createNamedQuery("Pokemon.findByLevelOrLife").getResultList();
    }
    
    public boolean insertPokemon(Pokemon p){
        if(!existsPokemon(p)){
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }else{return false;}
    }
    
    public Pokemon obtainPokemon(String name){
        return (Pokemon) emf.createEntityManager().createNamedQuery("Pokemon.findByName").setParameter("name", name).getSingleResult();
    }
    
    public boolean existsPokemon(Pokemon p){
        EntityManager em = emf.createEntityManager();
        Pokemon finded = em.find(Pokemon.class, p.getName());
        em.close();
        return finded != null;
    }
    
    public boolean deletePokemon(String name){
        Pokemon p = obtainPokemon(name);
        EntityManager em = emf.createEntityManager();
        Pokemon chosed = em.find(Pokemon.class, p.getName());
        if(existsPokemon(chosed)){
            em.remove(chosed); em.close(); return true;
        }else{return false;}
    }
    
    public List<Trainer> selectAllTrainers(){
        return emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    }
    
    public List<Trainer> selectAllTrainersInOrder(){
        return emf.createEntityManager().createNamedQuery("Trainer.findByLevelPoints").getResultList();
    }
    
    public boolean insertTrainer(Trainer t){
        if(!existsTrainer(t)){
            EntityManager em = emf.createEntityManager();
            em.persist(t); em.close(); return true;
        }else{return false;}
    }
    
    public Trainer obtainTrainer(String name){
        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", name).getSingleResult();
    }
    
    public boolean existsTrainer(Trainer t){
        EntityManager em = emf.createEntityManager();
        Trainer finded = em.find(Trainer.class, t.getName());
        em.close();
        return finded != null;
    }
    
    public boolean existsTrainerName(String trainer){
        EntityManager em = emf.createEntityManager();
        Trainer finded = em.find(Trainer.class, trainer);
        em.close();
        return finded != null;
    }
    
    public List<Trainer> selectTrainersWithPokemons(){
        List<Trainer> trainers = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> validTrainers = new ArrayList<>();
        for(Trainer t : trainers){
            if(t.getPokemonCollection().size()<6){
                validTrainers.add(t);
            }
        }
        return validTrainers;
    }
    
    public List<Trainer> selectTrainersWithPotions(){
        List<Trainer> trainers = emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
        List<Trainer> validTrainers = new ArrayList<>();
        for(Trainer t : trainers){
            if(t.getPokemonCollection().size()>0 && t.getPotions()>0){
                validTrainers.add(t);
            }
        }
        return validTrainers;
    }
}
