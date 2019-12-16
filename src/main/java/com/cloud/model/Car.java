package com.cloud.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * Car
 */
@Entity
@Table(name="Car")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Car   {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id = null;

  @JsonProperty("name")
  @Column(name = "name", nullable = false)
  private String name = null;

  @JsonProperty("model")
  @Column(name = "model", nullable = false)
  private String model = null;

  @JsonProperty("price")
  @Column(name = "price", nullable = false)
  private BigDecimal price = null;

  @JsonProperty("options")
  @Valid
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,  mappedBy = "car")
  @JsonManagedReference
  private List<Option> options = null;

}

