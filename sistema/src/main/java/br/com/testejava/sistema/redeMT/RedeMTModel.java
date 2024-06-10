package br.com.testejava.sistema.redeMT;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.testejava.sistema.subestacao.SubstationModel;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;

@Data
@Entity(name = "TB_REDE_MT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_rede_mt")
public class RedeMTModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_rede_mt", length = 11)
  private Integer id_rede_mt;

  @Column(unique = true, length = 5, nullable = false)
  private String codigo;

  @Column(length = 100)
  private String nome;

  @Column(precision = 5, scale = 2)
  private BigDecimal tensao_nominal;

  @ManyToOne
  @JoinColumn(name = "id_subestacao", nullable = false)
  private SubstationModel subestacao;
}
