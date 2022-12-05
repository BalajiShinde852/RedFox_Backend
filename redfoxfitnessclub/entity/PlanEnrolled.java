package com.recruitapp.redfoxfitnessclub.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "plan_enrolled")
@EntityListeners(AuditingEntityListener.class)
public class PlanEnrolled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "UserName is Mandatory")
    private String username;

    @NotNull(message = "PlanId is Mandatory")
    private int planId;

    @CreatedDate
    private LocalDate validFrom;

    private LocalDate validTill;

    private String status = "ACTIVE";

}
