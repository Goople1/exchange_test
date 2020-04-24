package com.examplesb.demosb.model.entity;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Type_Monies")
@Getter
@NoArgsConstructor
@Setter
public class TypeMoniesEntity {
  @Id
  @Column(name = "type_abbr")
  private String typeAbbr;

  @Basic
  @Column(name = "symbol")
  private String symbol;

  @Basic
  @Column(name = "description")
  private String description;

  @Basic
  @Column(name = "url_imagen")
  private String urlImagen;

  @Basic
  @Column(name = "create_date")
  private LocalDate createDate;

  @Basic
  @Column( name = "update_date")
  private LocalDate updateDate;
/*
  @OneToMany(mappedBy = "typeMoniesByFromOne")
  private Collection<ExchangeMoniesEntity> exchangeMoniesByTypeAbbr;
  @OneToMany(mappedBy = "typeMoniesByToOne")
  private Collection<ExchangeMoniesEntity> exchangeMoniesByTypeAbbr_0;
  @OneToMany(mappedBy = "typeMoniesByFromTwo")
  private Collection<ExchangeMoniesEntity> exchangeMoniesByTypeAbbr_1;
  @OneToMany(mappedBy = "typeMoniesByToTwo")
  private Collection<ExchangeMoniesEntity> exchangeMoniesByTypeAbbr_2;*/
}
