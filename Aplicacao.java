package app;

import static spark.Spark.*;
import service.ProdutoService;

public class Aplicacao {

    private static ProdutoService service = new ProdutoService();

    public static void main(String[] args) {

        port(6789);

        // Página inicial com formulário
        get("/", (req, res) -> {
            return "<h1>Cadastro de Produto</h1>" +
                   "<form action='/produto' method='post'>" +
                   "Nome: <input name='nome'><br>" +
                   "Preço: <input name='preco'><br>" +
                   "<input type='submit'>" +
                   "</form>" +
                   "<br><a href='/produto'>Ver Produtos</a>";
        });

        post("/produto", (req, res) -> service.add(req, res));

        get("/produto", (req, res) -> service.getAll(req, res));

        get("/produto/:id", (req, res) -> service.get(req, res));

        get("/produto/update/:id", (req, res) -> service.update(req, res));

        get("/produto/delete/:id", (req, res) -> service.remove(req, res));
    }
}
