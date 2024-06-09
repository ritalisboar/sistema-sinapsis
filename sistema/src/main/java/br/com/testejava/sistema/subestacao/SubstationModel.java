package br.com.testejava.sistema.subestacao;

import java.math.BigDecimal;
import java.util.List;

import br.com.testejava.sistema.redeMT.RedeMTModel;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;

@Data
@Entity(name = "TB_SUBESTACAO")
public class SubstationModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_subestacao", nullable = false, length = 11)
  private Integer id_subestacao;

  @Column(unique = true, length = 3, nullable = false)
  private String codigo;

  @Column(length = 100)
  private String nome;

  @Column(precision = 15, scale = 13)
  private BigDecimal latitude;

  @Column(precision = 15, scale = 13)
  private BigDecimal longitude;

  @OneToMany(mappedBy = "subestacao", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RedeMTModel> redesMT;
  
}
