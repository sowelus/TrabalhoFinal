
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Professor;
import java.io.Serializable;


public class ProfessorDAO<T> extends DAOGenerico<Professor> implements Serializable {
    
    public ProfessorDAO(){
        super();
        super.setClassePersistente(Professor.class);
    }

}
