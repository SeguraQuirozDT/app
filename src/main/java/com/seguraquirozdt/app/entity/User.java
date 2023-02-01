package com.seguraquirozdt.app.entity;



import java.sql.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "usuario")
@Entity(name = "usuario")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    
    @Column(name = "login", nullable = false)
    private String login;
    
    @Column(name = "password", nullable = false, updatable=false)
    private String password;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cliente", nullable = false, columnDefinition = "int default 1", insertable = false, updatable=false)
    private Float cliente;

    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "fechaalta", nullable = false, insertable = false, updatable=false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date fechaalta;

    @Column(name = "fechabaja", updatable=false)
    private Date fechabaja;

    @Column(name = "status", nullable = false)
    private char status;

    @Column(name = "intentos", nullable = false, columnDefinition = "int default 0", insertable = false, updatable=false)
    private Float intentos;
    
    @Column(name = "fecharevocado", updatable=false)
    private Date fecharevocado;

    @Column(name = "fecha_vigencia", nullable = false, insertable = false, updatable=false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date fecha_vigencia;

    @Column(name = "no_acceso", nullable = false, columnDefinition = "int default 1", insertable = false, updatable=false)
    private Integer no_acceso;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellido_paterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellido_materno;

    @Column(name = "number", nullable = false, columnDefinition = "int default 1", insertable = false, updatable=false)
    private Integer number;

    @Column(name = "fechamodificacion", nullable = false, insertable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date fechamodificacion;

}
