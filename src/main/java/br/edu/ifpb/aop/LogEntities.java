package br.edu.ifpb.aop;

import java.time.LocalDateTime;
import javax.persistence.PostLoad;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/10/2018, 07:54:59
 */
public class LogEntities {

    @PostLoad // método executado após o carregamento da entidade
    public void aposCarregar(Object object) {
        String value = String.format(
                "<objeto: %s, carregado em: %s>",
                object,
                LocalDateTime.now().toString()
        );
        System.out.println(value);
    }
}
