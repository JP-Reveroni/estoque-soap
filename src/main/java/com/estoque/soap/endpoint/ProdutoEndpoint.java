package com.estoque.soap.endpoint;

import com.estoque.soap.exception.CodigoProdutoInvalidoException;
import com.estoque.soap.exception.ProdutoNaoEncontradoException;
import com.estoque.soap.model.ConsultarProdutoRequest;
import com.estoque.soap.model.ConsultarProdutoResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProdutoEndpoint {

    private static final String NAMESPACE = "https://estoque.local/ws/produtos";

    @PayloadRoot(namespace = NAMESPACE, localPart = "consultarProdutoRequest")
    @ResponsePayload
    public ConsultarProdutoResponse consultarProduto(@RequestPayload ConsultarProdutoRequest request) {
        if (request == null || request.getCodigo() <= 0) {
            throw new CodigoProdutoInvalidoException(
                    "Código do produto inválido. Informe um código maior que zero.");
        }

        ConsultarProdutoResponse response = new ConsultarProdutoResponse();

        switch (request.getCodigo()) {
            case 1:
                response.setNome("Teclado USB");
                response.setDescricao("Teclado USB padrão ABNT2");
                response.setMarca("Logitech");
                response.setQuantidadeEstoque(25);
                break;
            case 2:
                response.setNome("Mouse óptico");
                response.setDescricao("Mouse óptico USB de 1200 DPI");
                response.setMarca("Microsoft");
                response.setQuantidadeEstoque(40);
                break;
            default:
                throw new ProdutoNaoEncontradoException(
                        "Não existe produto cadastrado com o código " + request.getCodigo() + ".");
        }

        return response;
    }
}
