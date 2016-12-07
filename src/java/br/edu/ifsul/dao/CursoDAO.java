
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;


public class CursoDAO<T> extends DAOGenerico<Curso>implements Serializable {

    public CursoDAO(){
        super();
        super.setClassePersistente(Curso.class);
        super.setOrdem("id");// ordem padrão
    }

}
