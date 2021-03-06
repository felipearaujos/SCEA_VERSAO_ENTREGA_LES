package scea.core.testes;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import scea.core.aplicacao.EmailAplicacao;
import scea.core.aplicacao.Estoque;
import scea.core.aplicacao.Resultado;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.aplicacao.relatorio.RelatorioDetalheEstoque;
import scea.core.aplicacao.relatorio.RelatorioDinamico;
import scea.core.aplicacao.relatorio.RelatorioEstoque;
import scea.core.impl.controle.Fachada;
import scea.core.impl.dao.RelatoriosDAO;
import scea.core.impl.dao.SimulacaoDAO;
import scea.core.impl.dao.TransacaoDAO;
import scea.core.impl.negocio.SimularEstoque;
import scea.core.impl.negocio.validadores.ValidarAutenticacao;
import scea.core.impl.negocio.validadores.ValidarDadosProduto;
import scea.core.impl.negocio.validadores.ValidarExistenciaFornecedor;
import scea.core.impl.negocio.validadores.ValidarExistenciaTipoDeProduto;
import scea.core.impl.negocio.validadores.ValidarLimiteEntrada;
import scea.core.impl.negocio.validadores.ValidarLimiteSaida;

import scea.core.testes.testesDAO.testaDAOAcesso;
import scea.core.testes.testesDAO.testeDAODFornecedor;
import static scea.core.testes.testesDAO.testeDAODFornecedor.testeConsultaTodosOsFornecedores;
import scea.core.testes.testesDAO.testeDAOProduto;
import scea.core.testes.testesDAO.testeValidadorLimiteEntrada;
import scea.core.testes.testesDAO.testeValidadorLimiteSaida;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Entrada;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Saida;
import scea.dominio.modelo.Simulacao;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class MainTestes {

    public static Resultado resultado;
    public static Fachada fachada;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        //testeConexao();
        //testeTransacao();
        //testeDAOProduto.testeCadastrarProduto();
        //testeSalvarFachada();
        //testeDAODFornecedor.testeConsultaTodosOsFornecedores();
        //testeAcesso();
        //testVerificaFachadaParaSimulacao();
        //testeRelatorioInicialFachada();
        //testeRelatorioInicialDAO();

        //ValidarExistenciaFornece();
        //testeValidarDadosProduto();
        //testeValidarExistenciaTipo();
        //testeDeveEnviarEmail();
        //testeconvercaodata();
        //testeRelatorioTransaPeriodoDAO();
        //
        //esteRelatorioTransacaoPeriodoFachada();
        //testeRelatorioProdPeriodoDAO();
        //testeRelatorioTransacaoProdPeriodoFachada();
       // testeRelatorioEstoqueDAO();
        //testeRelatorioDetalheInicial();
        //testeRelatorioEstoqueFachada();
        //testeNewTransacao();
        //testedia();
        //testeSalvarFachada();
        //testeConsultaTodosOsFornecedores();
        
        testeRelatorioDinamico();
        
        RelatorioDinamico r= new RelatorioDinamico()
                ;
        
        System.out.println(r.isAvgQuantidade());
        
    }//MAIN
    

    
    public static void testeRelatorioDinamico() {
        fachada = new Fachada();
        RelatorioDinamico rel = new RelatorioDinamico();
        resultado = new Resultado();

        rel.setMinQuantidade(true);
        rel.setMinQuantidade(true);
        
        rel.setAvgQuantidade(true);
        rel.setAvgValor(true);
        
        rel.setMaxQuantidade(true);
        rel.setMaxValor(true);
        
        
                
        rel.setNome("RELATORIODINAMICO");
        RelatoriosDAO ras = new RelatoriosDAO();
        
            resultado = fachada.consultar(rel);
        
        
        for (EntidadeDominio e : resultado.getEntidades()) {
            RelatorioDinamico s = (RelatorioDinamico) e;
            System.out.print( s.getMinTransacao().getQtdeDoTipo()

            );
            System.out.println();
        }
    }
    public static void testeRelatorioEstoqueFachada() {
        
        System.out.println("testeRelatorioEstoqueFachada");
        fachada = new Fachada();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        resultado = new Resultado();

        rel.setDtInicial("01/03/2015");
        rel.setDtFinal(("31/04/2015"));
        //resultado = fachada.relatorioEstoque(rel);
        rel.setNome("RELATORIODETALHEINICIAL");
        resultado = fachada.consultar(rel);
        
        
        for (EntidadeDominio e : resultado.getEntidades()) {
            RelatorioDetalheEstoque s = (RelatorioDetalheEstoque) e;
            System.out.print(
                    "Quantidade critico :  " + s.getQtdeCritico() + " "
                    + "Quantidade Disponivel:  " + s.getQtdeDiponivel() + " "
                    + "Quantidade Zerada:  " + s.getQtdeZerado() + " "
            );
        }
    }//testeRelatorioEstoqueFachada

    
    public static void testeRelatorioDetalheEstoqueFachada() {
        
        System.out.println("123123");
        fachada = new Fachada();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        resultado = new Resultado();

        
        //resultado = fachada.relatorioEstoque(rel);
        rel.setNome("RELATORIOSITUACAOESTOQUE");
        resultado = fachada.consultar(rel);
        
        
        for (EntidadeDominio e : resultado.getEntidades()) {
            RelatorioEstoque s = (RelatorioEstoque) e;
            System.out.print(
                    "Quantidade Ocupada:  " + s.getQtdeEstoque() + " "
                    + "Quantidade Disponivel :  " + s.getQtdeDiponivel() + " "
                    + "Pct ocp" + Math.floor(s.getPorcentagemOcupada()) + " "
            );
            System.out.println();
        }
    }//testeRelatorioEstoqueFachada

    
    
    public static void testeDAOTransacao() {
        Fachada f = new Fachada();
        Produto p = new Produto();

        p.setId(5);
        p.setQuantidade(666);
        p.setTipoDeProduto(new TipoDeProduto());
        p.getTipoDeProduto().setTipo("");

        Transacao ts = new Transacao();
        ts.setAcesso(new Acesso());
        ts.getAcesso().setId(1);
        ts.setTipoDeTransacao("ENTRADA");
        ts.setProduto(p);
        ts.setQtdeDoTipo(p.getQuantidade());
        TransacaoDAO d = new TransacaoDAO();
        try {
            d.salvar(ts);
        } catch (SQLException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println();

        /* if (r.getMsg() != null) {
         System.out.println(r.getMsg());
         } else {
         System.out.println(((Estoque) r.getEntidades().get(0)).getObs());
         System.out.println("ID: " + ((Estoque) r.getEntidades().get(0)).getProduto().getId());
         System.out.println("NOME: " + ((Estoque) r.getEntidades().get(0)).getProduto().getNome().toUpperCase());
         System.out.println("QUANTIDADE: " + ((Estoque) r.getEntidades().get(0)).getProduto().getQuantidade());
         System.out.println("MAX: " + ((Estoque) r.getEntidades().get(0)).getProduto().getTipoDeProduto().getQtdeMax());
         System.out.println("DESCRICAO: " + ((Estoque) r.getEntidades().get(0)).getProduto().getTipoDeProduto().getDescricao().toUpperCase());
         System.out.println("DISPONIVEL: " + ((Estoque) r.getEntidades().get(0)).getQtdeDisponivel());
         System.out.println("FUTURA: " + ((Estoque) r.getEntidades().get(0)).getQtdeFutura());
         System.out.println("TENTATIVA: " + ((Estoque) r.getEntidades().get(0)).getQtdeTentativa());
        
         System.out.println("ekse");
         }*/
    }

    
    
    
    public static void testeNewTransacao() {
        Fachada f = new Fachada();
        Produto p = new Produto();

        p.setId(3);
        p.setQuantidade(8);
        p.setTipoDeProduto(new TipoDeProduto());
        p.getTipoDeProduto().setTipo("");

        Transacao ts = new Transacao();
        ts.setAcesso(new Acesso());
        ts.getAcesso().setId(1);
        ts.setTipoDeTransacao("SAIDA");
        ts.setProduto(p);
        ts.setQtdeDoTipo(p.getQuantidade());
        //ValidarTransacao v = new ValidarTransacao();
        
        Resultado r = f.salvar(ts);

        System.out.println();

        if (r.getMsg() != null) {
            System.out.println(r.getMsg());
            
            System.out.println(r.getRetorno());
            
            
        } else {
            System.out.println("tudo ok");
          
            System.out.println(r.getRetorno());
            
        }
            
        
    }//testeTransacao

    public static void testedia() {
        Calendar c = Calendar.getInstance();
        //DateFormat df  = new SimpleDateFormat("dd/MM/yyyy");  
        System.out.println("m dia:" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("Maior dia:" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    public static void testeRelatorioEstoqueDAO() {
        RelatoriosDAO dao = new RelatoriosDAO();
        EntidadeRelatorio r = new EntidadeRelatorio();
        resultado = new Resultado();
        r.setDtInicial("01/03/2015");
        r.setDtFinal("31/12/2015");
        r.setNome("RELATORIOSITUACAOESTOQUE");
        try {
            resultado.setEntidades(dao.consultar(r));
        } catch (SQLException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (EntidadeDominio e : resultado.getEntidades()) {
            RelatorioEstoque s = (RelatorioEstoque) e;
            System.out.print(
                    "Quantidade Ocupada" + s.getQtdeEstoque() + " "
                    + "Quantidade Disponivel" + s.getQtdeDiponivel() + " "
                    + "Pct ocp" + Math.floor(s.getPorcentagemOcupada()) + " "
            );
            System.out.println();
        }
    }
    
    public static void testeRelatorioDetalheInicial() {
        RelatoriosDAO dao = new RelatoriosDAO();
        EntidadeRelatorio r = new EntidadeRelatorio();
        resultado = new Resultado();
       
        r.setNome("RELATORIODETALHEINICIAL");
        try {
            resultado.setEntidades(dao.consultar(r));
        } catch (SQLException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (EntidadeDominio e : resultado.getEntidades()) {
            RelatorioDetalheEstoque s = (RelatorioDetalheEstoque) e;
            System.out.print(
                    "Quantidade critico :  " + s.getQtdeCritico() + " "
                    + "Quantidade Disponivel:  " + s.getQtdeDiponivel() + " "
                    + "Quantidade Zerada:  " + s.getQtdeZerado() + " "
            );
            System.out.println();
        }
    }
    
    
    public static void testeRelatorioTransacaoProdPeriodoFachada() {
        fachada = new Fachada();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        resultado = new Resultado();

        rel.setDtInicial("01/03/2015");
        rel.setDtFinal(("31/04/2015"));
        rel.setNome("RELATORIOTRANSACOESPRODUTO");
        resultado = fachada.consultar(rel);
        System.out.println("testeRelatorioTransacaoProdPeriodoFachada");
        for (EntidadeDominio e : resultado.getEntidades()) {
            EntidadeRelatorio s = (EntidadeRelatorio) e;
            System.out.print(
                    "ID Produto: " + s.getTransacao().getProduto().getId() + " "
                    + "Produto: " + s.getTransacao().getProduto().getNome() + " "
                    + "Transção: " + s.getTransacao().getTipoDeTransacao() + " "
                    + "Quantidade: " + s.getTransacao().getQtdeDoTipo() + " "
                    + "Mês: " + s.getMes());
            System.out.println();
        }
    }//testeRelatorioTransacaoProdPeriodoFachada

    public static void testeRelatorioProdPeriodoDAO() {
        RelatoriosDAO dao = new RelatoriosDAO();
        EntidadeRelatorio r = new EntidadeRelatorio();
        resultado = new Resultado();
        r.setDtInicial("01/03/2015");
        r.setDtFinal("31/12/2015");
        r.setNome("RELATORIOTRANSACOESPRODUTO");

        try {
            resultado.setEntidades(dao.consultar(r));
        } catch (SQLException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (EntidadeDominio e : resultado.getEntidades()) {
            EntidadeRelatorio s = (EntidadeRelatorio) e;
            System.out.print(
                    "ID Produto: " + s.getTransacao().getProduto().getId() + " "
                    + "Produto: " + s.getTransacao().getProduto().getNome() + " "
                    + "Transção: " + s.getTransacao().getTipoDeTransacao() + " "
                    + "Quantidade: " + s.getTransacao().getQtdeDoTipo() + " "
                    + "Mês: " + s.getMes());
            System.out.println();
        }
    }
    public static void testeRelatorioTransacaoPeriodoFachada() {
        fachada = new Fachada();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        resultado = new Resultado();

        rel.setDtInicial(("01/01/2015"));
        rel.setDtFinal(("22/06/2015"));
        rel.setNome("RELATORIOTRANSACOES");
        resultado = fachada.consultar(rel);
        System.out.println("testeRelatorioTransacaoPeriodoFachada");
        for (EntidadeDominio e : resultado.getEntidades()) {
            EntidadeRelatorio s = (EntidadeRelatorio) e;
            System.out.print("Transção: " + s.getTransacao().getTipoDeTransacao() + " "
                    + "Quantidade: " + s.getTransacao().getQtdeDoTipo() + " "
                    + "Mês: " + s.getMes());

            System.out.println();
        }
    }//testeRelatorioTransacaoPeriodoFachada

    public static void testeRelatorioTransaPeriodoDAO() {
        RelatoriosDAO dao = new RelatoriosDAO();
        EntidadeRelatorio r = new EntidadeRelatorio();
        resultado = new Resultado();
        r.setDtInicial(("01/03/2015"));
        r.setDtFinal(("31/12/2015"));
        r.setNome("RELATORIOTRANSACOES");

        try {
            resultado.setEntidades(dao.consultar(r));
        } catch (SQLException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (EntidadeDominio e : resultado.getEntidades()) {
            EntidadeRelatorio s = (EntidadeRelatorio) e;
            System.out.print("Transção: " + s.getTransacao().getTipoDeTransacao() + " "
                    + "Quantidade: " + s.getTransacao().getQtdeDoTipo() + " "
                    + "Mês: " + s.getMes());

            System.out.println();
        }
    }
    public static void testeDeveEnviarEmail() {
        EmailAplicacao emailEnviado = new EmailAplicacao();
        emailEnviado.setAssunto("Teste Email");
        emailEnviado.setDestinatario("jonathan.santos15a@gmail.com");
        emailEnviado.setMensagem("Meu teste");

        Fachada f = new Fachada();
        Resultado r = f.enviarEmail(emailEnviado);
        System.out.println(r.getMsg());
    }//testeDeveEnviarEmail

    public static void testeValidarExistenciaTipo() {
        Resultado r; //= new Resultado();
        ValidarExistenciaTipoDeProduto validador = new ValidarExistenciaTipoDeProduto();
        TipoDeProduto f = new TipoDeProduto();
        f.setId(1);
        r = validador.processar(f);
        System.out.println(r.getMsg());
    }//testeValidarExistenciaTipo

    public static void testeValidarExistenciaFornecedor() {
        Resultado r;
        ValidarExistenciaFornecedor validador = new ValidarExistenciaFornecedor();
        Fornecedor f = new Fornecedor();
        f.setId(1);

        r = validador.processar(f);

        System.out.println(r.getMsg());
    }//testeValidarExistenciaTipo

    public static void testeRelatorioInicialFachada() {
        Fachada f = new Fachada();
        Resultado r = new Resultado();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        rel.setNome("RELATORIOINICIAL");
        
        r = f.consultar(rel);
        
        for (EntidadeDominio e : r.getEntidades()) {
            System.out.println(e.getId());
        }
    }//testeRelatorioInicialFachada
    
       public static void testeRelatorioInicialDAO() {
        Fachada f = new Fachada();
        Resultado r = new Resultado();
        EntidadeRelatorio rel = new EntidadeRelatorio();
        rel.setNome("RELATORIOINICIAL");
        RelatoriosDAO dao = new RelatoriosDAO();
        
        
        try {
            r.setEntidades(dao.consultar(rel));
        } catch (SQLException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        for (EntidadeDominio e : r.getEntidades()) {
            System.out.println(e.getId());
        }
    }    

    public static void testeAcesso() {
        Fachada f = new Fachada();
        Acesso ac = new Acesso();
        Resultado r = new Resultado();
        ac.setLogin("f");
        ac.setSenha("1");
        r = f.acessar(ac);
        System.out.println(r.getMsg());
    }//testeAcesso

    public static void testVerificaFachadaParaSimulacao() {
        Fachada f = new Fachada();

        Simulacao s = new Simulacao();
        SimulacaoDAO sdao = new SimulacaoDAO();
        Date d = new Date();
        s.setDtTransacaoFutura(d);
        s.setQtdeItens(3);
        s.setTipoDeTransacao("ENTRADA");
        Produto p = new Produto();;
        p.setId(1);
        s.setProduto(p);
        f.salvar(s);
        Resultado r = f.consultar(s);
        for (EntidadeDominio e : r.getEntidades()) {
            Simulacao s2 = new Simulacao();
            s2 = (Simulacao) e;
            System.out.println("ID:" + s2.getId() + " Data: " + s2.getDtTransacaoFutura() + " Quantidade: " + s2.getQtdeItens() + " Tipo de Transação: "
                    + s2.getTipoDeTransacao() + " ID PRODUTO " + s2.getProduto().getId());
        }
    }//testVerificaFachadaParaSimulacao

    public static void testeValidarDadosProduto() {
        ValidarDadosProduto validador = new ValidarDadosProduto();
        Produto p = new Produto();
        Resultado r = new Resultado();

        p.setNome("Lapis SB");
        p.getTipoDeProduto().setId(2);
        p.setQuantidade(2);
        p.setValor(0.50);
        p.getFornecedor().setId(1);
        r = validador.processar(p);
        System.out.println(r.getMsg());
    }//testeValidarDadosProduto

    public static void testeSalvarFachada() {
        Fachada f = new Fachada();
        Produto p = new Produto();
        Resultado r = new Resultado();

        p.setNome("banan");
        p.getTipoDeProduto().setId(1);
        p.setQuantidade(-1);
        p.setValor(0.50);
        p.getFornecedor().setId(1);
        r = f.salvar(p);

        System.out.println(r.getMsg());
    }//testeSalvarFachada

    public static void testeTransacao() {
        Fachada f = new Fachada();
        Produto p = new Produto();

        p.setId(1);
        p.setQuantidade(7);
        
        p.setTipoDeProduto(new TipoDeProduto());
        p.getTipoDeProduto().setTipo("");

        Saida ts = new Saida();
        ts.setAcesso(new Acesso());
        ts.getAcesso().setId(1);
        //ts.setTipoDeTransacao("ENTRADA");
        ts.setProduto(p);
        ts.setQtdeDoTipo(p.getQuantidade());
        Resultado r = f.salvar(ts);

        System.out.println();

        if (r.getMsg() != null) {
            System.out.println(r.getMsg());
        } else {
           /* System.out.println(((Estoque) r.getEntidades().get(0)).getObs());
            System.out.println("ID: " + ((Estoque) r.getEntidades().get(0)).getProduto().getId());
            System.out.println("NOME: " + ((Estoque) r.getEntidades().get(0)).getProduto().getNome().toUpperCase());
            System.out.println("QUANTIDADE: " + ((Estoque) r.getEntidades().get(0)).getProduto().getQuantidade());
            System.out.println("MAX: " + ((Estoque) r.getEntidades().get(0)).getProduto().getTipoDeProduto().getQtdeMax());
            System.out.println("DESCRICAO: " + ((Estoque) r.getEntidades().get(0)).getProduto().getTipoDeProduto().getDescricao().toUpperCase());
            System.out.println("DISPONIVEL: " + ((Estoque) r.getEntidades().get(0)).getQtdeDisponivel());
            System.out.println("FUTURA: " + ((Estoque) r.getEntidades().get(0)).getQtdeFutura());
            System.out.println("TENTATIVA: " + ((Estoque) r.getEntidades().get(0)).getQtdeTentativa());
        */
            System.out.println("ok");
                   }
    }//testeTransacao

    public static void testeConexao() {
        TesteConexao st = new TesteConexao();
        try {
            try {
                st.testeDeveRealizarConexaoComOBanco();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//testeConexao

    public static void testeSimulacao() throws SQLException {
        Simulacao s = new Simulacao();
        SimulacaoDAO sdao = new SimulacaoDAO();
        Date d = new Date();
        s.setDtTransacaoFutura(d);
        s.setQtdeItens(3);
        s.setTipoDeTransacao("ENTRADA");
        Produto p = new Produto();;
        p.setId(1);
        s.setProduto(p);

        sdao.salvar(s);
        List<EntidadeDominio> entidades = sdao.consultar(s);
        for (EntidadeDominio e : entidades) {
            Simulacao s2 = new Simulacao();
            s2 = (Simulacao) e;
            System.out.println("ID:" + s2.getId() + " Data: " + s2.getDtTransacaoFutura() + " Quantidade: " + s2.getQtdeItens() + " Tipo de Transação: "
                    + s2.getTipoDeTransacao() + " ID PRODUTO " + s2.getProduto().getId());
        }
    }//testeSimulacao

    public static void testeconvercaodata() {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();

        Date data2;
        try {
            data2 = f.parse("12/01/1995");
            System.out.println(data2);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Data formatada: " + sdf.format(data));

            System.out.println("Data convertida: " + sdf.parse("02/08/1970"));
        } catch (ParseException ex) {
            Logger.getLogger(MainTestes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//testeconvercaodata
}//MainTestes
