package br.com.testejava.sistema.redeMT;

import java.math.BigDecimal;

import br.com.testejava.sistema.subestacao.SubstationModel;
import lombok.Data;

@Data
public class RedeMTDto {
  private SubstationModel subestacao;
  private String codigo;
  private String nome;
  private BigDecimal tensao_nominal;
  private Integer id_rede_mt;
}
