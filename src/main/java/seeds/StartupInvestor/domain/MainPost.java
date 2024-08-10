package seeds.StartupInvestor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "main_post")
public class MainPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_post_id")
    private Long mainPostId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(name = "img_data", nullable = true)
    private byte[] imgData;

    @Column(name = "view_cnt", nullable = false)
    private int viewCnt;

    @Column(name = "bookmark_cnt", nullable = false)
    private int bookmarkCnt;

    @Column(name = "like_cnt", nullable = false)
    private int likeCnt;

    public MainPost(User user, String title, String description, byte[] imgData) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.imgData = imgData;
        this.viewCnt = 0;
        this.bookmarkCnt = 0;
        this.likeCnt = 0;
    }
}