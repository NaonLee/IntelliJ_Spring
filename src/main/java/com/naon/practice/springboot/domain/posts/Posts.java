package com.naon.practice.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter                     //getter 생성
@NoArgsConstructor          //기본 생성자 자동 추가
@Entity                     //테이블 링크 클래스 알림

public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //enable auto-increment (게시글 번호)
    private Long id;

    @Column(length = 500, nullable = false)                 //게시글 제목, 빈칸x
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)    //게시글 내용, 타입 Text, 빈칸 x
    private String content;

    private String author;                                  //게시글 작성자

    @Builder        //빌더 패턴 클래스 생성 (생성자 상단에 있으므로 생성자에 포함된 필드만 포함)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
