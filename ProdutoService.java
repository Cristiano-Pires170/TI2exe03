package service;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class ProdutoService {

    private ProdutoDAO dao = new ProdutoDAO();

    public Object add(Request req, Response res) {
        String nome = req.queryParams("nome");
        double preco = Double.parseDouble(req.queryParams("preco"));

        dao.inserir(new Produto(nome, preco));

        return "<h1>Produto cadastrado!</h1><a href='/'>Voltar</a>";
    }

    public Object get(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Produto p = dao.get(id);

        return "<h1>Produto: " + p.getNome() + "</h1>";
    }

    public Object getAll(Request req, Response res) {
        String html = "<h1>Lista de Produtos</h1>";

        for (Produto p : dao.getAll()) {
            html += "<p>" + p.getId() + " - " + p.getNome() + " - " + p.getPreco() + "</p>";
        }

        html += "<a href='/'>Voltar</a>";
        return html;
    }

    public Object update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        String nome = req.queryParams("nome");
        double preco = Double.parseDouble(req.queryParams("preco"));

        dao.atualizar(new Produto(id, nome, preco));

        return "Atualizado!";
    }

    public Object remove(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        dao.deletar(id);

        return "Removido!";
    }
}
