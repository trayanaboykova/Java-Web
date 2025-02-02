package app.subscription.model;

import app.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionPeriod period;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    @Column(nullable = false)
    private BigDecimal price;

    private boolean renewalAllowed;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime completedOn;

}
