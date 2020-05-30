package br.com.gl.controledeacesso.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Morador extends Pessoa {

    private Integer apartamento;
    private String bloco;

}
