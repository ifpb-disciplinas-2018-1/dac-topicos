package br.edu.ifpb.web.jsf;

import br.edu.ifpb.AlunoService;
import br.edu.ifpb.domain.Aluno;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/10/2018, 07:39:18
 */
@Named
@RequestScoped
public class ControladorDeAlunos {

    @Inject
    private AlunoService service;

    private Aluno aluno = new Aluno();

    public String salvar() {
        this.service.salvar(this.aluno);
        this.aluno = new Aluno();
        return null;
    }
    
    public List<Aluno> todosOsAlunos(){
        return this.service.todosOsAlunos();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
