/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pokemon;
import entities.Trainer;
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
    
    public boolean insertPokemon(Pokemon p){
        if(!existsPokemon(p)){
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existsPokemon(Pokemon p){
        EntityManager em = emf.createEntityManager();
        Pokemon finded = em.find(Pokemon.class, p.getName());
        em.close();
        return finded != null;
    }
    
    public List<Trainer> selectAllTrainers(){
        return emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
    }
    
    public boolean insertTrainer(Trainer t){
        if(!existsTrainer(t)){
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existsTrainer(Trainer t){
        EntityManager em = emf.createEntityManager();
        Trainer finded = em.find(Trainer.class, t.getName());
        em.close();
        return finded != null;
    }
}
