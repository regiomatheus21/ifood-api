package com.ifood.ifoodapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal taxaFrete;
    private BigDecimal subtotal;
    private BigDecimal valorTotal;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @CreationTimestamp
    private LocalDateTime dataCriacao;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;
    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;
    @Embedded
    private Endereco endereco;
    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id",nullable = false)
    private Usuario cliente;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items = new ArrayList<>();
}
