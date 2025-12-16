package com.IBMproject.documentIngester.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file")

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Builder
@Data

public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "type")
    private String type;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;


    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

