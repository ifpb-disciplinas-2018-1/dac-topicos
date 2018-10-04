package br.edu.ifpb.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/10/2018, 08:25:20
 */
public class Auditoria {

    @AroundInvoke
    public Object interceptador(InvocationContext context) throws Exception {
        // Antes da execução do método (salvar, todosOsAlunos)
        String name = context.getMethod().getName();
        long inicio = System.currentTimeMillis();
        Object retorno = context.proceed();
        // Após a execução do método (salvar, todosOsAlunos)
        long fim = System.currentTimeMillis();
        System.out.println(
                String.format(
                        "Auditoria - O método:%s demorou: %d ms",
                        name, (fim - inicio)
                )
        );
        context.getContextData().forEach(
                (k, v) -> System.out.println(k + " -> " + v)
        );
        return retorno;
    }
}
