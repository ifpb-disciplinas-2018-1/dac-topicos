package br.edu.ifpb.domain;

import br.edu.ifpb.aop.LogEntities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/10/2018, 07:24:02
 */
@Entity
@EntityListeners(LogEntities.class) // Precisamos adicionar à entidade
public class Aluno implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;

    public Aluno() {
    }

    public Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

//    @PrePersist // método executado antes de persistir
//    public void antesDePersistir() {
//        String value = String.format(
//                "<aluno: %d, salvo em: %s>",
//                this.getId(),
//                LocalDateTime.now().toString()
//        );
//        System.out.println(value);
//    }
//
//    @PostLoad // método executado após o carregamento da entidade
//    public void aposCarregar() {
//        String value = String.format(
//                "<aluno: %d, carregado em: %s>",
//                this.getId(),
//                LocalDateTime.now().toString()
//        );
//        System.out.println(value);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", nome=" + nome + '}';
    }

}
