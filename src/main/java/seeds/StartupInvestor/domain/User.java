package seeds.StartupInvestor.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    @Column(name = "personal_type", length = 255, nullable = false)
    private String personalType;

    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Column(name = "is_on_alarm", nullable = false)
    private Boolean isOnAlarm;

    @Lob
    @Column(name = "profile_img_data", nullable = true)
    private byte[] profileImgData;

    public User(UserType userType, String personalType, String username, String password,
        String email, Long phoneNumber, Boolean isOnAlarm, byte[] profileImgData) {
        this.userType = userType;
        this.personalType = personalType;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isOnAlarm = isOnAlarm;
        this.profileImgData = profileImgData;
    }
}
