package br.edu.ifpb.infra.jpa;

import br.edu.ifpb.AlunoService;
import br.edu.ifpb.domain.Aluno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/10/2018, 07:25:51
 */
@Stateless(name = "ejb/Repositorio")
public class Alunos {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Aluno aluno) {
        em.persist(aluno);
    }

    public List<Aluno> todosOsAlunos() {
        return em.createQuery("FROM Aluno a", Aluno.class)
                .getResultList();
    }
    @PostConstruct // método executado após a criação da instância
    public void init() {
        try {
            Context c = new InitialContext();
            NamingEnumeration<NameClassPair> list = c.list(c.getNameInNamespace());
            System.out.println("-----Informações do Context-------");
            while (list.hasMore()) {
                System.out.println(list.next());
            }
        } catch (NamingException ex) {
            Logger.getLogger(AlunoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//java:global/dac-topicos/ejb/Repositorio!br.edu.ifpb.infra.jpa.Alunos
//java:global/dac-topicos/AlunoService!br.edu.ifpb.AlunoService