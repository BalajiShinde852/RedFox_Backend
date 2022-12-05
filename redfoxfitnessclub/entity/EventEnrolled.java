package com.recruitapp.redfoxfitnessclub.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "event_enrolled")
@EntityListeners(AuditingEntityListener.class)
public class EventEnrolled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "UserName is Mandatory")
    private String username;

    @NotNull(message = "eventId is Mandatory")
    private int eventId;

    @Transient
    @JsonIgnore
    private String eventName;

    @CreatedDate
    private LocalDate enrolledOn;
    
    @Transient
    private Boolean paidForEvent = false;

}
