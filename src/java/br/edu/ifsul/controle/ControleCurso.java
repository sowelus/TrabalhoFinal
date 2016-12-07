package br.edu.ifsul.controle;




import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleCurso")
@ViewScoped
public class ControleCurso implements Serializable {
    private CursoDAO<Curso> dao;
    private Curso objeto;
    private InstituicaoDAO<Instituicao> daoInstituicao;
    private Disciplina disciplina;
    private Boolean novaDisciplina;

    
    public ControleCurso(){
        dao = new CursoDAO<>();  
        daoInstituicao = new InstituicaoDAO<>();      
    }
    
    public void novaDisciplina(){
        disciplina = new Disciplina();
        novaDisciplina = true;
    }
    
    public void alterarDisciplina(int index){
        disciplina = objeto.getDisciplinas().get(index);
        novaDisciplina = false;
    }
    
    public void salvarDisciplina(){
         
        objeto.adicionarDisciplina(disciplina);      
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }   
    
    public void removerDisciplina(int index){
        objeto.removerDisciplina(index);
        Util.mensagemInformacao("Objeto removido com sucesso!");
    }
    
    public String listar(){
        return "/privado/curso/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Curso();          
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }        
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }                        
    }    
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }
    }
    
    public void remover(Integer id){
        try {
            objeto = dao.localizar(id);
            if(dao.remove(objeto)){
                Util.mensagemInformacao(dao.getMensagem());
            } else {
                Util.mensagemErro(dao.getMensagem());
            }            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }
    }    
        

    public CursoDAO getDao() {
        return dao;
    }

    public void setDao(CursoDAO dao) {
        this.dao = dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }
   

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Boolean getNovaDisciplina() {
        return novaDisciplina;
    }

    public void setNovoDisciplina(Boolean novoDisciplina) {
        this.novaDisciplina = novoDisciplina;
    }
   public InstituicaoDAO<Instituicao> getDaoPessoaJuridica() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

}
