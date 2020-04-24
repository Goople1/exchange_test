package com.examplesb.demosb.model.entity;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Exchange_Monies")
@Getter
@NoArgsConstructor
@Setter
public class ExchangeMoniesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Basic
  @Column(name = "from_one")
  private String fromOne;
  @Basic
  @Column(name = "to_one")
  private String toOne;
  @Column(name = "rate_one")
  private String rateOne;
  @Basic
  @Column(name = "from_two")
  private String fromTwo;
  @Basic
  @Column(name = "to_two")
  private String toTwo;
  @Column(name = "rate_two")
  private String rateTwo;
  @Column(name = "create_date")
  private LocalDate createDate;
  @Column(name = "update_date")
  private LocalDate updateDate;
/*  @ManyToOne
  @JoinColumn(name = "from_one", referencedColumnName = "type_abbr")
  private TypeMoniesEntity typeMoniesByFromOne;
  @ManyToOne
  @JoinColumn(name = "to_one", referencedColumnName = "type_abbr")
  private TypeMoniesEntity typeMoniesByToOne;
  @ManyToOne
  @JoinColumn(name = "from_two", referencedColumnName = "type_abbr")
  private TypeMoniesEntity typeMoniesByFromTwo;
  @ManyToOne
  @JoinColumn(name = "to_two", referencedColumnName = "type_abbr")
  private TypeMoniesEntity typeMoniesByToTwo;*/

}
