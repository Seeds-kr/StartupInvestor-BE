package seeds.StartupInvestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_post_reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainPostReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_post_reply_id", nullable = false)
    private Long mainPostReplyId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "main_post_id", nullable = false)
    private MainPost mainPost;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    public MainPostReply(User user, MainPost mainPost, String content) {
        this.user = user;
        this.mainPost = mainPost;
        this.content = content;
    }
}
