package com.ifood.ifoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem Incompreensivel"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio","Erro Negocio");

    private String title;
    private String uri;

    ProblemType(String path,String title){
        this.uri = "https://ifoodapi.com" + path;
        this.title = title;
    }
}
