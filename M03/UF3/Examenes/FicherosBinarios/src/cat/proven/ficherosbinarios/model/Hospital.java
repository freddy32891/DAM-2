/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.ficherosbinarios.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author mati
 */
public class Hospital implements Serializable{
    private String nom;
    private int numeroLlits;
    private boolean complet;

    public Hospital(String nom, int numeroLlits, boolean complet) {
        this.nom = nom;
        this.numeroLlits = numeroLlits;
        this.complet = complet;
    }

    public Hospital() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroLlits() {
        return numeroLlits;
    }

    public void setNumeroLlits(int numeroLlits) {
        this.numeroLlits = numeroLlits;
    }

    public boolean isComplet() {
        return complet;
    }

    public void setComplet(boolean complet) {
        this.complet = complet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hospital other = (Hospital) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hospital{" + "nom=" + nom + ", numeroLlits=" + numeroLlits + ", complet=" + complet + '}';
    }
    
    
}
