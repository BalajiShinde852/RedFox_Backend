package com.recruitapp.redfoxfitnessclub.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "eventName is Mandatory")
    private String eventName;

    @NotNull(message = "trainerName is Mandatory")
    private String trainerName;

    private int maxMembers;

    private int remainingMembers;

    private String status;

    @NotNull(message = "scheduledDateTime is Mandatory")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime scheduledDateTime;

}
