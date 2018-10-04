package br.edu.ifpb;

import br.edu.ifpb.aop.Auditoria;
import br.edu.ifpb.domain.Aluno;
import br.edu.ifpb.infra.jpa.Alunos;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.sql.DataSource;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/10/2018, 07:28:22
 */
@EJB(name = "ejb/Repositorio", beanInterface = Alunos.class)
@Stateless
@Interceptors({Auditoria.class})
public class AlunoService {

    @Inject
    private Alunos repositorio;
    
    public void salvar(Aluno aluno) {
        // validação
        if("".equals(aluno.getNome().trim())){
            System.out.println("Nome inválido!");
        }
        
       this.repositorio.salvar(aluno);
       // pós-processamento...
       // System.out.println("Foi salvo o aluno: "+aluno);
    }
    

    public List<Aluno> todosOsAlunos() {
        return this.repositorio.todosOsAlunos();
    }
    
    @Schedule(hour = "*", minute = "*/1", second = "00")
    public void enviarEmailPorIntervalo() {
        System.out.println("enviando o email no intervalo... " + LocalDateTime.now());
    }
    @Schedule(hour = "8", minute = "57", second = "11")
    public void enviarEmailPorHorario() {
        System.out.println("enviando o email em um horário pré-definido... " + LocalDateTime.now());
    }
    
    @Resource
    private TimerService timerService;
    public void agendarEnvioDoRelatorioPara(Aluno aluno) {
        ScheduleExpression schedule = new ScheduleExpression()
                .dayOfMonth(1)
                .hour(12);
        // schedule.hour("*").minute("*").second("13,34,57");        
        // timerService.getAllTimers().forEach(t->t.cancel());
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(aluno);
        timerService.createCalendarTimer(schedule,timerConfig);

    }
    @Timeout
    public void enviarRelatorio(Timer timer) {
        // timer.getInfo() 
        System.out.println("enviando o relatorio em... " + LocalDateTime.now());
    }
    
    @AroundInvoke
    public Object interceptador(InvocationContext context) throws Exception {
        String name = context.getMethod().getName();
        context.getContextData().put("result", 1);
        System.out.println(
                String.format(
                        "A sequência do método:%s com parametros: %s", 
                        name,
                        Arrays.toString(context.getParameters())
                )
        );
        //abrir a transação
        Object proceed = context.proceed();
        // comitar a transação
      return proceed;
      
    }
    
    @Resource 
    private SessionContext context;
    @Resource(name = "jdbc/__default") // java:comp/env/jdbc/sample
    private DataSource dataSource;
    @PostConstruct
    public void init() {
        Alunos rep = (Alunos) context.lookup("ejb/Repositorio");
        System.out.println("Datasource: " + dataSource);
        System.out.println("todosOsAlunos:" + rep.todosOsAlunos().size());  
    }
}
